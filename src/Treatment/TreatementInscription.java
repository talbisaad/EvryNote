package Treatment;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import Beans.Enseignant;
import Beans.Etudiant;
import Dao.InscriptionDao;

public class TreatementInscription {

	Etudiant etudiant;
	Enseignant e;

	public Etudiant SearchStudent(HttpServletRequest request, InscriptionDao inscriptionDao) {
		etudiant = new Etudiant();

		etudiant = inscriptionDao.SearchStudent(Integer.parseInt(request.getParameter("ine")));
		return etudiant;

	}

	public void UpdateStudent(HttpServletRequest request, InscriptionDao inscriptionDao) {
		etudiant = new Etudiant();
		etudiant.setIne(Integer.parseInt(request.getParameter("idEtud")));

		try {
			java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("datenaissance"));
			etudiant.setDateDeNaissance(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		etudiant.setNomEtudiant(request.getParameter("nomEtud"));
		etudiant.setPrenomEtudiant(request.getParameter("prenomEtud"));
		etudiant.setEmailEtudiant(request.getParameter("mailEtud"));
		etudiant.setTelEtud(Integer.parseInt(request.getParameter("numEtud")));
		etudiant.setPasseword(request.getParameter("password"));
		inscriptionDao.UpdateStudent(etudiant);

	}

	public void InsertResponsable(HttpServletRequest request, InscriptionDao inscriptionDao) {
		e = new Enseignant();

		e.setNom(request.getParameter("nom"));
		e.setPrenom(request.getParameter("prenom"));
		e.setLogin(request.getParameter("login"));
		e.setMotdepasse(request.getParameter("password"));
		String[] list = request.getParameterValues("droit");

		if(list!=null &&list.length!=0){
		for (int i = 0; i < list.length; i++) {
			
			
			
			if (list[i].equals("CD")) {
				e.setChefDepart(true);
			}

			if (list[i].equals("RF")) {
				e.setReponsableFil(true);
			}
			if (list[i].equals("AD")) {
				e.setAdministratif(true);
			}
		}
		}

		inscriptionDao.InsertResponsable(e);

	}

}
