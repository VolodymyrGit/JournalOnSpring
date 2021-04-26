package volm.journal.servlets;


import volm.journal.dao.impl.HomeworkDaoImpl;
import volm.journal.dao.impl.LessonDaoImpl;
import volm.journal.enums.Role;
import volm.journal.model.Group;
import volm.journal.model.Homework;
import volm.journal.model.Lesson;
import volm.journal.model.User;
import volm.journal.service.LessonService;
import volm.journal.service.UserService;
import volm.journal.service.impl.LessonServiceImpl;
import volm.journal.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;


@WebServlet("/add-lesson")
public class AddLessonServlet extends HttpServlet {

    private LessonDaoImpl lessonDaoImpl = new LessonDaoImpl();
    private UserService userService = new UserServiceImpl();
    private HomeworkDaoImpl homeworkDaoImpl = new HomeworkDaoImpl();

    private LessonService lessonService = new LessonServiceImpl();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User currentUser = (User) req.getSession().getAttribute("currentUser");

        lessonService.addLesson(currentUser);

        resp.sendRedirect("/table");
    }
}
