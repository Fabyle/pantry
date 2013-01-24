package com.fabyle.team.managing.Services;

import java.text.SimpleDateFormat;
import java.util.Date;

import junit.framework.TestCase;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.fabyle.team.managing.Services.imp.POIExcelServicesImp;

public class Test_IPOIExcelServicesImp extends TestCase {

	public void testWorkbook() {

		SimpleDateFormat formatFile = new SimpleDateFormat(
				"dd-MM-yyyy-hh-mm-ss");

		POIExcelServicesImp service = new POIExcelServicesImp();

		HSSFWorkbook wkb = service.initWorkbook();
		service.createGrilleJours(wkb, "01-01-2013", "31-03-2013");
		service.SaveWorkbook("planning-" + (formatFile.format(new Date()))
				+ ".xls", wkb);

	}

}
