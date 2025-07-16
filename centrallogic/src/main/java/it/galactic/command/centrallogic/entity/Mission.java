package it.galactic.command.centrallogic.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import java.time.ZonedDateTime;


@Entity
@Data
public class Mission {

    @Id // Useremo l'ID della nave come chiave unica della missione
    private String shipId;

    private String origin;

    private String destination;

    private double initialFuelLevel;

    private String status; // Es. "IN_VIAGGIO", "COMPLETATA", "ANNULLATA"

    private ZonedDateTime departureTime;
}
