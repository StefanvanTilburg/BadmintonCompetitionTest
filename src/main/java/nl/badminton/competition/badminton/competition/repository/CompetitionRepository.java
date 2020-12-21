package nl.badminton.competition.badminton.competition.repository;

import nl.badminton.competition.badminton.competition.model.Competition;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Stefan van Tilburg <s.g.van.tilburg@st.hanze.nl>
 */
public interface CompetitionRepository extends JpaRepository<Competition, Long> {
}
