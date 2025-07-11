package it.galactic.command.securitycontrol.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data; // Lombok genera getter, setter, toString(), etc.
import java.time.ZonedDateTime;

@Entity // Dice a JPA che questa classe è una tabella del database
@Data
public class SecurityAlert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // L'ID sarà auto-generato dal DB (IDENTITY è ottimo per Postgres)
    private Long id;

    private String shipId;

    private String reason; // Motivo dell'allarme

    private ZonedDateTime alertTime; // Orario in cui è stato generato l'allarme
}
