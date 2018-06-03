package my.familientipp.app.controllers;

import my.familientipp.app.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class GameController {

    @Autowired
    private GameService gameService;

    GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/spiele")
    public String games(Model model){
        model.addAttribute("allGames", gameService.findAll());
        return "games";
    }

}
