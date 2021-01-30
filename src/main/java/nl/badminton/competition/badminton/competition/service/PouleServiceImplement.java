package nl.badminton.competition.badminton.competition.service;

import nl.badminton.competition.badminton.competition.model.Poule;
import nl.badminton.competition.badminton.competition.model.PouleDto;

/**
 * @author Stefan van Tilburg <s.g.van.tilburg@st.hanze.nl>
 */
public class PouleServiceImplement implements SuperConverter<PouleDto, Poule> {

    @Override
    public Poule apply(final PouleDto pouleDto) {
        final Poule poule = new Poule();
        poule.setPouleName(pouleDto.getPouleDtoName());
        return poule;
    }
}
