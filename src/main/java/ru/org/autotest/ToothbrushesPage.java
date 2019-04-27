package ru.org.autotest;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class ToothbrushesPage {
    private ChromeDriver driver;

    public ToothbrushesPage(ChromeDriver driver) {
        this.driver = driver;
    }

    @Step("Click on electric toothbrushes")
    public void clickElectricToothbBrushes(){
        // выбрать подраздел "Электрические зубные щетки"
        driver.findElement(By.linkText("Электрические зубные щетки")).click();
    }
}
