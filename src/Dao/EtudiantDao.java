package Dao;

import java.util.ArrayList;

import Beans.Etudiant;

public interface EtudiantDao {

	
	ArrayList<Etudiant> GetListOfStudent(int idClass)throws DAOException;
	void AddStudent(Etudiant e)throws DAOException;
	void UpdateStudent(Etudiant e)throws DAOException;
	
}
