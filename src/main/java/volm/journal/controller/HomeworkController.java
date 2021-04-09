package volm.journal.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import volm.journal.model.Homework;
import volm.journal.repo.HomeworkRepo;
import volm.journal.service.HomeworkService;

import java.util.NoSuchElementException;


@RequiredArgsConstructor
@Controller
public class HomeworkController {

    private final HomeworkRepo homeworkRepo;
    private final HomeworkService homeworkService;


    @GetMapping("/hw")
    public String getHwView(Model model) {
        Long hwId = 11L;

        Homework homework = homeworkRepo.findById(hwId)
                .orElseThrow(() -> new NoSuchElementException());

        model.addAttribute("hwId", hwId);

        model.addAttribute("description", homework.getHwDescription());

        return "hwForm";
    }


    @PostMapping("/hw")
    public String postHw(String description, Long hwId) {

        homeworkService.setNewDescription(description, hwId);

        return "redirect:table";
    }
}