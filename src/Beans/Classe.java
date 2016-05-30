package Beans;

public class Classe {

	private String nomClasse;
	private String niveau;
	private float moyenne;
	private String anneeUniversitaire;
	private Filiere filiere;
//
//	public String getNomFiliere() {
//		return nomFiliere;
//	}
//
//	public void setNomFiliere(String nomFiliere) {
//		this.nomFiliere = nomFiliere;
//	}

	public Classe() {
		super();
		// TODO Auto-generated constructor stub
		filiere= new Filiere();
	}

	public String getNomClasse() {
		return nomClasse;
	}

	public void setNomClasse(String nomClasse) {
		this.nomClasse = nomClasse;
	}

	public String getNiveau() {
		return niveau;
	}

	public void setNiveau(String niveau) {
		this.niveau = niveau;
	}

	public float getMoyenne() {
		return moyenne;
	}

	public void setMoyenne(float moyenne) {
		this.moyenne = moyenne;
	}

	public String getAnneeUniversitaire() {
		return anneeUniversitaire;
	}

	public void setAnneeUniversitaire(String anneeUniversitaire) {
		this.anneeUniversitaire = anneeUniversitaire;
	}
//
//	public int getIdFiliere() {
//		return idFiliere;
//	}
//
//	public void setIdFiliere(int idFiliere) {
//		this.idFiliere = idFiliere;
//	}

	@Override
	public String toString() {
		return "Classe [nomClasse=" + nomClasse + ", niveau=" + niveau + ", moyenne=" + moyenne
				+ ", anneeUniversitaire=" + anneeUniversitaire + ", idFiliere=";
	}

	public Filiere getFiliere() {
		return filiere;
	}

	public void setFiliere(Filiere filiere) {
		this.filiere = filiere;
	}
	
}