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
import Dao.DAOFactory;
import Treatment.TreatmentFiliere;

public class ServletFiliere extends HttpServlet {
	ArrayList<Enseignant> resps;
	ArrayList<Filiere> fils;
	TreatmentFiliere treatmentFiliere;
	private FiliereDao filiereDao;
	private EnseignantDao enseignantDao;
	private String page;

	public void init() {
		this.filiereDao = ((DAOFactory) getServletContext().getAttribute("daofactory")).getFiliereDao();
		this.enseignantDao = ((DAOFactory) getServletContext().getAttribute("daofactory")).getEnseignantDao();
		treatmentFiliere = new TreatmentFiliere();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// HttpSession session = request.getSession();
		page = request.getRequestURL().substring(31);
		if (page.equals("ListFil")) {
			fils = filiereDao.lister();
			request.setAttribute("fils", fils);
			this.getServletContext().getRequestDispatcher("/ListFil.jsp").forward(request, response);
		} else {
			resps = enseignantDao.lister();
			request.setAttribute("resps", resps);
			this.getServletContext().getRequestDispatcher("/CreerFil.jsp").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// HttpSession session = request.getSession();
		page = request.getRequestURL().substring(31);
		if (page.equals("ListFil")) {
			fils = filiereDao.lister();
			request.setAttribute("fils", fils);
			this.getServletContext().getRequestDispatcher("/ListFil.jsp").forward(request, response);
		} else {
			System.out.println(request.getRequestURL());
			treatmentFiliere.creerFiliere(request, filiereDao, enseignantDao);
			resps = enseignantDao.lister();
			request.setAttribute("fils", fils);
			this.getServletContext().getRequestDispatcher("/CreerFil.jsp").forward(request, response);
		}
	}

}
