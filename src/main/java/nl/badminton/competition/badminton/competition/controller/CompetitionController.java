package nl.badminton.competition.badminton.competition.controller;

import nl.badminton.competition.badminton.competition.model.Competition;
import nl.badminton.competition.badminton.competition.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author Stefan van Tilburg <s.g.van.tilburg@st.hanze.nl>
 */
@Controller
public class CompetitionController {

    private CompetitionService competitionServiceImplementation;

    @Autowired
    public CompetitionController(CompetitionService competitionServiceImplementation) {
        this.competitionServiceImplementation = competitionServiceImplementation;
    }

    @GetMapping("/competitions")
    protected String getIndex(Model model) {
        model.addAttribute("competitions", competitionServiceImplementation.getCompetitionDtos());
        model.addAttribute("competition", new Competition());
        return "competitions";
    }
}
