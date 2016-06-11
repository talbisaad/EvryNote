package Treatment;

import Dao.EnseignantDao;

import javax.servlet.http.HttpServletRequest;

import Beans.Enseignant;

public class TreatementAuth {

	public Enseignant login(HttpServletRequest request, EnseignantDao enseignantDao) {

		String login = getValeurChamp(request, "login");
		String pass = getValeurChamp(request, "pass");
		String loginE = "";
		String passE = "";
		String resultat = "";
		Enseignant enseignant = new Enseignant();

		enseignant = validationLogin(login, enseignantDao, enseignant);

		if (enseignant == null) {
			loginE = "Echec de la connexion login introuvable";
			request.setAttribute("loginE", loginE);
			return null;
		}

		enseignant = validationPass(pass, enseignantDao, enseignant);

		if (enseignant == null) {
			passE = "Echec de la connexion erreur du mot de passe";
			request.setAttribute("passE", passE);
			return null;
		}
		
		resultat = "Bonjour Mr. "+enseignant.getNom() + enseignant.getPrenom();
		request.setAttribute("resultat", resultat);

		return enseignant;
	}

	private Enseignant validationLogin(String login, EnseignantDao enseignantDao, Enseignant enseignant) {
		enseignant = enseignantDao.trouverByLogin(login);
		if (enseignant == null) {
			return null;
		} else {
			return enseignant;
		}

	}

	private Enseignant validationPass(String pass, EnseignantDao enseignantDao, Enseignant enseignant) {
		if (enseignant.getPassword().equals(pass)) {
			return enseignant;
		} else {
			return null;
		}
	}

	private static String getValeurChamp(HttpServletRequest request, String nomChamp) {
		String valeur = request.getParameter(nomChamp);
		if (valeur == null || valeur.trim().length() == 0) {
			return null;
		} else {
			return valeur;
		}
	}

}
