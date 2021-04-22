package volm.journal.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import volm.journal.model.Homework;
import volm.journal.model.User;
import volm.journal.service.HomeworkService;


@RequiredArgsConstructor
@Controller
public class HomeworkController {

    private final HomeworkService homeworkService;


    @GetMapping("/homework")
    public String getHomeworkView(@AuthenticationPrincipal User currentUser,
                                  Homework homework,
                                  Model model) {
        if(currentUser.getId() == homework.getStudent().getId()) {

            model.addAttribute("homework", homework);
        }
        return "hwForm";
    }


    @PostMapping("/homework")
    public String doPostHw(@AuthenticationPrincipal User currentUser,
                           Long hwId,
                           String hwDescription) {

        homeworkService.changeDescription(currentUser, hwId, hwDescription);

        return "redirect:/table";
    }
}