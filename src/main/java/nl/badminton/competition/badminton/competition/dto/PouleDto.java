package nl.badminton.competition.badminton.competition.dto;

/**
 * @author Stefan van Tilburg <s.g.van.tilburg@st.hanze.nl>
 */
public class PouleDto {

    private String name;

    public PouleDto(String name) {
        this.name = name;
    }

    public PouleDto() {
        this("");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Poule{name='" + name + '\'' + '}';
    }
}
