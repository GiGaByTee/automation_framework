import engine.WebDriverUtils;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import state.HomeState;

import java.io.File;
import java.io.IOException;

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
        System.out.println("All departments:");
        wb.get("http://www.lnu.edu.ua/about/faculties/");
        homeState.getAllDepartmentInfos().forEach(dep -> {
            System.out.println(dep);
            System.out.println("--------------------------------------------------------------------");
        });
    }

    @Test
    public void getSpecificDepartment() {
        String dep = "Факультет прикладної математики та інформатики";
        System.out.println("Get 'Факультет прикладної математики та інформатики':");
        wb.get("http://www.lnu.edu.ua/about/faculties/");
        System.out.println(homeState.getDepartmentInfo(dep));
    }

    @Test
    public void getSpecificInfoFromDepartment() {
        String dep = "Факультет прикладної математики та інформатики";
        String infoType = "E-mail";
        System.out.println("Get 'email' from 'Факультет прикладної математики та інформатики':");
        wb.get("http://www.lnu.edu.ua/about/faculties/");
        System.out.println(homeState.getSpecificInfoFromDepartment(dep, infoType));
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
        WebDriverUtils.stop();
    }

}
