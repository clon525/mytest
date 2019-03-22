package ru.org.autotest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FirstTest extends AutoSettings {
        @Test
        public void userLogin(){
            driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);
         driver.findElement(By.className("header2-nav__user")).click();
        driver.findElement(By.id("passp-field-login")).sendKeys("andreymyagkov98@yandex.ru");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='passp-field-passwd']")));
        driver.findElement(By.xpath("//input[@id='passp-field-passwd']")).sendKeys("Testing98");
//нажатие на кнопку "Войти"
        driver.findElement(By.xpath("//button[@type='submit']")).click();
//проверка соответствия названия заголовка
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.header2-nav__user")));
        Assert.assertTrue(driver.findElement(By.cssSelector("div.header2-nav__user")).getText().equals("Мой профиль"));
    }

}
