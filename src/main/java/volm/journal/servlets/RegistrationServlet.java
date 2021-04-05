package volm.journal.servlets;

import volm.journal.dao.GroupDao;
import volm.journal.dao.HomeworkDao;
import volm.journal.dao.LessonDao;
import volm.journal.dao.UserDao;
import volm.journal.dao.impl.GroupDaoImpl;
import volm.journal.dao.impl.HomeworkDaoImpl;
import volm.journal.dao.impl.LessonDaoImpl;
import volm.journal.dao.impl.UserDaoImpl;
import volm.journal.enums.Role;
import volm.journal.model.Group;
import volm.journal.model.Homework;
import volm.journal.model.Lesson;
import volm.journal.model.User;
import volm.journal.model.UserDto;
import volm.journal.util.SecurityUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;


@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    private final GroupDao groupDao = new GroupDaoImpl();
    private final UserDao userDao = new UserDaoImpl();
    private final LessonDao lessonDao = new LessonDaoImpl();
    private final HomeworkDao homeworkDao = new HomeworkDaoImpl();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Group> groups = groupDao.findAll();

        req.setAttribute("groups", groups);

        req.getRequestDispatcher("registration.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        Long groupId = Long.parseLong(req.getParameter("groupId"));
        Role role = Role.valueOf(req.getParameter("role"));
        String password = req.getParameter("password");

        UserDto



        Group group = groupDao.findById(groupId)
                .orElseThrow(() -> new NoSuchElementException());

        String salt = SecurityUtil.generateRandomSalt();
        String hashedPassword = SecurityUtil.getSecurePassword(password, salt);

        User user = new User(name, email, phone, hashedPassword, salt, group, role);

        List<Lesson> lessons = lessonDao.findLessonByGroup(group);

        User savedUser = userDao.save(user)
                .orElseThrow(() -> new NoSuchElementException());

        if(!lessons.isEmpty() && role.name().equals("STUDENT")) {

            for(Lesson l : lessons) {
                homeworkDao.save(new Homework(l, user));
            }
        }

        HttpSession session = req.getSession();
        session.setAttribute("currentUser", savedUser);

        resp.sendRedirect("/cabinet");
    }
}
