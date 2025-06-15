package it.galactic.command.fuelstationcontrol.dto;

import java.time.ZonedDateTime;
public class GalaxyMissionDTO {
    private String shipId;
    private String origin;
    private String destination;
    private double fuelLevel;
    private ZonedDateTime departureTime;

    public GalaxyMissionDTO() {}

    public String getShipId() { return shipId; }
    public void setShipId(String shipId) { this.shipId = shipId; }
    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }
    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
    public double getFuelLevel() { return fuelLevel; }
    public void setFuelLevel(double fuelLevel) { this.fuelLevel = fuelLevel; }
    public ZonedDateTime getDepartureTime() { return departureTime; }
    public void setDepartureTime(ZonedDateTime departureTime) { this.departureTime = departureTime; }
}
