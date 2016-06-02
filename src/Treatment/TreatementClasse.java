package Treatment;

import Beans.Classe;
import Beans.Filiere;
import Dao.ClasseDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

public class TreatementClasse {
	Classe classe;

	public void CreatClass(HttpServletRequest request, ClasseDao classeDao, ArrayList<Filiere> listfiliere) {

		classe = new Classe();

		classe.setNomClasse(request.getParameter("NomClasse"));
		classe.getFiliere().setNom(request.getParameter("filiere").toString());
		classe.getFiliere().setId(getFiliereByName(request.getParameter("filiere").toString(), listfiliere));
		classe.setNiveau(request.getParameter("niveau"));
		classe.setMoyenne(0);
		classe.setAnneeUniversitaire(request.getParameter("annee"));

		classeDao.CreatClass(classe);
		DisplayClass(request, classe);

	}

	public void UpdateClass(HttpServletRequest request, ClasseDao classeDao, ArrayList<Filiere> listfiliere) {

		classe = new Classe();
		Classe HiddenClasse = new Classe();

		classe.setNomClasse(request.getParameter("NomClasse"));
		classe.getFiliere().setNom(request.getParameter("filiere").toString());
		classe.getFiliere().setId(getFiliereByName(request.getParameter("filiere").toString(), listfiliere));
		classe.setNiveau(request.getParameter("niveau"));
		classe.setMoyenne(0);
		classe.setAnneeUniversitaire(request.getParameter("annee"));

		HiddenClasse.setNomClasse(request.getParameter("NomClasseHidden"));
		HiddenClasse.getFiliere().setNom(request.getParameter("filiereHidden").toString());
		HiddenClasse.getFiliere()
				.setId(getFiliereByName(request.getParameter("filiereHidden").toString(), listfiliere));
		HiddenClasse.setNiveau(request.getParameter("niveauHidden"));
		HiddenClasse.setMoyenne(0);
		HiddenClasse.setAnneeUniversitaire(request.getParameter("anneeHidden"));

		classe.setIdClasse(getIdClasseByAttributs(HiddenClasse, classeDao));

		classeDao.UpdateClass(classe);
		DisplayClass(request, classe);

	}

	// Affichage de la classe en synthese
	public void DisplayClass(HttpServletRequest request, Classe classe) {

		request.setAttribute("classe", classe);
		request.setAttribute("creation", EvryNoteUtils.FALSE);
		request.setAttribute("affichage", EvryNoteUtils.TRUE);

	}

	// Affichage de la classe en modification
	public void DisplayClassForModification(HttpServletRequest request) {
		DisplayClass(request, classe);

	}

	// Methode pour trouver l'id de la filiere par son nom
	public int getFiliereByName(String nom, ArrayList<Filiere> listfiliere) {
		int id = 0;
		for (Filiere i : listfiliere) {
			if (i.getNom().equals(nom))
				id = i.getId();
		}
		return id;
	}

	public int getIdClasseByAttributs(Classe classe, ClasseDao classeDao) {
		int id = 0;
		id = classeDao.getIdClasseByAttributs(classe);
		return id;

	}

	public ArrayList<Classe> GetClassList(ClasseDao classeDao) {

		ArrayList<Classe> classelist = classeDao.GetClassList();
		return classelist;
	}
	
	public ArrayList<Classe>  MapResultSetToClasse(ResultSet resultSet){
		
		ArrayList<Classe> classelist = new ArrayList<>();
		
		
		try {
			while(resultSet.next()){
				
				Classe classe= new Classe();
				classe.setIdClasse(resultSet.getInt("IdClasse"));
				classe.setNomClasse(resultSet.getString("NomClasse"));
				classe.setNiveau(resultSet.getString("Niveau"));
				classe.setMoyenne(resultSet.getFloat("Moyenne"));
				classe.getFiliere().setId(resultSet.getInt("IdFiliere"));
				classe.setAnneeUniversitaire(resultSet.getString("AnneeUniversitaire"));
				classelist.add(classe);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return classelist;		
	}

}
