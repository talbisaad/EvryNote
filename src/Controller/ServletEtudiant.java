package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Beans.Classe;
import Beans.Etudiant;
import Beans.Filiere;
import Dao.ClasseDao;
import Dao.DAOFactory;
import Dao.EtudiantDao;
import Dao.FiliereDao;
import Treatment.EvryNoteUtils;
import Treatment.TreatementEtudiant;

/**
 * Servlet implementation class ServletGestionClass
 */
@WebServlet("/ServletEtudiant")
public class ServletEtudiant extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ClasseDao classeDao;
	private FiliereDao filiereDao;
	private EtudiantDao etudiantDao;
	private ArrayList<Filiere> listfiliere;
	private ArrayList<Etudiant> listetudiant;
	private TreatementEtudiant treatementEtudiant;
	private Classe c;
	private int lengh;

	public void init() {
		treatementEtudiant = new TreatementEtudiant();
		this.classeDao = ((DAOFactory) getServletContext().getAttribute("daofactory")).getClasseDao();
		this.filiereDao = ((DAOFactory) getServletContext().getAttribute("daofactory")).getFiliereDao();
		this.etudiantDao = ((DAOFactory) getServletContext().getAttribute("daofactory")).getEtudiantDao();
		listetudiant = new ArrayList<Etudiant>();
		c= new Classe();
		
		
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		listfiliere = filiereDao.lister();
		request.setAttribute("listfiliere", listfiliere);
		request.setAttribute("ListNiveau", EvryNoteUtils.ListNiveau);
		this.getServletContext().getRequestDispatcher("/GestionClass.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		listfiliere = filiereDao.lister();

		switch(action){
		
		case "GestionClass":
			listetudiant = treatementEtudiant.GetListOfStudent(request, listfiliere, classeDao, etudiantDao,false);
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
			listetudiant = treatementEtudiant.GetListOfStudent(request, listfiliere, classeDao, etudiantDao,true);
			lengh = listetudiant.size();
			request.setAttribute("listetudiant", listetudiant);
			request.setAttribute("lengh", lengh);
			listfiliere = filiereDao.lister();
			request.setAttribute("listfiliere", listfiliere);
			request.setAttribute("ListNiveau", EvryNoteUtils.ListNiveau);
			this.getServletContext().getRequestDispatcher("/GestionClass.jsp").forward(request, response);
			break;
			
		case "ModifierEtudiantFromListEtudiant":
			//On set les attributs de la classe pour qu'on puisse savoir la classe en question
			request.setAttribute("NomClasse", c.getNomClasse());
			request.setAttribute("filiere", c.getFiliere().getNom());
			request.setAttribute("niveau", c.getNiveau());
			request.setAttribute("annee", c.getAnneeUniversitaire());
			treatementEtudiant.DisplayStudentForModify(request, listetudiant);
			this.getServletContext().getRequestDispatcher("/DisplayStudentForModify.jsp").forward(request, response);
		break;
		
		case "UpdateStudent":
			treatementEtudiant.UpdateStudent(request, etudiantDao);
			listetudiant = treatementEtudiant.GetListOfStudent(request, listfiliere, classeDao, etudiantDao,true);
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
			listetudiant = treatementEtudiant.GetListOfStudent(request, listfiliere, classeDao, etudiantDao,true);
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
	
}
