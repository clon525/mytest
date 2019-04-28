package ru.org.autotest;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

public class EventScreen extends AbstractWebDriverEventListener{
    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {
        makeScreen();
    }

    @Override
    public void afterClickOn(WebElement element, WebDriver driver) {
        makeScreen();
    }

    @Override
    public void afterChangeValueOf(WebElement element, WebDriver driver, java.lang.CharSequence[] keysToSend){
        makeScreen();
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    private byte[] makeScreen(){
        return ((TakesScreenshot) AutoSettings.getDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
