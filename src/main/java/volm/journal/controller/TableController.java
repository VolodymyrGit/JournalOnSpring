package volm.journal.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import volm.journal.enums.Role;
import volm.journal.model.Group;
import volm.journal.model.Homework;
import volm.journal.model.Lesson;
import volm.journal.model.User;
import volm.journal.repo.LessonRepo;
import volm.journal.repo.UserRepo;
import volm.journal.service.HomeworkService;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;


@RequiredArgsConstructor
@Controller
public class TableController {

    private final UserRepo userRepo;
    private final LessonRepo lessonRepo;
    private final HomeworkService homeworkService;

    @GetMapping("/table")
    public String getTableView(Model model) {

        User currentUser = userRepo.findById(4L)
                .orElseThrow(() -> new NoSuchElementException());

        Group group = currentUser.getGroup();

        List<User> teachers = userRepo.findAllByGroupEqualsAndRoleEquals(group, Role.TEACHER);

        List<User> students = userRepo.findAllByGroupEqualsAndRoleEquals(group, Role.STUDENT);

        List<Lesson> lessons = lessonRepo.findAllByGroupEquals(group);

        Map<User, List<Homework>> homeworks = homeworkService.mapHomeworks(lessons);

        model.addAttribute("currentUser", currentUser);
        model.addAttribute("teachers", teachers);
        model.addAttribute("students", students);
        model.addAttribute("lessons", lessons);
        model.addAttribute("homeworks", homeworks);
        model.addAttribute("group", group);

        return "table";
    }
}