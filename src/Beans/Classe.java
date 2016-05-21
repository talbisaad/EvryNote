package Beans;

public class Classe {
	
	private String NomClasse;
	private String Niveau;
	private String Moyenne;
	
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
	public String getMoyenne() {
		return Moyenne;
	}
	public void setMoyenne(String moyenne) {
		Moyenne = moyenne;
	}
	public Classe(String nomClasse, String niveau, String moyenne) {
		super();
		NomClasse = nomClasse;
		Niveau = niveau;
		Moyenne = moyenne;
	}
	@Override
	public String toString() {
		return "Classe [NomClasse=" + NomClasse + ", Niveau=" + Niveau + ", Moyenne=" + Moyenne + "]";
	}
	
	 
}
