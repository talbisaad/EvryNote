package Beans;

import java.util.Date;

public class Etudiant {
	private int INE ;
	private String NomEtudiant;
	private String PrenomEtudiant;
	private  Date DateDeNaissance;
	private String EmailEtudiant;
	private int TelEtud;
	private String Niveau;
	
	public Etudiant(int iNE, String nomEtudiant, String prenomEtudiant, Date dateDeNaissance, String emailEtudiant,
			int telEtud, String niveau) {
		super();
		INE = iNE;
		NomEtudiant = nomEtudiant;
		PrenomEtudiant = prenomEtudiant;
		DateDeNaissance = dateDeNaissance;
		EmailEtudiant = emailEtudiant;
		TelEtud = telEtud;
		Niveau = niveau;
	}
	public Etudiant() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public int getINE() {
		return INE;
	}
	public void setINE(int iNE) {
		INE = iNE;
	}
	public String getNomEtudiant() {
		return NomEtudiant;
	}
	public void setNomEtudiant(String nomEtudiant) {
		NomEtudiant = nomEtudiant;
	}
	public String getPrenomEtudiant() {
		return PrenomEtudiant;
	}
	public void setPrenomEtudiant(String prenomEtudiant) {
		PrenomEtudiant = prenomEtudiant;
	}
	public Date getDateDeNaissance() {
		return DateDeNaissance;
	}
	public void setDateDeNaissance(Date dateDeNaissance) {
		DateDeNaissance = dateDeNaissance;
	}
	public String getEmailEtudiant() {
		return EmailEtudiant;
	}
	public void setEmailEtudiant(String emailEtudiant) {
		EmailEtudiant = emailEtudiant;
	}
	public int getTelEtud() {
		return TelEtud;
	}
	public void setTelEtud(int telEtud) {
		TelEtud = telEtud;
	}
	public String getNiveau() {
		return Niveau;
	}
	public void setNiveau(String niveau) {
		Niveau = niveau;
	}
	
	
	
	

}
