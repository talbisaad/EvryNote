package Dao;

import java.util.ArrayList;

import Beans.NoteMatiere;

public interface NoteMatiereDao {

	public ArrayList<NoteMatiere> listerNoteClasseEtudaints(int idMatiere, int idResponsable) throws DAOException;

	public void insererNoteMatiere(int ine, int idMatiere, float note, int idResponsable) throws DAOException;
	
	public void modifierNoteMatiere(float note, int ine, int idMatiere, int idResponsable) throws DAOException;

}
