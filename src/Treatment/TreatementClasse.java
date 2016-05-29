package Treatment;

import Beans.Classe;
import Beans.Filiere;
import Dao.ClasseDao;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
public class TreatementClasse {
	
	
	public void CreatClass(HttpServletRequest request, ClasseDao classeDao,ArrayList<Filiere> listfiliere){
		
		Classe classe = new Classe();
		
		
		
		classe.setNomClasse(request.getParameter("NomClasse"));
		classe.getFiliere().setNom(request.getParameter("filiere").toString());
		classe.getFiliere().setId(getFiliereByName(request.getParameter("filiere").toString(),listfiliere));		
		classe.setNiveau(request.getParameter("niveau"));
		classe.setMoyenne(0);
		classe.setAnneeUniversitaire(request.getParameter("annee"));
		
		classeDao.creer(classe);
		DisplayClass(request,classe);
		
		
	}
	
	public void DisplayClass(HttpServletRequest request, Classe classe){
		
		request.setAttribute("classe", classe);
		request.setAttribute("creation",EvryNoteUtils.FALSE);
		request.setAttribute("affichage", EvryNoteUtils.TRUE);
		
	}
	
	
	//Methode pour trouver l'id de la filiere par son nom
	public int getFiliereByName(String nom, ArrayList<Filiere> listfiliere){
		int id=0;
		for(Filiere i:listfiliere){
			if(i.getNom().equals(nom))
			id= i.getId();
		}
		return id;
		
		
	}

}
