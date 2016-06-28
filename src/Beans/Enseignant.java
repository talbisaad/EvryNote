package Beans;

import java.io.Serializable;

public class Enseignant implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String nom, prenom;
	private String login;
	private String motdepasse;
	
	
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getMotdepasse() {
		return motdepasse;
	}

	public void setMotdepasse(String motdepasse) {
		this.motdepasse = motdepasse;
	}

	private boolean reponsableFil;
	private boolean chefDepart;
	private boolean administratif;
	
	public boolean isAdministratif() {
		return administratif;
	}

	public void setAdministratif(boolean administratif) {
		this.administratif = administratif;
	}

	public Enseignant(){
		
	}

	public Enseignant(int id, String nom, String prenom) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
	}

	public boolean isReponsableFil() {
		return reponsableFil;
	}

	public void setReponsableFil(boolean reponsableFil) {
		this.reponsableFil = reponsableFil;
	}

	public boolean isChefDepart() {
		return chefDepart;
	}

	public void setChefDepart(boolean chefDepart) {
		this.chefDepart = chefDepart;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	@Override
	public String toString() {
		return "Enseignant [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", reponsableFil=" +reponsableFil+ ", chefDepar ="+chefDepart+"]";
	}
	
	

}
