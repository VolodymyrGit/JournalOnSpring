package volm.journal.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import volm.journal.exceptions.EntityNotFoundException;
import volm.journal.model.Group;
import volm.journal.model.Homework;
import volm.journal.model.Lesson;
import volm.journal.model.User;
import volm.journal.repo.GroupRepo;
import volm.journal.repo.HomeworkRepo;
import volm.journal.repo.LessonRepo;
import volm.journal.repo.UserRepo;
import volm.journal.security.Role;

import java.util.Arrays;
import java.util.List;


@PreAuthorize("hasAuthority('ADMIN')")
@RequiredArgsConstructor
@Controller
public class AdminPageController {

    private final HomeworkRepo homeworkRepo;
    private final LessonRepo lessonRepo;
    private final UserRepo userRepo;
    private final GroupRepo groupRepo;


    @GetMapping("/admin-page")
    public String getAdminPageView(@AuthenticationPrincipal User admin, Model model) {

        List<Group> groups = groupRepo.findAll();

        List<User> users = userRepo.findAllByRolesIsNotContaining(Role.ADMIN);

        List<Role> roles = Arrays.asList(Role.values());

        model.addAttribute("users", users);
        model.addAttribute("groups", groups);
        model.addAttribute("admin", admin);
        model.addAttribute("roles", roles);

        return "adminPage";
    }


    @PostMapping("/set-role")
    public String doPostSetRole(String role, Long id) {

        User user = userRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(User.class));

        if (!user.getRoles().contains(Role.valueOf(role))) {

            user.getRoles().add(Role.valueOf(role));
            userRepo.save(user);
        }
        return "redirect:/admin-page";
    }


    @PostMapping("/set-group")
    public String doPostSetGroup(Long userId, Group group) {

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(User.class));

            user.setGroup(group);
            userRepo.save(user);

            List<Lesson> lessons = lessonRepo.findAllByGroupEquals(group);
            List<Homework> homeworks = homeworkRepo.findAllByStudentEquals(user);

            if (user.getRoles().contains(Role.STUDENT) && !lessons.isEmpty() && homeworks.isEmpty()) {

                lessons.forEach(l -> homeworkRepo.save(new Homework(l, user)));
            }
        return "redirect:/admin-page";
    }
}
