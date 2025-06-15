package it.galactic.command.navecom.service;

import it.galactic.command.navecom.dto.GalaxyMissionDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MissionCommandService {
    private static final Logger log = LoggerFactory.getLogger(MissionCommandService.class);
    private static final String VOYAGE_TOPIC = "voyage-topic";

    private final KafkaTemplate<String, GalaxyMissionDTO> kafkaTemplate;

    // Spring inietta automaticamente il KafkaTemplate che abbiamo configurato nel file application.properties
    public MissionCommandService(KafkaTemplate<String, GalaxyMissionDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    /**
     * Invia un ordine di missione al topic Kafka.
     * La shipId viene usata come chiave del messaggio per garantire che tutti i messaggi
     * relativi a una stessa nave finiscano nella stessa partizione (utile per l'ordine).
     */
    public void sendMissionOrder(GalaxyMissionDTO mission) {
        log.info("🚀 Ricevuto ordine per la nave: {}. Invio a Kafka...", mission.getShipId());
        try {
            kafkaTemplate.send(VOYAGE_TOPIC, mission.getShipId(), mission);
            log.info("✅ Missione per {} inviata con successo al topic '{}'", mission.getShipId(), VOYAGE_TOPIC);
        } catch (Exception e) {
            log.error("❌ Errore durante l'invio della missione per {} a Kafka", mission.getShipId(), e);
        }
    }

}
