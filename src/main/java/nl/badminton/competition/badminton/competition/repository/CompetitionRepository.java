package nl.badminton.competition.badminton.competition.repository;

import nl.badminton.competition.badminton.competition.model.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * @author Stefan van Tilburg <s.g.van.tilburg@st.hanze.nl>
 */
public interface CompetitionRepository extends JpaRepository<Competition, Long> {
    Optional<Competition> findByCompetitionName(String name);
}
