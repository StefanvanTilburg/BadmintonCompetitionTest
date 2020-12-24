package nl.badminton.competition.badminton.competition;

import nl.badminton.competition.badminton.competition.model.Competition;
import nl.badminton.competition.badminton.competition.model.CompetitionDto;
import nl.badminton.competition.badminton.competition.model.Poule;
import nl.badminton.competition.badminton.competition.repository.CompetitionRepository;
import nl.badminton.competition.badminton.competition.repository.PouleRepository;
import nl.badminton.competition.badminton.competition.service.CompetitionService;
import nl.badminton.competition.badminton.competition.service.CompetitionServiceImplementation;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	CompetitionRepository competitionRepository;

	@Autowired
	PouleRepository pouleRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Competition competition = new Competition("First comp in app");
		competition = competitionRepository.save(competition);

		pouleRepository.save(new Poule("Poule A", competition));
		pouleRepository.save(new Poule("Poule B", competition));
		pouleRepository.save(new Poule("Poule C", competition));
		pouleRepository.save(new Poule("Poule D", competition));

		CompetitionService competitionService = new CompetitionServiceImplementation(competitionRepository);

		System.out.println("--------- converting to Dto -------------");
		CompetitionDto competitionDto = competitionService.convertToDto(competition);
		System.out.println(competition);
		System.out.println(competitionDto);

		competition = null;
		System.out.println("--------- check if null -------------");
		System.out.println("Competition is null ? : " + (competition == null));

		System.out.println("--------- converting to Entity  -------------");
		competition = competitionService.convertToEntity(competitionDto);
		System.out.println(competition);
		System.out.println(competitionDto);

		System.out.println("--------- converting 5 Dto's  -------------");
		List<CompetitionDto> competitionDtoList = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			competitionDtoList.add(new CompetitionDto(i, "Name " + i));
		}
		for (CompetitionDto dto : competitionDtoList) {
			Competition competition1 = competitionService.convertToEntity(dto);
			System.out.println(competition1);
			System.out.println(dto);
		}

		System.out.println("Testing if comp will toString with poules attached");
		Optional<Competition> competition1 = competitionRepository.findById(Long.parseLong("1"));
		if (competition1.isPresent()) {
			System.out.println(competition1.get());
		}
	}
}
