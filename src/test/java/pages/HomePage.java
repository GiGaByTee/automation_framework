package pages;

import engine.WebDriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends AbstractPage {

    public static final String DEPARTMENT_INFO = "//li[@class='clearfix' and child::*" +
            "[contains(text(),'%s')]]//*[@class='details']";
    public static final String DEP_SPECIFIC_INFO = ".//*[contains(text(), '%s')]/../*[@class='value']";

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

    @FindBy(css = ".structural-units .title")
    private List<WebElement> departmentsTitle;

    @FindBy(xpath = "//*[@class='structural-units']//*[@class='details']")
    private List<WebElement> departmentsDetails;

    public HomePage() {
        super(WebDriverUtils.getDriver(), HomePage.class);
    }

    public WebElement getLogoLink() {
        return logoLink;
    }

    public List<WebElement> getDepartmentsTitle() {
        return departmentsTitle;
    }

    public List<WebElement> getDepartmentsDetails() {
        return WebDriverUtils.getDriver().findElements(By.xpath("//*[@class='structural-units']//*[@class='clearfix']"));
    }

    public WebElement getDepartmentInfo(String departmentTitle) {
        return WebDriverUtils.getDriver().findElement(By.xpath(String.format(
                DEPARTMENT_INFO, departmentTitle
        )));
    }

    public WebElement getSpecificInfoFromDepartment(String depTitle, String infoType) {
        return getDepartmentInfo(depTitle).findElement(By.xpath(String.format(DEP_SPECIFIC_INFO, infoType)));
    }

    public WebElement getDepartmentsHTML() {
        return WebDriverUtils.getDriver().findElement(By.cssSelector(".structural-units"));
    }
}
