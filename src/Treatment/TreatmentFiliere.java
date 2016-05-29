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

	// Creation de la filiere
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

	// Ajout des matieres de la filiere
	public void ajoutMatiere(HttpServletRequest request, MatiereDao matiereDao, EnseignantDao enseignantDao) {
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
				matiere.setEnseignant(enseignantDao.trouver(Integer.parseInt(request.getParameter("respFil_" + i))));
				matiereDao.ajouter(request.getParameter("nomMatiere_" + i));
				matiereDao.ajouterMatEns(matiere, Integer.parseInt(request.getParameter("filiere")));
			} else {
				System.out.println("la creation de la matiere " + i + " est echouée !!");
			}
			i++;
		}
	}

	// Lister et gerer les filieres
	public ArrayList<Matiere> trouverMatFil(HttpServletRequest request, MatiereDao matiereDao, FiliereDao filiereDao) {
		ArrayList<Matiere> matieres = new ArrayList<Matiere>();
		Filiere filiere = null;
		if (request.getParameter("modify") != null && request.getParameter("modify") != "") {
			int idfiliere = Integer.parseInt(request.getParameter("modify"));
			if (idfiliere != 0) {
				matieres = matiereDao.trouverMatFil(idfiliere);
				filiere = filiereDao.trouver(idfiliere);
			}
			request.setAttribute("filiere", filiere);
			request.setAttribute("modification", "modification");
		} else if (request.getParameter("delete") != null && request.getParameter("delete") != "") {
			int idfiliere = Integer.parseInt(request.getParameter("delete"));
			if (idfiliere != 0) {
				matieres = matiereDao.trouverMatFil(idfiliere);
			}
		}
		return matieres;
	}

	// mode modification
	public void modeModifMatFil(HttpServletRequest request, MatiereDao matiereDao, EnseignantDao enseignantDao,
			FiliereDao filiereDao) {
		ArrayList<Matiere> matieres = new ArrayList<Matiere>();
		ArrayList<Enseignant> enseignants = new ArrayList<Enseignant>();
		matieres = trouverMatFil(request, matiereDao, filiereDao);
		for (Matiere matiere : matieres) {
			System.out.println("matiere : ");
			System.out.println(matiere.getEnseignant());
		}
		enseignants = enseignantDao.lister();
		request.setAttribute("enseignants", enseignants);
		request.setAttribute("matieres", matieres);

	}

	// modification de la filiere
	public void modifierFiliere(HttpServletRequest request, MatiereDao matiereDao, EnseignantDao enseignantDao) {
		int i = 0;
		Matiere matiere;
		while (i < 8) {
			matiere = new Matiere();

			if (request.getParameter("idMatiere_" + i) != null && request.getParameter("nomMatiere_" + i) != null
					&& request.getParameter("coeffMatiere_" + i) != null
					&& request.getParameter("heureMatiere_" + i) != null && request.getParameter("respFil_" + i) != null
					&& request.getParameter("nomMatiere_" + i) != "" && request.getParameter("coeffMatiere_" + i) != ""
					&& request.getParameter("heureMatiere_" + i) != "" && request.getParameter("respFil_" + i) != ""
					&& request.getParameter("idMatiere_" + i) != "") {

				matiere.setId(Integer.parseInt(request.getParameter("idMatiere_" + i)));
				matiere.setNom(request.getParameter("nomMatiere_" + i));
				matiere.setCoefficient(Integer.parseInt(request.getParameter("coeffMatiere_" + i)));
				matiere.setNbrHeure(Integer.parseInt(request.getParameter("heureMatiere_" + i)));
				matiere.setEnseignant(enseignantDao.trouver(Integer.parseInt(request.getParameter("respFil_" + i))));
				// on test si cette matiere existe dans la liste de la filiere
				// si oui on va la modifier sinon on va l'ajouter

				matiereDao.modifier(Integer.parseInt(request.getParameter("idMatiere_" + i)),
						request.getParameter("nomMatiere_" + i));
				matiereDao.modifierMatEns(matiere, Integer.parseInt(request.getParameter("idFil")));
			} else {
				System.out.println("la modification de la matiere " + i + " est echouée !!");
			}
			i++;
		}
	}

	// la suppression d'une filiere
	public void supprimerFiliere() {

	}

}
