package volm.journal.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import volm.journal.model.User;
import volm.journal.repo.UserRepo;
import volm.journal.service.UserService;

import java.util.Optional;


@RequiredArgsConstructor
@Controller
public class LoginController {

    private final UserService userService;
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;


    @GetMapping("/login")
    public String getLoginView(@RequestParam(required = false) String error, Model model) {

//        if(error != null) {
//            String errorMessage = "Wrong login or password";
//            model.addAttribute("errorMessage", errorMessage);
//        }
        return "login";
    }


    @PostMapping("/login")
    public String postLogin(@RequestParam(name = "email") String login, String password, Model model) {

                Optional<User> userFromDB = userRepo.findByEmailEquals(login);

                String securePass = passwordEncoder.encode(password);

        if (userFromDB.isPresent() && securePass.equals(userFromDB.get().getPassword())) {

            return "redirect:/cabinet";

        } else {
            String errorMessage = "Wrong login or password";
            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("previousLogin", login);
            return "login";
        }
    }
}