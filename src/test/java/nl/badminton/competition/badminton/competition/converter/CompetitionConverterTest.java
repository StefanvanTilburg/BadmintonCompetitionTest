package nl.badminton.competition.badminton.competition.converter;

import nl.badminton.competition.badminton.competition.dto.CompetitionDto;
import nl.badminton.competition.badminton.competition.model.Competition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


class CompetitionConverterTest {

    private CompetitionConverter converter;
    private ModelMapper modelMapper;

    @BeforeEach
    void setup() {
        this.modelMapper = Mockito.mock(ModelMapper.class);
        this.converter = new CompetitionConverter(modelMapper);
    }

    private CompetitionDto getFirstCompetitionDto() {
        return new CompetitionDto(1,"First competition");
    }

    private CompetitionDto getSecondCompetitionDto() {
        return new CompetitionDto(2, "Second competition");
    }

    private Competition getFirstCompetition() {
        return new Competition(1,"First competition");
    }

    private Competition getSecondCompetition() {
        return new Competition(2,"Second competition");
    }

    @Test
    void convertToListDto() {
        //Arrange
        List<Competition> input = new ArrayList<>();
        Competition competition1 = getFirstCompetition();
        Competition competition2 = getSecondCompetition();
        input.add(competition1);
        input.add(competition2);

        List<CompetitionDto> result = new ArrayList<>();
        CompetitionDto competitionDto1 = getFirstCompetitionDto();
        CompetitionDto competitionDto2 = getSecondCompetitionDto();
        result.add(competitionDto1);
        result.add(competitionDto2);

        //Act
        when(modelMapper.map(competition1, CompetitionDto.class)).thenReturn(competitionDto1);
        when(modelMapper.map(competition2, CompetitionDto.class)).thenReturn(competitionDto2);
        List<CompetitionDto> output = converter.convertToListDto(input);

        //Assert
        assertThat(output).isEqualTo(result);
    }

    @Test
    void convertToListEntity() {
        //Arrange
        List<CompetitionDto> input = new ArrayList<>();
        CompetitionDto competitionDto1 = getFirstCompetitionDto();
        CompetitionDto competitionDto2 = getSecondCompetitionDto();
        input.add(competitionDto1);
        input.add(competitionDto2);

        List<Competition> result = new ArrayList<>();
        Competition competition1 = getFirstCompetition();
        Competition competition2 = getSecondCompetition();
        result.add(competition1);
        result.add(competition2);

        //Act
        when(modelMapper.map(competitionDto1, Competition.class)).thenReturn(competition1);
        when(modelMapper.map(competitionDto2, Competition.class)).thenReturn(competition2);
        List<Competition> output = converter.convertToListEntity(input);

        //Assert
        assertThat(output).isEqualTo(result);
    }

    @Test
    void convertToDto() {
        //Arrange
        Competition input = getFirstCompetition();
        CompetitionDto result = getFirstCompetitionDto();

        //Act
        when(modelMapper.map(input, CompetitionDto.class)).thenReturn(result);
        CompetitionDto output = converter.convertToDto(input);

        //Assert
        assertThat(output).isEqualTo(result);
    }

    @Test
    void convertToEntity() {
        //Arrange
        CompetitionDto input = getFirstCompetitionDto();
        Competition result = getFirstCompetition();

        //Act
        when(modelMapper.map(input, Competition.class)).thenReturn(result);
        Competition output = converter.convertToEntity(input);

        //Assert
        assertThat(output).isEqualTo(result);
    }
}