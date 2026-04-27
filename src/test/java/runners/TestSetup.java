package runners;

import org.testng.annotations.BeforeSuite;
import utils.*;

public class TestSetup {

    @BeforeSuite
    public void generateFeature() {
        FeatureFileGenerator.generateFeatureFile();
        TextBoxFeatureGenerator.generateFeatureFile();
        // WebTablesFeatureFileGenerator.generateFeatureFile();
    }
}