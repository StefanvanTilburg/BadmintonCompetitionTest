package nl.badminton.competition.badminton.competition.model;

/**
 * @author Stefan van Tilburg <s.g.van.tilburg@st.hanze.nl>
 */
public class CompetitionDto {
    private long competitionDtoId;
    private String competitionDtoName;

    public CompetitionDto(long competitionDtoId, String competitionDtoName) {
        this.competitionDtoId = competitionDtoId;
        this.competitionDtoName = competitionDtoName;
    }

    public CompetitionDto() {
    }

    public long getCompetitionDtoId() {
        return competitionDtoId;
    }

    public void setCompetitionDtoId(long competitionDtoId) {
        this.competitionDtoId = competitionDtoId;
    }

    public String getCompetitionDtoName() {
        return competitionDtoName;
    }

    public void setCompetitionDtoName(String competitionDtoName) {
        this.competitionDtoName = competitionDtoName;
    }
}
