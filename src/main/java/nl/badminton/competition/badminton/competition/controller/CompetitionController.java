package nl.badminton.competition.badminton.competition.controller;

import nl.badminton.competition.badminton.competition.repository.CompetitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

    @GetMapping("/")
    protected String getIndex(Model model) {
        model.addAttribute("competitions", competitionRepository.findAll());
        return "index";
    }
}
