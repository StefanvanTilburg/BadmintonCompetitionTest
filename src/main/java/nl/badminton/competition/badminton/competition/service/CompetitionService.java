package nl.badminton.competition.badminton.competition.service;

import nl.badminton.competition.badminton.competition.model.Competition;
import nl.badminton.competition.badminton.competition.model.CompetitionDto;
import org.modelmapper.PropertyMap;

import java.util.List;

public interface CompetitionService {
    PropertyMap<Competition, CompetitionDto> getCompetitionMap();
    List<CompetitionDto> getCompetitionDtos();
    CompetitionDto convertToDto(Competition competition);
    Competition convertToEntity(CompetitionDto competitionDto);
}
