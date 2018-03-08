package beans.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
@SuppressWarnings("unused")
public class UIController {

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "templates/index.html";
    }
}
