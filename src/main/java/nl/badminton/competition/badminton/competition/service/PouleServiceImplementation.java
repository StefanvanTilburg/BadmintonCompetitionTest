package nl.badminton.competition.badminton.competition.service;

import nl.badminton.competition.badminton.competition.converter.PouleConverter;
import nl.badminton.competition.badminton.competition.model.Poule;
import nl.badminton.competition.badminton.competition.dto.PouleDto;
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
    private final PouleConverter pouleConverter;

    @Autowired
    public PouleServiceImplementation(PouleRepository pouleRepository, PouleConverter pouleConverter) {
        this.pouleRepository = pouleRepository;
        this.pouleConverter = pouleConverter;
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
    public PouleDto saveEntity(PouleDto input) throws SQLDataException {
        Poule poule = pouleConverter.convertToEntity(input);

        // Because in Entity Poule has the relation ManyToOne(cascade = Cascade.All) to Competition. A new Competition
        // that will use the default constructor of Competition, will be saved in the database.

//        TODO : This works fine and well for the first unconnected poule, but the second one will throw an
//         duplicate error when trying to save it. So how to solve missing data link when converting back?
//         possible option: Have default competition in db and always reference to it as default from here?
//         (*problem convoluted, adding competition fetch logic / service / jpa into this class).
        try {
            poule = pouleRepository.save(poule);
        } catch (DataAccessException exception) {
            throw new SQLDataException("Error during save: " + exception.getMessage());
        }

        return pouleConverter.convertToDto(poule);
    }
}
