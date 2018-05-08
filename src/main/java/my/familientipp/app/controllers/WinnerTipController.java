package my.familientipp.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WinnerTipController {

    @RequestMapping("/siegertipp")
    public String winnertip(){
        return "winnertip";
    }

}
