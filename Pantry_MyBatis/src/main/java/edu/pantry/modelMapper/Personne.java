package edu.pantry.modelMapper;

import org.apache.ibatis.type.Alias;

@Alias("Personne")
public class Personne {

	public Personne() {
		super();
		
	}

	public Personne( String nom, String prenom) {
		super();
		this.nom = nom;
		this.prenom = prenom;
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
	private int id;
	private String nom;
	private String prenom;
	

}
