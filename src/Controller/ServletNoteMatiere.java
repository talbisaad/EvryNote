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
import Beans.Matiere;
import Beans.NoteMatiere;
import Dao.ClasseDao;
import Dao.DAOFactory;
import Dao.EtudiantDao;
import Dao.MatiereDao;
import Dao.NoteMatiereDao;
import Treatment.TreatementNoteMatiere;

public class ServletNoteMatiere extends HttpServlet {

	private MatiereDao matiereDao;
	private ClasseDao classeDao;
	private EtudiantDao etudiantDao;
	NoteMatiereDao noteMatiereDao;
	ArrayList<Matiere> matieres = new ArrayList<Matiere>();
	ArrayList<Classe> classes = new ArrayList<Classe>();
	ArrayList<Etudiant> etudiants = new ArrayList<Etudiant>();
	ArrayList<NoteMatiere> noteMatieres = new ArrayList<NoteMatiere>();
	TreatementNoteMatiere treatementNoteMatiere;
	private String page;

	public void init() {
		this.matiereDao = ((DAOFactory) getServletContext().getAttribute("daofactory")).getMatiereDao();
		this.classeDao = ((DAOFactory) getServletContext().getAttribute("daofactory")).getClasseDao();
		this.etudiantDao = ((DAOFactory) getServletContext().getAttribute("daofactory")).getEtudiantDao();
		this.noteMatiereDao = ((DAOFactory) getServletContext().getAttribute("daofactory")).getNoteMatiereDao();
		treatementNoteMatiere = new TreatementNoteMatiere();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		page = request.getRequestURL().substring(31);
		Enseignant enseignant = (Enseignant) request.getSession().getAttribute("sessionUtilisateur");
		if (page.equals("platform/SaisieNote")) {
			classes = classeDao.classesEnseignant(enseignant.getId());
			request.setAttribute("classes", classes);
			this.getServletContext().getRequestDispatcher("/platform/SaisieNote.jsp").forward(request, response);
		} else if (page.equals("platform/ListNote")) {
			classes = classeDao.classesEnseignant(enseignant.getId());
			request.setAttribute("classes", classes);
			this.getServletContext().getRequestDispatcher("/platform/ListNote.jsp").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		page = request.getRequestURL().substring(31);
		Enseignant enseignant = (Enseignant) request.getSession().getAttribute("sessionUtilisateur");
		if (page.equals("platform/SaisieNote")) {
			classes = classeDao.classesEnseignant(enseignant.getId());
			if (request.getParameter("insertion") != null) {
				treatementNoteMatiere.insererNoteMatiereEnseignant(request, noteMatiereDao, enseignant,
						etudiants.size());
			} else {
				matieres = matiereDao.listerMatEnsSansNote(enseignant.getId(),
						Integer.parseInt(request.getParameter("classe")));
				etudiants = etudiantDao.classeEtudiant(Integer.parseInt(request.getParameter("classe")));
				request.setAttribute("etudiants", etudiants);
				request.setAttribute("matieres", matieres);
			}
			request.setAttribute("classes", classes);
			request.setAttribute("insertion", "insertion");
			this.getServletContext().getRequestDispatcher("/platform/SaisieNote.jsp").forward(request, response);
		} else if (page.equals("platform/ListNote")) {
			classes = classeDao.classesEnseignant(enseignant.getId());
			if (request.getParameter("insertion") != null) {
				treatementNoteMatiere.modifierNoteMatiereEnseignant(request, noteMatiereDao, enseignant,
						noteMatieres.size());
			} else {
				matieres = matiereDao.listerMatEnsAvecNote(enseignant.getId(),
						Integer.parseInt(request.getParameter("classe")));
				if (request.getParameter("matiere") != null) {
					noteMatieres = noteMatiereDao.listerNoteClasseEtudaints(
							Integer.parseInt(request.getParameter("matiere")), enseignant.getId());
					request.setAttribute("noteMatieres", noteMatieres);
				}
				request.setAttribute("matieres", matieres);
			}
			request.setAttribute("classe", request.getParameter("classe"));
			request.setAttribute("classes", classes);
			request.setAttribute("insertion", "insertion");
			this.getServletContext().getRequestDispatcher("/platform/ListNote.jsp").forward(request, response);
		}
	}
}
