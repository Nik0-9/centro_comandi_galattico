package it.galactic.command.securitycontrol.repository;

import it.galactic.command.securitycontrol.entity.SecurityAlert;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Indica a Spring che questa è un'interfaccia di accesso ai dati
public interface SecurityAlertRepository extends JpaRepository<SecurityAlert, Long> {
   // Spring Data JPA implementa magicamente metodi come save(), findById(), findAll(), etc.
}