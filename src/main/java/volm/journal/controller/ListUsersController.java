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
import volm.journal.model.Group;
import volm.journal.model.User;
import volm.journal.repo.GroupRepo;
import volm.journal.repo.UserRepo;
import volm.journal.security.Role;

import java.util.Arrays;
import java.util.List;


@RequiredArgsConstructor
@Controller
public class ListUsersController {

    private final UserRepo userRepo;
    private final GroupRepo groupRepo;


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/list-users")
    public String getListUsersView(@AuthenticationPrincipal User admin, Model model) {

        List<Group> groups = groupRepo.findAll();

        List<User> users = userRepo.findAllByRolesIsNotContaining(Role.ADMIN);

        List<Role> roles = Arrays.asList(Role.values());

        model.addAttribute("users", users);
        model.addAttribute("groups", groups);
        model.addAttribute("admin", admin);
        model.addAttribute("roles", roles);

        return "listUsers";
    }


    @PostMapping("/list-users")
    public String doPostListUsers(@RequestParam(required = false) String role,
                                  @RequestParam(required = false) Long groupId,
                                  Long id) {

        User user = userRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(User.class));

        if(role != null && !user.getRoles().contains(Role.valueOf(role))) {

                user.getRoles().add(Role.valueOf(role));
                userRepo.save(user);
        }

        if(groupId != null) {
            Group group = groupRepo.findById(groupId)
                    .orElseThrow(() -> new EntityNotFoundException(Group.class));

            user.setGroup(group);
            userRepo.save(user);
        }

        return "redirect:/list-users";
    }
}
