package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.Classe;
import Beans.Filiere;
import Dao.ClasseDao;
import Dao.DAOFactory;
import Dao.FiliereDao;
import Treatment.EvryNoteUtils;
import Treatment.TreatementClasse;

/**
 * Servlet implementation class ServletClass
 */
@WebServlet("/ServletClass")
public class ServletClass extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private TreatementClasse treaitmentClasse;
	private ClasseDao classeDao;
	private FiliereDao filiereDao;
	private ArrayList<Filiere> listfiliere;
	private ArrayList<Classe> listclasse;

	public void init() {

		this.treaitmentClasse = new TreatementClasse();
		this.classeDao = ((DAOFactory) getServletContext().getAttribute("daofactory")).getClasseDao();
		this.filiereDao = ((DAOFactory) getServletContext().getAttribute("daofactory")).getFiliereDao();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String page = request.getRequestURL().substring(31);
		listfiliere = filiereDao.lister();
		request.setAttribute("listfiliere", listfiliere);
		request.setAttribute("ListNiveau", EvryNoteUtils.ListNiveau);
		
		
		if (page.equals("CreerClass")) {
			
			this.getServletContext().getRequestDispatcher("/CreerClass.jsp").forward(request, response);

		} else {
			
			listclasse=treaitmentClasse.GetClassList(classeDao);
			
			this.getServletContext().getRequestDispatcher("/ListClass.jsp").forward(request, response);
		}
		 


	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		

	if (action.equals("CreerClass")) {
		listfiliere = filiereDao.lister();
		treaitmentClasse.CreatClass(request, classeDao, listfiliere);
		this.getServletContext().getRequestDispatcher("/CreerClass.jsp").forward(request, response);

		} 
	
	if(action.equals("ModifierClass")){
			treaitmentClasse.DisplayClassForModification(request);
			request.setAttribute("listfiliere", listfiliere);
			request.setAttribute("ListNiveau", EvryNoteUtils.ListNiveau);
			this.getServletContext().getRequestDispatcher("/ModifierClass.jsp").forward(request, response);
			
		}
	
	if(action.equals("SaveClass")){
		
		treaitmentClasse.UpdateClass(request, classeDao, listfiliere);
		this.getServletContext().getRequestDispatcher("/CreerClass.jsp").forward(request, response);
	
	}
	if(action.equals("")){
		
	}
		

	}

}
