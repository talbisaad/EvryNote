package Dao;

import Beans.Filiere;

public interface FiliereDao {
	
	void creer( Filiere filiere ) throws DAOException;


    Filiere trouver( String nom ) throws DAOException;

}
