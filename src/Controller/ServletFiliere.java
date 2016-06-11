package Controller;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Beans.Enseignant;
import Beans.Filiere;
import Dao.EnseignantDao;
import Dao.FiliereDao;
import Dao.MatiereDao;
import Dao.DAOFactory;
import Treatment.TreatmentFiliere;

public class ServletFiliere extends HttpServlet {
	ArrayList<Enseignant> resps;
	ArrayList<Enseignant> enseignants;
	ArrayList<Filiere> fils;
	TreatmentFiliere treatmentFiliere;
	private FiliereDao filiereDao;
	private EnseignantDao enseignantDao;
	private MatiereDao matiereDao;
	private String page;

	public void init() {
		this.filiereDao = ((DAOFactory) getServletContext().getAttribute("daofactory")).getFiliereDao();
		this.enseignantDao = ((DAOFactory) getServletContext().getAttribute("daofactory")).getEnseignantDao();
		this.matiereDao = ((DAOFactory) getServletContext().getAttribute("daofactory")).getMatiereDao();
		treatmentFiliere = new TreatmentFiliere();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		page = request.getRequestURL().substring(31);
		Enseignant enseignant = (Enseignant) request.getSession().getAttribute("sessionUtilisateur");

		if (page.equals("platform/GestionFil") && enseignant.isReponsableFil()) {
			enseignants = enseignantDao.lister();
			fils = filiereDao.listerFilSansMat(enseignant.getId());
			request.setAttribute("fils", fils);
			request.setAttribute("enseignants", enseignants);
			this.getServletContext().getRequestDispatcher("/platform/GestionFil.jsp").forward(request, response);
		} else if (page.equals("platform/ListFil") && (enseignant.isChefDepart() || enseignant.isReponsableFil())) {
			if (enseignant.isChefDepart()) {
				fils = filiereDao.listerFilAvecMat();
			} else if (enseignant.isReponsableFil()) {
				fils = filiereDao.listerFilAvecMatResp(enseignant.getId());
			}
			request.setAttribute("fils", fils);
			this.getServletContext().getRequestDispatcher("/platform/ListFil.jsp").forward(request, response);
		} else if (page.equals("platform/CreerFil") && enseignant.isChefDepart()) {
			resps = enseignantDao.listerEns();
			request.setAttribute("resps", resps);
			this.getServletContext().getRequestDispatcher("/platform/CreerFil.jsp").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Enseignant enseignant = (Enseignant) request.getSession().getAttribute("sessionUtilisateur");
		page = request.getRequestURL().substring(31);
		if (page.equals("platform/GestionFil") && enseignant.isReponsableFil()) {
			fils = filiereDao.listerFilSansMat(enseignant.getId());

			if (request.getParameter("upload").equals("Upload")) {
				treatmentFiliere.upload("/Users/badrzahir/Downloads/" + request.getParameter("fichier"), request,
						matiereDao, enseignantDao);
			} else {
				enseignants = enseignantDao.lister();
				treatmentFiliere.ajoutMatiere(request, matiereDao, enseignantDao);
			}
			request.setAttribute("fils", fils);
			request.setAttribute("enseignants", enseignants);
			this.getServletContext().getRequestDispatcher("/platform/GestionFil.jsp").forward(request, response);

		} else if (page.equals("platform/ListFil") && (enseignant.isChefDepart() || enseignant.isReponsableFil())) {

			treatmentFiliere.modeModifMatFil(request, matiereDao, enseignantDao, filiereDao);
			if (request.getParameter("mode").equals("modification")) {			
				if (request.getParameter("valider").equals("Valider")) {
					treatmentFiliere.modifierFiliere(request, matiereDao, enseignantDao, filiereDao);
					fils = filiereDao.listerFilAvecMat();
				}

			} else if (request.getParameter("mode").equals("") && request.getParameter("delete") != null) {
				treatmentFiliere.supprimerFiliere(request, matiereDao, enseignantDao, filiereDao);
				fils = filiereDao.listerFilAvecMat();
			}
			if (enseignant.isChefDepart()) {
				fils = filiereDao.listerFilAvecMat();
			} else if (enseignant.isReponsableFil()) {
				fils = filiereDao.listerFilAvecMatResp(enseignant.getId());
			}
			request.setAttribute("fils", fils);
			this.getServletContext().getRequestDispatcher("/platform/ListFil.jsp").forward(request, response);

		} else if (page.equals("platform/CreerFil") && enseignant.isChefDepart()) {

			treatmentFiliere.creerFiliere(request, filiereDao, enseignantDao);
			resps = enseignantDao.listerEns();
			request.setAttribute("resps", resps);
			this.getServletContext().getRequestDispatcher("/platform/CreerFil.jsp").forward(request, response);

		}
	}

}
