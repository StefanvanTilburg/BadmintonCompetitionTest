package nl.badminton.competition.badminton.competition;

import nl.badminton.competition.badminton.competition.model.Competition;
import nl.badminton.competition.badminton.competition.model.CompetitionDto;
import nl.badminton.competition.badminton.competition.model.Poule;
import nl.badminton.competition.badminton.competition.repository.CompetitionRepository;
import nl.badminton.competition.badminton.competition.repository.PouleRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

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

		ModelMapper modelMapper = new ModelMapper();
		PropertyMap<Competition, CompetitionDto> competitionMap = new PropertyMap<Competition, CompetitionDto>() {
			protected void configure() {
				map().setCompetitionDtoId(source.getCompetitionId());
				map(source.getCompetitionName(), destination.getCompetitionDtoName());
			}
		};

		modelMapper.addMappings(competitionMap);
		CompetitionDto competitionDto = modelMapper.map(competition, CompetitionDto.class);
		System.out.println("Competition Id : " + competition.getCompetitionId());
		System.out.println("CompetitionDto Id : " + competitionDto.getCompetitionDtoId());
		System.out.println("Competition Name : " + competition.getCompetitionName());
		System.out.println("CompetitionDto Name : " + competitionDto.getCompetitionDtoName());

		competition = null;
		System.out.println("--------- check if null -------------");
		System.out.println("Competition is null ? : " + (competition == null));


		competition = modelMapper.map(competitionDto, Competition.class);
		System.out.println("--------- converting back -------------");
		System.out.println("Competition Id : " + competition.getCompetitionId());
		System.out.println("CompetitionDto Id : " + competitionDto.getCompetitionDtoId());
		System.out.println("Competition Name : " + competition.getCompetitionName());
		System.out.println("CompetitionDto Name : " + competitionDto.getCompetitionDtoName());

		List<CompetitionDto> competitionDtoList = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			competitionDtoList.add(new CompetitionDto(i, "Name " + i));
		}
		System.out.println("--------- Testing multiple non existance dto's convertions -------------");
		for (CompetitionDto dto : competitionDtoList) {
			Competition competition1 = modelMapper.map(dto, Competition.class);
			System.out.println(competition1);
			System.out.println(dto);
		}

	}
}
