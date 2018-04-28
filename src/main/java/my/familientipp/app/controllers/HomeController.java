package my.familientipp.app.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @RequestMapping("/")
    public String index() {
        return "Hier entsteht unsere neue Tipp-App für die WM 2018!";
    }

}
