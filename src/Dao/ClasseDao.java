package Dao;

import java.util.ArrayList;

import Beans.Classe;
import Beans.Filiere;

public interface ClasseDao {
	
	void CreatClass(Classe classe) throws DAOException; 
	void UpdateClass(Classe classe)throws DAOException;
	void DeleteClass(Classe classe)throws DAOException;
	int getIdClasseByAttributs(Classe classe)throws DAOException;
	ArrayList<Classe> GetClassList(ArrayList<Filiere> listfiliere)throws DAOException;
	
	// (zahir badr)
	ArrayList<Classe> classesFiliere(int idFiliere) throws DAOException;

	Classe trouverClasse(int idClasse) throws DAOException;

	ArrayList<Classe> classesEnseignant(int idEnseignant) throws DAOException;

}
