//package volm.journal.controller;
//
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import volm.journal.model.User;
//import volm.journal.repo.UserRepo;
//import volm.journal.service.LessonService;
//
//import java.util.NoSuchElementException;
//
//
//@RequiredArgsConstructor
//@Controller
//public class AddLessonController {
//
//    private final UserRepo userRepo;
//    private final LessonService lessonService;
//
//
//    @GetMapping("/add-lesson")
//    public String addLesson() {
//
//        User currentUser = userRepo.findById(4L)
//                .orElseThrow(() -> new NoSuchElementException());
//
//        lessonService.addLesson(currentUser);
//
//        return "redirect:table";
//    }
//}