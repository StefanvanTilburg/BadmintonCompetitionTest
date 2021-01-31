package nl.badminton.competition.badminton.competition;

import nl.badminton.competition.badminton.competition.model.Competition;
import nl.badminton.competition.badminton.competition.dto.CompetitionDto;
import nl.badminton.competition.badminton.competition.model.Poule;
import nl.badminton.competition.badminton.competition.dto.PouleDto;
import nl.badminton.competition.badminton.competition.service.ServiceFunctions;
import nl.badminton.competition.badminton.competition.converter.SuperConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class Application implements CommandLineRunner {

	private final ServiceFunctions<Competition, CompetitionDto> competitionService;
	private final ServiceFunctions<Poule, PouleDto> pouleService;
	private final SuperConverter<Competition, CompetitionDto> competitionConverter;
	private final SuperConverter<Poule, PouleDto> pouleConverter;

	@Autowired
	public Application(ServiceFunctions<Competition, CompetitionDto> competitionService,
					   ServiceFunctions<Poule, PouleDto> pouleService,
					   SuperConverter<Competition, CompetitionDto> competitionConverter,
					   SuperConverter<Poule, PouleDto> pouleConverter) {
		this.competitionService = competitionService;
		this.pouleService = pouleService;
		this.competitionConverter = competitionConverter;
		this.pouleConverter = pouleConverter;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Competition competition = new Competition("First comp in app");
		competition = competitionService .saveEntity(competition);

		pouleService.saveEntity(new Poule("Poule A", competition));
		pouleService.saveEntity(new Poule("Poule B", competition));
		pouleService.saveEntity(new Poule("Poule C", competition));
		pouleService.saveEntity(new Poule("Poule D", competition));

		System.out.println("--------- converting to Dto -------------");
		CompetitionDto competitionDto = competitionConverter.convertToDto(competition);
		System.out.println(competition);
		System.out.println(competitionDto);

		competition = null;
		System.out.println("--------- check if null -------------");
		System.out.println("Competition is null ? : " + (competition == null));

		System.out.println("--------- converting to Entity  -------------");
		competition = competitionConverter.convertToEntity(competitionDto);
		System.out.println(competition);
		System.out.println(competitionDto);

		System.out.println("--------- converting 5 Dto's  -------------");
		List<CompetitionDto> competitionDtoList = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			competitionDtoList.add(new CompetitionDto("Name " + i));
		}
		for (CompetitionDto dto : competitionDtoList) {
			Competition competition1 = competitionConverter.convertToEntity(dto);
			System.out.println(competition1);
			System.out.println(dto);
		}

		System.out.println("Testing if comp will toString with poules attached");
		Optional<Competition> competition1 = competitionService.findById(1L);
		competition1.ifPresent(System.out::println);
	}
}
