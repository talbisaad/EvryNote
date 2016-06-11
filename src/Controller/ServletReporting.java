package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.Classe;
import Beans.Enseignant;
import Beans.Etudiant;
import Beans.Filiere;
import Beans.Matiere;
import Beans.NoteMatiere;
import Dao.ClasseDao;
import Dao.DAOFactory;
import Dao.EtudiantDao;
import Dao.FiliereDao;
import Dao.MatiereDao;
import Dao.NoteMatiereDao;

public class ServletReporting extends HttpServlet {

	private FiliereDao filiereDao;
	private MatiereDao matiereDao;
	private ClasseDao classeDao;
	private EtudiantDao etudiantDao;
	NoteMatiereDao noteMatiereDao;
	ArrayList<Matiere> matieres = new ArrayList<Matiere>();
	ArrayList<Classe> classes = new ArrayList<Classe>();
	ArrayList<Etudiant> etudiants = new ArrayList<Etudiant>();
	ArrayList<NoteMatiere> noteMatieres = new ArrayList<NoteMatiere>();

	public void init() {
		filiereDao = ((DAOFactory) getServletContext().getAttribute("daofactory")).getFiliereDao();
		this.matiereDao = ((DAOFactory) getServletContext().getAttribute("daofactory")).getMatiereDao();
		this.classeDao = ((DAOFactory) getServletContext().getAttribute("daofactory")).getClasseDao();
		this.etudiantDao = ((DAOFactory) getServletContext().getAttribute("daofactory")).getEtudiantDao();
		this.noteMatiereDao = ((DAOFactory) getServletContext().getAttribute("daofactory")).getNoteMatiereDao();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Enseignant enseignant = (Enseignant) request.getSession().getAttribute("sessionUtilisateur");
		if (enseignant.isReponsableFil()) {
			Filiere filiere = filiereDao.filiereResp(enseignant.getId());
			classes = classeDao.classesFiliere(filiere.getId());
			request.setAttribute("classes", classes);
			this.getServletContext().getRequestDispatcher("/platform/Reporting.jsp").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Enseignant enseignant = (Enseignant) request.getSession().getAttribute("sessionUtilisateur");
		if (enseignant.isReponsableFil()) {
			Filiere filiere = filiereDao.filiereResp(enseignant.getId());
			classes = classeDao.classesFiliere(filiere.getId());
			if (request.getParameter("affichage") == null) {
				matieres = matiereDao.listerMatClasseFiliereAvecNote(filiere.getId(),
						Integer.parseInt(request.getParameter("classe")));
				if (request.getParameter("matiere") != null) {
					noteMatieres = noteMatiereDao.listerNoteClasseEtudaints(
							Integer.parseInt(request.getParameter("matiere")), enseignant.getId());
					request.setAttribute("noteMatieres", noteMatieres);
					request.setAttribute("size", noteMatieres.size());
				}
				request.setAttribute("matieres", matieres);
			}
			request.setAttribute("classe", request.getParameter("classe"));
			request.setAttribute("classes", classes);
			request.setAttribute("affichage", "affichage");
			this.getServletContext().getRequestDispatcher("/platform/Reporting.jsp").forward(request, response);
		}
	}
}
