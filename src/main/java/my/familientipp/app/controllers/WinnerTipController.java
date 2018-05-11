package my.familientipp.app.controllers;

import my.familientipp.app.models.AppUser;
import my.familientipp.app.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class WinnerTipController {

    @Autowired
    private AppUserService appUserService;

    WinnerTipController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @RequestMapping("/siegertipp")
    public String winnertip(Model model){

        List<AppUser> allAppUsers = appUserService.findAll();
        model.addAttribute("appUsers", allAppUsers);
        return "winnertip";
    }

}
