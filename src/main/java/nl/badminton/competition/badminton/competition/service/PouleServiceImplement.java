package nl.badminton.competition.badminton.competition.service;

import nl.badminton.competition.badminton.competition.converter.PouleConverter;
import nl.badminton.competition.badminton.competition.model.Poule;
import nl.badminton.competition.badminton.competition.dto.PouleDto;
import nl.badminton.competition.badminton.competition.repository.PouleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * @author Stefan van Tilburg <s.g.van.tilburg@st.hanze.nl>
 */
@Service
public class PouleServiceImplement implements ServiceFunctions<Poule, PouleDto> {

    private final PouleRepository pouleRepository;
    private final PouleConverter pouleConverter;

    @Autowired
    public PouleServiceImplement(PouleRepository pouleRepository, PouleConverter pouleConverter) {
        this.pouleRepository = pouleRepository;
        this.pouleConverter = pouleConverter;
    }

    @Override
    public List<PouleDto> getAll() {
        return pouleConverter.convertToListDto(pouleRepository.findAll());
    }

    @Override
    public Optional<Poule> findById(long id) {
        return pouleRepository.findById(id);
    }

    @Override
    public Poule saveEntity(Poule input) {
        return pouleRepository.save(input);
    }
}
