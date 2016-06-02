package Treatment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
		String niveau = request.getParameter("niveau");
		Filiere filiere = new Filiere();
		if (nomFil != null && responsable != null && niveau != null && nomFil != "" && responsable != ""
				&& niveau != "") {
			int idResp = Integer.parseInt(responsable);
			filiere.setNom(nomFil);
			filiere.setRespFil(enseignantDao.trouverById(idResp));
			filiere.setNiveau(niveau);
			enseignantDao.modifierDroit(idResp, true, false);
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
				matiere.setProf(enseignantDao.trouverById(Integer.parseInt(request.getParameter("respFil_" + i))));
				int idMat = matiereDao.trouveByNom(request.getParameter("nomMatiere_" + i));
				if (idMat == 0) {
					matiereDao.ajouter(request.getParameter("nomMatiere_" + i));
					matiere.setId(matiereDao.trouveByNom(request.getParameter("nomMatiere_" + i)));
					matiereDao.ajouterMatEns(matiere, Integer.parseInt(request.getParameter("filiere")));
				} else if (idMat != 0) {
					matiere.setId(matiereDao.trouveByNom(request.getParameter("nomMatiere_" + i)));
					matiereDao.ajouterMatEns(matiere, Integer.parseInt(request.getParameter("filiere")));
				}
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
		}
		return matieres;
	}

	// mode modification
	public void modeModifMatFil(HttpServletRequest request, MatiereDao matiereDao, EnseignantDao enseignantDao,
			FiliereDao filiereDao) {
		ArrayList<Matiere> matieres = new ArrayList<Matiere>();
		ArrayList<Enseignant> enseignants = new ArrayList<Enseignant>();
		matieres = trouverMatFil(request, matiereDao, filiereDao);
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

			System.out.println(request.getParameter("nomMatiere_" + i));
			System.out.println(request.getParameter("coeffMatiere_" + i));
			System.out.println(request.getParameter("heureMatiere_" + i));
			System.out.println(request.getParameter("respFil_" + i));
			System.out.println();

			if (request.getParameter("nomMatiere_" + i) != null && request.getParameter("coeffMatiere_" + i) != null
					&& request.getParameter("heureMatiere_" + i) != null && request.getParameter("respFil_" + i) != null
					&& request.getParameter("nomMatiere_" + i) != "" && request.getParameter("coeffMatiere_" + i) != ""
					&& request.getParameter("heureMatiere_" + i) != "" && request.getParameter("respFil_" + i) != "") {

				matiere.setNom(request.getParameter("nomMatiere_" + i));
				matiere.setCoefficient(Integer.parseInt(request.getParameter("coeffMatiere_" + i)));
				matiere.setNbrHeure(Integer.parseInt(request.getParameter("heureMatiere_" + i)));
				matiere.setProf(enseignantDao.trouverById(Integer.parseInt(request.getParameter("respFil_" + i))));
				// on test si cette matiere existe dans la liste de la filiere
				// si oui on va la modifier sinon on va l'ajouter

				if (request.getParameter("idMatiere_" + i) != null && request.getParameter("idMatiere_" + i) != "") {

					if (!matiereDao.trouverMatFilEns(Integer.parseInt(request.getParameter("idMatiere_" + i)),
							Integer.parseInt(request.getParameter("idFil")))
							&& !matiereDao.trouveById(Integer.parseInt(request.getParameter("idMatiere_" + i)))) {

						matiereDao.modifier(Integer.parseInt(request.getParameter("idMatiere_" + i)),
								request.getParameter("nomMatiere_" + i));
						matiereDao.modifierMatEns(matiere, Integer.parseInt(request.getParameter("idFil")),
								Integer.parseInt(request.getParameter("idMatiere_" + i)));

					}
				} else if (request.getParameter("idMatiere_" + i) == null
						|| request.getParameter("idMatiere_" + i) == "") {
					int idMat = matiereDao.trouveByNom(request.getParameter("nomMatiere_" + i));
					if (idMat == 0) {
						matiereDao.ajouter(request.getParameter("nomMatiere_" + i));
						matiere.setId(matiereDao.trouveByNom(request.getParameter("nomMatiere_" + i)));
						matiereDao.ajouterMatEns(matiere, Integer.parseInt(request.getParameter("idFil")));
					} else if (idMat != 0) {
						matiere.setId(matiereDao.trouveByNom(request.getParameter("nomMatiere_" + i)));
						matiereDao.ajouterMatEns(matiere, Integer.parseInt(request.getParameter("idFil")));
					}

				}

			} else {
				System.out.println("la modification de la matiere " + i + " est echouée !!");
			}
			i++;
		}
	}

	// la suppression d'une filiere
	public void supprimerFiliere(HttpServletRequest request, MatiereDao matiereDao, EnseignantDao enseignantDao,
			FiliereDao filiereDao) {
		if (request.getParameter("delete") != null && request.getParameter("delete") != "") {
			int idFiliere = Integer.parseInt(request.getParameter("delete"));
			Filiere filiere = filiereDao.trouver(idFiliere);
			filiereDao.supprimerFiliere(idFiliere);
			matiereDao.supprimerMatieres(idFiliere);
			enseignantDao.modifierDroit(filiere.getRespFil().getId(), false, false);
		}

	}

	// Partie UPLOAD pour ajouter des matieres automatiquement

	public void upload(String file, HttpServletRequest request, MatiereDao matiereDao, EnseignantDao enseignantDao)
			throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		String ligne = null;
		int i = 0;
		Matiere matiere = new Matiere();
		while ((ligne = br.readLine()) != null) {
			// Retourner la ligne dans un tableau
			String[] data = ligne.split(",");

			// Afficher le contenu du tableau
			for (String val : data) {
				switch (i) {
				case 0:
					matiere.setNom(val);
					i++;
					break;
				case 1:
					matiere.setCoefficient(Integer.parseInt(val));
					i++;
					break;
				case 2:
					matiere.setNbrHeure(Integer.parseInt(val));
					i++;
					break;
				case 3:
					matiere.setProf(enseignantDao.trouverById(Integer.parseInt(val)));
					i++;
					break;
				}
				if (i == 4) {
					int idMat = matiereDao.trouveByNom(matiere.getNom());
					if (idMat == 0) {
						matiereDao.ajouter(matiere.getNom());
						matiere.setId(matiereDao.trouveByNom(matiere.getNom()));
						matiereDao.ajouterMatEns(matiere, Integer.parseInt(request.getParameter("filiere")));
					} else if (idMat != 0) {
						matiere.setId(matiereDao.trouveByNom(matiere.getNom()));
						matiereDao.ajouterMatEns(matiere, Integer.parseInt(request.getParameter("filiere")));
					}
					matiere = new Matiere();
					i = 0;
				}
			}
		}
		br.close();
	}

}
