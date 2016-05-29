package Dao;

import java.util.ArrayList;

import Beans.Filiere;

public interface FiliereDao {

	void creer(Filiere filiere) throws DAOException;

	Filiere trouver(String nom) throws DAOException;

	ArrayList<Filiere> lister() throws DAOException;
	
	ArrayList<Filiere> listerFilSansMat() throws DAOException;
	
	ArrayList<Filiere> listerFilAvecMat() throws DAOException;

}
