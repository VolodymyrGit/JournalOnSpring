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

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Controller
public class CabinetController {

    private final UserRepo userRepo;
    private final GroupRepo groupRepo;


    @PreAuthorize("hasAnyAuthority('ADMIN', 'STUDENT', 'TEACHER')")
    @GetMapping("/cabinet")
    public String getCabinetView(@AuthenticationPrincipal User currentUser, Model model) {

        List<Group> groups = groupRepo.findAll();

        model.addAttribute("currentUser", currentUser);
        model.addAttribute("groups", groups);

        return "cabinet";
    }


    @PreAuthorize("hasAnyAuthority('ADMIN', 'STUDENT', 'TEACHER')")
    @PostMapping("/cabinet")
    public String doPostCabinet(@AuthenticationPrincipal User currentUser,
                                @RequestParam(required = false) Long newGroupId,
                                @RequestParam(required = false) String info,
                                Model model) {

        List<Group> groups = groupRepo.findAll();

        if(newGroupId != null) {

            Optional<Group> group = groupRepo.findById(newGroupId);

            if(!group.isPresent()) {
                Group newGroup = new Group(newGroupId, info);
                groupRepo.save(newGroup);
            }
        }

        model.addAttribute("currentUser", currentUser);

        return "redirect:/cabinet";
    }
}