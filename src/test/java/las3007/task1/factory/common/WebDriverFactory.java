package las3007.task1.factory.common;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class WebDriverFactory {

    private static final String CHROME = "chrome";
    private static final String FIREFOX = "firefox";

    private static final String GRID_URL = "http://192.168.1.244:4444/wd/hub";

    private static WebDriver webDriver;

    public static WebDriver createWebDriver() {
        if (webDriver == null) {
            try {
                webDriver = new RemoteWebDriver(
                        new URL(GRID_URL),
                        getCapabilities(FIREFOX)
                );
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return webDriver;
    }

    public static void closeWebDriver() {
        if (webDriver != null) {
            webDriver.quit();
            webDriver = null;
        }
    }

    public static DesiredCapabilities getChromeCapabilities() {
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("useAutomationExtension", false);

        capabilities.setBrowserName("chrome");
        capabilities.setPlatform(Platform.WINDOWS);
        capabilities.setVersion("83");

        capabilities.setCapability(ChromeOptions.CAPABILITY,options);
        return capabilities;
    }


    public static DesiredCapabilities getCapabilities(final String driverName) {
        DesiredCapabilities capabilities;
        switch (driverName) {
            case CHROME:
                capabilities = DesiredCapabilities.chrome();
                ChromeOptions options = new ChromeOptions();
                options.setExperimentalOption("useAutomationExtension", false);

                capabilities.setBrowserName("chrome");
                capabilities.setPlatform(Platform.WINDOWS);
                capabilities.setVersion("83");
                capabilities.setCapability(ChromeOptions.CAPABILITY,options);
                break;
            case FIREFOX:
            default:
                capabilities = new DesiredCapabilities();
                capabilities.setBrowserName("firefox");
                capabilities.setPlatform(Platform.WINDOWS);
                capabilities.setVersion("77");
        }
        return capabilities;
    }

}
