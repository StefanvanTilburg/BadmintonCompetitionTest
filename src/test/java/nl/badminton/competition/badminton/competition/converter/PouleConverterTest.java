package nl.badminton.competition.badminton.competition.converter;

import nl.badminton.competition.badminton.competition.dto.PouleDto;
import nl.badminton.competition.badminton.competition.model.Competition;
import nl.badminton.competition.badminton.competition.model.Poule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

class PouleConverterTest {

    private PouleConverter converter;
    private ModelMapper modelMapper;

    @BeforeEach
    void setup() {
        this.modelMapper = Mockito.mock(ModelMapper.class);
        this.converter = new PouleConverter(modelMapper);
    }

    private Competition getDefaultCompetition() {
        return new Competition("First competition");
    }

    private Poule getFirstPoule(Competition competition) {
        return new Poule(1, "Poule 1", competition);
    }

    private Poule getSecondPoule(Competition competition) {
        return new Poule(2, "Poule 2", competition);
    }

    private PouleDto getPouleDto1() {
        return new PouleDto("Poule 1");
    }

    private PouleDto getPouleDto2() {
        return new PouleDto("Poule 2");
    }

    @Test
    void convertToListDto() {
        //Arrange
        List<Poule> input = new ArrayList<>();
        Competition competition = getDefaultCompetition();
        Poule poule1 = getFirstPoule(competition);
        Poule poule2 = getSecondPoule(competition);
        input.add(poule1);
        input.add(poule2);

        List<PouleDto> result = new ArrayList<>();
        PouleDto pouleDto1 = getPouleDto1();
        PouleDto pouleDto2 = getPouleDto2();
        result.add(pouleDto1);
        result.add(pouleDto2);

        //Act
        when(modelMapper.map(poule1, PouleDto.class)).thenReturn(pouleDto1);
        when(modelMapper.map(poule2, PouleDto.class)).thenReturn(pouleDto2);
        List<PouleDto> output = converter.convertToListDto(input);

        //Assert
        assertThat(output).isEqualTo(result);
    }

    @Test
    void convertToListEntity() {
        //Arrange
        List<PouleDto> input = new ArrayList<>();
        PouleDto pouleDto1 = getPouleDto1();
        PouleDto pouleDto2 = getPouleDto2();
        input.add(pouleDto1);
        input.add(pouleDto2);


        List<Poule> result = new ArrayList<>();
        Competition competition = getDefaultCompetition();
        Poule poule1 = getFirstPoule(competition);
        Poule poule2 = getSecondPoule(competition);
        result.add(poule1);
        result.add(poule2);

        //Act
        when(modelMapper.map(pouleDto1, Poule.class)).thenReturn(poule1);
        when(modelMapper.map(pouleDto2, Poule.class)).thenReturn(poule2);
        List<Poule> output = converter.convertToListEntity(input);

        //Assert
        assertThat(output).isEqualTo(result);
    }

    @Test
    void convertToDto() {
        //Arrange
        Competition competition = getDefaultCompetition();
        Poule input = getFirstPoule(competition);

        PouleDto result = getPouleDto1();

        //Act
        when(modelMapper.map(input, PouleDto.class)).thenReturn(result);
        PouleDto output = converter.convertToDto(input);

        //Assert
        assertThat(output).isEqualTo(result);
    }

    @Test
    void convertToEntity() {
        //Arrange
        PouleDto input = getPouleDto1();
        Competition competition = getDefaultCompetition();
        Poule result = getFirstPoule(competition);

        //Act
        when(modelMapper.map(input, Poule.class)).thenReturn(result);
        Poule output = converter.convertToEntity(input);

        //Assert
        assertThat(output).isEqualTo(result);
    }
}
