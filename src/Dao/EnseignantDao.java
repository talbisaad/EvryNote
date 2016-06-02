package Dao;

import java.util.ArrayList;

import Beans.Enseignant;

public interface EnseignantDao {

	void creer(Enseignant enseignant) throws DAOException;

	Enseignant trouverById(int id) throws DAOException;
	
	Enseignant trouverByNom(String nom) throws DAOException;
	
	Enseignant trouverByLogin(String login) throws DAOException;
	
	Enseignant trouverEnsMat(String matiere) throws DAOException;

	ArrayList<Enseignant> listerEns() throws DAOException;
	
	ArrayList<Enseignant> lister() throws DAOException;

	Enseignant modifier(int id) throws DAOException;
	
	public void modifierDroit(int id,boolean RF, boolean CD) throws DAOException;

}
