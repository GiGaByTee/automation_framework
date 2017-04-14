import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test {

    @org.testng.annotations.Test
    public void tesst() {
        System.setProperty("webdriver.chrome.driver", "web-drivers/chromedriver.exe");
        WebDriver wb = new ChromeDriver();
        wb.get("http://www.lnu.edu.ua/");
        wb.findElement(By.xpath("//*[@class='site-logo uk']")).click();
    }
}
