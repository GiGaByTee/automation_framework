package engine;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import support.Driver;

import java.util.concurrent.TimeUnit;

public class WebDriverUtils {

    public static final long IMPLICITLY_WAIT_TIMEOUT = 30;
    // Driver pool
    private static final Logger LOG = Logger.getLogger(WebDriverUtils.class);
    private static final ThreadLocal<WebDriver> pool = new ThreadLocal<>();
    private static final WebDriverFactory webDriverFactory = new WebDriverFactory();

    private WebDriverUtils() {
    }

    public static WebDriver getDriver() {
        return pool.get() != null ? pool.get() : createAndGetDriver();
    }

    private static WebDriver createAndGetDriver() {
        LOG.info("Starting new browser instance");
        WebDriver driver = webDriverFactory.newInstance(Driver.CHROME);
        driver.manage().timeouts().implicitlyWait(IMPLICITLY_WAIT_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        pool.set(driver);
        return driver;
    }

    public static void stop() {
        WebDriver driver = getDriver();
        LOG.info("Stopping browser.");
        removeDriverFromDriverPool();
        if (driver != null) {
            driver.quit();
        }
        LOG.info("Browser has been stopped.");
    }

    public static void removeDriverFromDriverPool() {
        pool.remove();
    }

    public static void mouseOver(WebElement el) {
        Actions action = new Actions(getDriver());
        action.moveToElement(el).build().perform();
    }

    public static void dragAndDrop(WebElement elementToDrag, WebElement placeDropTo) {
        Actions action = new Actions(getDriver());
        action.clickAndHold(elementToDrag).moveToElement(placeDropTo).release(placeDropTo).build().perform();
    }

    public static void doubleClick(WebElement element) {
        new Actions(getDriver()).doubleClick(element).build().perform();
    }

    public static String getCurrentUrl() {
        return getDriver().getCurrentUrl();
    }

    public static void refresh() {
        getDriver().navigate().refresh();
    }

    public static void alertAccept() {
        getDriver().switchTo().alert().accept();
    }

    public static void alertDismiss() {
        getDriver().switchTo().alert().dismiss();
    }
}
