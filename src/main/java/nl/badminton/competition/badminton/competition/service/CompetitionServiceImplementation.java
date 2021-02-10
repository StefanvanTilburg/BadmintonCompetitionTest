package nl.badminton.competition.badminton.competition.service;

import nl.badminton.competition.badminton.competition.converter.CompetitionConverter;
import nl.badminton.competition.badminton.competition.model.Competition;
import nl.badminton.competition.badminton.competition.dto.CompetitionDto;
import nl.badminton.competition.badminton.competition.model.Poule;
import nl.badminton.competition.badminton.competition.repository.CompetitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.sql.SQLDataException;
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
    public Optional<CompetitionDto> findById(long id) {
        Optional<Competition> competition = competitionRepository.findById(id);
        Optional<CompetitionDto> competitionDto = Optional.empty();

        if (competition.isPresent()) {
            competitionDto = Optional.of(competitionConverter.convertToDto(competition.get()));
        }

        return competitionDto;
    }

    @Override
    public Optional<CompetitionDto> findByName(String name) {
        Optional<Competition> competition = competitionRepository.findByCompetitionName(name);
        Optional<CompetitionDto> competitionDto = Optional.empty();

        if (competition.isPresent()) {
            competitionDto = Optional.of(competitionConverter.convertToDto(competition.get()));
        }


        return competitionDto;
    }

    @Override
    public CompetitionDto saveEntity(CompetitionDto input) throws SQLDataException {
        Competition competition = competitionConverter.convertToEntity(input);
        // Set reference to this competition inside poule in competition.poules variable!
        // Because in Entity Competition with relation OneToMany(cascade = Cascade.All) the poules will be saved
        // as will the competition with competitionRepository.save(competition).
        for (Poule poule : competition.getPoules()) {
            poule.setCompetition(competition);
        }

        try {
            competition = competitionRepository.save(competition);
        } catch (DataAccessException exception) {
            throw new SQLDataException("Not able to save: " + exception.getMessage());
        }

        return competitionConverter.convertToDto(competition);
    }

    //TODO : Additions work fine, but saving the Entity where the poules already exists gives duplicate error.
    // Not sure how to fix for now WIP.
    @Override
    public CompetitionDto updateEntity(CompetitionDto input) throws SQLDataException {
        Optional<Competition> competition = competitionRepository.findByCompetitionName(input.getName());
        Competition competitionInput = competitionConverter.convertToEntity(input);

        if (competition.isPresent()) {

            competitionInput.setCompetitionId(competition.get().getCompetitionId());
            for (Poule poule : competitionInput.getPoules()) {
                poule.setCompetition(competitionInput);
            }

            try {
                competitionInput = competitionRepository.save(competitionInput);
            } catch (DataAccessException exception) {
                throw new SQLDataException("Not able to save: " + exception.getMessage());
            }
        }

        return competitionConverter.convertToDto(competitionInput);
    }

    @Override
    public void deleteEntity(CompetitionDto input) throws SQLDataException {
        Optional<Competition> competition = competitionRepository.findByCompetitionName(input.getName());

        if (competition.isPresent()) {
            try {
                competitionRepository.delete(competition.get());
            } catch (DataAccessException exception) {
                throw new SQLDataException("Not able to delete: " + exception.getMessage());
            }
        }
    }
}
