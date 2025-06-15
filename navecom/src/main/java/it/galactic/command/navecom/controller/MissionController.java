package it.galactic.command.navecom.controller;

import it.galactic.command.navecom.dto.GalaxyMissionDTO;
import it.galactic.command.navecom.service.MissionCommandService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/missions")
public class MissionController {
    private final MissionCommandService missionService;

    public MissionController(MissionCommandService missionService) {
        this.missionService = missionService;
    }

    @PostMapping
    public ResponseEntity<String> launchMission(@RequestBody GalaxyMissionDTO mission) {
        // Valida l'input (omesso per semplicità)
        if (mission.getShipId() == null || mission.getDestination() == null) {
            return ResponseEntity.badRequest().body("Ship ID e Destination sono obbligatori.");
        }
        
        missionService.sendMissionOrder(mission);
        
        String responseMessage = String.format(
            "Comando ricevuto! La nave %s è stata inviata in missione verso %s.",
            mission.getShipId(),
            mission.getDestination()
        );
        return ResponseEntity.accepted().body(responseMessage);
    }

}
