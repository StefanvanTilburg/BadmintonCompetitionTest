package nl.badminton.competition.badminton.competition.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Stefan van Tilburg <s.g.van.tilburg@st.hanze.nl>
 */
@Entity
public class Competition {

    private static final String DEFAULT_COMPETITION_NAME = "Default competition name";
    private static final long COMPETITION_ID = -1;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long competitionId;

    @Column(unique = true)
    private String competitionName;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "competition", cascade = CascadeType.ALL)
    private Set<Poule> poules;

    public Competition(long competitionId, String competitionName) {
        this.competitionId = competitionId;
        this.competitionName = competitionName;
        this.poules = new HashSet<>();
    }

    public Competition(String competitionName) {
        this(COMPETITION_ID, competitionName);
    }

    public Competition() {
        this(DEFAULT_COMPETITION_NAME);
    }

    public long getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(long competitionId) {
        this.competitionId = competitionId;
    }

    public String getCompetitionName() {
        return competitionName;
    }

    public void setCompetitionName(String competitionName) {
        this.competitionName = competitionName;
    }

    public Set<Poule> getPoules() {
        return poules;
    }

    public void setPoules(Set<Poule> poules) {
        this.poules = poules;
    }

    @Override
    public String toString() {
        String message = "Competition{competitionId="+ competitionId + ", competitionName='" + competitionName + '\'' +
                ", poules = {";
        for (Poule poule : poules) {
            System.out.println(poule);
            message = message.concat(poule.toString() + ", ");
        }
        return message + "}}";
    }
}
