package nl.badminton.competition.badminton.competition.model;

import javax.persistence.*;

/**
 * @author Stefan van Tilburg <s.g.van.tilburg@st.hanze.nl>
 */
@Entity
public class Poule {

    private static final String POULE_NAME = "Default poule name";
    private static final int POULE_ID = -1;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long pouleId;

    @Column(unique = true)
    private String pouleName;

    @ManyToOne
    private Competition competition;

    public Poule(long pouleId, String pouleName, Competition competition) {
        this.pouleId = pouleId;
        this.pouleName = pouleName;
        this.competition = competition;
    }

    public Poule(String pouleName, Competition competition) {
        this(POULE_ID, pouleName, competition);
    }

    public Poule(String pouleName) {
        this(pouleName, new Competition());
    }

    public Poule() {
        this(POULE_NAME);
    }

    public long getPouleId() {
        return pouleId;
    }

    public void setPouleId(long pouleId) {
        this.pouleId = pouleId;
    }

    public String getPouleName() {
        return pouleName;
    }

    public void setPouleName(String pouleName) {
        this.pouleName = pouleName;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    @Override
    public String toString() {
        return "Poule{" +
                "pouleId=" + pouleId +
                ", pouleName='" + pouleName + "'}}";
    }
}
