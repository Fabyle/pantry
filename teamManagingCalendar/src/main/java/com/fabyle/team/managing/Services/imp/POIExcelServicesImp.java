package com.fabyle.team.managing.Services.imp;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

public class POIExcelServicesImp {

	private static Map<String, CellStyle> mapStyle;
	private static String[] mois = { "Janvier", "Février", "Mars", "Avril",
			"Mai", "Juin", "Juillet", "Août", "Septembre", "Octobre",
			"Novembre", "Décembre" };

	public HSSFWorkbook initWorkbook() {
		HSSFWorkbook workbook = new HSSFWorkbook();
		mapStyle = createStyles(workbook);
		return workbook;
	}

	public void SaveWorkbook(String fileName, HSSFWorkbook workbook) {
		try {
			FileOutputStream fileOut = new FileOutputStream(fileName);
			workbook.write(fileOut);
			fileOut.flush();
			fileOut.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void createGrilleJours(HSSFWorkbook workbook, String dateDebutS,
			String dateFinS) {

		SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat formatDateDay = new SimpleDateFormat("dd");
		HSSFSheet worksheet = workbook.createSheet("Planning");
		HSSFRow row2 = worksheet.createRow((short) 1);

		Date dateDebut = null;
		Date dateFin = null;

		try {
			dateDebut = formatDate.parse(dateDebutS);
			dateFin = formatDate.parse(dateFinS);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int column = 0;
		int firstColumn = 0;

		GregorianCalendar cal = (GregorianCalendar) GregorianCalendar
				.getInstance();
		cal.setTime(dateDebut);
		HSSFRow row1 = worksheet.createRow((short) 0);
		HSSFCell cellA = row1.createCell(column);
		cellA.setCellStyle(mapStyle.get("mois"));
		cellA.setCellValue(mois[cal.get(Calendar.MONTH)]);

		while (dateDebut.before(dateFin)) {
			HSSFCell cellA1 = row2.createCell(column);
			cellA1.setCellValue("-" + formatDateDay.format(dateDebut) + "-");
			worksheet.autoSizeColumn(column);

			cal.setTime(dateDebut);

			if (cal.get(Calendar.DAY_OF_WEEK) == GregorianCalendar.SUNDAY
					|| cal.get(Calendar.DAY_OF_WEEK) == GregorianCalendar.SATURDAY) {
				cellA1.setCellStyle(mapStyle.get("week-end"));
			} else {
				cellA1.setCellStyle(mapStyle.get("jour"));

			}

			Date dateDebutSuivante = new Date(dateDebut.getTime()
					+ TimeUnit.DAYS.toMillis(1));
			GregorianCalendar calSuivant = (GregorianCalendar) GregorianCalendar
					.getInstance();
			calSuivant.setTime(dateDebutSuivante);

			if (calSuivant.get(Calendar.MONTH) != cal.get(Calendar.MONTH)) {

				HSSFCell cell = row1.createCell(column + 1);
				cell.setCellStyle(mapStyle.get("mois"));
				cell.setCellValue(mois[calSuivant.get(Calendar.MONTH)]);

				worksheet.addMergedRegion(new CellRangeAddress(0, // first row
																	// (0-based)
						0, // last row (0-based)
						firstColumn, // first column (0-based)
						column // last column (0-based)
						));
				firstColumn = column + 1;
			}

			dateDebut = dateDebutSuivante;
			column++;

		}

		// dernier région
		worksheet.addMergedRegion(new CellRangeAddress(0, // first row (0-based)
				0, // last row (0-based)
				firstColumn, // first column (0-based)
				column - 1 // last column (0-based)
				));

	}

	/**
	 * cell styles used for formatting calendar sheets
	 */
	private static Map<String, CellStyle> createStyles(Workbook wb) {
		Map<String, CellStyle> styles = new HashMap<String, CellStyle>();

		short borderColor = IndexedColors.GREY_50_PERCENT.getIndex();

		CellStyle style;
		Font jourFont = wb.createFont();
		jourFont.setColor(IndexedColors.DARK_BLUE.getIndex());
		jourFont.setFontHeightInPoints((short) 10);
		style = wb.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(CellStyle.ALIGN_CENTER);
		style.setFont(jourFont);
		styles.put("jour", style);

		style = wb.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(CellStyle.ALIGN_CENTER);
		style.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE
				.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style.setFont(jourFont);
		styles.put("week-end", style);

		style = wb.createCellStyle();
		Font moisFont = wb.createFont();
		moisFont.setFontHeightInPoints((short) 24);
		moisFont.setColor(IndexedColors.DARK_BLUE.getIndex());
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(CellStyle.ALIGN_CENTER);
		style.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE
				.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style.setBorderLeft(CellStyle.BORDER_DOUBLE);
		style.setFont(moisFont);
		styles.put("mois", style);

		Font titleFont = wb.createFont();
		titleFont.setFontHeightInPoints((short) 48);
		titleFont.setColor(IndexedColors.DARK_BLUE.getIndex());
		style = wb.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		style.setFont(titleFont);
		styles.put("title", style);

		Font monthFont = wb.createFont();
		monthFont.setFontHeightInPoints((short) 12);
		monthFont.setColor(IndexedColors.WHITE.getIndex());
		monthFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		style = wb.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		style.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style.setFont(monthFont);
		styles.put("month", style);

		Font dayFont = wb.createFont();
		dayFont.setFontHeightInPoints((short) 14);
		dayFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		style = wb.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_LEFT);
		style.setVerticalAlignment(CellStyle.VERTICAL_TOP);
		style.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE
				.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style.setBorderLeft(CellStyle.BORDER_THIN);
		style.setLeftBorderColor(borderColor);
		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBottomBorderColor(borderColor);
		style.setFont(dayFont);
		styles.put("weekend_left", style);

		style = wb.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(CellStyle.VERTICAL_TOP);
		style.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE
				.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style.setBorderRight(CellStyle.BORDER_THIN);
		style.setRightBorderColor(borderColor);
		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBottomBorderColor(borderColor);
		styles.put("weekend_right", style);

		style = wb.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_LEFT);
		style.setVerticalAlignment(CellStyle.VERTICAL_TOP);
		style.setBorderLeft(CellStyle.BORDER_THIN);
		style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style.setLeftBorderColor(borderColor);
		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBottomBorderColor(borderColor);
		style.setFont(dayFont);
		styles.put("workday_left", style);

		style = wb.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(CellStyle.VERTICAL_TOP);
		style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style.setBorderRight(CellStyle.BORDER_THIN);
		style.setRightBorderColor(borderColor);
		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBottomBorderColor(borderColor);
		styles.put("workday_right", style);

		style = wb.createCellStyle();
		style.setBorderLeft(CellStyle.BORDER_THIN);
		style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBottomBorderColor(borderColor);
		styles.put("grey_left", style);

		style = wb.createCellStyle();
		style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style.setBorderRight(CellStyle.BORDER_THIN);
		style.setRightBorderColor(borderColor);
		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBottomBorderColor(borderColor);
		styles.put("grey_right", style);

		return styles;
	}

}
