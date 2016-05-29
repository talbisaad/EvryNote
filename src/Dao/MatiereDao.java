package Dao;

import java.util.ArrayList;

import Beans.Matiere;

public interface MatiereDao {

	public void ajouter(String matiere) throws DAOException;

	public void modifier(int id, String matiere) throws DAOException;
	
	public boolean trouver(int id) throws DAOException;

	public void ajouterMatEns(Matiere matiere,int idFil) throws DAOException;

	public void modifierMatEns(Matiere matiere,int idFil) throws DAOException;
	
	public boolean trouverMatFilEns(int idmatiere,int idFil) throws DAOException;

	public ArrayList<Matiere> trouverMatFil(int id) throws DAOException;

	//public void modifierMat(Matiere matiere, String filiere) throws DAOException;

}
