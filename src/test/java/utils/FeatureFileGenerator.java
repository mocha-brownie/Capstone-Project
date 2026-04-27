package utils;

import org.apache.poi.xssf.usermodel.*;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class FeatureFileGenerator {

    public static void generateFeatureFile() {

        String excelPath = "src/test/resources/testdata/loginData.xlsx";
        String featurePath = "src/test/resources/features/generatedLogin.feature";

        try {
            FileInputStream file = new FileInputStream(excelPath);
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheet("Sheet1");

            BufferedWriter writer = new BufferedWriter(new FileWriter(featurePath));

            // Write Feature Header
            writer.write("Feature: DemoQA Login\n\n");

            int rows = sheet.getPhysicalNumberOfRows();

            for (int i = 1; i < rows; i++) {

                String username = sheet.getRow(i).getCell(0).toString();
                String password = sheet.getRow(i).getCell(1).toString();
                String result = sheet.getRow(i).getCell(2).toString();

                writer.write("Scenario: Login with user " + username + "\n");
                writer.write("  Given user uses API to  enter username \"" + username + "\" and password \"" + password + "\"\n");
                writer.write("  Given user is on login page\n");
                
                writer.write("  When user enters username \"" + username + "\" and password \"" + password + "\"\n");
                writer.write("  And clicks on login button\n");
                writer.write("  And login result should be \"" + result + "\"\n");
                writer.write("  Then I delete the user account using API\n\n");
            }

            writer.close();
            workbook.close();

            System.out.println("Feature file generated successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}