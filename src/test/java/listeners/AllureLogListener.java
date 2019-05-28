package listeners;

import engine.WebDriverUtils;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.util.List;

public class AllureLogListener implements ITestListener, IInvokedMethodListener {

    @Step("Full log")
    public String getFullTestLog(ITestResult iTestResult) {
        List<String> out = Reporter.getOutput(iTestResult);
        StringBuilder sb = new StringBuilder("Reporter Output: \n");
        for (String s : out) {
            sb.append(s).append("\n");
        }
        return attach(sb.toString());
    }

    @Step("Step1")
    public String step1() {
        return "Step1 descr";
    }

    @Step("Step2")
    public String step2() {
        return "Step2 descr";
    }

    @Attachment
    public String attach(String log) {
        return log;
    }


    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshot(byte[] screenShot) {
        return screenShot;
    }

    public void screenshot() {
        if (WebDriverUtils.getDriver() == null) {
            Reporter.log("Driver for screenshot not found");
            return;
        }

        saveScreenshot(((TakesScreenshot) WebDriverUtils.getDriver()).getScreenshotAs(OutputType.BYTES));

    }




    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {

        step1();
        step2();
    }

    public void afterInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {
        if (iInvokedMethod.isTestMethod()) {
            getFullTestLog(iTestResult);
        }
    }

    public void onStart(ITestContext arg0) {

        System.out.println("Start Of Execution(TEST)->"+arg0.getName());
        Reporter.log( "Start Of Execution(TEST)->"+arg0.getName());

    }

    @Override

    public void onTestStart(ITestResult arg0) {

        step1();
        step2();

    }

    @Override

    public void onTestSuccess(ITestResult arg0) {

        System.out.println("Test Pass->"+arg0.getName());
        Reporter.log( "Test Pass->"+arg0.getName());

    }

    @Override

    public void onTestFailure(ITestResult arg0) {

        screenshot();
    }

    @Override

    public void onTestSkipped(ITestResult arg0) {

        System.out.println("Test Skipped->"+arg0.getName());
        Reporter.log( "Test Skipped->"+arg0.getName());

    }

    @Override

    public void onFinish(ITestContext arg0) {

        System.out.println("END Of Execution(TEST)->"+arg0.getName());
        Reporter.log( "END Of Execution(TEST)->"+arg0.getName());

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }
}