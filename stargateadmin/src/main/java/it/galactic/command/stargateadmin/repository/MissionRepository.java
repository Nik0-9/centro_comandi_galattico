package it.galactic.command.stargateadmin.repository;

import it.galactic.command.stargateadmin.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MissionRepository extends JpaRepository<Mission, String> {
}

