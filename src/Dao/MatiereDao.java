package Dao;

import java.util.ArrayList;

import Beans.Matiere;

public interface MatiereDao {

	public void ajouter(Matiere matiere) throws DAOException;

	public void ajouterMatEns(Matiere matiere, String filiere, int idResp) throws DAOException;
	
	public ArrayList<Matiere> trouverMatFil(String filiere) throws DAOException;

}
