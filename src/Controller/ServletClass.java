package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.Classe;
import Beans.Enseignant;
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
		Enseignant enseignant = (Enseignant) request.getSession().getAttribute("sessionUtilisateur");
		String page = request.getRequestURL().substring(31);
		listfiliere = filiereDao.lister();
		request.setAttribute("listfiliere", listfiliere);
		request.setAttribute("ListNiveau", EvryNoteUtils.ListNiveau);

		if (page.equals("CreerClass")) {

			this.getServletContext().getRequestDispatcher("/CreerClass.jsp").forward(request, response);
			
		}

		if (page.equals("ListClass")) {

			listclasse = treaitmentClasse.GetClassList(classeDao, listfiliere);
			int lengh = listclasse.size();
			request.setAttribute("listclasse", listclasse);
			request.setAttribute("lengh", lengh);

			this.getServletContext().getRequestDispatcher("/ListClass.jsp").forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		@SuppressWarnings("unused")
		String page = request.getRequestURL().substring(31);
		switch (action) {

		case "CreerClass":
			listfiliere = filiereDao.lister();
			treaitmentClasse.CreatClass(request, classeDao, listfiliere);
			this.getServletContext().getRequestDispatcher("/CreerClass.jsp").forward(request, response);
			break;
		case "ModifierClass":
			listclasse = treaitmentClasse.GetClassList(classeDao, listfiliere);
			treaitmentClasse.DisplayClassForModification(request, listclasse);
			request.setAttribute("listfiliere", listfiliere);
			request.setAttribute("ListNiveau", EvryNoteUtils.ListNiveau);
			this.getServletContext().getRequestDispatcher("/ModifierClass.jsp").forward(request, response);
			break;

		case "Annuler":
			listfiliere = filiereDao.lister();
			request.setAttribute("listfiliere", listfiliere);
			request.setAttribute("ListNiveau", EvryNoteUtils.ListNiveau);
			this.getServletContext().getRequestDispatcher("/CreerClass.jsp").forward(request, response);
			break;

		case "SaveClass":
			treaitmentClasse.UpdateClass(request, classeDao, listfiliere);
			this.getServletContext().getRequestDispatcher("/CreerClass.jsp").forward(request, response);
			break;

		case "ModifierClassFromListClass":
			listfiliere = filiereDao.lister();
			listclasse = treaitmentClasse.GetClassList(classeDao, listfiliere);
			treaitmentClasse.DisplayClassForModification(request, listclasse);
			request.setAttribute("listfiliere", listfiliere);
			request.setAttribute("ListNiveau", EvryNoteUtils.ListNiveau);
			this.getServletContext().getRequestDispatcher("/ModifierClass.jsp").forward(request, response);
			break;
		case "SupprimerClass":
			treaitmentClasse.DeleteClass(request, classeDao);
			listclasse = treaitmentClasse.GetClassList(classeDao, listfiliere);
			request.setAttribute("listclasse", listclasse);
			this.getServletContext().getRequestDispatcher("/ListClass.jsp").forward(request, response);

			break;
		}

	}

}
