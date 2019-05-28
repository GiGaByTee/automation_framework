package listeners;

import engine.WebDriverUtils;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ScreenshotListener extends TestListenerAdapter {

    static {
        final String ESCAPE_PROPERTY = "org.uncommons.reportng.escape-output";
        System.setProperty(ESCAPE_PROPERTY, "false");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
        String methodName = result.getName();
        if(!result.isSuccess()){
            File scrFile = ((TakesScreenshot) WebDriverUtils.getDriver()).getScreenshotAs(OutputType.FILE);
            try {
                String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath() + "/target/surefire-reports";

                Date currentDate = calendar.getTime();
                File destFile = new File((String) reportDirectory+"/failure_screenshots/"+methodName+"_"+formater.format(currentDate)+".png");
                FileUtils.copyFile(scrFile, destFile);

                File destFile1 = new File((String) reportDirectory + "/html/failure_screenshots/" + methodName + "_" + formater.format(currentDate) + ".png");
                FileUtils.copyFile(scrFile, destFile1);

                String relPath = "failure_screenshots/"+methodName+"_"+formater.format(currentDate)+".png";

                Reporter.log("<a href='"+ relPath + "'> <img src='"+ relPath + "' height='100' width='100'/> </a>");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}