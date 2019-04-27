package ru.org.autotest;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private ChromeDriver driver;

    public LoginPage(ChromeDriver driver) {
        this.driver = driver;
    }

    @Step("Input login")
    public void inputLogin() {
        driver.findElement(By.id("passp-field-login")).sendKeys("andreymyagkov98@yandex.ru");
    }

    @Step("Submit login or password input")
    public void clickSubmitButton() {
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

    @Step("Wait for password textarea")
    public void waitPassword() {
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='passp-field-passwd']")));
    }

    @Step("Input Password")
    public void inputPassword() {
        driver.findElement(By.xpath("//input[@id='passp-field-passwd']")).sendKeys("Testing98");
    }
}
