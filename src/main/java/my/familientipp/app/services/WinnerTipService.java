package my.familientipp.app.services;

import my.familientipp.app.DTO.SoccerTeamDTO;
import my.familientipp.app.DTO.WinnerTipDTO;
import my.familientipp.app.models.AppUser;
import my.familientipp.app.models.SoccerTeam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WinnerTipService {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private SoccerTeamService soccerTeamService;

    WinnerTipService(AppUserService appUserService, SoccerTeamService soccerTeamService) {
        this.appUserService = appUserService;
        this.soccerTeamService = soccerTeamService;
    }

    public List<WinnerTipDTO> getAllWinnertips() {
        List<WinnerTipDTO> winnerTipDTOS = new ArrayList<>();

        List<AppUser> appUsers = appUserService.findAll();
        appUsers.forEach(appUser -> winnerTipDTOS.add(
                        new WinnerTipDTO(
                                appUser.getFirstName(),
                                appUser.getWinnertip().map(SoccerTeam::getFifaCode).orElse("leer"))
                ));
        return winnerTipDTOS;
    }


    public List<SoccerTeamDTO> getAllSoccerTeams() {
        List<SoccerTeamDTO> soccerTeamDTOS = new ArrayList<>();

        List<SoccerTeam> soccerTeams = soccerTeamService.findAll();
        soccerTeams.forEach(team -> soccerTeamDTOS.add(
                new SoccerTeamDTO(team.getFifaCode(),team.getCountry())
        ));

        return soccerTeamDTOS;
    }
}
