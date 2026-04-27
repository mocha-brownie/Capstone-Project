package utils;

import org.apache.poi.xssf.usermodel.*;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class TextBoxFeatureGenerator {

    public static void generateFeatureFile() {

        String excelPath = "src/test/resources/testdata/textBoxData.xlsx";
        String featurePath = "src/test/resources/features/textBoxFeatureFile.feature";

        try {
            FileInputStream file = new FileInputStream(excelPath);
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheet("Sheet1");

            BufferedWriter writer = new BufferedWriter(new FileWriter(featurePath));

            // Write Feature Header
            writer.write("Feature: Text Box Verification \n\n");

            int rows = sheet.getPhysicalNumberOfRows();

            for (int i = 1; i < rows; i++) {

                String fullName = sheet.getRow(i).getCell(0).toString();
                String email = sheet.getRow(i).getCell(1).toString();
                String currentAdd = sheet.getRow(i).getCell(2).toString();
                String permanentAdd = sheet.getRow(i).getCell(3).toString();

                writer.write("Scenario: Text data with fullName " + fullName + "\n");
                writer.write("  Given user is on text box page\n");
                writer.write("  When user enters fullname \"" + fullName + "\" and email \"" + email + "\"\n");
                writer.write("And user also enters current Address \"" + currentAdd + "\" and permanent address \"" + permanentAdd + "\"\n");
                writer.write("  And user clicks on submit button\n");
                writer.write("  Then text data result should be verified");
            }

            writer.close();
            workbook.close();

            System.out.println("Feature file generated successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}