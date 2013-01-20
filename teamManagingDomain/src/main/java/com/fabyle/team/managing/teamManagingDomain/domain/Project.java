package com.fabyle.team.managing.teamManagingDomain.domain;

import java.util.Date;
import java.util.List;

public class Project {

	private String nom;
	private Date dateFinContrainte;
	private String commentaires;
	private List<Step> etapes;
	private List<Project> prerequis;

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getCommentaires() {
		return commentaires;
	}

	public void setCommentaires(String commentaires) {
		this.commentaires = commentaires;
	}

	public Date getDateFinContrainte() {
		return dateFinContrainte;
	}

	public void setDateFinContrainte(Date dateFinContrainte) {
		this.dateFinContrainte = dateFinContrainte;
	}

	public List<Project> getPrerequis() {
		return prerequis;
	}

	public void setPrerequis(List<Project> prerequis) {
		this.prerequis = prerequis;
	}

	public List<Step> getEtapes() {
		return etapes;
	}

	public void setEtapes(List<Step> etapes) {
		this.etapes = etapes;
	}

}
