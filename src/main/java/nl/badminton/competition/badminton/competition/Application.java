package nl.badminton.competition.badminton.competition;

import nl.badminton.competition.badminton.competition.model.Competition;
import nl.badminton.competition.badminton.competition.model.Poule;
import nl.badminton.competition.badminton.competition.repository.CompetitionRepository;
import nl.badminton.competition.badminton.competition.repository.PouleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
	}
}
