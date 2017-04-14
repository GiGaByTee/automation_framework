package engine;

import org.openqa.selenium.Platform;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import support.Driver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class WebDriverFactory {
    private static final String HUB_URL = "";

    public WebDriver newInstance(Driver driverType) {
        switch (driverType) {
        case CHROME:
            System.setProperty("webdriver.chrome.driver", "web-drivers/chromedriver.exe");
            DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
            desiredCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            desiredCapabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,
                    UnexpectedAlertBehaviour.ACCEPT);
            // changed default download folder
            ChromeOptions options = new ChromeOptions();
            Map<String, Object> prefs = new HashMap<>();
            prefs.put("profile.default_content_settings.popups", 0);
            prefs.put("download.default_directory", "downloads/");
            options.setExperimentalOption("prefs", prefs);
            desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, options);
            return new ChromeDriver(desiredCapabilities);

        case FIREFOX:
            System.setProperty("webdriver.gecko.driver", "/web-drivers/geckodriver.exe");
            // Configured to accept untrusted ssl
            DesiredCapabilities firefoxDesiredCapabilities = new DesiredCapabilities();
            firefoxDesiredCapabilities.setCapability("marionette", true);
            firefoxDesiredCapabilities.setCapability("acceptSslCerts", true);
            firefoxDesiredCapabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,
                    UnexpectedAlertBehaviour.ACCEPT);
            // changed default download folder
            FirefoxProfile profile = new FirefoxProfile();
            profile.setPreference("browser.download.dir", "downloads/");
            profile.setPreference("browser.download.folderList", 2);
            firefoxDesiredCapabilities.setCapability("firefoxOptions", profile);
            return new FirefoxDriver(firefoxDesiredCapabilities);

        case REMOTE_WEB_DRIVER:
            DesiredCapabilities remoteCapabilities = new DesiredCapabilities();
            remoteCapabilities.setPlatform(Platform.WINDOWS);
            remoteCapabilities.setBrowserName("chrome");
            remoteCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            remoteCapabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,
                    UnexpectedAlertBehaviour.ACCEPT);
            // changed default download folder
            ChromeOptions remoteOptions = new ChromeOptions();
            Map<String, Object> remotePrefs = new HashMap<>();
            remotePrefs.put("profile.default_content_settings.popups", 0);
            remotePrefs.put("download.default_directory", "downloads/");
            remoteOptions.setExperimentalOption("prefs", remotePrefs);
            remoteCapabilities.setCapability(ChromeOptions.CAPABILITY, remoteOptions);
            URL url = null;
            try {
                url = new URL(HUB_URL);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return new RemoteWebDriver(url, remoteCapabilities);
        }
        throw new RuntimeException("Unsupported driver type");
    }
}
