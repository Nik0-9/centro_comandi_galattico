package it.galactic.command.centrallogic.service;


import it.galactic.command.centrallogic.dto.GalaxyMissionDTO;
import it.galactic.command.centrallogic.entity.Mission;
import it.galactic.command.centrallogic.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MissionLogicConsumer {

    private final MissionRepository missionRepository;

    @KafkaListener(topics = "voyage-topic", groupId = "central-logic-group")
    public void recordMission(GalaxyMissionDTO missionDTO) {
        log.info("🧠 [CentralLogic] Ricevuto ordine di viaggio per la nave: {}. Aggiornando il log di bordo.", missionDTO.getShipId());

        // Mappiamo il DTO ricevuto in un'entità del database
        Mission mission = new Mission();
        mission.setShipId(missionDTO.getShipId());
        mission.setOrigin(missionDTO.getOrigin());
        mission.setDestination(missionDTO.getDestination());
        mission.setInitialFuelLevel(missionDTO.getFuelLevel());
        mission.setDepartureTime(missionDTO.getDepartureTime());
        mission.setStatus("IN_VIAGGIO"); // Impostiamo uno stato iniziale

        // Usiamo save(), che fa un INSERT se la chiave non esiste, o un UPDATE se esiste. Perfetto!
        missionRepository.save(mission);

        log.info("✅ Log di bordo per la nave {} aggiornato correttamente.", missionDTO.getShipId());
    }

}
