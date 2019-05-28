import engine.WebDriverUtils;
import listeners.BaseHTMLReporter;
import listeners.SimpleTestNGListener;
import listeners.SimpleTestNGReporter;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import state.HomeState;

import java.io.File;
import java.io.IOException;

@Listeners({SimpleTestNGListener.class})
public class TestOne {

    public Logger logger = Logger.getLogger(getClass());
    WebDriver wb;
    HomeState homeState = new HomeState();

    @BeforeMethod
    public void onStart() {
        wb = WebDriverUtils.getDriver();
    }

    @Test
    public void getAllDepartments() {
        Reporter.log("Open browser url: http://www.lnu.edu.ua/about/faculties/");
        wb.get("http://www.lnu.edu.ua/about/faculties/");
        Reporter.log("getAllDepartmentInfos");
        homeState.getAllDepartmentInfos().forEach(dep -> {
            Reporter.log(dep);
            Reporter.log("--------------------------------------------------------------------");
        });
    }

    @Test
    public void getSpecificDepartment() {
        String dep = "Факультет прикладної математики та інформатики";
        Reporter.log("Open browser url: http://www.lnu.edu.ua/about/faculties/");
        Reporter.log("Get 'Факультет прикладної математики та інформатики':");
        wb.get("http://www.lnu.edu.ua/about/faculties/");
        Reporter.log(homeState.getDepartmentInfo(dep));
    }

    @Test
    public void getSpecificInfoFromDepartment() {
        String dep = "Факультет прикладної математики та інформатики";
        String infoType = "E-mail";
        Reporter.log("Open browser url: http://www.lnu.edu.ua/about/faculties/");
        Reporter.log("Get 'email' from 'Факультет прикладної математики та інформатики':");
        wb.get("http://www.lnu.edu.ua/about/faculties/");
        Reporter.log(homeState.getSpecificInfoFromDepartment(dep, infoType));
    }

    @Test
    public void generateHtmlFileFromDepInfo() throws IOException {
        wb.get("http://www.lnu.edu.ua/about/faculties/");
        File htmlTemplateFile = new File("html/template.html");
        String htmlString = FileUtils.readFileToString(htmlTemplateFile);
        String body = homeState.getDepartmentsAsHtml();
        htmlString = htmlString.replace("$body", body);
        File newHtmlFile = new File("html/new.html");
        FileUtils.writeStringToFile(newHtmlFile, htmlString);
    }

    @AfterMethod
    public void close() {
        Reporter.log("Close browser");
        WebDriverUtils.stop();
    }

}
