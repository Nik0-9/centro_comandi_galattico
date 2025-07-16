package it.galactic.command.centrallogic.repository;

import it.galactic.command.centrallogic.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MissionRepository extends JpaRepository<Mission, String> {
}
