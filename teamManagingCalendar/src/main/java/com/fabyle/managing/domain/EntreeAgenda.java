package com.fabyle.managing.domain;

import java.util.ArrayList;
import java.util.List;

public class EntreeAgenda {
	
	
	public String proprietaire;
	public String client; 
	public String typeProjet;
	public String projet;
	public String planification;
	public String livrable;
	public String commentaires;
	public List<EntreeAgenda> prerequis = new ArrayList<EntreeAgenda>();
	
	public List<EntreeAgenda> getPrerequis() {
		return prerequis;
	}

	public EntreeAgenda() {
		super();
		
	}
	
	// constructor
	public EntreeAgenda(String proprietaire, String client,
			 String typeProjet, String projet,
			String planification, String livrable) {
		super();
		this.proprietaire = proprietaire;
		this.client = client;
		this.typeProjet = typeProjet;
		this.projet = projet;
		this.planification = planification;		
		this.livrable = livrable;
	}
	
	
	public String getTitle(){
		return projet+"/"+typeProjet+"/"+planification;
	}
	
	public String getCalendar(){
		return proprietaire;		
	}
		
	
	public String getProprietaire() {
		return proprietaire;
	}
	
	public void addPrerequis(EntreeAgenda entree) {
		this.prerequis.add(entree);
	}
	
	public String dumpPrerequis(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("Pré-requis :\n");
		
		for (EntreeAgenda entree : prerequis) {
			if (! entree.getProprietaire().equals(this.proprietaire)){
			buffer.append("\t"+entree.getTitle()+" réalisé par "+entree.getProprietaire());
			buffer.append("\n");			
			}
		}
		return buffer.toString();
	}
	
	
	

}
