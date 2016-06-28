package Controller;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import Beans.Classe;
import Beans.Etudiant;
import Beans.Filiere;
import Beans.Matiere;
import Dao.ClasseDao;
import Dao.DAOFactory;
import Dao.EtudiantDao;
import Dao.FiliereDao;
import Treatment.EvryNoteUtils;
import Treatment.TreatementEtudiant;
import au.com.bytecode.opencsv.CSVReader;

/**
 * Servlet implementation class ServletGestionClass
 */
@WebServlet("/ServletEtudiant")
@MultipartConfig
public class ServletEtudiant extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ClasseDao classeDao;
	private FiliereDao filiereDao;
	private EtudiantDao etudiantDao;
	private ArrayList<Filiere> listfiliere;
	private ArrayList<Etudiant> listetudiant;
	private ArrayList<Matiere> listmatiere;
	private TreatementEtudiant treatementEtudiant;
	private Classe c;
	private int lengh;

	public void init() {
		treatementEtudiant = new TreatementEtudiant();
		this.classeDao = ((DAOFactory) getServletContext().getAttribute("daofactory")).getClasseDao();
		this.filiereDao = ((DAOFactory) getServletContext().getAttribute("daofactory")).getFiliereDao();
		this.etudiantDao = ((DAOFactory) getServletContext().getAttribute("daofactory")).getEtudiantDao();
		listetudiant = new ArrayList<Etudiant>();
		listmatiere = new ArrayList<Matiere>();
		c = new Classe();

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String page = request.getRequestURL().substring(31);

		listfiliere = filiereDao.lister();
		request.setAttribute("listfiliere", listfiliere);
		request.setAttribute("ListNiveau", EvryNoteUtils.ListNiveau);

		if (page.equals("Simulation")) {
			this.getServletContext().getRequestDispatcher("/Simulation.jsp").forward(request, response);
		} else {
			this.getServletContext().getRequestDispatcher("/GestionClass.jsp").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		listfiliere = filiereDao.lister();

		switch (action) {

		case "GestionClass":
			listetudiant = treatementEtudiant.GetListOfStudent(request, listfiliere, classeDao, etudiantDao, false);
			lengh = listetudiant.size();
			request.setAttribute("listetudiant", listetudiant);
			request.setAttribute("lengh", lengh);
			c.setNomClasse(request.getParameter("NomClasse"));
			c.getFiliere().setNom(request.getParameter("filiere"));
			c.setNiveau(request.getParameter("niveau"));
			c.setAnneeUniversitaire(request.getParameter("annee"));
			listfiliere = filiereDao.lister();
			request.setAttribute("listfiliere", listfiliere);
			request.setAttribute("ListNiveau", EvryNoteUtils.ListNiveau);
			this.getServletContext().getRequestDispatcher("/GestionClass.jsp").forward(request, response);
			break;

		case "AddStudent":
			treatementEtudiant.AddStudent(request, etudiantDao);
			listetudiant = treatementEtudiant.GetListOfStudent(request, listfiliere, classeDao, etudiantDao, true);
			lengh = listetudiant.size();
			request.setAttribute("listetudiant", listetudiant);
			request.setAttribute("lengh", lengh);
			listfiliere = filiereDao.lister();
			request.setAttribute("listfiliere", listfiliere);
			request.setAttribute("ListNiveau", EvryNoteUtils.ListNiveau);
			this.getServletContext().getRequestDispatcher("/GestionClass.jsp").forward(request, response);
			break;

		case "ModifierEtudiantFromListEtudiant":
			// On set les attributs de la classe pour qu'on puisse savoir la
			// classe en question
			request.setAttribute("NomClasse", c.getNomClasse());
			request.setAttribute("filiere", c.getFiliere().getNom());
			request.setAttribute("niveau", c.getNiveau());
			request.setAttribute("annee", c.getAnneeUniversitaire());
			treatementEtudiant.DisplayStudentForModify(request, listetudiant);
			this.getServletContext().getRequestDispatcher("/DisplayStudentForModify.jsp").forward(request, response);
			break;

		case "UpdateStudent":
			treatementEtudiant.UpdateStudent(request, etudiantDao);
			listetudiant = treatementEtudiant.GetListOfStudent(request, listfiliere, classeDao, etudiantDao, true);
			lengh = listetudiant.size();
			request.setAttribute("listetudiant", listetudiant);
			request.setAttribute("lengh", lengh);
			listfiliere = filiereDao.lister();
			request.setAttribute("listfiliere", listfiliere);
			request.setAttribute("ListNiveau", EvryNoteUtils.ListNiveau);
			this.getServletContext().getRequestDispatcher("/GestionClass.jsp").forward(request, response);
			break;

		case "SupprimerEtudiant":
			treatementEtudiant.DeleteStudent(request, etudiantDao);
			listetudiant = treatementEtudiant.GetListOfStudent(request, listfiliere, classeDao, etudiantDao, true);
			lengh = listetudiant.size();
			request.setAttribute("listetudiant", listetudiant);
			request.setAttribute("lengh", lengh);
			listfiliere = filiereDao.lister();
			request.setAttribute("listfiliere", listfiliere);
			request.setAttribute("ListNiveau", EvryNoteUtils.ListNiveau);
			this.getServletContext().getRequestDispatcher("/GestionClass.jsp").forward(request, response);
			break;

		case "SearchMatiere":
			listfiliere = filiereDao.lister();
			request.setAttribute("listfiliere", listfiliere);
			request.setAttribute("ListNiveau", EvryNoteUtils.ListNiveau);
			listmatiere = treatementEtudiant.GetMatiereForSimulate(request, etudiantDao,false);
			if (listmatiere != null) {
				request.setAttribute("listmatiere", listmatiere);
				request.setAttribute("lengh", listmatiere.size());
				this.getServletContext().getRequestDispatcher("/Simulation.jsp").forward(request, response);
			}
			break;
		case"Calculate":
			HashMap<String, Float> listresult = new HashMap<String,Float>();
			listfiliere = filiereDao.lister();
			request.setAttribute("listfiliere", listfiliere);
			request.setAttribute("ListNiveau", EvryNoteUtils.ListNiveau);
			listmatiere = treatementEtudiant.GetMatiereForSimulate(request, etudiantDao,true);
			if (listmatiere != null) {
				listresult=Simulate(request,listmatiere);
				request.setAttribute("sumcoeff", listresult.get("sumcoeff"));
				request.setAttribute("result",	listresult.get("result"));
				request.setAttribute("listmatiere", listmatiere);
				request.setAttribute("lengh", listmatiere.size());
			this.getServletContext().getRequestDispatcher("/Simulation.jsp").forward(request, response);
		}
			break;
		case "upload":
			treatementEtudiant.Upload(request, etudiantDao);
			listetudiant = treatementEtudiant.GetListOfStudent(request, listfiliere, classeDao, etudiantDao, true);
			lengh = listetudiant.size();
			request.setAttribute("listetudiant", listetudiant);
			request.setAttribute("lengh", lengh);
			listfiliere = filiereDao.lister();
			request.setAttribute("listfiliere", listfiliere);
			request.setAttribute("ListNiveau", EvryNoteUtils.ListNiveau);
			this.getServletContext().getRequestDispatcher("/GestionClass.jsp").forward(request, response);
			break;
		}
		}
	private HashMap<String, Float> Simulate(HttpServletRequest request, ArrayList<Matiere> listmatiere) {
		HashMap<String, Float> resultMap = new HashMap<String, Float>();
		float tmp = 0;
		int sumcoeff = 0;
		float result = 0;
		for (int i = 0; i < listmatiere.size(); i++) {
			tmp += listmatiere.get(i).getCoefficient() * Float.parseFloat(request.getParameter("" + i));
			sumcoeff += listmatiere.get(i).getCoefficient();
		}
		result=tmp/sumcoeff;
		resultMap.put("sumcoeff", (float) sumcoeff);
		resultMap.put("result", result);
		return resultMap;
		
		
	}

}
