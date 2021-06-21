package listmanagement.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Vector;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import listmanagement.db.List;

public class ExcelManagement {

	private String path;

	public ExcelManagement(String path) {
		this.path = path;
	}

	public void writeExcelFile(Vector<List> list) throws EncryptedDocumentException, IOException {

		XSSFWorkbook workbook = new XSSFWorkbook();

		XSSFSheet sheet = workbook.createSheet();
		sheet.setColumnWidth(0, (sheet.getColumnWidth(0) + 2048));
		sheet.setColumnWidth(1, (sheet.getColumnWidth(1) + 2048));
		sheet.setColumnWidth(2, (sheet.getColumnWidth(2) + 2048));
		sheet.setColumnWidth(3, (sheet.getColumnWidth(3) + 2048));

		XSSFRow row = sheet.createRow(0);

		XSSFCell cell;

		// "날짜", "거주지", "핸드폰 번호", "비고"
		cell = row.createCell(0);
		cell.setCellValue("날짜");

		cell = row.createCell(1);
		cell.setCellValue("거주지");

		cell = row.createCell(2);
		cell.setCellValue("핸드폰 번호");

		cell = row.createCell(3);
		cell.setCellValue("비고");

		for (int rowIdx = 0; rowIdx < list.size(); rowIdx++) {
			List l = list.get(rowIdx);

			row = sheet.createRow(rowIdx + 1);

			cell = row.createCell(0);
			cell.setCellValue(new SimpleDateFormat("yyyy/MM/dd").format(l.getDate().getDate()));

			cell = row.createCell(1);
			cell.setCellValue(l.getAdd());

			cell = row.createCell(2);
			cell.setCellValue(l.getpNumber());

			cell = row.createCell(3);
			cell.setCellValue(l.getEtc());

		}

		File file = new File(path);
		FileOutputStream fos = null;

		try {
			fos = new FileOutputStream(file);
			workbook.write(fos);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (workbook != null)
					workbook.close();
				if (fos != null)
					fos.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
