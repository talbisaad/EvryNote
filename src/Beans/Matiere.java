package Beans;

public class Matiere {
	
	private String nom;
	private int coefficient;
	private int nbrHeure;
	
	public Matiere() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Matiere(String nom, int coefficient, int nbrHeure) {
		super();
		this.nom = nom;
		this.coefficient = coefficient;
		this.nbrHeure = nbrHeure;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getCoefficient() {
		return coefficient;
	}

	public void setCoefficient(int coefficient) {
		this.coefficient = coefficient;
	}

	public int getNbrHeure() {
		return nbrHeure;
	}

	public void setNbrHeure(int nbrHeure) {
		this.nbrHeure = nbrHeure;
	}
	
	
	
	

}