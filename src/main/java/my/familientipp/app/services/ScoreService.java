package my.familientipp.app.services;

import my.familientipp.app.DTO.ScoreDTO;
import my.familientipp.app.models.AppUser;

import java.util.List;

class ScoreService {

    private AppUserService appUserService;

    ScoreService(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    List<ScoreDTO> getSoresOfAllAppusers(){
        List<AppUser> appUsers = appUserService.findAll();

        return null;
    }

}
