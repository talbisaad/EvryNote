package Dao;

import java.util.ArrayList;

import Beans.Matiere;

public interface MatiereDao {

	public void ajouter(String matiere) throws DAOException;

	public void ajouterMatEns(Matiere matiere, int idEns , int idFil) throws DAOException;
	
	public ArrayList<Matiere> trouverMatFil(int id) throws DAOException;
	
	public void modifierMat(Matiere matiere, String filiere) throws DAOException;

}
