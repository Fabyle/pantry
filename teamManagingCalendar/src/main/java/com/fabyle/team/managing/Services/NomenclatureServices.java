package com.fabyle.team.managing.Services;

public interface NomenclatureServices {

	// CLIENT
	public static final String CLIENT_AG2R = "AG2R";
	public static final String CLIENT_REUNICA = "REUNICA";

	// CONTRIBUTION
	public static final String CONTRIBUTION_PALIER2 = "PALIER_2";
	public static final String CONTRIBUTION_INTERSIS = "INTERSIS";
	public static final String CONTRIBUTION_IOPA = "IOPA";
	public static final String CONTRIBUTION_LINK = "LINK";
	public static final String CONTRIBUTION_ESIC = "ESIC";

	// TYPE PROJET
	public static final String TYPE_SPEC = "Spécification";
	public static final String TYPE_CONCEPT = "Conception";
	public static final String TYPE_DEV = "Développement";
	public static final String TYPE_CONC_DEV = "Conception/Développement";
	public static final String TYPE_VALIDATION = "Validation";
	public static final String TYPE_EXPLOIT = "Exploitabilité";
	public static final String TYPE_ASSISTANCE = "Assistance";
	public static final String TYPE_ETUDE = "Etudes";

	// PROJET
	public static final String P_LANCEUR = "Lanceur Excel";
	public static final String P_EXPORT_PTF = "Batch export PTF";	
	public static final String P_VALID_BCN = "Batch import BCN";
	public static final String P_VALID_GEC = "Batch GEC";
	public static final String P_VALID_EXPLOIT_P2 = "Montée en charge P2";
	// suivi de VGS - pré-requis il faut un acces à EPV. NMA
	public static final String P_VALID_VGS_P2 = "Suivi exploitation VGS";
	public static final String P_VALID_EXTRACT = "Validation des extracteurs UR";
	

}
