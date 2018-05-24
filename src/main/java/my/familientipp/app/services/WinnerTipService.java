package my.familientipp.app.services;

import my.familientipp.app.DTO.SoccerTeamDTO;
import my.familientipp.app.DTO.WinnerTipDTO;

import java.util.List;

public interface WinnerTipService {
    List<WinnerTipDTO> getAllWinnertips();

    List<SoccerTeamDTO> getAllSoccerTeams();

    void persistEdited(WinnerTipDTO winnertip);
}
