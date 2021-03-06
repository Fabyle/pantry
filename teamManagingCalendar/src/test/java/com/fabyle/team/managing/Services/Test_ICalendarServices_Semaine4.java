/**
 * 
 */
package com.fabyle.team.managing.Services;

import static com.fabyle.team.managing.Services.NomenclatureServices.CLIENT_REUNICA;
import static com.fabyle.team.managing.Services.NomenclatureServices.DOC_CONCEPT;
import static com.fabyle.team.managing.Services.NomenclatureServices.DOC_SPEC;
import static com.fabyle.team.managing.Services.NomenclatureServices.DOC_VALID;
import static com.fabyle.team.managing.Services.NomenclatureServices.OBO;
import static com.fabyle.team.managing.Services.NomenclatureServices.LOG_DOC_INST;
import static com.fabyle.team.managing.Services.NomenclatureServices.NMA;
import static com.fabyle.team.managing.Services.NomenclatureServices.NMA2;
import static com.fabyle.team.managing.Services.NomenclatureServices.PLAN_1;
import static com.fabyle.team.managing.Services.NomenclatureServices.P_ESIC_LANCEUR;
import static com.fabyle.team.managing.Services.NomenclatureServices.P_EXPORT_CRM;
import static com.fabyle.team.managing.Services.NomenclatureServices.P_EXPORT_MVO;
import static com.fabyle.team.managing.Services.NomenclatureServices.P_EXPORT_PTF;
import static com.fabyle.team.managing.Services.NomenclatureServices.P_LINK_LANCEUR_GRAPHIQUE;
import static com.fabyle.team.managing.Services.NomenclatureServices.P_SMOG_MULTI;
import static com.fabyle.team.managing.Services.NomenclatureServices.P_SYNCHRO_CTX;
import static com.fabyle.team.managing.Services.NomenclatureServices.P_VALID_BCN;
import static com.fabyle.team.managing.Services.NomenclatureServices.P_VALID_EXPLOIT_P2;
import static com.fabyle.team.managing.Services.NomenclatureServices.P_VALID_GEC;
import static com.fabyle.team.managing.Services.NomenclatureServices.P_WM_FRAME;
import static com.fabyle.team.managing.Services.NomenclatureServices.TYPE_CONCEPT;
import static com.fabyle.team.managing.Services.NomenclatureServices.TYPE_CONC_DEV;
import static com.fabyle.team.managing.Services.NomenclatureServices.TYPE_DEV;
import static com.fabyle.team.managing.Services.NomenclatureServices.TYPE_SPEC;
import static com.fabyle.team.managing.Services.NomenclatureServices.TYPE_VALIDATION;
import static com.fabyle.team.managing.Services.NomenclatureServices.XRO;
import static com.fabyle.team.managing.Services.NomenclatureServices.YBA;
import static com.fabyle.team.managing.Services.NomenclatureServices.PLAN_2;
import static com.fabyle.team.managing.Services.NomenclatureServices.P_VALID_VGS_P2;
import static com.fabyle.team.managing.Services.NomenclatureServices.P_WS_FIRME_PRIVILEGE;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.SocketAddress;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import junit.framework.TestCase;

import com.fabyle.managing.domain.EntreeAgenda;
import com.fabyle.managing.domain.EntreeAgendaCongee;
import com.fabyle.team.managing.Services.imp.CalendarServicesImp;
import com.google.gdata.util.ServiceException;

/**
 * @author fabien
 * 
 */
public class Test_ICalendarServices_Semaine4 extends TestCase {

	
	ICalendarServices service = new CalendarServicesImp();

	/**
	 * ----------
	 */
	public void A_ajouterConge() {
		// attention au libell� identique pour les cong�s - il ne pourront pas
		// etre inserer
		service.addEntreeAgendaConge(new EntreeAgendaCongee(XRO), "2013-02-08", "2013-02-12");

	}
	
	
	/**
	 * ----------
	 */	
	public void B_validationBatchGEC_PLAN1() {
		
		service.addEntreeAgendaTravail(new EntreeAgenda(NMA, CLIENT_REUNICA,
				 TYPE_VALIDATION, P_VALID_GEC, PLAN_1,
				DOC_VALID), "2013-01-21", 3, 60);
	}
	
