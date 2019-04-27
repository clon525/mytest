package ru.org.autotest;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class ToothbrushesPage {
    private WebDriver driver;

    public ToothbrushesPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Click on electric toothbrushes")
    public void clickElectricToothbBrushes(){
        // выбрать подраздел "Электрические зубные щетки"
        driver.findElement(By.linkText("Электрические зубные щетки")).click();
    }
}
