package volm.journal.servlets;

import volm.journal.dao.GroupDao;
import volm.journal.dao.UserDao;
import volm.journal.dao.impl.GroupDaoImpl;
import volm.journal.dao.impl.UserDaoImpl;
import volm.journal.enums.Role;
import volm.journal.model.Group;
import volm.journal.model.User;
import volm.journal.model.UserDto;
import volm.journal.service.UserService;
import volm.journal.service.impl.UserServiceImpl;
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

    UserService userService = new UserServiceImpl();


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

        UserDto userDto = UserDto.UserDtoBuilder.anUserDto()
                .id(id)
                .name(name)
                .email(email)
                .phone(phone)
                .groupId(groupId)
                .role(role)
                .password(password)
                .npassword(npassword)
                .build();

        User currentUser = (User) req.getSession().getAttribute("currentUser");

        User changedUser = userService.updateUserInfo(userDto, currentUser);

        if (!currentUser.getId().equals(changedUser.getId())) {

            String errorMessage = "You entered wrong current password";
            req.setAttribute("errorMessage", errorMessage);
            req.getRequestDispatcher("changeInfo.jsp").forward(req, resp);
        }
        HttpSession session = req.getSession();
        session.removeAttribute("currentUser");
        session.setAttribute("currentUser", changedUser);

        resp.sendRedirect("/cabinet");
    }
}