	/**
	 * ----------
	 */	
	public void C_esicLanceurExcel_PLAN1() {
		
		service.addEntreeAgendaTravail(new EntreeAgenda(XRO, CLIENT_REUNICA
				, TYPE_CONC_DEV, P_ESIC_LANCEUR, PLAN_1,
				LOG_DOC_INST), "2013-01-21", 2, 60);
		
	}
	
	
	/**
	 * ----------
	 */
	public void D_batchMouvementPTF_PLAN1() {
		
		EntreeAgenda entree1 = new EntreeAgenda(YBA, CLIENT_REUNICA,
				 TYPE_SPEC, P_EXPORT_PTF, PLAN_1,
				DOC_SPEC);
		
		service.addEntreeAgendaTravail(entree1, "2013-01-21", 3, 70);
		
		EntreeAgenda entree2 = new EntreeAgenda(XRO, CLIENT_REUNICA,
				 TYPE_CONCEPT, P_EXPORT_PTF, PLAN_1,
				DOC_CONCEPT);
		entree2.addPrerequis(entree1);
		
		service.addEntreeAgendaTravail(entree2, "2013-01-24", 4, 70);
		
		EntreeAgenda entree3 = new EntreeAgenda(XRO, CLIENT_REUNICA,
				 TYPE_DEV, P_EXPORT_PTF, PLAN_1,
				 LOG_DOC_INST);
		entree3.addPrerequis(entree2);
		
		service.addEntreeAgendaTravail(entree3, "2013-01-24", 5, 70);
		
		EntreeAgenda entree3b = new EntreeAgenda(XRO, CLIENT_REUNICA,
				 TYPE_DEV, P_EXPORT_PTF, PLAN_2,
				 LOG_DOC_INST);
		entree3.addPrerequis(entree2);
		
		service.addEntreeAgendaTravail(entree3b, "2013-01-24", 5, 100);
		
		EntreeAgenda entree4 = new EntreeAgenda(XRO, CLIENT_REUNICA,
				 TYPE_VALIDATION, P_EXPORT_PTF, PLAN_1,
				 DOC_VALID);
		entree4.addPrerequis(entree3);	
		
		service.addEntreeAgendaTravail(entree4, "2013-01-24", 1, 70);
		
		
		
	}
	
	
	/**
	 * ----------
	 */
	public void E_batchMouvementORG_PLAN1() {
		
		EntreeAgenda entree1 = new EntreeAgenda(YBA, CLIENT_REUNICA,
				 TYPE_SPEC, P_EXPORT_MVO, PLAN_1,
				DOC_SPEC);
		
		service.addEntreeAgendaTravail(entree1, "2013-01-21", 3, 70);
		
		EntreeAgenda entree2 = new EntreeAgenda(OBO, CLIENT_REUNICA,
				 TYPE_CONCEPT, P_EXPORT_MVO, PLAN_1,
				DOC_CONCEPT);
		entree2.addPrerequis(entree1);
		
		service.addEntreeAgendaTravail(entree2, "2013-03-11", 5, 100);
		
		EntreeAgenda entree3 = new EntreeAgenda(OBO, CLIENT_REUNICA,
				 TYPE_DEV, P_EXPORT_MVO, PLAN_1,
				 LOG_DOC_INST);
		entree3.addPrerequis(entree2);
		
		service.addEntreeAgendaTravail(entree3, "2013-03-04", 10, 100);
		
		EntreeAgenda entree4 = new EntreeAgenda(OBO, CLIENT_REUNICA,
				 TYPE_VALIDATION, P_EXPORT_MVO, PLAN_1,
				 DOC_VALID);
		entree4.addPrerequis(entree3);		
		
		service.addEntreeAgendaTravail(entree4, "2013-03-04", 5, 100);
		
				
	}
	
	
	
