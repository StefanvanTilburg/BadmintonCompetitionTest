package nl.badminton.competition.badminton.competition.service;

import nl.badminton.competition.badminton.competition.model.Competition;
import nl.badminton.competition.badminton.competition.model.CompetitionDto;
import nl.badminton.competition.badminton.competition.repository.CompetitionRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Stefan van Tilburg <s.g.van.tilburg@st.hanze.nl>
 * info:
 * https://www.baeldung.com/entity-to-and-from-dto-for-a-java-spring-application
 * http://modelmapper.org/getting-started/
 */
@Service
public class CompetitionService {

    private CompetitionRepository competitionRepository;
    private ModelMapper modelMapper;

    @Autowired
    public CompetitionService(CompetitionRepository competitionRepository) {
        this.competitionRepository = competitionRepository;
        this.modelMapper = new ModelMapper();
        this.modelMapper.addMappings(getCompetitionMap());
    }

    public List<Competition> getCompetitions() {
        return competitionRepository.findAll();
    }

    public List<CompetitionDto> getCompetitionDtos() {
        return competitionRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public CompetitionDto convertToDto(Competition competition) {
        CompetitionDto competitionDto = modelMapper.map(competition, CompetitionDto.class);
        return competitionDto;
    }

    public PropertyMap<Competition, CompetitionDto> getCompetitionMap() {
        PropertyMap<Competition, CompetitionDto> competitionMap = new PropertyMap<Competition, CompetitionDto>() {
            protected void configure() {
                map().setCompetitionDtoId(source.getCompetitionId());
                map(source.getCompetitionName(), destination.getCompetitionDtoName());
            }
        };
        return competitionMap;
    }
}
