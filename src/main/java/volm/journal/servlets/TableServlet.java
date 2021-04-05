package volm.journal.servlets;

import volm.journal.dao.HomeworkDao;
import volm.journal.dao.LessonDao;
import volm.journal.dao.impl.HomeworkDaoImpl;
import volm.journal.dao.impl.LessonDaoImpl;
import volm.journal.enums.Role;
import volm.journal.model.Group;
import volm.journal.model.Homework;
import volm.journal.model.Lesson;
import volm.journal.model.User;
import volm.journal.service.UserService;
import volm.journal.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@WebServlet("/table")
public class TableServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();
    private HomeworkDao homeworkDao = new HomeworkDaoImpl();
    private LessonDao lessonDao = new LessonDaoImpl();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User currentUser = (User) req.getSession().getAttribute("currentUser");
        Group group = currentUser.getGroup();

        List<User> teachers = userService.findUsersByRole(group, Role.TEACHER);

        List<User> students = userService.findUsersByRole(group, Role.STUDENT);

        List<Lesson> lessons = lessonDao.findLessonByGroup(group);

        Map<Long, List<Homework>> homeworks = lessons.stream()
                .map(l -> l.getId())
                .collect(Collectors.toList()).stream()
                .map(id -> homeworkDao.findByLessonId(id))
                .flatMap(List::stream)
                .collect(Collectors.groupingBy(hw -> hw.getStudent().getId(), Collectors.toList()));


        req.setAttribute("teachers", teachers);
        req.setAttribute("students", students);
        req.setAttribute("lessons", lessons);
        req.setAttribute("homeworks", homeworks);
        req.setAttribute("group", group);

        req.getRequestDispatcher("table.jsp").forward(req, resp);
    }
}
