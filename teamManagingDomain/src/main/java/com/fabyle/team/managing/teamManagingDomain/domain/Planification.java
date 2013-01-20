package com.fabyle.team.managing.teamManagingDomain.domain;

import java.util.Date;

public class Planification {
	
	private Date dateDebut;
	private Resource ressource;
	private Date dateFin;
	private String commentaires;
	
	public Date getDateDebut() {
		return dateDebut;
	}
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}
	public Resource getRessource() {
		return ressource;
	}
	public void setRessource(Resource ressource) {
		this.ressource = ressource;
	}
	public Date getDateFin() {
		return dateFin;
	}
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}
	public String getCommentaires() {
		return commentaires;
	}
	public void setCommentaires(String commentaires) {
		this.commentaires = commentaires;
	}
	

}
