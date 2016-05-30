package Controller;

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
		// HttpSession session = request.getSession();
		page = request.getRequestURL().substring(31);
		if (page.equals("GestionFil")) {
			enseignants = enseignantDao.lister();
			fils = filiereDao.listerFilSansMat();
			request.setAttribute("fils", fils);
			request.setAttribute("enseignants", enseignants);
			this.getServletContext().getRequestDispatcher("/GestionFil.jsp").forward(request, response);
		} else if (page.equals("ListFil")) {
			fils = filiereDao.listerFilAvecMat();
			request.setAttribute("fils", fils);
			this.getServletContext().getRequestDispatcher("/ListFil.jsp").forward(request, response);
		} else {
			resps = enseignantDao.listerEns();
			request.setAttribute("resps", resps);
			this.getServletContext().getRequestDispatcher("/CreerFil.jsp").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// HttpSession session = request.getSession();
		page = request.getRequestURL().substring(31);
		if (page.equals("GestionFil")) {
			
			enseignants = enseignantDao.lister();
			fils = filiereDao.listerFilSansMat();
			treatmentFiliere.ajoutMatiere(request, matiereDao, enseignantDao);
			request.setAttribute("fils", fils);
			request.setAttribute("enseignants", enseignants);
			this.getServletContext().getRequestDispatcher("/GestionFil.jsp").forward(request, response);
			
		} else if (page.equals("ListFil")) {
			
			fils = filiereDao.listerFilAvecMat();
			treatmentFiliere.modeModifMatFil(request, matiereDao, enseignantDao, filiereDao);
			if (request.getParameter("mode").equals("modification")) {
				if (request.getParameter("valider").equals("Valider")) {
					treatmentFiliere.modifierFiliere(request, matiereDao, enseignantDao);
					fils = filiereDao.listerFilAvecMat();
				}
			}else if(request.getParameter("mode").equals("") && request.getParameter("delete") != null){
				treatmentFiliere.supprimerFiliere(request, matiereDao, enseignantDao, filiereDao);
				fils = filiereDao.listerFilAvecMat();
			}
			request.setAttribute("fils", fils);
			this.getServletContext().getRequestDispatcher("/ListFil.jsp").forward(request, response);
			
		} else if (page.equals("CreerFil")){
			
			System.out.println(request.getRequestURL());
			treatmentFiliere.creerFiliere(request, filiereDao, enseignantDao);
			resps = enseignantDao.listerEns();
			request.setAttribute("resps", resps);
			this.getServletContext().getRequestDispatcher("/CreerFil.jsp").forward(request, response);
			
		}
	}

}
