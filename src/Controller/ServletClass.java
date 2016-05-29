package Controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

	public void init() {

		this.treaitmentClasse = new TreatementClasse();
		this.classeDao = ((DAOFactory) getServletContext().getAttribute("daofactory")).getClasseDao();
		this.filiereDao = ((DAOFactory) getServletContext().getAttribute("daofactory")).getFiliereDao();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// treaitmentClasse.CreatClass(request, classeDao);

		listfiliere = filiereDao.lister();
		request.setAttribute("listfiliere", listfiliere);
		request.setAttribute("ListNiveau", EvryNoteUtils.ListNiveau);
		this.getServletContext().getRequestDispatcher("/CreerClass.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		treaitmentClasse.CreatClass(request, classeDao, listfiliere);
		this.getServletContext().getRequestDispatcher("/CreerClass.jsp").forward(request, response);

	}

}
