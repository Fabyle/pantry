package com.fabyle.managing.domain;


public class EntreeAgendaCongee extends EntreeAgenda {
	
	public static String CONGE ="Congé";
	public static String PATTERN_CONGE =CONGE+".*";

	public EntreeAgendaCongee(String proprietaire) {
		super();
		this.proprietaire = proprietaire;
	}
	
	public String getTitle(){
		return CONGE+" de "+proprietaire;
	}

}
