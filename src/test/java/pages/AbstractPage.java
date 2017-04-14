package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class AbstractPage {
    public AbstractPage(final WebDriver driver, Object page){
        PageFactory.initElements(driver, page);
    }
}
