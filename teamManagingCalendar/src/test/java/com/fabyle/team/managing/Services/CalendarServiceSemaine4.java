/**
 * 
 */
package com.fabyle.team.managing.Services;

import static com.fabyle.team.managing.Services.NomenclatureServices.CLIENT_REUNICA;
import static com.fabyle.team.managing.Services.NomenclatureServices.CONTRIBUTION_INTERSIS;
import static com.fabyle.team.managing.Services.NomenclatureServices.CONTRIBUTION_PALIER2;
import static com.fabyle.team.managing.Services.NomenclatureServices.NMA;
import static com.fabyle.team.managing.Services.NomenclatureServices.PLAN_1;
import static com.fabyle.team.managing.Services.NomenclatureServices.P_EXPORT_PTF;
import static com.fabyle.team.managing.Services.NomenclatureServices.P_LANCEUR;
import static com.fabyle.team.managing.Services.NomenclatureServices.P_VALID_BCN;
import static com.fabyle.team.managing.Services.NomenclatureServices.P_VALID_EXPLOIT_P2;
import static com.fabyle.team.managing.Services.NomenclatureServices.P_VALID_GEC;
import static com.fabyle.team.managing.Services.NomenclatureServices.TYPE_CONCEPT;
import static com.fabyle.team.managing.Services.NomenclatureServices.TYPE_CONC_DEV;
import static com.fabyle.team.managing.Services.NomenclatureServices.TYPE_SPEC;
import static com.fabyle.team.managing.Services.NomenclatureServices.TYPE_VALIDATION;
import static com.fabyle.team.managing.Services.NomenclatureServices.XRO;
import static com.fabyle.team.managing.Services.NomenclatureServices.YBA;

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

/**
 * @author fabien
 * 
 */
public class CalendarServiceSemaine4 extends TestCase {

	ICalendarServices service = new CalendarServicesImp();

	protected void setUp() throws Exception {

		super.setUp();

		final Properties props = new Properties();

		InputStream is = ClassLoader
				.getSystemResourceAsStream("connexion.properties");
		try {
			props.load(is);
		} catch (IOException e) {
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
							new InetSocketAddress(InetAddress.getByName(props
									.getProperty("proxy.ip")), 8080)));
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return retour;
			}
		});

		service.init(props.getProperty("google.login"),
				props.getProperty("google.password"), "testService");
	}

	public void testSemaine4() {

		service.addEntreeAgenda(new EntreeAgenda(XRO, CLIENT_REUNICA,
				CONTRIBUTION_PALIER2, TYPE_CONC_DEV, P_LANCEUR, PLAN_1,
				"Pas de commentaires"), "2013-01-21", 2, 60);
		service.addEntreeAgenda(new EntreeAgenda(XRO, CLIENT_REUNICA,
				CONTRIBUTION_PALIER2, TYPE_CONCEPT, P_EXPORT_PTF, PLAN_1,
				"Pas de commentaires"), "2013-01-24", 4, 60);
		service.addEntreeAgenda(new EntreeAgenda(NMA, CLIENT_REUNICA,
				CONTRIBUTION_PALIER2, TYPE_VALIDATION, P_VALID_GEC, PLAN_1,
				"Pas de commentaires"), "2013-01-21", 3, 60);
		service.addEntreeAgenda(new EntreeAgenda(NMA, CLIENT_REUNICA,
				CONTRIBUTION_INTERSIS, TYPE_VALIDATION, P_VALID_BCN, PLAN_1,
				"Pas de commentaires"), "2013-01-24", 2, 60);
		service.addEntreeAgenda(new EntreeAgenda(YBA, CLIENT_REUNICA,
				CONTRIBUTION_PALIER2, TYPE_SPEC, P_EXPORT_PTF, PLAN_1,
				"Pas de commentaires"), "2013-01-21", 5, 60);

	}

	public void testAjouterConge() {
		// attention au libellé identique pour les congés - il ne pourront pas
		// etre inserer
		service.addEntreeAgenda(new EntreeAgendaCongee(XRO), "2013-02-08", 3,
				100);

	}

	public void testAjouterTestMonteeEnCharge() {

		service.addEntreeAgenda(new EntreeAgenda(NMA, CLIENT_REUNICA,
				CONTRIBUTION_PALIER2, TYPE_VALIDATION, P_VALID_EXPLOIT_P2,
				PLAN_1,
				"réalisation de la documentation specification et conception"),
				"2013-01-31", 2, 60);

		service.addEntreeAgenda(new EntreeAgenda(NMA, CLIENT_REUNICA,
				CONTRIBUTION_PALIER2, TYPE_VALIDATION, P_VALID_EXPLOIT_P2,
				PLAN_1, "Planning des tirs de montée en charge. Tir 1"),
				"2013-02-21", 1, 100);

		service.addEntreeAgenda(new EntreeAgenda(NMA, CLIENT_REUNICA,
				CONTRIBUTION_PALIER2, TYPE_VALIDATION, P_VALID_EXPLOIT_P2,
				PLAN_1, "Planning des tirs de montée en charge. Tir 1"),
				"2013-02-21", 1, 100);

		service.addEntreeAgenda(new EntreeAgenda(NMA, CLIENT_REUNICA,
				CONTRIBUTION_PALIER2, TYPE_VALIDATION, P_VALID_EXPLOIT_P2,
				PLAN_1, "Planning des tirs de montée en charge. Tir 2"),
				"2013-02-25", 1, 100);
		service.addEntreeAgenda(new EntreeAgenda(NMA, CLIENT_REUNICA,
				CONTRIBUTION_PALIER2, TYPE_VALIDATION, P_VALID_EXPLOIT_P2,
				PLAN_1, "Planning des tirs de montée en charge. Tir 3"),
				"2013-02-21", 1, 100);
		service.addEntreeAgenda(new EntreeAgenda(NMA, CLIENT_REUNICA,
				CONTRIBUTION_PALIER2, TYPE_VALIDATION, P_VALID_EXPLOIT_P2,
				PLAN_1, "Planning des tirs de montée en charge. Tir 4"),
				"2013-02-28", 1, 100);
		service.addEntreeAgenda(new EntreeAgenda(NMA, CLIENT_REUNICA,
				CONTRIBUTION_PALIER2, TYPE_VALIDATION, P_VALID_EXPLOIT_P2,
				PLAN_1, "Planning des tirs de montée en charge. Tir 5"),
				"2013-03-04", 1, 100);
		service.addEntreeAgenda(new EntreeAgenda(NMA, CLIENT_REUNICA,
				CONTRIBUTION_PALIER2, TYPE_VALIDATION, P_VALID_EXPLOIT_P2,
				PLAN_1, "Planning des tirs de montée en charge. Tir 6"),
				"2013-03-07", 1, 100);
		service.addEntreeAgenda(new EntreeAgenda(NMA, CLIENT_REUNICA,
				CONTRIBUTION_PALIER2, TYPE_VALIDATION, P_VALID_EXPLOIT_P2,
				PLAN_1, "Planning des tirs de montée en charge. Tir 7"),
				"2013-03-11", 1, 100);
	}

}