	/**
	 * ----------
	 */	
	public void F_smog_multi_BCB() {
		
		EntreeAgenda entree1 = new EntreeAgenda(YBA, CLIENT_REUNICA,
				 TYPE_SPEC, P_SMOG_MULTI, PLAN_1,
				DOC_SPEC);
		
		service.addEntreeAgendaTravail(entree1, "2013-01-31", 5, 100);
		
		EntreeAgenda entree2 = new EntreeAgenda(YBA, CLIENT_REUNICA,
				 TYPE_CONCEPT, P_SMOG_MULTI, PLAN_1,
				DOC_CONCEPT);
		entree2.addPrerequis(entree1);
		
		service.addEntreeAgendaTravail(entree2, "2013-01-31", 5, 100);
		
		EntreeAgenda entree3 = new EntreeAgenda(YBA, CLIENT_REUNICA,
				 TYPE_DEV, P_SMOG_MULTI, PLAN_1,
				 LOG_DOC_INST);
		entree3.addPrerequis(entree2);
		
		service.addEntreeAgendaTravail(entree3, "2013-01-31", 5, 100);
		
		EntreeAgenda entree4 = new EntreeAgenda(YBA, CLIENT_REUNICA,
				 TYPE_VALIDATION, P_SMOG_MULTI, PLAN_1,
				 DOC_VALID);
		entree4.addPrerequis(entree3);	
		
		service.addEntreeAgendaTravail(entree4, "2013-02-01", 5, 100);
	
	
		
	}
	
	
	
	/**
	 * ----------
	 */
	public void G_ajouterTestMonteeEnCharge_Plan1() {
		
		service.addEntreeAgendaTravail(new EntreeAgenda(NMA2, CLIENT_REUNICA,
				 TYPE_VALIDATION, P_VALID_EXPLOIT_P2,
				PLAN_1,
				DOC_VALID),
				"2013-01-31", 30, 100);

//		service.addEntreeAgendaTravail(new EntreeAgenda(NMA, CLIENT_REUNICA,
//				 TYPE_VALIDATION, P_VALID_EXPLOIT_P2+" tir1",
//				PLAN_1,
//				DOC_VALID),
//				"2013-01-31", 2, 60);
//
//		service.addEntreeAgendaTravail(new EntreeAgenda(NMA, CLIENT_REUNICA,
//				 TYPE_VALIDATION, P_VALID_EXPLOIT_P2+" tir2",
//				PLAN_1, DOC_VALID),
//				"2013-02-21", 1, 100);
//
//		service.addEntreeAgendaTravail(new EntreeAgenda(NMA, CLIENT_REUNICA,
//				 TYPE_VALIDATION, P_VALID_EXPLOIT_P2+" tir3",
//				PLAN_1, DOC_VALID),
//				"2013-02-21", 1, 100);
//
//		service.addEntreeAgendaTravail(new EntreeAgenda(NMA, CLIENT_REUNICA,
//				 TYPE_VALIDATION, P_VALID_EXPLOIT_P2+" tir4",
//				PLAN_1, DOC_VALID),
//				"2013-02-25", 1, 100);
//		service.addEntreeAgendaTravail(new EntreeAgenda(NMA, CLIENT_REUNICA,
//				 TYPE_VALIDATION, P_VALID_EXPLOIT_P2+" tir5",
//				PLAN_1, DOC_VALID),
//				"2013-02-21", 1, 100);
//		service.addEntreeAgendaTravail(new EntreeAgenda(NMA, CLIENT_REUNICA,
//				 TYPE_VALIDATION, P_VALID_EXPLOIT_P2+" tir6",
//				PLAN_1, DOC_VALID),
//				"2013-02-28", 1, 100);
//		service.addEntreeAgendaTravail(new EntreeAgenda(NMA, CLIENT_REUNICA,
//				 TYPE_VALIDATION, P_VALID_EXPLOIT_P2+" tir7",
//				PLAN_1, DOC_VALID),
//				"2013-03-04", 1, 100);
//		service.addEntreeAgendaTravail(new EntreeAgenda(NMA, CLIENT_REUNICA,
//				 TYPE_VALIDATION, P_VALID_EXPLOIT_P2+" tir8",
//				PLAN_1, DOC_VALID),
//				"2013-03-07", 1, 100);
//		service.addEntreeAgendaTravail(new EntreeAgenda(NMA, CLIENT_REUNICA,
//				 TYPE_VALIDATION, P_VALID_EXPLOIT_P2+" tir9",
//				PLAN_1, DOC_VALID),
//				"2013-03-11", 1, 100);
//	}
	
//	public void testDelete(){
//		EntreeAgenda entree1 = new EntreeAgenda(YBA, CLIENT_REUNICA,
//				 TYPE_SPEC, P_EXPORT_PTF, PLAN_1,
//				DOC_SPEC);
//		
//		EntreeAgenda entree2 = new EntreeAgenda(YBA, CLIENT_REUNICA,
//				 TYPE_SPEC, P_EXPORT_MVO, PLAN_1,
//				DOC_SPEC);
//		
//		service.deleteEntreeAgendaConge(entree1);
//		service.deleteEntreeAgendaConge(entree2);
//		
//		
	}
	
