package my.familientipp.app.controllers;

import my.familientipp.app.DTO.SoccerTeamDTO;
import my.familientipp.app.DTO.WinnerTipDTO;
import my.familientipp.app.services.WinnerTipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class WinnerTipController {

    @Autowired
    private WinnerTipService winnertipService;

    WinnerTipController(WinnerTipService winnertipService) {
        this.winnertipService = winnertipService;
    }

    @RequestMapping("/siegertipp")
    public String winnertip(Model model){

        List<WinnerTipDTO> allWinnertips = winnertipService.getAllWinnertips();
        List<SoccerTeamDTO> allSoccerTeams = winnertipService.getAllSoccerTeams();
        model.addAttribute("winnertips", allWinnertips);
        model.addAttribute("soccerTeams",allSoccerTeams);
        return "winnertip";
    }

}
