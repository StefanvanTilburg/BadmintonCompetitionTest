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

import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.List;

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

		// Add one competition and save child poules through saveEntity of competition
		CompetitionDto competitionDto1 = new CompetitionDto("First Competition");
		List<PouleDto> pouleDtoList1 = new ArrayList<>();
		PouleDto pouleDto1 = new PouleDto("Poule A");
		PouleDto pouleDto2 = new PouleDto("Poule B");
		PouleDto pouleDto3 = new PouleDto("Poule C");
		PouleDto pouleDto4 = new PouleDto("Poule D");
		pouleDtoList1.add(pouleDto1);
		pouleDtoList1.add(pouleDto2);
		pouleDtoList1.add(pouleDto3);
		pouleDtoList1.add(pouleDto4);
		competitionDto1.setPoules(pouleDtoList1);

		try {
			competitionService.saveEntity(competitionDto1);
		} catch (SQLDataException e) {
			System.out.println("Not saved error caught!");
		}

		List<PouleDto> pouleDtoList = new ArrayList<>();
		pouleDtoList.add(new PouleDto("Test addition"));
		pouleDtoList.add(new PouleDto("Test delete poule function"));
		competitionDto1.setPoules(pouleDtoList);

		//Update entity
		try {
			competitionService.updateEntity(competitionDto1);
		} catch (SQLDataException e) {
			System.out.println("Updating went wrong!");
		}

		//Delete entity
		try {
			pouleService.deleteEntity(new PouleDto("Test delete poule function"));
		} catch (SQLDataException e) {
			System.out.println("Deleting went wrong!");
		}

		// Add two competition and save child poules through saveEntity of competition
		CompetitionDto competitionDto2 = new CompetitionDto("Second Competition");
		List<PouleDto> pouleDtoList2 = new ArrayList<>();
		PouleDto pouleDto5 = new PouleDto("Poule E");
		PouleDto pouleDto6 = new PouleDto("Poule F");
		PouleDto pouleDto7 = new PouleDto("Poule G");
		PouleDto pouleDto8 = new PouleDto("Poule H");
		pouleDtoList2.add(pouleDto5);
		pouleDtoList2.add(pouleDto6);
		pouleDtoList2.add(pouleDto7);
		pouleDtoList2.add(pouleDto8);
		competitionDto2.setPoules(pouleDtoList2);

		try {
			competitionService.saveEntity(competitionDto2);
		} catch (SQLDataException e) {
			System.out.println("Not saved error caught!");
		}

		// Add two poules and 1 existing
		PouleDto pouleDto9 = new PouleDto("Poule E");
		PouleDto pouleDto10 = new PouleDto("Poule F");
		pouleDtoList2.add(pouleDto9);
		pouleDtoList2.add(pouleDto10);
		competitionDto2.setPoules(pouleDtoList2);

		try {
			competitionService.saveEntity(competitionDto2);
		} catch (SQLDataException e) {
			System.out.println("Not saved error caught!");
		}

		//----------------------------------------------------later
		//Add poule that is not connected to a competition
		PouleDto newPouleDto1 = new PouleDto("Without competition?");
		try {
			pouleService.saveEntity(newPouleDto1);
		} catch (SQLDataException e) {
			System.out.println("Not saved error caught! " + e.getMessage());
		}

		//Add poule that is not connected to a competition
		PouleDto newPouleDto2 = new PouleDto("Without competition2?");
		try {
			pouleService.saveEntity(newPouleDto2);
		} catch (SQLDataException e) {
			System.out.println("Not saved error caught! " + e.getMessage());
		}
	}
}
