package com.fabyle.managing.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EntreeAgenda {
	
	
	public String proprietaire;
	public String client; 
	public String contribution;
	public String typeProjet;
	public String projet;
	public String planification;
	public String livrable;
	public String commentaires;
	
	public EntreeAgenda() {
		super();
		
	}
	
	// constructor
	public EntreeAgenda(String proprietaire, String client,
			String contribution, String typeProjet, String projet,
			String planification, String livrable) {
		super();
		this.proprietaire = proprietaire;
		this.client = client;
		this.contribution = contribution;
		this.typeProjet = typeProjet;
		this.projet = projet;
		this.planification = planification;		
		this.livrable = livrable;
	}
	
	
	public String getTitle(){
		return client+"/"+contribution+"/"+projet+"/"+typeProjet+"/"+planification;
	}
	
	public String getCalendar(){
		return proprietaire;		
	}
		
	
	public String getProprietaire() {
		return proprietaire;
	}
	
	
	

}
