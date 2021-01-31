package nl.badminton.competition.badminton.competition.service;

import nl.badminton.competition.badminton.competition.converter.CompetitionConverter;
import nl.badminton.competition.badminton.competition.model.Competition;
import nl.badminton.competition.badminton.competition.dto.CompetitionDto;
import nl.badminton.competition.badminton.competition.repository.CompetitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * @author Stefan van Tilburg <s.g.van.tilburg@st.hanze.nl>
 * info:
 * https://www.baeldung.com/entity-to-and-from-dto-for-a-java-spring-application
 * http://modelmapper.org/getting-started/
 */
@Service
public class CompetitionServiceImplementation implements ServiceFunctions<Competition, CompetitionDto> {

    private final CompetitionRepository competitionRepository;
    private final CompetitionConverter competitionConverter;

    @Autowired
    public CompetitionServiceImplementation(CompetitionRepository competitionRepository,
                                            CompetitionConverter competitionConverter) {
        this.competitionRepository = competitionRepository;
        this.competitionConverter = competitionConverter;
    }

    @Override
    public List<CompetitionDto> getAll() {
        return competitionConverter.convertToListDto(competitionRepository.findAll());
    }

    @Override
    public Optional<Competition> findById(long id) {
        return competitionRepository.findById(id);
    }

    @Override
    public Competition saveEntity(Competition input) {
        return competitionRepository.save(input);
    }
}
