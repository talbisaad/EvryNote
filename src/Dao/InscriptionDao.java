package Dao;

import Beans.Enseignant;
import Beans.Etudiant;

public interface InscriptionDao {
	
	Etudiant SearchStudent(int ine)throws DAOException;
	void UpdateStudent(Etudiant e)throws DAOException;
	void InsertResponsable(Enseignant enseignant) throws DAOException;

}
