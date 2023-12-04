package resources;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataDriven {

	public ArrayList<String> getData(String testcasename , String SheetName) throws Exception {

		// Identify the test case column by scanning the entire first row.
		// once column is identified then scan entire test case column to identify
		// purchase test case row.
		// after you grab purchase test case row pull all the data from the row and feed
		// it into the our test.

		ArrayList<String> a = new ArrayList<String>();
		FileInputStream fi = new FileInputStream(
				"C:\\Users\\MY PC\\Desktop\\RestAssuredDocuments\\DataDriven\\DataDrivenTest.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fi);

		int sheets = workbook.getNumberOfSheets();
		for (int i = 0; i < sheets; i++) {
			if (workbook.getSheetName(i).equalsIgnoreCase(SheetName)) {
				XSSFSheet sheet = workbook.getSheetAt(i);

				// Identify the test cases column by scanning the entire first row.

				Iterator<Row> rows = sheet.rowIterator(); // sheet is a collection of rows.
				Row firstrow = rows.next();

				Iterator<Cell> ce = firstrow.cellIterator(); // rows are a collection of cells.
				ce.next();
				int k = 0;
				int column = 0;
				while (ce.hasNext()) {
					Cell value = ce.next();
					if (value.getStringCellValue().equalsIgnoreCase("Test Cases")) {
						// desired column found.
						column = k;
					}
					k++;
				}
				System.out.println(column);

				// once column is identified then scan entire test case column to identify
				// purchase test case row.
				while (rows.hasNext()) {
					Row r = rows.next();
					if (r.getCell(column).getStringCellValue().equalsIgnoreCase(testcasename)) {

						// Now if Purchase match found then you have to grab all the data from that
						// purchase row.
						Iterator<Cell> cv = r.cellIterator();
						while (cv.hasNext()) {
						
							Cell cl = cv.next();
							if(cl.getCellType()==CellType.STRING) {

							// We need to store the cell data in an arraylist.
							a.add(cl.getStringCellValue());
						}
							else {
								a.add(NumberToTextConverter.toText(cl.getNumericCellValue()));
							}
						}
					}

				}
			}
		}
		return a;

	}

	public static void main(String[] args) throws Exception {

	}

}
