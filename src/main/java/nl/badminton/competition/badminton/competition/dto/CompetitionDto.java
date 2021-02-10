package nl.badminton.competition.badminton.competition.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Stefan van Tilburg <s.g.van.tilburg@st.hanze.nl>
 */
public class CompetitionDto {

    private static final int ID = -1;
    private long id;
    private String name;
    private List<PouleDto> poules;

    public CompetitionDto(long id, String name, List<PouleDto> poules) {
        this.id = id;
        this.name = name;
        this.poules = poules;
    }

    public CompetitionDto(String name, List<PouleDto> poules) {
        this(ID, name, poules);
    }

    public CompetitionDto(long id, String name) {
        this(id, name, new ArrayList<>());
    }

    public CompetitionDto(String name) {
        this(ID, name);
    }

    public CompetitionDto() {
        this("");
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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