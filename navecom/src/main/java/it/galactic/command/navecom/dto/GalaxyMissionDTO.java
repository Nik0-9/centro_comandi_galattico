package it.galactic.command.navecom.dto;

import java.time.ZonedDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GalaxyMissionDTO {
    private String shipId;
    private String origin;
    private String destination;
    private double fuelLevel;
    private ZonedDateTime departureTime;
    
    @Override
    public String toString() {
        return "GalaxyMissionDTO{" +
        "shipId='" + shipId + '\'' +
        ", destination='" + destination + '\'' +
        '}';
    }
}
