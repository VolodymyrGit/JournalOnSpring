package volm.journal.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import volm.journal.dto.RegistrationDto;
import volm.journal.exceptions.EntityNotFoundException;
import volm.journal.model.User;
import volm.journal.repo.UserRepo;
import volm.journal.service.UserService;
import volm.journal.util.ValidationUtil;


@RequiredArgsConstructor
@RequestMapping("/registration")
@Controller
public class RegistrationController {

    private final UserService userService;
    private final UserRepo userRepo;


    @GetMapping
    public String getRegistrationView() {

        return "registration";
    }


    @PostMapping("/validate-email")
    public String postEmailRegistration(RegistrationDto registrationDto, Model model) {

        if (!ValidationUtil.validateEmailByRegex(registrationDto.getEmail())) {

            model.addAttribute("regexErrorMessage", "Not correct format of email");

            return "registration";

        } else if (userService.checkIfEmailAlreadyExist(registrationDto.getEmail())) {

            model.addAttribute("alreadyExistErrorMessage", "User with this email is already registered");

            return "registration";

        } else {
            userService.registerUser(registrationDto);
        }
        return "redirect:/login";
    }


    @GetMapping("/confirm")
    public String doGetRegistrationConfirm(String code) {

        User user = userRepo.findBySecurityCodeEquals(code)
                .orElseThrow(() -> new EntityNotFoundException(User.class));

        user.setSecurityCode(null);

        userRepo.save(user);

        return "redirect:/login";
    }
}