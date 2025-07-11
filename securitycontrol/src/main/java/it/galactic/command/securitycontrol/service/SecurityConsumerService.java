package it.galactic.command.securitycontrol.service;


import it.galactic.command.securitycontrol.dto.GalaxyMissionDTO;
import it.galactic.command.securitycontrol.entity.SecurityAlert;
import it.galactic.command.securitycontrol.repository.SecurityAlertRepository;
import lombok.RequiredArgsConstructor; // Un modo più moderno per l'iniezione delle dipendenze
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j // Abilita i log (log.info, log.warn, etc.)
@RequiredArgsConstructor // Lombok crea un costruttore con i campi 'final'
public class SecurityConsumerService {

    private final SecurityAlertRepository repository; // L'iniezione avviene tramite costruttore

    private static final double MIN_SAFE_FUEL_LEVEL = 20.0;
    private static final String FORBIDDEN_DESTINATION = "Settore Proibito-Z9";

    @KafkaListener(topics = "voyage-topic", groupId = "security-group")
    public void analyzeMission(GalaxyMissionDTO mission) {
        log.info("🛡️ [SecurityControl] Analizzando la missione per la nave: {}", mission.getShipId());

        List<String> anomalies = findAnomalies(mission);

        if (!anomalies.isEmpty()) {
            String reason = String.join("; ", anomalies);
            log.warn("⚠️ ALERT SICUREZZA! Rilevata anomalia per la nave {}. Motivo: {}", mission.getShipId(), reason);

            saveAlert(mission, reason);
        } else {
            log.info("✅ Missione della nave {} approvata dal SecurityControl. Nessuna anomalia rilevata.", mission.getShipId());
        }
    }

    private List<String> findAnomalies(GalaxyMissionDTO mission) {
        List<String> anomalies = new ArrayList<>();

        // Regola di sicurezza 1: Carburante basso
        if (mission.getFuelLevel() < MIN_SAFE_FUEL_LEVEL) {
            anomalies.add("Livello carburante criticamente basso (" + mission.getFuelLevel() + "%)");
        }

        // Regola di sicurezza 2: Destinazione proibita
        if (FORBIDDEN_DESTINATION.equalsIgnoreCase(mission.getDestination())) {
            anomalies.add("Rotta verso una destinazione proibita: " + mission.getDestination());
        }

        return anomalies;
    }

    private void saveAlert(GalaxyMissionDTO mission, String reason) {
        SecurityAlert alert = new SecurityAlert();
        alert.setShipId(mission.getShipId());
        alert.setReason(reason);
        alert.setAlertTime(ZonedDateTime.now());

        repository.save(alert);
        log.info("💾 Allarme per la nave {} salvato correttamente nel database di sicurezza.", mission.getShipId());
    }
}
