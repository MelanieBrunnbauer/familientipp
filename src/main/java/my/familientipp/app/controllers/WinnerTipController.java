package my.familientipp.app.controllers;

import my.familientipp.app.DTO.SoccerTeamDTO;
import my.familientipp.app.DTO.WinnerTipDTO;
import my.familientipp.app.services.WinnerTipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class WinnerTipController {

    @Autowired
    private WinnerTipService winnertipService;

    WinnerTipController(WinnerTipService winnertipService) {
        this.winnertipService = winnertipService;
    }

    @GetMapping("/siegertipp")
    public String winnertips(Model model) {

        List<WinnerTipDTO> allWinnertips = winnertipService.getAllWinnertips();
        model.addAttribute("winnertips", allWinnertips);
        return "winnertip";
    }

    @GetMapping("/siegertipp/edit/{appUser}")
    public String editWinnertip(Model model, @PathVariable("appUser") String appUserFirstName) {
        List<SoccerTeamDTO> allSoccerTeams = winnertipService.getAllSoccerTeams();
        model.addAttribute("soccerTeams", allSoccerTeams);
        model.addAttribute("appUser", appUserFirstName);
        model.addAttribute("winnertip", new WinnerTipDTO(appUserFirstName));
        return "editWinnertip";
    }

    @PostMapping("/siegertipp/edit/{appUser}")
    public String submitWinnertip(@ModelAttribute("winnertip") WinnerTipDTO winnertip, @PathVariable("appUser") String appUserFirstName) {
        System.out.println(winnertip.getFifaCodeOfSoccerTeam());
        System.out.println(winnertip.getFirstNameOfAppUser());
        return "redirect:/siegertipp";
    }
}
