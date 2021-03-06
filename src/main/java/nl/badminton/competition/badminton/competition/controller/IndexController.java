package nl.badminton.competition.badminton.competition.controller;

import nl.badminton.competition.badminton.competition.dto.CompetitionDto;
import nl.badminton.competition.badminton.competition.dto.PouleDto;
import nl.badminton.competition.badminton.competition.model.Competition;
import nl.badminton.competition.badminton.competition.model.Poule;
import nl.badminton.competition.badminton.competition.service.ServiceFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Stefan van Tilburg <s.g.van.tilburg@st.hanze.nl>
 */
@Controller
public class IndexController {

    private final ServiceFunctions<Competition, CompetitionDto> competitionService;
    private final ServiceFunctions<Poule, PouleDto> pouleService;

    @Autowired
    public IndexController(ServiceFunctions<Competition, CompetitionDto> competitionService,
                           ServiceFunctions<Poule, PouleDto> pouleService) {
        this.competitionService = competitionService;
        this.pouleService = pouleService;
    }

    @GetMapping("/")
    protected String getIndex(Model model) {
        model.addAttribute("competitions", competitionService.getAll());
        model.addAttribute("poules", pouleService.getAll());
        return "index";
    }
}
