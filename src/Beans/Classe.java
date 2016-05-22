package Beans;

public class Classe {

	private String NomClasse;
	private String Niveau;
	private float Moyenne;
	private String AnneeUniversitaire;
	private String IdFiliere;

	public Classe() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Classe( String nomClasse, String niveau, float moyenne, String anneeUniversitaire,
			String idFiliere) {
		super();
		NomClasse = nomClasse;
		Niveau = niveau;
		Moyenne = moyenne;
		AnneeUniversitaire = anneeUniversitaire;
		IdFiliere = idFiliere;
	}


	public String getNomClasse() {
		return NomClasse;
	}

	public void setNomClasse(String nomClasse) {
		NomClasse = nomClasse;
	}

	public String getNiveau() {
		return Niveau;
	}

	public void setNiveau(String niveau) {
		Niveau = niveau;
	}

	public float getMoyenne() {
		return Moyenne;
	}

	public void setMoyenne(float moyenne) {
		Moyenne = moyenne;
	}

	public String getAnneeUniversitaire() {
		return AnneeUniversitaire;
	}

	public void setAnneeUniversitaire(String anneeUniversitaire) {
		AnneeUniversitaire = anneeUniversitaire;
	}

	public String getIdFiliere() {
		return IdFiliere;
	}

	public void setIdFiliere(String idFiliere) {
		IdFiliere = idFiliere;
	}

	@Override
	public String toString() {
		return "Classe [NomClasse=" + NomClasse + ", Niveau=" + Niveau + ", Moyenne=" + Moyenne
				+ ", AnneeUniversitaire=" + AnneeUniversitaire + ", IdFiliere=" + IdFiliere + "]";
	}

}
