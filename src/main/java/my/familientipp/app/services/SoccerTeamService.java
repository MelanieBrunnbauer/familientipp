package my.familientipp.app.services;

import my.familientipp.app.models.SoccerTeam;

import java.util.List;

public interface SoccerTeamService {

    List<SoccerTeam> findAll();

    SoccerTeam findByFIFACode(String fifaCode);
}
