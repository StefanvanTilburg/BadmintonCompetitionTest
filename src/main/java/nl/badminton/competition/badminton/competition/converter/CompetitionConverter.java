package nl.badminton.competition.badminton.competition.converter;

import nl.badminton.competition.badminton.competition.model.Competition;
import nl.badminton.competition.badminton.competition.dto.CompetitionDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Stefan van Tilburg <s.g.van.tilburg@st.hanze.nl>
 */
@Service
public class CompetitionConverter implements SuperConverter<Competition, CompetitionDto> {

    private final ModelMapper modelMapper;

    public CompetitionConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CompetitionConverter() {
        this(new ModelMapper());
    }

    @Override
    public PropertyMap<Competition, CompetitionDto> getConversionMap() {
        return new PropertyMap<>() {
            protected void configure() {
                map(source.getCompetitionId(), destination.getId());
                map(source.getCompetitionName(), destination.getName());
                map(source.getPoules(), destination.getPoules());
            }
        };
    }

    @Override
    public List<CompetitionDto> convertToListDto(List<Competition> input) {
        return input.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<Competition> convertToListEntity(List<CompetitionDto> input) {
        return input.stream().map(this::convertToEntity).collect(Collectors.toList());
    }

    @Override
    public CompetitionDto convertToDto(Competition input) {
        return modelMapper.map(input, CompetitionDto.class);
    }

    @Override
    public Competition convertToEntity(CompetitionDto input) {
        return modelMapper.map(input, Competition.class);
    }
}
