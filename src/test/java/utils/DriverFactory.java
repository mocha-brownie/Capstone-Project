package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.net.URL;

public class DriverFactory {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver initDriver() {
        try {
            ChromeOptions options = new ChromeOptions();
            // This connects to your Selenium Server / Grid Hub
            driver.set(new RemoteWebDriver(
                    new URL("http://localhost:4444/wd/hub"),
                    options
            ));
        } catch (Exception e) {
            System.out.println("Could not connect to Selenium Grid: " + e.getMessage());
            e.printStackTrace();
        }
        return getDriver();
    }

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            return initDriver();
        }
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}