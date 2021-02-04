package nl.badminton.competition.badminton.competition.service;

import nl.badminton.competition.badminton.competition.converter.PouleConverter;
import nl.badminton.competition.badminton.competition.model.Competition;
import nl.badminton.competition.badminton.competition.model.Poule;
import nl.badminton.competition.badminton.competition.dto.PouleDto;
import nl.badminton.competition.badminton.competition.repository.CompetitionRepository;
import nl.badminton.competition.badminton.competition.repository.PouleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.sql.SQLDataException;
import java.util.List;
import java.util.Optional;

/**
 * @author Stefan van Tilburg <s.g.van.tilburg@st.hanze.nl>
 */
@Service
public class PouleServiceImplementation implements ServiceFunctions<Poule, PouleDto> {

    private final PouleRepository pouleRepository;
    private final CompetitionRepository competitionRepository;
    private final PouleConverter pouleConverter;

    @Autowired
    public PouleServiceImplementation(PouleRepository pouleRepository,
                                      PouleConverter pouleConverter,
                                      CompetitionRepository competitionRepository) {
        this.pouleRepository = pouleRepository;
        this.pouleConverter = pouleConverter;
        this.competitionRepository = competitionRepository;
    }

    @Override
    public List<PouleDto> getAll() {
        return pouleConverter.convertToListDto(pouleRepository.findAll());
    }

    @Override
    public Optional<PouleDto> findById(long id) {
        Optional<Poule> poule = pouleRepository.findById(id);
        Optional<PouleDto> pouleDtoOptional = Optional.empty();

        if (poule.isPresent()) {
            pouleDtoOptional = Optional.of(pouleConverter.convertToDto(poule.get()));
        }

        return pouleDtoOptional;
    }

    @Override
    public Optional<PouleDto> findByName(String name) {
        Optional<Poule> poule = pouleRepository.findByPouleName(name);
        Optional<PouleDto> pouleDtoOptional = Optional.empty();

        if (poule.isPresent()) {
            pouleDtoOptional = Optional.of(pouleConverter.convertToDto(poule.get()));
        }

        return pouleDtoOptional;
    }

    @Override
    public PouleDto saveEntity(PouleDto input) throws SQLDataException {
        Poule poule = pouleConverter.convertToEntity(input);
        Optional<Competition> competition = competitionRepository
                .findByCompetitionName(poule.getCompetition().getCompetitionName());

        if (competition.isPresent()) {
            // Set reference to found competition this prevents duplicate save errors
            poule.setCompetition(competition.get());
        } else {
            // Save new competition and set reference to it in poule
            Competition competitionSaved = competitionRepository.save(poule.getCompetition());
            poule.setCompetition(competitionSaved);
        }

        // Because the Entity Poule has relation ManyToOne(cascade = Cascade.All) to Competition. A new Competition
        // that will use the default constructor of Competition, will be saved in the database if previous
        // competition search results in isPresent == false.
        try {
            poule = pouleRepository.save(poule);
        } catch (DataAccessException exception) {
            throw new SQLDataException("Error during save: " + exception.getMessage());
        }

        return pouleConverter.convertToDto(poule);
    }

    //TODO : This update function will not do anything because name is the identifying variable, so it can't be changed.
    @Override
    public PouleDto updateEntity(PouleDto input) throws SQLDataException {
        Optional<Poule> poule = pouleRepository.findByPouleName(input.getName());

        if (poule.isPresent()) {
            poule.get().setPouleName(input.getName());

            try {
                pouleRepository.save(poule.get());
            } catch (DataAccessException exception) {
                throw new SQLDataException("Error during save: " + exception.getMessage());
            }
        } else {
            throw new SQLDataException("Could not update entity " + input);
        }

        return pouleConverter.convertToDto(poule.get());
    }

    @Override
    public void deleteEntity(PouleDto input) throws SQLDataException {
        Optional<Poule> poule = pouleRepository.findByPouleName(input.getName());

        if (poule.isPresent()) {
            try {
                pouleRepository.delete(poule.get());
            } catch (DataAccessException exception) {
                throw new SQLDataException("Error during delete: " + exception.getMessage());
            }
        }
    }
}
