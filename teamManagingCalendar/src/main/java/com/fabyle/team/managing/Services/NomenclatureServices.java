package com.fabyle.team.managing.Services;

public interface NomenclatureServices {
	
	
	// PROPRIETAIRE
	public static final String XRO = "Xavier";
	public static final String NMA = "Nicolas";
	public static final String NMA2 = "Nicolas-Montée en charge";
	public static final String YBA = "Yohan";
	public static final String JGR = "Jonathan";
	
	// CLIENT
	public static final String CLIENT_AG2R = "AG2R";
	public static final String CLIENT_REUNICA = "REUNICA";

	// CONTRIBUTION
	public static final String CONTRIBUTION_PALIER2 = "[P2]";
	public static final String CONTRIBUTION_INTERSIS = "[INTERSIS]";
	public static final String CONTRIBUTION_IOPA = "[IOPA]";
	public static final String CONTRIBUTION_LINK = "[LINK]";
	public static final String CONTRIBUTION_ESIC = "[ESIC]";
	public static final String CONTRIBUTION_2UR ="[2UR]";
	public static final String CONTRIBUTION_PDT ="[PDT]";
	public static final String CONTRIBUTION_WEBMETHOD ="[WM]";

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
	public static final String P_ESIC_LANCEUR = CONTRIBUTION_PALIER2+" Lanceur EXCEL ESIC";
	public static final String P_EXPORT_PTF = CONTRIBUTION_PALIER2+" Batch interface mouvements PTF";
	public static final String P_EXPORT_MVO = CONTRIBUTION_PALIER2+" Batch Interface mouvements Organisation";
	public static final String P_EXPORT_CRM = CONTRIBUTION_PALIER2+" Batch interface CRM";
	public static final String P_EXPORT_DN = CONTRIBUTION_PALIER2+" Batch d'éclatement données DN";
	public static final String P_ESIC_PREF = CONTRIBUTION_ESIC+" Préférences utilisateur";
	public static final String P_ESIC_SAV = CONTRIBUTION_ESIC+" Sauvegarde espace de travail";
	public static final String P_ESIC_ACCUEIL = CONTRIBUTION_ESIC+" Espace d'accueil";
	public static final String P_LINK_LANCEUR_GRAPHIQUE = CONTRIBUTION_LINK+" Lanceur graphique";
	public static final String P_LINK_APP_STORE = CONTRIBUTION_LINK+" App Store";
	public static final String P_LINK_APP_INT = CONTRIBUTION_LINK+" Intégration de fonctions externes";
	public static final String P_GESTION_ABSENCE = CONTRIBUTION_PALIER2+" TP Gestion des absences";
	public static final String P_SMOG_MULTI = CONTRIBUTION_2UR+ " SMOG Multi-BCB";
	public static final String P_VALID_BCN = CONTRIBUTION_INTERSIS+" Batch import BCN";
	public static final String P_VALID_GEC = CONTRIBUTION_PALIER2+" Batch GEC";
	public static final String P_SERV_AFF = CONTRIBUTION_PDT+" Service d'affectation universel";
	public static final String P_WM_FRAME = CONTRIBUTION_WEBMETHOD+" Framework intégration WM V1";
	public static final String P_WS_FIRME = CONTRIBUTION_PALIER2+"Intégration WS Firme privilégiée";
	public static final String P_VALID_EXPLOIT_P2 = CONTRIBUTION_PALIER2+"Montée en charge P2";
	// suivi de VGS - pré-requis il faut un acces à EPV. NMA
	public static final String P_VALID_VGS_P2 = CONTRIBUTION_PALIER2+"Suivi exploitation VGS";
	public static final String P_VALID_EXTRACT = CONTRIBUTION_INTERSIS+"Validation des extracteurs UR";

	// PLANIFICATION
	public static final String PLAN_1 = "Planification 1";
	public static final String PLAN_2 = "1ere replanification";
	public static final String PLAN_3 = "2eme replanification";
	public static final String PLAN_4 = "3eme replanification";
	public static final String PLAN_5 = "4eme replanification";
	
	// LIVRABLE
	public static final String DOC_SPEC = "Dossier de spécification";
	public static final String DOC_CONCEPT = "Dossier de conception";
	public static final String LOG_DOC_INST = "livrable logiciel et Dossier installation";
	public static final String DOC_VALID = "Dossier de conception";
	
	

}
