package Beans;

public class ChefDepartement extends Enseignant {

	private String nomDepartement;

	public ChefDepartement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ChefDepartement(int id, String nom, String prenom) {
		super(id, nom, prenom);
		// TODO Auto-generated constructor stub
	}

	public ChefDepartement(int id, String nom, String prenom, String nomDepartement) {
		super(id, nom, prenom);
		this.nomDepartement = nomDepartement;
	}

	public String getNomDepartement() {
		return nomDepartement;
	}

	public void setNomDepartement(String nomDepartement) {
		this.nomDepartement = nomDepartement;
	}

	@Override
	public String toString() {
		return "ChefDepartement [getId()=" + getId() + ", getNom()=" + getNom()
				+ ", getPrenom()=" + getPrenom() + " nomDepartement=" + nomDepartement + "]";
	}

	
}
