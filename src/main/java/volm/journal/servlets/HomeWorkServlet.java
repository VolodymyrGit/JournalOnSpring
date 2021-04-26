package volm.journal.servlets;

import org.springframework.beans.factory.annotation.Autowired;
import volm.journal.dao.HomeworkDao;
import volm.journal.dao.impl.HomeworkDaoImpl;
import volm.journal.model.Homework;
import volm.journal.repo.HomeworkRepo;
import volm.journal.service.HomeworkService;
import volm.journal.service.impl.HomeworkServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.NoSuchElementException;


@WebServlet("/hw")
public class HomeWorkServlet extends HttpServlet {

    private HomeworkDao homeworkDao = new HomeworkDaoImpl();

    @Autowired
    private HomeworkRepo homeworkRepo;

    private HomeworkService homeworkService = new HomeworkServiceImpl();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String hwId = req.getParameter("hwId");
        req.setAttribute("hwId", hwId);

        Homework hwById = homeworkRepo.findById(Long.parseLong(hwId))
                .orElseThrow(() -> new NoSuchElementException());

        req.setAttribute("description", hwById.getHwDescription());

        req.getRequestDispatcher("hwForm.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String description = req.getParameter("description");
        Long hwId = Long.parseLong(req.getParameter("hwId"));

        homeworkService.setNewDescription(description, hwId);

        resp.sendRedirect("/table");
    }
}
