package com.fabyle.managing.domain;

import java.util.Date;

public class EntreeAgendaCongee extends EntreeAgenda {

	public EntreeAgendaCongee(String proprietaire) {
		super();
		this.proprietaire = proprietaire;
	}
	
	public String getTitle(){
		return "Congé de "+proprietaire+" ( enregistrer le :"+new Date()+")";
	}

}