	/**
	 * ----------
	 */
	public void H_validationBatchBCN_PLAN1() {
		
		service.addEntreeAgendaTravail(new EntreeAgenda(NMA, CLIENT_REUNICA,
				 TYPE_VALIDATION, P_VALID_BCN, PLAN_1,
				DOC_VALID), "2013-01-24", 2, 60);
	}
	
	
	/**
	 * ----------
	 */
	public void I_batchCRM_PLAN1() {
		
//		EntreeAgenda entree1 = new EntreeAgenda(OBO, CLIENT_REUNICA,
//				 TYPE_SPEC, P_EXPORT_CRM, PLAN_1,
//				DOC_SPEC);
//		
//		service.addEntreeAgendaTravail(entree1, "2013-02-18", 5, 100);
//		
		EntreeAgenda entree2 = new EntreeAgenda(OBO, CLIENT_REUNICA,
				 TYPE_CONCEPT, P_EXPORT_CRM, PLAN_1,
				DOC_CONCEPT);
//		entree2.addPrerequis(entree1);
		
		service.addEntreeAgendaTravail(entree2, "2013-02-11", 5, 100);
		
		EntreeAgenda entree3 = new EntreeAgenda(OBO, CLIENT_REUNICA,
				 TYPE_DEV, P_EXPORT_CRM, PLAN_1,
				 LOG_DOC_INST);
		entree3.addPrerequis(entree2);
		
		service.addEntreeAgendaTravail(entree3, "2013-02-11", 15, 100);
		
		EntreeAgenda entree4 = new EntreeAgenda(OBO, CLIENT_REUNICA,
				 TYPE_VALIDATION, P_EXPORT_CRM, PLAN_1,
				 DOC_VALID);
		entree4.addPrerequis(entree3);		
		
		service.addEntreeAgendaTravail(entree4, "2013-03-11", 5, 100);
		
	}
	
	/**
	 * ----------
	 */
	public void J_FrameWork_webMethod() {
		
		EntreeAgenda entree1 = new EntreeAgenda(XRO, CLIENT_REUNICA,
				 TYPE_SPEC, P_WM_FRAME, PLAN_1,
				 DOC_CONCEPT);
		
		service.addEntreeAgendaTravail(entree1, "2013-04-01", 5, 80);
		
		EntreeAgenda entree2 = new EntreeAgenda(XRO, CLIENT_REUNICA,
				 TYPE_DEV, P_WM_FRAME, PLAN_1,
				 LOG_DOC_INST);
		entree2.addPrerequis(entree1);
		
		service.addEntreeAgendaTravail(entree2, "2013-04-01", 5, 80);
		
		EntreeAgenda entree3 = new EntreeAgenda(XRO, CLIENT_REUNICA,
				 TYPE_VALIDATION, P_WM_FRAME, PLAN_1,
				 DOC_VALID);
		entree3.addPrerequis(entree2);
		
		service.addEntreeAgendaTravail(entree3, "2013-04-01", 5, 80);
		
		
		
	}
	
	/**
	 * ----------
	 */
	public void K_Provision_LINK() {
		
		EntreeAgenda entree1 = new EntreeAgenda(XRO, CLIENT_REUNICA,
				 TYPE_SPEC, P_LINK_LANCEUR_GRAPHIQUE, PLAN_1,
				DOC_SPEC);
		
		service.addEntreeAgendaTravail(entree1, "2013-03-01", 5, 80);
		
		EntreeAgenda entree2 = new EntreeAgenda(XRO, CLIENT_REUNICA,
				 TYPE_CONCEPT, P_LINK_LANCEUR_GRAPHIQUE, PLAN_1,
				DOC_CONCEPT);
		entree2.addPrerequis(entree1);
		
		service.addEntreeAgendaTravail(entree2, "2013-03-01", 4, 80);
		
		EntreeAgenda entree3 = new EntreeAgenda(XRO, CLIENT_REUNICA,
				 TYPE_DEV, P_LINK_LANCEUR_GRAPHIQUE, PLAN_1,
				 LOG_DOC_INST);
		entree3.addPrerequis(entree2);
		
		service.addEntreeAgendaTravail(entree3, "2013-03-01", 5, 80);
		
		EntreeAgenda entree4 = new EntreeAgenda(XRO, CLIENT_REUNICA,
				 TYPE_VALIDATION, P_LINK_LANCEUR_GRAPHIQUE, PLAN_1,
				 DOC_VALID);
		entree4.addPrerequis(entree3);		
		
		service.addEntreeAgendaTravail(entree4, "2013-03-01", 5, 80);
		
		
		
	}
	
