//package volm.journal.controller;
//
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import volm.journal.dto.ChangeInfoDto;
//import volm.journal.model.User;
//import volm.journal.repo.UserRepo;
//import volm.journal.service.UserService;
//import volm.journal.util.SecurityUtil;
//
//import java.util.NoSuchElementException;
//
//
//@RequiredArgsConstructor
//@Controller
//public class ChangeInfoDataController {
//
//    private final UserRepo userRepo;
//    private final UserService userService;
//
//
//    @GetMapping("/change-info")
//    public String getChangeInfoView(Model model) {
//
//        User currentUser = userRepo.findById(4L)
//                .orElseThrow(() -> new NoSuchElementException());
//
//        model.addAttribute("currentUser", currentUser);
//
//        return "changeInfo";
//    }
//
//
//    @PostMapping("/change-info")
//    public String postChangeInfo(ChangeInfoDto changeInfoDto, Model model) {
//
//        User currentUser = userRepo.findById(4L)
//                .orElseThrow(() -> new NoSuchElementException());
//
//        userService.updateUserInfo(changeInfoDto, currentUser);
//
//        String hashedPassword = SecurityUtil.getSecurePassword(changeInfoDto.getPassword(), currentUser.getSalt());
//
//        if (!currentUser.getPassword().equals(hashedPassword)) {
//
//            String errorMessage = "You entered wrong current password";
//
//            model.addAttribute("errorMessage", errorMessage);
//
//            return "changeInfo";
//        }
//        return "redirect:/cabinet";
//    }
//}