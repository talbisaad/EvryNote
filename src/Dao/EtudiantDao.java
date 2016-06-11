package Dao;

import java.util.ArrayList;

import Beans.Etudiant;

public interface EtudiantDao {

	ArrayList<Etudiant> GetListOfStudent(int idClass) throws DAOException;

	void AddStudent(Etudiant e) throws DAOException;

	void UpdateStudent(Etudiant e) throws DAOException;

	void DeleteStudent(Etudiant e) throws DAOException;

	// zahir badr
	public ArrayList<Etudiant> classeEtudiant(int idClasse) throws DAOException;

	public Etudiant trouverEtudiant(int idEtudaint) throws DAOException;

}
