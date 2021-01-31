package nl.badminton.competition.badminton.competition.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Stefan van Tilburg <s.g.van.tilburg@st.hanze.nl>
 */
public class CompetitionDto {

    private String name;
    private List<PouleDto> poules;

    public CompetitionDto(String name, List<PouleDto> poules) {
        this.name = name;
        this.poules = poules;
    }

    public CompetitionDto(String name) {
        this(name, new ArrayList<>());
    }

    public CompetitionDto() {
        this("");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PouleDto> getPoules() {
        return poules;
    }

    public void setPoules(List<PouleDto> poules) {
        this.poules = poules;
    }
}