package Dao;

import java.util.ArrayList;

import Beans.Etudiant;

public interface EtudiantDao {

	
	ArrayList<Etudiant> GetListOfStudent(int idClass)throws DAOException;
}
