package controllers.favorites;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Favorite;
import models.Report;
import utils.DBUtil;

/**
 * Servlet implementation class FavoritesCreateServlet
 */
@WebServlet("/favorites/create")
public class FavoritesCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FavoritesCreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        String _token = (String) request.getParameter("_token");
        if (_token != null && _token.equals(request.getSession().getId())) {

            Favorite favorites_check = null;

            EntityManager em = DBUtil.createEntityManager();
            try {
                favorites_check = (Favorite) em.createNamedQuery("checkFavorite", Favorite.class)
                        .setParameter("em_id", request.getSession().getAttribute("login_employee"))
                        .setParameter("repo", request.getSession().getAttribute("report_favorite"))
                        .getSingleResult();
            } catch (NoResultException ex) {
            }


            if (favorites_check != null) {
                em.close();
                response.sendRedirect(request.getContextPath() + "/reports/index");
            }else{


            Favorite f = new Favorite();

            f.setEmployee((Employee) request.getSession().getAttribute("login_employee"));
            f.setReport((Report) request.getSession().getAttribute("report_favorite"));

            em.getTransaction().begin();
            em.persist(f);
            em.getTransaction().commit();
            em.close();


            request.getSession().removeAttribute("report_favorite");
            request.getSession().setAttribute("flush", "いいね！しました");
            response.sendRedirect(request.getContextPath() + "/reports/index");
            }
        }
    }
}
