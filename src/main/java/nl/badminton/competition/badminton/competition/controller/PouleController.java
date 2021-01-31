package nl.badminton.competition.badminton.competition.controller;

import nl.badminton.competition.badminton.competition.dto.PouleDto;
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
public class PouleController {

    private final ServiceFunctions<Poule, PouleDto> pouleService;

    @Autowired
    public PouleController(ServiceFunctions<Poule, PouleDto> pouleService) {
        this.pouleService = pouleService;
    }

    @GetMapping("/poules")
    protected String getPoules(Model model) {
        model.addAttribute("allPoules", pouleService.getAll());
        return "poules";
    }
}
