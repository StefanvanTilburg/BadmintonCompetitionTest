package nl.badminton.competition.badminton.competition.model;

/**
 * @author Stefan van Tilburg <s.g.van.tilburg@st.hanze.nl>
 */
public class PouleDto {

    private long pouleDtoId;
    private String pouleDtoName;

    public PouleDto(long pouleDtoId, String pouleDtoName) {
        this.pouleDtoId = pouleDtoId;
        this.pouleDtoName = pouleDtoName;
    }

    public PouleDto() {
    }

    public long getPouleDtoId() {
        return pouleDtoId;
    }

    public void setPouleDtoId(long pouleDtoId) {
        this.pouleDtoId = pouleDtoId;
    }

    public String getPouleDtoName() {
        return pouleDtoName;
    }

    public void setPouleDtoName(String pouleDtoName) {
        this.pouleDtoName = pouleDtoName;
    }

    @Override
    public String toString() {
        return "PouleDto{" +
                "pouleDtoId=" + pouleDtoId +
                ", pouleDtoName='" + pouleDtoName + '\'' +
                '}';
    }
}
