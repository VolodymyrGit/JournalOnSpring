package volm.journal.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import volm.journal.model.User;
import volm.journal.repo.UserRepo;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Controller
public class CabinetController {

    private final UserRepo userRepo;


    @GetMapping("/cabinet")
    public String getCabinetView(Model model) {

        User currentUser = userRepo.findById(4L)
                .orElseThrow(() -> new NoSuchElementException());

        model.addAttribute("currentUser", currentUser);

        return "cabinet";
    }
}