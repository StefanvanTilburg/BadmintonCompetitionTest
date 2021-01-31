package nl.badminton.competition.badminton.competition.converter;

import nl.badminton.competition.badminton.competition.model.Poule;
import nl.badminton.competition.badminton.competition.dto.PouleDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Stefan van Tilburg <s.g.van.tilburg@st.hanze.nl>
 */
@Service
public class PouleConverter implements SuperConverter<Poule, PouleDto> {

    private final ModelMapper modelMapper;

    public PouleConverter() {
        this.modelMapper = new ModelMapper();
    }

    @Override
    public PropertyMap<Poule, PouleDto> getConvertionMap() {
        return new PropertyMap<>() {
            protected void configure() {
                map(source.getPouleName(), destination.getName());
            }
        };
    }

    @Override
    public List<PouleDto> convertToListDto(List<Poule> input) {
        return input.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<Poule> convertToListEntity(List<PouleDto> input) {
        return input.stream().map(this::convertToEntity).collect(Collectors.toList());
    }

    @Override
    public PouleDto convertToDto(Poule input) {
        return modelMapper.map(input, PouleDto.class);
    }

    @Override
    public Poule convertToEntity(PouleDto input) {
        return modelMapper.map(input, Poule.class);
    }
}
