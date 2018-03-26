package state;

import org.openqa.selenium.WebElement;
import pages.HomePage;

import java.util.List;
import java.util.stream.Collectors;

public class HomeState {

    HomePage homePage;

    public HomeState() {
        homePage = new HomePage();
    }

    public String getDepartmentInfo(String title) {
        return homePage.getDepartmentInfo(title).getText();
    }

    public String getSpecificInfoFromDepartment(String title, String infoType) {
        return homePage.getSpecificInfoFromDepartment(title, infoType).getText();
    }

    public List<String> getAllDepartmentInfos() {
        return homePage.getDepartmentsDetails().stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public String getDepartmentsAsHtml() {
        return homePage.getDepartmentsHTML().getAttribute("innerHTML");
    }
}
