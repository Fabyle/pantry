package com.fabyle.managing.domain;

import java.util.Date;

public class GantEntry {
	public Date getDateDebut() {
		return dateDebut;
	}
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}
	public Date getDateFin() {
		return dateFin;
	}
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}
	public String getTache() {
		return Tache;
	}
	public void setTache(String tache) {
		Tache = tache;
	}
	public String getProprietaire() {
		return proprietaire;
	}
	public void setProprietaire(String propriétaire) {
		this.proprietaire = propriétaire;
	}
	Date dateDebut;
	Date dateFin;
	String Tache;
	String proprietaire;
	

}
