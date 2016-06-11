package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.Enseignant;
import Dao.DAOFactory;
import Dao.EnseignantDao;
import Treatment.TreatementListEnseignant;

public class ServletListEnseignant extends HttpServlet {

	private EnseignantDao enseignantDao;
	ArrayList<Enseignant> enseignants = new ArrayList<Enseignant>();
	TreatementListEnseignant treatementListEnseignant;

	public void init() {
		((DAOFactory) getServletContext().getAttribute("daofactory")).getFiliereDao();
		this.enseignantDao = ((DAOFactory) getServletContext().getAttribute("daofactory")).getEnseignantDao();
		((DAOFactory) getServletContext().getAttribute("daofactory")).getMatiereDao();
		treatementListEnseignant = new TreatementListEnseignant();

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Enseignant enseignant = (Enseignant) request.getSession().getAttribute("sessionUtilisateur");
		if (enseignant.isChefDepart()) {
			enseignants = enseignantDao.lister();
			if (request.getParameter("rechEns") != null && request.getParameter("rechEns") != "") {
				enseignants = enseignantDao.trouverByNomPrenomId(request.getParameter("rechEns"));
			}
			request.setAttribute("enseignants", enseignants);
			this.getServletContext().getRequestDispatcher("/platform/ListEns.jsp").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Enseignant enseignant = (Enseignant) request.getSession().getAttribute("sessionUtilisateur");
		if (enseignant.isChefDepart()) {
			if (request.getParameter("delete") != null && request.getParameter("delete") != "") {
				treatementListEnseignant.supprimerEnseignant(request, enseignantDao);
			}
			enseignants = enseignantDao.lister();
			request.setAttribute("enseignants", enseignants);
			this.getServletContext().getRequestDispatcher("/platform/ListEns.jsp").forward(request, response);
		}
	}
}
