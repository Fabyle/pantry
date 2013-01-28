/**
 * 
 */
package com.fabyle.team.managing.Services;

import static com.fabyle.team.managing.Services.NomenclatureServices.CLIENT_REUNICA;
import static com.fabyle.team.managing.Services.NomenclatureServices.LOG_DOC_INST;
import static com.fabyle.team.managing.Services.NomenclatureServices.PLAN_1;
import static com.fabyle.team.managing.Services.NomenclatureServices.P_SYNCHRO_CTX;
import static com.fabyle.team.managing.Services.NomenclatureServices.TYPE_CONC_DEV;
import static com.fabyle.team.managing.Services.NomenclatureServices.XRO;

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
import com.fabyle.team.managing.Services.imp.CalendarServicesImp;

/**
 * @author fabien
 * 
 */
public class Test_ICalendarServices_Semaine5 extends TestCase {

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

	public void testGeneral() {

		L_synchroContexte2UR_PLAN1();

	}

	/**
	 * ----------
	 */
	public void L_synchroContexte2UR_PLAN1() {

		service.addEntreeAgendaTravail(new EntreeAgenda(XRO, CLIENT_REUNICA,
				TYPE_CONC_DEV, P_SYNCHRO_CTX, PLAN_1, LOG_DOC_INST),
				"2013-02-10", 5, 70);

	}

}
