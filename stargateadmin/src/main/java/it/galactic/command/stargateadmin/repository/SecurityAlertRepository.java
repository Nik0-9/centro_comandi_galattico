package it.galactic.command.stargateadmin.repository;

import it.galactic.command.stargateadmin.entity.SecurityAlert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecurityAlertRepository extends JpaRepository<SecurityAlert, Long> {
}
