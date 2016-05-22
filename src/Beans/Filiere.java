package Beans;

import java.io.Serializable;
import java.util.ArrayList;

public class Filiere implements Serializable {

	String nom;
	ArrayList<Matiere> matieres;
	ArrayList<Classe> classes;
	Enseignant respFil;

	public Filiere() {
	}

	public Filiere(String nom, Enseignant respFil) {
		this.nom = nom;
		this.respFil = respFil;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public ArrayList<Matiere> getMatieres() {
		return matieres;
	}

	public void setMatieres(ArrayList<Matiere> matieres) {
		this.matieres = matieres;
	}

	public ArrayList<Classe> getClasses() {
		return classes;
	}

	public void setClasses(ArrayList<Classe> classes) {
		this.classes = classes;
	}

	public Enseignant getRespFil() {
		return respFil;
	}

	public void setRespFil(Enseignant respFil) {
		this.respFil = respFil;
	}

	@Override
	public String toString() {
		return "Filiere [nom=" + nom + ", matieres=" + matieres + ", classes=" + classes + ", respFil=" + respFil + "]";
	}

}
