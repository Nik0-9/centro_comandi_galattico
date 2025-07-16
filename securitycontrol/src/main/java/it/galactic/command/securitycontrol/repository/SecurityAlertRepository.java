package it.galactic.command.securitycontrol.repository;

import it.galactic.command.securitycontrol.entity.SecurityAlert;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecurityAlertRepository extends JpaRepository<SecurityAlert, Long> {
}