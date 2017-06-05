import engine.WebDriverUtils;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestOne {

    public Logger logger = Logger.getLogger(getClass());
    WebDriver wb;

    @BeforeMethod
    public void onStart() {
        wb = WebDriverUtils.getDriver();
    }

    @Test
    public void tesst() {
        BasicConfigurator.configure();
        logger.info("Start test");
        System.setProperty("webdriver.chrome.driver", "web-drivers/chromedriver.exe");
        logger.info("Create browser instance");
        wb.get("http://www.lnu.edu.ua/");
        wb.findElement(By.xpath("//*[@class='site-logo uk']")).click();
        logger.info("Finish test");
    }

    @AfterMethod
    public void close() {
        wb.quit();
    }

}
