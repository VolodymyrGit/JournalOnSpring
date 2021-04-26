package volm.journal.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import volm.journal.model.Group;
import volm.journal.model.User;
import volm.journal.repo.GroupRepo;
import volm.journal.security.Role;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Controller
public class CabinetController {

    private final GroupRepo groupRepo;


    @GetMapping("/cabinet")
    public String getCabinetView(@AuthenticationPrincipal User currentUser, Model model) {

        List<Group> groups = groupRepo.findAll();

        if(currentUser.getSecurityCode() != (null) && !currentUser.getRoles().contains(Role.ADMIN)) {

            String message = "Please confirm your email to make full use of the magazine." +
                    "\n We have sent you a letter, please read it and follow the link";
            model.addAttribute("confirmEmailErrorMessage", message);
        }
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("groups", groups);

        return "cabinet";
    }


    @PostMapping("/cabinet")
    public String doPostCabinet() {
        return "redirect:/cabinet";
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/cabinet-add-group")
    public String doPostAddGroupCabinet(Long newGroupId, String info) {

            Optional<Group> group = groupRepo.findById(newGroupId);

            if(!group.isPresent()) {
                Group newGroup = new Group(newGroupId, info);
                groupRepo.save(newGroup);
            }
        return "redirect:/cabinet";
    }
}