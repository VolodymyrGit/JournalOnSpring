package volm.journal.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import volm.journal.dto.ChangeUserPasswordDto;
import volm.journal.dto.ChangeUserNameEmailPhoneDto;
import volm.journal.model.User;
import volm.journal.repo.UserRepo;
import volm.journal.service.UserService;
import volm.journal.util.ValidationUtil;


@RequiredArgsConstructor
@Controller
public class ChangeUserInfoController {

    private final UserService userService;


    @PreAuthorize("hasAnyAuthority('ADMIN', 'STUDENT', 'TEACHER')")
    @GetMapping("/change-info")
    public String getChangeInfoView(@AuthenticationPrincipal User currentUser,
                                    Model model) {

        model.addAttribute("currentUser", currentUser);

        return "changeInfo";
    }


    @PreAuthorize("hasAnyAuthority('ADMIN', 'STUDENT', 'TEACHER')")
    @PostMapping("/change-info")
    public String postChangeInfo(@AuthenticationPrincipal User currentUser,
                                 ChangeUserNameEmailPhoneDto nameEmailPhoneDto,
                                 ChangeUserPasswordDto userPasswordDto,
                                 Model model) {

        if(ValidationUtil.checkWhetherParamsNotNull(nameEmailPhoneDto, currentUser)) {

            if (!ValidationUtil.validateEmailByRegex(nameEmailPhoneDto.getEmail())) {

                model.addAttribute("emailErrorMessage", "Not correct format of email");
            } else if (!userService.checkIfEmailNotExist(nameEmailPhoneDto.getEmail())
                    && !currentUser.getEmail().equals(nameEmailPhoneDto.getEmail())) {

                model.addAttribute("emailErrorMessage", "User with this email is already registered");
            } else {
                userService.changeUserNameEmailPhone(nameEmailPhoneDto, currentUser);
            }
        }

        if(userPasswordDto.getPassword() != null && userPasswordDto.getNpassword() != null) {

            if (userService.compareUserPassWithEnteredCurrentPass(userPasswordDto.getPassword(), currentUser)) {

                userService.saveEncodedPassword(currentUser, userPasswordDto.getNpassword());
            } else {
                model.addAttribute("passwordErrorMessage", "You entered wrong current password");
            }
        }
        model.addAttribute("currentUser", currentUser);

        return "changeInfo";
    }
}