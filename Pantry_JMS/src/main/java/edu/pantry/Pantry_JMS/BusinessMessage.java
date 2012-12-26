package edu.pantry.Pantry_JMS;

import java.io.Serializable;

public class BusinessMessage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9156063125762315765L;
	/**
	 * @param nom
	 * @param prenom
	 */
	public BusinessMessage(String nom, String prenom) {
		super();
		this.nom = nom;
		this.prenom = prenom;
	}
	
	public String nom;
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
	public String prenom;
}
