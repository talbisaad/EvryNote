package Beans;

import java.util.Date;

public class Etudiant {
	private int ine;
	private String nomEtudiant;
	private String prenomEtudiant;
	private Date dateDeNaissance;
	private String emailEtudiant;
	private int telEtud;
	private Classe classe;
	private String passeword;
	private String login;
	private boolean active;

	public Etudiant(int ine, String nomEtudiant, String prenomEtudiant, Date dateDeNaissance, String emailEtudiant,
			int telEtud, Classe classe, String passeword, String login, boolean active) {
		super();
		this.ine = ine;
		this.nomEtudiant = nomEtudiant;
		this.prenomEtudiant = prenomEtudiant;
		this.dateDeNaissance = dateDeNaissance;
		this.emailEtudiant = emailEtudiant;
		this.telEtud = telEtud;
		this.classe = classe;
		this.passeword = passeword;
		this.login = login;
		this.active = active;
	}

	public Etudiant() {
		super();
		// TODO Auto-generated constructor stub
		classe=new Classe();
	}

	public int getIne() {
		return ine;
	}

	public void setIne(int ine) {
		this.ine = ine;
	}

	public String getNomEtudiant() {
		return nomEtudiant;
	}

	public void setNomEtudiant(String nomEtudiant) {
		this.nomEtudiant = nomEtudiant;
	}

	public String getPrenomEtudiant() {
		return prenomEtudiant;
	}

	public void setPrenomEtudiant(String prenomEtudiant) {
		this.prenomEtudiant = prenomEtudiant;
	}

	public Date getDateDeNaissance() {
		return dateDeNaissance;
	}

	public void setDateDeNaissance(Date dateDeNaissance) {
		this.dateDeNaissance = dateDeNaissance;
	}

	public String getEmailEtudiant() {
		return emailEtudiant;
	}

	public void setEmailEtudiant(String emailEtudiant) {
		this.emailEtudiant = emailEtudiant;
	}

	public int getTelEtud() {
		return telEtud;
	}

	public void setTelEtud(int telEtud) {
		this.telEtud = telEtud;
	}

	public Classe getClasse() {
		return classe;
	}

	public void setClasse(Classe classe) {
		this.classe = classe;
	}

	public String getPasseword() {
		return passeword;
	}

	public void setPasseword(String passeword) {
		this.passeword = passeword;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