	/**
	 * ----------
	 */
	public void L_synchroContexte2UR_PLAN1() {

		service.addEntreeAgendaTravail(new EntreeAgenda(XRO, CLIENT_REUNICA,
				TYPE_CONC_DEV, P_SYNCHRO_CTX, PLAN_1, LOG_DOC_INST),
				"2013-02-10", 5, 70);

	}
	
	/**
	 * ----------
	 */
	public void M_suiviPerformanceVGS() {

		service.addEntreeAgendaTravail(new EntreeAgenda(NMA, CLIENT_REUNICA,
				TYPE_VALIDATION, P_VALID_VGS_P2, PLAN_1, DOC_VALID),
				"2013-02-02", 5, 100);

	}
	
	/**
	 * ----------
	 */
	public void N_IntegrationWSFirmePrivilegiee() {

		service.addEntreeAgendaTravail(new EntreeAgenda(YBA, CLIENT_REUNICA,
				TYPE_CONC_DEV, P_WS_FIRME_PRIVILEGE, PLAN_1, LOG_DOC_INST),
				
				"2013-03-01", 10, 60);

	}
	
	
	protected void setUp() throws Exception {
		
		super.setUp();
		
		final Properties props = new Properties();

		
		InputStream is = ClassLoader.getSystemResourceAsStream("connexion.properties");
		try {
		  props.load(is);
		}
		catch (IOException e) {
		 // Handle exception here
		}
		
		System.setProperty("http.proxyHost", props.getProperty("proxy.url"));
		System.setProperty("http.proxyPort", props.getProperty("proxy.port"));
		// Override system DNS setting with Google free DNS server
		System.setProperty("sun.net.spi.nameservice.nameservers", "8.8.8.8");
		System.setProperty("sun.net.spi.nameservice.provider.1", "dns,sun");

		ProxySelector.setDefault(new ProxySelector() {

			@Override
			public void connectFailed(URI uri, SocketAddress sa, IOException ioe) {
				throw new RuntimeException("Proxy connect failed", ioe);
			}

			@Override
			public List select(URI uri) {
				List retour = new ArrayList();
				try {
					// ip de du proxy
					retour = Arrays.asList(new Proxy(Proxy.Type.HTTP,
							new InetSocketAddress(InetAddress
									.getByName(props.getProperty("proxy.ip")), 8080)));
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return retour;
			}
		});

		service.init(props.getProperty("google.login"), props.getProperty("google.password"), "testService");
	}
	
	
	public void testGeneral(){
		
		try {
			service.deleteCalendar(XRO);
			service.deleteCalendar(NMA);
			service.deleteCalendar(YBA);
			service.deleteCalendar(OBO);
			service.deleteCalendar(NMA2);
		} catch (IOException | ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			service.createCalendar(XRO, "Calendrier de Xavier",
					ICalendarServices.GREEN);
			service.createCalendar(NMA, "Calendrier de Nicolas",
					ICalendarServices.RED);
			service.createCalendar(YBA, "Calendrier de Yohan",
					ICalendarServices.BLUE);
			service.createCalendar(OBO, "Calendrier d'olivier",
					ICalendarServices.BLUE);
			service.createCalendar(NMA2, "Calendrier de Nicolas pour la mont�e en charge",
					ICalendarServices.RED);
			


		} catch (IOException | ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		A_ajouterConge();
		B_validationBatchGEC_PLAN1();
		
		C_esicLanceurExcel_PLAN1();
		D_batchMouvementPTF_PLAN1();
		I_batchCRM_PLAN1();
		E_batchMouvementORG_PLAN1();
		F_smog_multi_BCB();
				
		G_ajouterTestMonteeEnCharge_Plan1();
		H_validationBatchBCN_PLAN1();
		
		J_FrameWork_webMethod();
		//K_Provision_LINK();
		L_synchroContexte2UR_PLAN1();
		M_suiviPerformanceVGS();
		N_IntegrationWSFirmePrivilegiee();
		
	}

	

}
