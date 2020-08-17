package controllers_follow;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Follow;
import utils.DBUtil;

/**
 * Servlet implementation class FollowsCreateServlet
 */
@WebServlet("/follows/create")
public class FollowsCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FollowsCreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = (String) request.getParameter("_token");
        if (_token != null && _token.equals(request.getSession().getId())) {



            EntityManager em = DBUtil.createEntityManager();

            Follow f = new Follow();

            f.setEmployee((Employee) request.getSession().getAttribute("login_employee"));
            f.setFollowerd_id(Integer.parseInt(request.getParameter("repo_id")));

            em.getTransaction().begin();
            em.persist(f);
            em.getTransaction().commit();
            em.close();

            request.getSession().setAttribute("flush", "フォローしました");
            response.sendRedirect(request.getContextPath() + "/reports/index");
    }
        }
    }


