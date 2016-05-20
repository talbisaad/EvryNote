package Beans;

import java.util.ArrayList;

public class Filiere {

	String nom;
	ArrayList<Matiere> matieres;
	ArrayList<Classe> classes;
	ChefDepartement chefDep;
	ResponsableFiliere respFil;

	public Filiere() {
	}

	public Filiere(String nom,ResponsableFiliere respFil) {
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

	public ChefDepartement getChefDep() {
		return chefDep;
	}

	public void setChefDep(ChefDepartement chefDep) {
		this.chefDep = chefDep;
	}

	public ResponsableFiliere getRespFil() {
		return respFil;
	}

	public void setRespFil(ResponsableFiliere respFil) {
		this.respFil = respFil;
	}
	
	

}
