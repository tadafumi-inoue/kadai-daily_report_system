package controllers_follow;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Follow;
import utils.DBUtil;

/**
 * Servlet implementation class FollowsDestroyServlet
 */
@WebServlet("/follows/destroy")
public class FollowsDestroyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FollowsDestroyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String _token = (String) request.getParameter("_token");
        if (_token != null && _token.equals(request.getSession().getId())) {

            Follow follows_check = null;

            EntityManager em = DBUtil.createEntityManager();
            try {
                follows_check = (Follow) em.createNamedQuery("checkFollow", Follow.class)
                        .setParameter("emp", request.getSession().getAttribute("login_employee"))
                        .setParameter("fol_id", Integer.parseInt(request.getParameter("repo_id")))
                        .getSingleResult();
            } catch (NoResultException ex) {
            }

            if (follows_check == null) {
                em.close();
                response.sendRedirect(request.getContextPath() + "/reports/index");
            } else {

                em.getTransaction().begin();
                em.remove(follows_check);
                em.getTransaction().commit();
                em.close();

                request.getSession().setAttribute("flush", "フォローを解除しました");
                response.sendRedirect(request.getContextPath() + "/reports/index");
            }
        }

    }
}
