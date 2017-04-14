package pages;

import engine.WebDriverUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends AbstractPage {

    @FindBy(xpath = "//*[@class = 'site-logo uk']")
    private WebElement logoLink;

    @FindBy(xpath = "//*[@class = 'menu']//a[contains(@href, 'admission')]")
    private WebElement admissionButton;

    @FindBy(xpath = "//*[@class = 'menu']//a[contains(@href, 'academic')]")
    private WebElement academicButton;

    @FindBy(xpath = "//*[@class = 'menu']//a[contains(@href, 'for-alumni')]")
    private WebElement alumniButton;

    @FindBy(xpath = "//*[@class = 'menu']//a[contains(@href, 'for-employees')]")
    private WebElement employeesButton;

    @FindBy(xpath = "//*[@class = 'menu']//a[contains(@href, 'cooperation')]")
    private WebElement cooperationButton;

    @FindBy(xpath = "//form[contains(@class , 'search-form')]")
    private WebElement searchButton;

    @FindBy(xpath = "//form[contains(@class , 'search-form')]//input")
    private WebElement searchInput;

    public HomePage() {
        super(WebDriverUtils.getDriver(), HomePage.class);
    }

    public WebElement getLogoLink() {
        return logoLink;
    }
}
