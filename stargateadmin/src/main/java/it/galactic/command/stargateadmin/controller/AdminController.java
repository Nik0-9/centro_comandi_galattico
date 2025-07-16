package it.galactic.command.stargateadmin.controller;

import it.galactic.command.stargateadmin.entity.Mission;
import it.galactic.command.stargateadmin.entity.SecurityAlert;
import it.galactic.command.stargateadmin.repository.MissionRepository;
import it.galactic.command.stargateadmin.repository.SecurityAlertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final MissionRepository missionRepository;
    private final SecurityAlertRepository securityAlertRepository;

    /**
     * Endpoint per visualizzare il log di bordo di tutte le missioni.
     * @return Una lista di tutte le missioni registrate da CentralLogic.
     */
    @GetMapping("/missions")
    public List<Mission> getAllMissions() {
        return missionRepository.findAll();
    }

    /**
     * Endpoint per visualizzare tutti gli allarmi di sicurezza.
     * @return Una lista di tutti gli allarmi registrati da SecurityControl.
     */
    @GetMapping("/alerts")
    public List<SecurityAlert> getAllAlerts() {
        return securityAlertRepository.findAll();
    }

}
