package controllers.reports;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Favorite;
import models.Follow;
import models.Report;
import utils.DBUtil;

/**
 * Servlet implementation class ReportsShowServlet
 */
@WebServlet("/reports/show")
public class ReportsShowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportsShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Favorite favorites_check = null;
        Follow follows_check = null;

        EntityManager em = DBUtil.createEntityManager();

        Report r = em.find(Report.class, Integer.parseInt(request.getParameter("id")));
        try {
            favorites_check = (Favorite) em.createNamedQuery("checkFavorite", Favorite.class)
                    .setParameter("em_id", request.getSession().getAttribute("login_employee"))
                    .setParameter("repo", r)
                    .getSingleResult();
        } catch (NoResultException ex) {
        }

        try {
            follows_check = (Follow) em.createNamedQuery("checkFollow", Follow.class)
                    .setParameter("emp", request.getSession().getAttribute("login_employee"))
                    .setParameter("fol_id", r.getEmployee().getId())
                    .getSingleResult();
        } catch (NoResultException ex) {
        }


        long favorites_count = (long) em.createNamedQuery("getFavoritesCount", Long.class)
                .setParameter("f_report", r)
                .getSingleResult();
        em.close();

        if (favorites_check != null) {
            request.setAttribute("favorites_check", favorites_check);
        }
        if (follows_check != null) {
            request.setAttribute("follows_check", follows_check);
        }

        request.setAttribute("report", r);
        request.setAttribute("_token", request.getSession().getId());
        request.setAttribute("favorites_count", favorites_count);
        request.getSession().setAttribute("report_id", r.getId());
        request.getSession().setAttribute("report_favorite", r);


        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/reports/show.jsp");
        rd.forward(request, response);
    }

}
