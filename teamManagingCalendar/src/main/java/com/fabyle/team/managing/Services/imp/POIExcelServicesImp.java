package com.fabyle.team.managing.Services.imp;

import java.io.FileNotFoundException;
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
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

public class POIExcelServicesImp {
	
	
  /**
  * cell styles used for formatting calendar sheets
  */
 private static Map<String, CellStyle> createStyles(Workbook wb){
     Map<String, CellStyle> styles = new HashMap<String, CellStyle>();

     short borderColor = IndexedColors.GREY_50_PERCENT.getIndex();

     CellStyle style;
     Font titleFont = wb.createFont();
     titleFont.setFontHeightInPoints((short)48);
     titleFont.setColor(IndexedColors.DARK_BLUE.getIndex());
     style = wb.createCellStyle();
     style.setAlignment(CellStyle.ALIGN_CENTER);
     style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
     style.setFont(titleFont);
     styles.put("title", style);

     Font monthFont = wb.createFont();
     monthFont.setFontHeightInPoints((short)12);
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
     dayFont.setFontHeightInPoints((short)14);
     dayFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
     style = wb.createCellStyle();
     style.setAlignment(CellStyle.ALIGN_LEFT);
     style.setVerticalAlignment(CellStyle.VERTICAL_TOP);
     style.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
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
     style.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
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


	public void test() {

		SimpleDateFormat formatFile = new SimpleDateFormat(
				"dd-MM-yyyy-hh-mm-ss");
		SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat formatDateDay = new SimpleDateFormat("dd");

		try {
			FileOutputStream fileOut = new FileOutputStream("planning-"
					+ (formatFile.format(new Date())) + ".xls");
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet worksheet = workbook.createSheet("Planning");

			// index from 0,0... cell A1 is cell(0,0)
			//HSSFRow row1 = worksheet.createRow((short) 0);
			
			worksheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$N$1"));
			
			HSSFRow row2 = worksheet.createRow((short) 1);
			HSSFRow row1 = worksheet.createRow((short) 0);

			Date dateDebut = null;
			Date dateFin = null;

			try {
				dateDebut = formatDate.parse("01-01-2013");
				dateFin = formatDate.parse("31-03-2013");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			HSSFCellStyle cellStyle = workbook.createCellStyle();
			cellStyle = workbook.createCellStyle();
			cellStyle
					.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
			cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

			HSSFCellStyle cellStyle2 = workbook.createCellStyle();
			cellStyle2 = workbook.createCellStyle();
			cellStyle2.setFillForegroundColor(HSSFColor.AQUA.index);
			cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

			int column = 0;
			int firstColumn = 0;
			while (dateDebut.before(dateFin)) {
				HSSFCell cellA1 = row2.createCell(column);
				
				cellA1.setCellValue("-"+formatDateDay.format(dateDebut)+"-");
				
			

				GregorianCalendar cal = (GregorianCalendar) GregorianCalendar
						.getInstance();
				cal.setTime(dateDebut);

				if (cal.get(Calendar.DAY_OF_WEEK) == GregorianCalendar.SUNDAY
						|| cal.get(Calendar.DAY_OF_WEEK) == GregorianCalendar.SATURDAY) {
					cellA1.setCellStyle(cellStyle);
				} else {
					cellA1.setCellStyle(cellStyle2);

				}
				
				Date dateDebutSuivante = new Date(dateDebut.getTime()
						+ TimeUnit.DAYS.toMillis(1));
				GregorianCalendar calSuivant = (GregorianCalendar) GregorianCalendar
						.getInstance();
				calSuivant.setTime(dateDebutSuivante);
				
				if (calSuivant.get( Calendar.MONTH ) != cal.get( Calendar.MONTH )){
				
				worksheet.addMergedRegion(new CellRangeAddress(
			            0, //first row (0-based)
			            0, //last row  (0-based)
			            firstColumn, //first column (0-based)
			            column //last column  (0-based)
			        ));
				firstColumn = column +1; 
				}

				worksheet.autoSizeColumn(column);
				dateDebut = dateDebutSuivante;
				column++;

			}
			
			// dernier région 
			worksheet.addMergedRegion(new CellRangeAddress(
		            0, //first row (0-based)
		            0, //last row  (0-based)
		            firstColumn, //first column (0-based)
		            column-1 //last column  (0-based)
		        ));
			
			HSSFCell cellA1 = row1.createCell(0);
			cellA1.setCellValue("Janvier");
		

			// HSSFCell cellA1 = row1.createCell(0);
			// cellA1.setCellValue("Hello");
			// HSSFCellStyle cellStyle = workbook.createCellStyle();
			// cellStyle.setFillForegroundColor(HSSFColor.GOLD.index);
			// cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			// cellA1.setCellStyle(cellStyle);
			//
			// HSSFCell cellB1 = row1.createCell( 1);
			// cellB1.setCellValue("Goodbye");
			// cellStyle = workbook.createCellStyle();
			// cellStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
			// cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			// cellB1.setCellStyle(cellStyle);
			//
			// HSSFCell cellC1 = row1.createCell(2);
			// cellC1.setCellValue(true);
			//
			// HSSFCell cellD1 = row1.createCell(3);
			// cellD1.setCellValue(new Date());
			// cellStyle = workbook.createCellStyle();
			// cellStyle.setDataFormat(HSSFDataFormat
			// .getBuiltinFormat("m/d/yy h:mm"));
			// cellD1.setCellStyle(cellStyle);

			workbook.write(fileOut);
			fileOut.flush();
			fileOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
