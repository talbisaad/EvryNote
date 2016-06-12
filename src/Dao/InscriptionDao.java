package Dao;

import Beans.Etudiant;

public interface InscriptionDao {
	
	Etudiant SearchStudent(int ine)throws DAOException;
	void UpdateStudent(Etudiant e)throws DAOException;

}
