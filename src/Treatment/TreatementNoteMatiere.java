package Treatment;

import javax.servlet.http.HttpServletRequest;

import Beans.Enseignant;
import Dao.NoteMatiereDao;

public class TreatementNoteMatiere {

	public void insererNoteMatiereEnseignant(HttpServletRequest request, NoteMatiereDao noteMatiereDao,
			Enseignant enseignant, int size) {
		int i = 0;
		while (i < size) {
			if (request.getParameter("noteMat_" + i) != null && request.getParameter("noteMat_" + i) != "") {
				int ine = Integer.parseInt(request.getParameter("ine_" + i));
				int idMatiere = Integer.parseInt(request.getParameter("matiere"));
				float note = Float.parseFloat(request.getParameter("noteMat_" + i));
				int idResponsable = enseignant.getId();
				noteMatiereDao.insererNoteMatiere(ine, idMatiere, note, idResponsable);
			} else {
				System.out.println("erreur d'insertion de la note !!");
			}
			i++;
		}
	}

	public void modifierNoteMatiereEnseignant(HttpServletRequest request, NoteMatiereDao noteMatiereDao,
			Enseignant enseignant, int size) {
		int i = 0;
		while (i < size) {
			if (request.getParameter("noteMat_" + i) != null && request.getParameter("noteMat_" + i) != "") {
				float note = Float.parseFloat(request.getParameter("noteMat_" + i));
				int ine = Integer.parseInt(request.getParameter("ine_" + i));
				int idMatiere = Integer.parseInt(request.getParameter("matiere"));
				int idResponsable = enseignant.getId();
				noteMatiereDao.modifierNoteMatiere(note, ine, idMatiere, idResponsable);
			} else {
				System.out.println("erreur de modification de la note !!");
			}
			i++;
		}
	}

}
