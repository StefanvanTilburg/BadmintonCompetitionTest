package nl.badminton.competition.badminton.competition.repository;

import nl.badminton.competition.badminton.competition.model.Poule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PouleRepository extends JpaRepository<Poule, Long> {
}
