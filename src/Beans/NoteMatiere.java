package Beans;

import Beans.Enseignant;
import Beans.Etudiant;
import Beans.Matiere;

public class NoteMatiere {

	private Matiere matiere;
	private Etudiant etudiant;
	private Enseignant enseignant;
	private float note;

	public NoteMatiere() {
		super();
	}

	public Matiere getMatiere() {
		return matiere;
	}

	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}

	public Etudiant getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}

	public Enseignant getEnseignant() {
		return enseignant;
	}

	public void setEnseignant(Enseignant enseignant) {
		this.enseignant = enseignant;
	}

	public float getNote() {
		return note;
	}

	public void setNote(float note) {
		this.note = note;
	}

}
