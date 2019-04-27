package ru.org.autotest;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class FailScreen extends TestListenerAdapter {
    @Override
    public void onTestFailure(ITestResult result){
        saveFail(result);
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    private byte[] saveFail(ITestResult result){
        return ((TakesScreenshot) AutoSettings.getDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
