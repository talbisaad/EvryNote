package Treatment;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import Beans.Enseignant;
import Beans.Filiere;
import Beans.Matiere;
import Dao.EnseignantDao;
import Dao.FiliereDao;
import Dao.MatiereDao;

public class TreatmentFiliere {

	String msg;

	public void creerFiliere(HttpServletRequest request, FiliereDao filiereDao, EnseignantDao enseignantDao)
			throws ServletException, IOException {
		String nomFil = request.getParameter("nomFil");
		String responsable = request.getParameter("respFil");
		Filiere filiere = new Filiere();
		if (nomFil != null && responsable != null && nomFil != "" && responsable != "") {
			int idResp = Integer.parseInt(responsable);
			filiere.setNom(nomFil);
			filiere.setRespFil(enseignantDao.trouver(idResp));
			enseignantDao.modifierDroit(idResp);
			filiereDao.creer(filiere);
		} else {
			System.out.println("la creation du filiere est echouée !!");
		}
	}

	public void ajoutMatiere(HttpServletRequest request, MatiereDao matiereDao) {
		int i = 0;
		Matiere matiere;
		while (i < 8) {
			matiere = new Matiere();
			if (request.getParameter("nomMatiere_" + i) != null && request.getParameter("coeffMatiere_" + i) != null
					&& request.getParameter("heureMatiere_" + i) != null && request.getParameter("respFil_" + i) != null
					&& request.getParameter("nomMatiere_" + i) != "" && request.getParameter("coeffMatiere_" + i) != ""
					&& request.getParameter("heureMatiere_" + i) != "" && request.getParameter("respFil_" + i) != "") {

				matiere.setNom(request.getParameter("nomMatiere_" + i));
				matiere.setCoefficient(Integer.parseInt(request.getParameter("coeffMatiere_" + i)));
				matiere.setNbrHeure(Integer.parseInt(request.getParameter("heureMatiere_" + i)));
				matiereDao.ajouter(matiere);
				matiereDao.ajouterMatEns(matiere, request.getParameter("filiere"),
						Integer.parseInt(request.getParameter("respFil_" + i)));
			} else {
				System.out.println("la creation de la matiere " + i + " est echouée !!");
			}
			i++;
		}
	}

	public ArrayList<Matiere> trouverMatFil(HttpServletRequest request, MatiereDao matiereDao) {
		ArrayList<Matiere> matieres = new ArrayList<Matiere>();
		String filiere = request.getParameter("modify");
		if (filiere != null && filiere != "") {
			matieres = matiereDao.trouverMatFil(filiere);
		}
		request.setAttribute("modification", "modification");
		return matieres;
	}

	public void modeModifMatFil(HttpServletRequest request, MatiereDao matiereDao, EnseignantDao enseignantDao) {
		ArrayList<Matiere> matieres = new ArrayList<Matiere>();
		ArrayList<Enseignant> enseignants = new ArrayList<Enseignant>();
		int i = 0;
		matieres = trouverMatFil(request, matiereDao);
		enseignants = enseignantDao.lister();
		request.setAttribute("enseignants", enseignants);
		request.setAttribute("matieres", matieres);
	}

}
