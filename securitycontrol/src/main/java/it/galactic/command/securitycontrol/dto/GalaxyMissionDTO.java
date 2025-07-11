package it.galactic.command.securitycontrol.dto;

import lombok.Data;
import java.time.ZonedDateTime;

@Data
public class GalaxyMissionDTO {
    private String shipId;
    private String origin;
    private String destination;
    private double fuelLevel;
    private ZonedDateTime departureTime;
}