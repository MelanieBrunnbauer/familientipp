package my.familientipp.app.services;

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

    public WinnerTipService(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    public List<WinnerTipDTO> getAllWinnertips() {

        List<AppUser> appUsers = appUserService.findAll();
        List<WinnerTipDTO> winnerTipDTOS = new ArrayList<>();
        appUsers.stream()
                .forEach(appUser -> winnerTipDTOS.add(
                        new WinnerTipDTO(
                                appUser.getFirstName(),
                                appUser.getWinnertip().map(SoccerTeam::getFifaCode).orElse("leer"))
                ));
        return winnerTipDTOS;
    }
}
