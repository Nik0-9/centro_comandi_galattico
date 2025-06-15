package it.galactic.command.fuelstationcontrol.listener;
import it.galactic.command.fuelstationcontrol.dto.GalaxyMissionDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class FuelRequestListener {

    private static final Logger log = LoggerFactory.getLogger(FuelRequestListener.class);

    @KafkaListener(
        topics = "voyage-topic",
        groupId = "fuel-station-group" // Identifica questo gruppo di consumer
    )
    public void listen(GalaxyMissionDTO mission) {
        log.info("⛽️ Richiesta Carburante Ricevuta | Nave: {} | Rotta: {} -> {} | Livello Carburante: {}%",
            mission.getShipId(),
            mission.getOrigin(),
            mission.getDestination(),
            mission.getFuelLevel()
        );

        if (mission.getFuelLevel() < 30) {
            log.warn(">>> ALLERTA RIFORNIMENTO CRITICO per la nave {}!", mission.getShipId());
        }
    }
}