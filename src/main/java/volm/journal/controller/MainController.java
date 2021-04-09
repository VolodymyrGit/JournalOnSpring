package volm.journal.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/test")
public class MainController {

    @GetMapping("/main")
    @RequestMapping(method = RequestMethod.GET, path = "/main")
    public String main(Model model) {
        model.addAttribute("info", "some info");
        model.addAttribute("name", "Journal");

        return "main";
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("message", "Hello World");

        return "index";
    }
}