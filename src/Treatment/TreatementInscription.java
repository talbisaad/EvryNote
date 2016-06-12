package Treatment;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import Beans.Etudiant;
import Dao.InscriptionDao;

public class TreatementInscription {

	Etudiant etudiant;

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

}
