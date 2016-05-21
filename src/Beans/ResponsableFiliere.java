package Beans;

public class ResponsableFiliere extends Enseignant{

	public ResponsableFiliere() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ResponsableFiliere(int id, String nom, String prenom) {
		super(id, nom, prenom);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "ResponsableFiliere [getId()=" + getId() + ", getNom()=" + getNom() + ", getPrenom()=" + getPrenom()
				+ "]";
	}

	

	
}
