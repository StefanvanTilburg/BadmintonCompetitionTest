package nl.badminton.competition.badminton.competition.dto;

/**
 * @author Stefan van Tilburg <s.g.van.tilburg@st.hanze.nl>
 */
public class PouleDto {

    private long id;
    private String name;

    public PouleDto(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public PouleDto(String name) {
        this(-1, name);
    }

    public PouleDto() {
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

    @Override
    public String toString() {
        return "PouleDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
