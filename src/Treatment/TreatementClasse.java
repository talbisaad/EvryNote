package Treatment;

import Beans.Classe;
import Dao.ClasseDao;
import javax.servlet.http.HttpServletRequest;
public class TreatementClasse {
	
	
	public void CreatClass(HttpServletRequest request, ClasseDao classeDao){
		
		Classe classe = new Classe();
		
		
		
		classe.setNomClasse(request.getParameter("NomClasse"));
		classe.setIdFiliere(request.getParameter("filiere").toString());
		classe.setNiveau(request.getParameter("niveau"));
		classe.setMoyenne(0);
		classe.setAnneeUniversitaire(request.getParameter("annee"));
		classeDao.creer(classe);
		
	}

}
