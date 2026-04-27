package stepdefinitions;

import io.cucumber.java.en.*;
import utils.JMeterUtils;

public class JMeterSteps {

    @Given("I execute JMeter test")
    public void runTest() throws Exception {
        JMeterUtils.runJMeter();
    }

    @Then("response time should be below {int} ms")
    public void validatePerformance(int threshold) throws Exception {
        JMeterUtils.validatePerformance(threshold);
    }
}