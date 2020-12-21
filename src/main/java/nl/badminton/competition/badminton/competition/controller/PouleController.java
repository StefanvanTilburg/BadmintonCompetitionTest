package nl.badminton.competition.badminton.competition.controller;

import nl.badminton.competition.badminton.competition.repository.PouleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Stefan van Tilburg <s.g.van.tilburg@st.hanze.nl>
 */
@Controller
public class PouleController {

    private PouleRepository pouleRepository;

    @Autowired
    public PouleController(PouleRepository pouleRepository) {
        this.pouleRepository = pouleRepository;
    }

    @GetMapping("/poules")
    protected String getPoules(Model model) {
        model.addAttribute("poules", pouleRepository.findAll());
        return "poules";
    }
}
