package utils;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.*;
import java.io.FileInputStream;

public class ExcelUtil {

    public static Object[][] getExcelData(String path, String sheetName) throws Exception {

        FileInputStream file = new FileInputStream(path);
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheet(sheetName);

        int rows = sheet.getPhysicalNumberOfRows();
        int cols = sheet.getRow(0).getPhysicalNumberOfCells();

        Object[][] data = new Object[rows - 1][cols];
        DataFormatter formatter = new DataFormatter(); // Added DataFormatter

        for (int i = 1; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // data[i - 1][j] = sheet.getRow(i).getCell(j).toString();
                // Use formatter instead of .toString()
                data[i - 1][j] = formatter.formatCellValue(sheet.getRow(i).getCell(j));
            }
        }

        workbook.close();
        return data;
    }
}