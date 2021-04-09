package volm.journal.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import volm.journal.model.User;
import volm.journal.repo.UserRepo;
import volm.journal.service.UserService;

import java.util.Optional;


@RequiredArgsConstructor
@Controller
public class LoginController {

    private final UserService userService;
    private final UserRepo userRepo;


    @GetMapping("/login")
    public String getLoginView() {
        return "login";
    }


    @PostMapping("/login")
    public String postLogin(String login, String password, Model model) {

        if (userService.authorized(login, password)) {

            User user = userRepo.findByEmailEquals(login)
                    .orElseThrow(() -> new NullPointerException());

            model.addAttribute("userId", user.getId());

            return "redirect:/cabinet";

        } else {
            String errorMessage = "Wrong login or password";
            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("previousLogin", login);
            return "login";
        }
    }
}