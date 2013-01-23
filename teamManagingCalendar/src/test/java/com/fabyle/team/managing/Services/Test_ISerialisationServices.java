package com.fabyle.team.managing.Services;

import static com.fabyle.team.managing.Services.NomenclatureServices.CLIENT_REUNICA;
import static com.fabyle.team.managing.Services.NomenclatureServices.CONTRIBUTION_PALIER2;
import static com.fabyle.team.managing.Services.NomenclatureServices.DOC_VALID;
import static com.fabyle.team.managing.Services.NomenclatureServices.NMA;
import static com.fabyle.team.managing.Services.NomenclatureServices.PLAN_1;
import static com.fabyle.team.managing.Services.NomenclatureServices.P_VALID_EXPLOIT_P2;
import static com.fabyle.team.managing.Services.NomenclatureServices.TYPE_VALIDATION;
import junit.framework.TestCase;

import com.fabyle.managing.domain.EntreeAgenda;
import com.fabyle.team.managing.Services.imp.SerialisationServicesImp;

public class Test_ISerialisationServices extends TestCase {
	
	public void testSerialiseUneEntreeAgenda() {
		
		ISerialisationServices service = new SerialisationServicesImp();
		String xml = service.serialise(new EntreeAgenda(NMA, CLIENT_REUNICA,
				CONTRIBUTION_PALIER2, TYPE_VALIDATION, P_VALID_EXPLOIT_P2,
				PLAN_1, DOC_VALID));
		
		EntreeAgenda entre = service.deSerialise(xml);
		System.out.println(entre);
		
		
		
	}
	

}
