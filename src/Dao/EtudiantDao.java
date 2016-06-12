package Dao;

import java.util.ArrayList;

import Beans.Etudiant;
import Beans.Filiere;
import Beans.Matiere;

public interface EtudiantDao {

	
	ArrayList<Etudiant> GetListOfStudent(int idClass)throws DAOException;
	void AddStudent(Etudiant e)throws DAOException;
	void UpdateStudent(Etudiant e)throws DAOException;
	void DeleteStudent(Etudiant e)throws DAOException;
	ArrayList<Matiere> GetMatiereForSimulate(Filiere filiere)throws DAOException;
	
}
