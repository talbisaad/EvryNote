package Treatment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import Beans.Classe;
import Beans.Etudiant;
import Beans.Filiere;
import Dao.ClasseDao;
import Dao.EtudiantDao;

public class TreatementEtudiant {

	private Classe classe;
	private Etudiant etudiant;
	private TreatementClasse treatementclasse;

	public void AddStudent(HttpServletRequest request, EtudiantDao etudiantDao) {
		etudiant = new Etudiant();
		etudiant = MapRequestToObject(request);
		etudiantDao.AddStudent(etudiant);
	}

	public void UpdateStudent(HttpServletRequest request, EtudiantDao etudiantDao) {
		etudiant = new Etudiant();
		etudiant = MapRequestToObject(request);
		etudiantDao.UpdateStudent(etudiant);

	}

	public void DisplayStudentForModify(HttpServletRequest request, ArrayList<Etudiant> listetudiant) {

		etudiant = new Etudiant();
		etudiant = getEtudiantFromList(request, listetudiant);
		request.setAttribute("etudiant", etudiant);

	}

	public Etudiant getEtudiantFromList(HttpServletRequest request, ArrayList<Etudiant> listetudiant) {

		for (Etudiant i : listetudiant) {
			if (i.getIne() == Integer.parseInt(request.getParameter("IdRow"))) {
				return i;
			}
		}
		return null;

	}

	public ArrayList<Etudiant> GetListOfStudent(HttpServletRequest request, ArrayList<Filiere> listfiliere,
			ClasseDao classeDao, EtudiantDao etudiantDao, Boolean var) {
		if (!var) {
			classe = new Classe();
			treatementclasse = new TreatementClasse();

			classe.setNomClasse(request.getParameter("NomClasse"));
			classe.getFiliere().setNom(request.getParameter("filiere").toString());
			classe.getFiliere()
					.setId(treatementclasse.getFiliereByName(request.getParameter("filiere").toString(), listfiliere));
			classe.setNiveau(request.getParameter("niveau"));
			classe.setMoyenne(0);
			classe.setAnneeUniversitaire(request.getParameter("annee"));
			classe.setIdClasse(treatementclasse.getIdClasseByAttributs(classe, classeDao));
		}
		if (classe.getIdClasse() != 0) {
			return etudiantDao.GetListOfStudent(classe.getIdClasse());
		}
		return null;
	}

	public ArrayList<Etudiant> MapResultSetToClasse(ResultSet resultSet) {
		ArrayList<Etudiant> listetudiant = new ArrayList<Etudiant>();

		try {
			while (resultSet.next()) {

				Etudiant etudiant = new Etudiant();
				etudiant.setIne(resultSet.getInt("INE"));
				etudiant.setNomEtudiant(resultSet.getString("NomEtudiant"));
				etudiant.setPrenomEtudiant(resultSet.getString("PrenomEtudiant"));
				etudiant.setDateDeNaissance(resultSet.getDate("DateDeNaissance"));
				etudiant.setTelEtud(resultSet.getInt("TelEtud"));
				etudiant.setEmailEtudiant(resultSet.getString("EmailEtudiant"));
				etudiant.getClasse().setIdClasse(resultSet.getInt("IdClasse"));
				listetudiant.add(etudiant);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listetudiant;

	}

	public Etudiant MapRequestToObject(HttpServletRequest request) {
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
		etudiant.getClasse().setIdClasse(classe.getIdClasse());
		return etudiant;
	}

}
