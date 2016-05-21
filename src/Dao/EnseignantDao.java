package Dao;

import java.util.ArrayList;

import Beans.Enseignant;

public interface EnseignantDao {

	void creer(Enseignant enseignant) throws DAOException;

	ArrayList<Enseignant> trouver() throws DAOException;

	Enseignant modifier(int id) throws DAOException;

}