package nl.badminton.competition.badminton.competition.service;

import nl.badminton.competition.badminton.competition.converter.CompetitionConverter;
import nl.badminton.competition.badminton.competition.dto.CompetitionDto;
import nl.badminton.competition.badminton.competition.model.Competition;
import nl.badminton.competition.badminton.competition.repository.CompetitionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

class CompetitionServiceImplementationTest {

    private CompetitionRepository competitionRepository;
    private CompetitionConverter competitionConverter;
    private CompetitionServiceImplementation competitionService;

    @BeforeEach
    void setup() {
        this.competitionRepository = Mockito.mock(CompetitionRepository.class);
        this.competitionConverter = Mockito.mock(CompetitionConverter.class);
        this.competitionService = new CompetitionServiceImplementation(competitionRepository,
                competitionConverter);
    }

    @Test
    void getAll() {
        //Arrange
        List<CompetitionDto> competitionDtos = new ArrayList<>();
        CompetitionDto competitionDto1 = new CompetitionDto("First Competition");
        CompetitionDto competitionDto2 = new CompetitionDto("Second Competition");
        competitionDtos.add(competitionDto1);
        competitionDtos.add(competitionDto2);

        List<Competition> competitions = new ArrayList<>();
        Competition competition1 = new Competition(1,"First Competition");
        Competition competition2 = new Competition(2,"Second Competition");
        competitions.add(competition1);
        competitions.add(competition2);

        //Act
        when(competitionRepository.findAll()).thenReturn(competitions);
        when(competitionConverter.convertToListDto(competitions)).thenReturn(competitionDtos);
        List<CompetitionDto> output = competitionService.getAll();

        //Assert
        assertThat(output).isEqualTo(competitionDtos);
    }

    @Test
    void findById() {
        //Arrange
        Competition competition1 = new Competition(1, "First Competition");
        Optional<Competition> competition = Optional.of(competition1);
        CompetitionDto competitionDto = new CompetitionDto("First Competition");

        //Act
        when(competitionRepository.findById(competition1.getCompetitionId())).thenReturn(competition);
        when(competitionConverter.convertToDto(competition1)).thenReturn(competitionDto);
        Optional<CompetitionDto> output = competitionService.findById(competition1.getCompetitionId());

        //Assert
        assertThat(output.get()).isEqualTo(competitionDto);
    }

    @Test
    void findByName() {
        //Arrange
        Competition competition1 = new Competition(1, "First Competition");
        Optional<Competition> competition = Optional.of(competition1);
        CompetitionDto competitionDto = new CompetitionDto("First Competition");

        //Act
        when(competitionRepository.findByCompetitionName(competition1.getCompetitionName())).thenReturn(competition);
        when(competitionConverter.convertToDto(competition1)).thenReturn(competitionDto);
        Optional<CompetitionDto> output = competitionService.findByName(competition1.getCompetitionName());

        //Assert
        assertThat(output.get()).isEqualTo(competitionDto);
    }

    @Test
    void saveEntity() {
    }

    @Test
    void updateEntity() {
    }

    @Test
    void deleteEntity() {
    }
}