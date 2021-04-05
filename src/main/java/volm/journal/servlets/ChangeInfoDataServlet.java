package volm.journal.servlets;

import volm.journal.dao.GroupDao;
import volm.journal.dao.UserDao;
import volm.journal.dao.impl.GroupDaoImpl;
import volm.journal.dao.impl.UserDaoImpl;
import volm.journal.enums.Role;
import volm.journal.model.Group;
import volm.journal.model.User;
import volm.journal.util.SecurityUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.NoSuchElementException;


@WebServlet("/change-info")
public class ChangeInfoDataServlet extends HttpServlet {

    private final UserDao userDao = new UserDaoImpl();
    private final GroupDao groupDao = new GroupDaoImpl();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("changeInfo.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long id = Long.parseLong(req.getParameter("id"));
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        Long groupId = Long.parseLong(req.getParameter("groupId"));
        Role role = Role.valueOf(req.getParameter("role"));
        String password = req.getParameter("password");
        String npassword = req.getParameter("npassword");

        Group group = groupDao.findById(groupId)
                .orElseThrow(() -> new NoSuchElementException());

        String salt = SecurityUtil.generateRandomSalt();
        String newHashedPassword = SecurityUtil.getSecurePassword(npassword, salt);

        User currentUser = (User) req.getSession().getAttribute("currentUser");

        User changedUser = new User(id, name, email, phone, newHashedPassword, salt, group, role);

        String hashedPassword = SecurityUtil.getSecurePassword(password, currentUser.getSalt());

        if (!currentUser.getPassword().equals(hashedPassword)) {

            String errorMessage = "You entered wrong current password";
            req.setAttribute("errorMessage", errorMessage);
            req.getRequestDispatcher("changeInfo.jsp").forward(req, resp);
        }

        userDao.update(changedUser);

        HttpSession session = req.getSession();
        session.removeAttribute("currentUser");
        session.setAttribute("currentUser", changedUser);

        resp.sendRedirect("/cabinet");
    }
}
