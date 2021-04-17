package volm.journal.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import volm.journal.dto.ChangeUserInfoDto;
import volm.journal.model.User;
import volm.journal.repo.UserRepo;
import volm.journal.service.UserService;


@RequiredArgsConstructor
@Controller
public class ChangeUserInfoController {

    private final UserRepo userRepo;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;


    @PreAuthorize("hasAnyAuthority('ADMIN', 'STUDENT', 'TEACHER')")
    @GetMapping("/change-info")
    public String getChangeInfoView(@AuthenticationPrincipal User currentUser,
                                    @RequestParam(required = false) String emailErrorMessage,
                                    @RequestParam(required = false) String passwordErrorMessage,
                                    Model model) {

        model.addAttribute("currentUser", currentUser);
        model.addAttribute("emailErrorMessage", emailErrorMessage);
        model.addAttribute("passwordErrorMessage", passwordErrorMessage);

        return "changeInfo";
    }


    @PreAuthorize("hasAnyAuthority('ADMIN', 'STUDENT', 'TEACHER')")
    @PostMapping("/change-info")
    public String postChangeInfo(@AuthenticationPrincipal User currentUser,
                                 ChangeUserInfoDto changeUserInfoDto,
                                 Model model) {

        userService.changeUserNameAndPhone(changeUserInfoDto, currentUser);

        String emailErrorMessage = userService.changeUserEmail(changeUserInfoDto, currentUser);
        model.addAttribute("emailErrorMessage", emailErrorMessage);

        String passwordErrorMessage = userService.changeUserPassword(changeUserInfoDto, currentUser);
        model.addAttribute("passwordErrorMessage", passwordErrorMessage);

        return "redirect:/change-info";
    }
}