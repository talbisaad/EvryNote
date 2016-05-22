package Treatment;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.Filiere;
import Beans.ResponsableFiliere;
import Dao.EnseignantDao;
import Dao.FiliereDao;

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

}
