package com.fabyle.team.managing.teamManagingDomain.domain;

import java.util.List;

public class Step {
	
	
	private String nom;
	private String type;
	private List<Step> prerequis;
	private List<Planification> planification;
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public List<Step> getPrerequis() {
		return prerequis;
	}
	public void setPrerequis(List<Step> prerequis) {
		this.prerequis = prerequis;
	}
	public List<Planification> getPlanification() {
		return planification;
	}
	public void setPlanification(List<Planification> planification) {
		this.planification = planification;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	
	
	

}
