package Treatment;

import javax.servlet.http.HttpServletRequest;

import Dao.EnseignantDao;

public class TreatementListEnseignant {

	public void supprimerEnseignant(HttpServletRequest request, EnseignantDao enseignantDao) {
		if (request.getParameter("delete") != null && request.getParameter("delete") != "") {
			int idResponsable = Integer.parseInt(request.getParameter("delete"));
			enseignantDao.modifierRespFil(idResponsable);
			enseignantDao.supprimerDroit(idResponsable);
			enseignantDao.supprimerEnsMat(idResponsable);
			enseignantDao.supprimerEns(idResponsable);
		} else {
			System.out.println("Erreur de la suppression de l'enseignant");
		}
	}

}
