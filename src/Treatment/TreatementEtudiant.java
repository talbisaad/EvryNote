package Treatment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import Beans.Classe;
import Beans.Etudiant;
import Beans.Filiere;
import Dao.ClasseDao;
import Dao.EtudiantDao;

public class TreatementEtudiant {

	private Classe classe;
	private TreatementClasse treatementclasse;

	public ArrayList<Etudiant> GetListOfStudent(HttpServletRequest request, ArrayList<Filiere> listfiliere, ClasseDao classeDao,
			EtudiantDao etudiantDao) {

		classe = new Classe();
		treatementclasse= new TreatementClasse();

		classe.setNomClasse(request.getParameter("NomClasse"));
		classe.getFiliere().setNom(request.getParameter("filiere").toString());
		classe.getFiliere()
				.setId(treatementclasse.getFiliereByName(request.getParameter("filiere").toString(), listfiliere));
		classe.setNiveau(request.getParameter("niveau"));
		classe.setMoyenne(0);
		classe.setAnneeUniversitaire(request.getParameter("annee"));
		classe.setIdClasse(treatementclasse.getIdClasseByAttributs(classe, classeDao));

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
}
