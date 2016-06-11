package Dao;

import java.util.ArrayList;

import Beans.Filiere;

public interface FiliereDao {

	void creer(Filiere filiere) throws DAOException;

	Filiere trouver(int id) throws DAOException;

	ArrayList<Filiere> lister() throws DAOException;

	ArrayList<Filiere> listerFilSansMat(int idResponsable) throws DAOException;

	ArrayList<Filiere> listerFilAvecMat() throws DAOException;

	void supprimerFiliere(int idFiliere) throws DAOException;

	ArrayList<Filiere> listerFilAvecMatResp(int id) throws DAOException;

	public void modifierFiliere(String nomFiliere, int idResponsable, String niveau, int idFiliere) throws DAOException;

	public Filiere filiereResp(int idResponsable) throws DAOException;

}
