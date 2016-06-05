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
	private ArrayList<Classe> listclasse;   	
	private ArrayList<Etudiant>listetudiant;
	private TreatementEtudiant treatementEtudiant;
	private int lengh;
  

	public void init() {
		treatementEtudiant= new TreatementEtudiant();
		this.classeDao = ((DAOFactory) getServletContext().getAttribute("daofactory")).getClasseDao();
		this.filiereDao = ((DAOFactory) getServletContext().getAttribute("daofactory")).getFiliereDao();
		this.etudiantDao=((DAOFactory) getServletContext().getAttribute("daofactory")).getEtudiantDao();
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
		
		if(action.equals("GestionClass")){
			listetudiant= new ArrayList<Etudiant>();
			
			listetudiant=treatementEtudiant.GetListOfStudent(request,listfiliere,classeDao,etudiantDao);	
			lengh=listetudiant.size();			
			request.setAttribute("listetudiant", listetudiant);
			request.setAttribute("lengh", lengh);
			doGet(request, response);
		}
		
		//doGet(request, response);
	}

}
