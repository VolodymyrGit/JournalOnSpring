package volm.journal.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import volm.journal.dto.RegistrationDto;
import volm.journal.model.Group;
import volm.journal.model.User;
import volm.journal.repo.GroupRepo;
import volm.journal.service.UserService;

import java.util.List;


@RequiredArgsConstructor
@Controller
public class RegistrationController {

        private final UserService userService;
        private final GroupRepo groupRepo;


    @GetMapping("/registration")
    public String getRegistrationView(Model model) {

        List<Group> groups = groupRepo.findAll();

        model.addAttribute("groups", groups);

        return "registration";
    }


    @PostMapping("/registration")
    public String postRegistration(RegistrationDto registrationDto, Model model) {

        User savedUser = userService.registerUser(registrationDto);

        model.addAttribute("currentUser", savedUser);

        return "redirect:/cabinet";
    }
}