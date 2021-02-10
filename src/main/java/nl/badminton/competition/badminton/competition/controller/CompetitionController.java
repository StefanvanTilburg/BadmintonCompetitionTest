package nl.badminton.competition.badminton.competition.controller;

import nl.badminton.competition.badminton.competition.model.Competition;
import nl.badminton.competition.badminton.competition.dto.CompetitionDto;
import nl.badminton.competition.badminton.competition.service.ServiceFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLDataException;

/**
 * @author Stefan van Tilburg <s.g.van.tilburg@st.hanze.nl>
 */
@Controller
public class CompetitionController {

    private final ServiceFunctions<Competition, CompetitionDto> competitionService;

    @Autowired
    public CompetitionController(ServiceFunctions<Competition, CompetitionDto> competitionService) {
        this.competitionService = competitionService;
    }

    @GetMapping("/competitions")
    protected String getIndex(Model model) {
        model.addAttribute("competitions", competitionService.getAll());
        model.addAttribute("competition", new Competition());
        return "competitions";
    }

    @GetMapping("/competitionsform")
    protected String getForm(Model model) {
        model.addAttribute("competition", new CompetitionDto());
        return "competitionForm";
    }

    @PostMapping("/competitionformsave")
    protected String saveForm(@ModelAttribute CompetitionDto competitionDto) throws SQLDataException {
        competitionService.saveEntity(competitionDto);
        return "redirect:/competitions";
    }
}
