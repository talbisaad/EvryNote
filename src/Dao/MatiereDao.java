package Dao;

import java.util.ArrayList;

import Beans.Matiere;

public interface MatiereDao {

	public void ajouter(String matiere) throws DAOException;

	public void modifier(int id, String matiere) throws DAOException;

	public Matiere trouveById(int id) throws DAOException;

	public int trouveByNom(String nom) throws DAOException;

	public void ajouterMatEns(Matiere matiere, int idFil) throws DAOException;

	public void modifierMatEns(Matiere matiere, int idFil, int idMat) throws DAOException;

	public boolean trouverMatFilEns(int idmatiere, int idFil) throws DAOException;

	public ArrayList<Matiere> trouverMatFil(int id) throws DAOException;

	void supprimerMatieres(int idFiliere) throws DAOException;
	
	public void supprimerMatiere(int idMatiere,int coeff, int nbrHeure, int idEnseignant, int idFiliere) throws DAOException;

	public ArrayList<Matiere> listerMatEns(int idEnseignant, int idClasse) throws DAOException;

	public ArrayList<Matiere> listerMatEnsAvecNote(int idEnseignant, int idClasse) throws DAOException;

	public ArrayList<Matiere> listerMatEnsSansNote(int idEnseignant, int idClasse) throws DAOException;
	
	public ArrayList<Matiere> listerMatClasseFiliereAvecNote(int idFiliere, int idClasse) throws DAOException;

}
