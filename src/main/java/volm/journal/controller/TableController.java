package volm.journal.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import volm.journal.exceptions.EntityNotFoundException;
import volm.journal.security.Role;
import volm.journal.model.Group;
import volm.journal.model.Homework;
import volm.journal.model.Lesson;
import volm.journal.model.User;
import volm.journal.repo.LessonRepo;
import volm.journal.repo.UserRepo;
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


    @PreAuthorize("hasAnyAuthority('ADMIN', 'STUDENT', 'TEACHER')")
    @GetMapping("/table")
    public String getTableView(@AuthenticationPrincipal User currentUser,
                               @RequestParam(name = "group", required = false) Group selectedGroup,
                               Model model) {


        Group group = null;

        if(selectedGroup != null) {
            group = selectedGroup;
        } else {
            group = currentUser.getGroup();
        }

//        Group group = currentUser.getGroup();

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


    @PreAuthorize("hasAnyAuthority('ADMIN', 'STUDENT', 'TEACHER')")
    @PostMapping("/table")
    public String doPostTable(@RequestParam(name = "group", required = false) Group group) {

        lessonService.addLesson(group);

        return ("redirect:/table?group=" + group.getId());
    }
}