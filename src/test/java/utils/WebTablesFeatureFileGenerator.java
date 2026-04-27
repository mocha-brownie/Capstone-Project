package utils;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.*;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class WebTablesFeatureFileGenerator {

    public static void generateFeatureFile() {

        String excelPath = "src/test/resources/testdata/webTableData.xlsx";
        String featurePath = "src/test/resources/features/webTableFeatureFile.feature";

        try {
            FileInputStream file = new FileInputStream(excelPath);
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheet("Sheet1");

            BufferedWriter writer = new BufferedWriter(new FileWriter(featurePath));
            DataFormatter formatter = new DataFormatter(); // Added DataFormatter
            
            // Write Feature Header
            writer.write("Feature: Web Tables data push\n\n");

            int rows = sheet.getPhysicalNumberOfRows();

            for (int i = 1; i < rows; i++) {

                // String firstName = sheet.getRow(i).getCell(0).toString();
                // String lastName = sheet.getRow(i).getCell(1).toString();
                // String email = sheet.getRow(i).getCell(2).toString();
                // String age = sheet.getRow(i).getCell(3).toString();
                // String salary = sheet.getRow(i).getCell(4).toString();
                // String department = sheet.getRow(i).getCell(5).toString();

                String firstName = formatter.formatCellValue(sheet.getRow(i).getCell(0));
                String lastName = formatter.formatCellValue(sheet.getRow(i).getCell(1));
                String email = formatter.formatCellValue(sheet.getRow(i).getCell(2));
                String age = formatter.formatCellValue(sheet.getRow(i).getCell(3));
                String salary = formatter.formatCellValue(sheet.getRow(i).getCell(4));
                String department = formatter.formatCellValue(sheet.getRow(i).getCell(5));

                writer.write("Scenario: Web tables data with name " + firstName + "\n");
                writer.write("  Given user is on web-table page\n");
                writer.write("  When user click on ADD button\n");
                writer.write("  When user enters firstname \"" + firstName + "\" and lastname \"" + lastName + "\"\n");
                writer.write("  And user also enters E-mail \"" + email + "\", age \"" + age + "\", salary \"" + salary
                        + "\" and department \"" + department + "\"\n");
                writer.write("  And user clicks on the submit button\n");
                writer.write("  Then text data in web table should be verified: \"" + firstName + "\", \"" + lastName + "\", \"" + age + "\", \"" + email + "\", \"" + salary + "\" and \"" + department + "\"\n");
                writer.write("  Then user deleted the entered data\n\n");
            }

            writer.close();
            workbook.close();

            System.out.println("Web-tables Feature file generated successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}