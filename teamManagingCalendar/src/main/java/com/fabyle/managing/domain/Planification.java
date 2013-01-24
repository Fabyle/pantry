package com.fabyle.managing.domain;

import java.util.Date;

public class Planification {
	
	
	Date dateDebut;
	Date dateFebut;
	EntreeAgenda entree;

	public Planification(Date dateDebut, Date dateFebut, EntreeAgenda entree) {
		super();
		this.dateDebut = dateDebut;
		this.dateFebut = dateFebut;
		this.entree = entree;
	}
	
	
	
	public Date getDateDebut() {
		return dateDebut;
	}
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}
	public Date getDateFebut() {
		return dateFebut;
	}
	public void setDateFebut(Date dateFebut) {
		this.dateFebut = dateFebut;
	}
	public EntreeAgenda getEntree() {
		return entree;
	}
	public void setEntree(EntreeAgenda entree) {
		this.entree = entree;
	}
	
	
}
