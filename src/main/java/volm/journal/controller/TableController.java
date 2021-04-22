package volm.journal.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import volm.journal.model.Group;
import volm.journal.model.Homework;
import volm.journal.model.Lesson;
import volm.journal.model.User;
import volm.journal.repo.LessonRepo;
import volm.journal.repo.UserRepo;
import volm.journal.security.Role;
import volm.journal.service.HomeworkService;
import volm.journal.service.LessonService;

import java.util.List;
import java.util.Map;


@RequiredArgsConstructor
@Controller
public class TableController {

    private final UserRepo userRepo;
    private final LessonRepo lessonRepo;
    private final HomeworkService homeworkService;
    private final LessonService lessonService;


    @GetMapping("/table")
    public String getTableView(@AuthenticationPrincipal User currentUser, Model model) {

        Group group = currentUser.getGroup();

        List<User> teachers = userRepo.findAllByGroupEqualsAndRolesContaining(group, Role.TEACHER);

        List<User> students = userRepo.findAllByGroupEqualsAndRolesContaining(group, Role.STUDENT);

        List<Lesson> lessons = lessonRepo.findAllByGroupEquals(group);

        Map<User, List<Homework>> homeworks = homeworkService.findHomeworksForEachStudent(students);

        model.addAttribute("currentUser", currentUser);
        model.addAttribute("teachers", teachers);
        model.addAttribute("students", students);
        model.addAttribute("lessons", lessons);
        model.addAttribute("homeworks", homeworks);
        model.addAttribute("group", group);

        return "table";
    }


    @GetMapping("/admin-table")
    public String getAdminTableView(@AuthenticationPrincipal User currentUser, Group selectedGroup) {

        currentUser.setGroup(selectedGroup);
        userRepo.save(currentUser);

        return "redirect:/table";
    }


    @PostMapping("/table-add-lesson")
    public String doPostTable(@AuthenticationPrincipal User currentUser) {

        Group group = currentUser.getGroup();
        lessonService.addLesson(group);

        return ("redirect:/table");
    }
}