package nl.badminton.competition.badminton.competition.controller;

import nl.badminton.competition.badminton.competition.model.Competition;
import nl.badminton.competition.badminton.competition.repository.CompetitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Stefan van Tilburg <s.g.van.tilburg@st.hanze.nl>
 */
@Controller
public class CompetitionController {

    private CompetitionRepository competitionRepository;

    @Autowired
    public CompetitionController(CompetitionRepository competitionRepository) {
        this.competitionRepository = competitionRepository;
    }

    @GetMapping("/competitions")
    protected String getIndex(Model model) {
        model.addAttribute("competitions", competitionRepository.findAll());
        model.addAttribute("competition", new Competition());
        return "competitions";
    }

    @PostMapping("/competitions/add")
    protected String addCompetition(@ModelAttribute Competition competition) {
        try {
            competitionRepository.save(competition);
        } catch (DataIntegrityViolationException exception) {
            System.out.println("Duplicate entry, so it will not be added again!");
        }
        return "redirect:/competitions";
    }
}
