package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Beans.Enseignant;
import Dao.DAOFactory;
import Dao.EnseignantDao;
import Treatment.TreatementAuth;

public class ServletAuth extends HttpServlet {

	private EnseignantDao enseignantDao;
	public static final String ATT_SESSION_USER = "sessionUtilisateur";

	public void init() {
		this.enseignantDao = ((DAOFactory) getServletContext().getAttribute("daofactory")).getEnseignantDao();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			this.getServletContext().getRequestDispatcher("/Login.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		TreatementAuth treatementAuth = new TreatementAuth();
		Enseignant enseignant = treatementAuth.login(request, enseignantDao);

		if (enseignant != null) {
			session.setAttribute(ATT_SESSION_USER, enseignant);
			this.getServletContext().getRequestDispatcher("/platform/SaisieNote.jsp").forward(request, response);

		} else {
			session.setAttribute(ATT_SESSION_USER, null);
		}

		request.setAttribute("treatementAuth", treatementAuth);
		request.setAttribute("enseignant", enseignant);

		response.sendRedirect("/EvryNote/Login");

	}

}
