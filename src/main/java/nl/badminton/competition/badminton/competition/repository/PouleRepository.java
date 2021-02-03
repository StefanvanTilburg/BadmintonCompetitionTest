package nl.badminton.competition.badminton.competition.repository;

import nl.badminton.competition.badminton.competition.model.Poule;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PouleRepository extends JpaRepository<Poule, Long> {
    Optional<Poule> findByPouleName(String name);
}
