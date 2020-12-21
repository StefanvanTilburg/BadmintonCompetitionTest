package nl.badminton.competition.badminton.competition.controller;

import nl.badminton.competition.badminton.competition.repository.CompetitionRepository;
import nl.badminton.competition.badminton.competition.repository.PouleRepository;
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
    private PouleRepository pouleRepository;

    @Autowired
    public CompetitionController(CompetitionRepository competitionRepository, PouleRepository pouleRepository) {
        this.competitionRepository = competitionRepository;
        this.pouleRepository = pouleRepository;
    }

    @GetMapping("/")
    protected String getIndex(Model model) {
        model.addAttribute("competitions", competitionRepository.findAll());
        model.addAttribute("poules", pouleRepository.findAll());
        return "index";
    }
}
