package volm.journal.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import volm.journal.dto.ChangeUserPasswordDto;
import volm.journal.dto.ChangeUserInfoDto;
import volm.journal.model.User;
import volm.journal.service.UserService;
import volm.journal.util.ValidationUtil;


@RequiredArgsConstructor
@Controller
public class ChangeUserInfoController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;


    @GetMapping("/change-info")
    public String getChangeInfoView(@AuthenticationPrincipal User currentUser,
                                    Model model) {

        model.addAttribute("currentUser", currentUser);
        return "changeInfo";
    }


    @PostMapping("/change-info")
    public String postChangeInfo(@AuthenticationPrincipal User currentUser,
                                 ChangeUserInfoDto userInfoDto,
                                 Model model) {
        if (!ValidationUtil.validateEmailByRegex(userInfoDto.getEmail())) {

            model.addAttribute("emailErrorMessage", "Not correct format of email");

        } else if (userService.checkIfEmailAlreadyExist(userInfoDto.getEmail())
                && !currentUser.getEmail().equals(userInfoDto.getEmail())) {

            model.addAttribute("emailErrorMessage", "User with this email is already registered");
        } else {
            userService.changeUserInfo(userInfoDto, currentUser);
        }
        model.addAttribute("currentUser", currentUser);

        return "changeInfo";
    }


    @PostMapping("/change-password")
    public String postChangePassword(@AuthenticationPrincipal User currentUser,
                                     ChangeUserPasswordDto changePasswordDto,
                                     Model model) {
        if (passwordEncoder.matches(changePasswordDto.getPassword(), currentUser.getPassword())) {

            userService.saveEncodedPassword(currentUser, changePasswordDto.getNpassword());
        } else {
            model.addAttribute("passwordErrorMessage", "You entered wrong current password");
        }
        model.addAttribute("currentUser", currentUser);

        return "changeInfo";
    }
}