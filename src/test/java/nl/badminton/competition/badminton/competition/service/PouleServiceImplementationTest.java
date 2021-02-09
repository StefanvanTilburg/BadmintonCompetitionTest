package nl.badminton.competition.badminton.competition.service;

import nl.badminton.competition.badminton.competition.converter.PouleConverter;
import nl.badminton.competition.badminton.competition.dto.PouleDto;
import nl.badminton.competition.badminton.competition.model.Competition;
import nl.badminton.competition.badminton.competition.model.Poule;
import nl.badminton.competition.badminton.competition.repository.CompetitionRepository;
import nl.badminton.competition.badminton.competition.repository.PouleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

class PouleServiceImplementationTest {

    private PouleRepository pouleRepository;
    private CompetitionRepository competitionRepository;
    private PouleConverter pouleConverter;
    private PouleServiceImplementation pouleService;

    @BeforeEach
    void setup() {
        this.pouleRepository = Mockito.mock(PouleRepository.class);
        this.competitionRepository = Mockito.mock(CompetitionRepository.class);
        this.pouleConverter = Mockito.mock(PouleConverter.class);
        this.pouleService = new PouleServiceImplementation(pouleRepository, pouleConverter, competitionRepository);
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
    void getAll() {
        //Arrange
        List<PouleDto> pouleDtoList = new ArrayList<>();
        PouleDto pouleDto1 = getPouleDto1();
        PouleDto pouleDto2 = getPouleDto2();
        pouleDtoList.add(pouleDto1);
        pouleDtoList.add(pouleDto2);

        List<Poule> pouleList = new ArrayList<>();
        Competition competition = getDefaultCompetition();
        Poule poule1 = getFirstPoule(competition);
        Poule poule2 = getSecondPoule(competition);
        pouleList.add(poule1);
        pouleList.add(poule2);

        //Act
        when(pouleRepository.findAll()).thenReturn(pouleList);
        when(pouleConverter.convertToListDto(pouleList)).thenReturn(pouleDtoList);
        List<PouleDto> output = pouleService.getAll();

        //Assert
        assertThat(output).isEqualTo(pouleDtoList);
    }

    @Test
    void findById() {
        //Arrange
        Competition competition = getDefaultCompetition();
        Poule poule = getFirstPoule(competition);
        Optional<Poule> resultRepository = Optional.of(poule);
        PouleDto pouleDto = getPouleDto1();
        Optional<PouleDto> resultFunction = Optional.of(pouleDto);
        long inputID = 1;

        //Act
        when(pouleRepository.findById(inputID)).thenReturn(resultRepository);
        when(pouleConverter.convertToDto(poule)).thenReturn(pouleDto);
        Optional<PouleDto> output = pouleService.findById(inputID);

        //Assert
        assertThat(output).isEqualTo(resultFunction);
    }

    @Test
    void findByName() {
        //Arrange
        Competition competition = getDefaultCompetition();
        Poule poule = getFirstPoule(competition);
        Optional<Poule> resultRepository = Optional.of(poule);
        PouleDto pouleDto = getPouleDto1();
        Optional<PouleDto> resultFunction = Optional.of(pouleDto);
        String inputName = "Poule 1";

        //Act
        when(pouleRepository.findByPouleName(inputName)).thenReturn(resultRepository);
        when(pouleConverter.convertToDto(poule)).thenReturn(pouleDto);
        Optional<PouleDto> output = pouleService.findByName(inputName);

        //Assert
        assertThat(output).isEqualTo(resultFunction);
    }

    @Test
    void saveEntityCompetitionNotPresent() throws SQLDataException {
        //Arrange
        PouleDto pouleDto = getPouleDto1();
        Competition competition = getDefaultCompetition();
        Competition competitionResult = getDefaultCompetition();
        competitionResult.setCompetitionId(1);
        Poule poule = getFirstPoule(competition);
        Poule pouleResult = getFirstPoule(competitionResult);

        //Act
        when(pouleConverter.convertToEntity(pouleDto)).thenReturn(poule);
        when(competitionRepository.findByCompetitionName(poule.getCompetition().getCompetitionName()))
                .thenReturn(Optional.empty());
        when(competitionRepository.save(competition)).thenReturn(competitionResult);
        when(pouleRepository.save(poule)).thenReturn(pouleResult);
        when(pouleConverter.convertToDto(pouleResult)).thenReturn(pouleDto);
        PouleDto output = pouleService.saveEntity(pouleDto);

        //Assert
        assertThat(output).isEqualTo(pouleDto);
    }

    @Test
    void saveEntityCompetitionPresent() throws SQLDataException {
        //Arrange
        PouleDto pouleDto = getPouleDto1();
        Competition competition = getDefaultCompetition();
        Competition competitionResult = getDefaultCompetition();
        competitionResult.setCompetitionId(1);
        Poule poule = getFirstPoule(competition);
        Poule pouleResult = getFirstPoule(competitionResult);

        //Act
        when(pouleConverter.convertToEntity(pouleDto)).thenReturn(poule);
        when(competitionRepository.findByCompetitionName(poule.getCompetition().getCompetitionName()))
                .thenReturn(Optional.of(competitionResult));
        when(pouleRepository.save(poule)).thenReturn(pouleResult);
        when(pouleConverter.convertToDto(pouleResult)).thenReturn(pouleDto);
        PouleDto output = pouleService.saveEntity(pouleDto);

        //Assert
        assertThat(output).isEqualTo(pouleDto);
    }

    @Test
    void updateEntity() {
        // not usefull for now
    }

    @Test
    void deleteEntity() throws SQLDataException {
        //Arrange
        Competition competition = getDefaultCompetition();
        Optional<Poule> poule = Optional.of(getFirstPoule(competition));
        PouleDto pouleDto = getPouleDto1();

        //Act
        when(pouleRepository.findByPouleName(poule.get().getPouleName())).thenReturn(poule);
        pouleService.deleteEntity(pouleDto);

        //Assert
        verify(pouleRepository).delete(poule.get());
    }
}