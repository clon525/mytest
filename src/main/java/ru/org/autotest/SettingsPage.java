package ru.org.autotest;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;


public class SettingsPage {
    private ChromeDriver driver;

    public SettingsPage(ChromeDriver driver) {
        this.driver = driver;
    }

    @Step("Compare regular region and delivery region")
    public void checkCity2() {
        Assert.assertEquals(driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[3]/noindex/div/div[1]/div/div/div[1]/span/span[2]/span/span")).getText(), driver.findElement(By.xpath("//*[@id=\"region\"]/div/div/h2/span/span")).getText(), "Города не совпадают");
    }

    @Step("Close settings tab and roll back to home page")
    public void rollbackHome(String winHandleBefore) {
        driver.close();
        driver.switchTo().window(winHandleBefore);
    }
}
