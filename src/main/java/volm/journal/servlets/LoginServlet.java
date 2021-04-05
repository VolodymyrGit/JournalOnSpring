package volm.journal.servlets;

import volm.journal.dao.UserDao;
import volm.journal.dao.impl.UserDaoImpl;
import volm.journal.model.User;
import volm.journal.service.UserService;
import volm.journal.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final UserService userService = new UserServiceImpl();
    private final UserDao userDao = new UserDaoImpl();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("login");
        String password = req.getParameter("password");

        if (userService.authorized(email, password)) {

            Optional<User> optUser = userDao.findByEmail(email);

            HttpSession session = req.getSession();
            session.setAttribute("currentUser", optUser.get());

            resp.sendRedirect("/cabinet");
        } else {
            String errorMessage = "Wrong login or password";
            req.setAttribute("errorMessage", errorMessage);
            req.setAttribute("previousLogin", email);
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}

