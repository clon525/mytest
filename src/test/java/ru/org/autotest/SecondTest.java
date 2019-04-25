package ru.org.autotest;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class SecondTest extends AutoSettings {
    @Test

    @Parameters("cityName")

    public void changeCity(String cityName) {
        driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);
        driver.findElement(By.xpath("//span[@class='link__inner']")).click();
        WebElement city = driver.findElement(By.cssSelector("[class*='region-suggest']")).
                findElement(By.cssSelector("[class*='input__control']"));
        city.click();
        city.sendKeys(cityName);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElementByClassName("suggest2-item_type_text").click();
        for (int i = 0; i < 3; i++) {
            city.sendKeys(Keys.ENTER);
        }
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        String newcity = driver.findElementByClassName("link__inner").getText();
        if (newcity.equals(cityName) != true) {
            Assert.fail("Неверный город " + newcity);
        }
        driver.findElement(By.className("header2-nav__user")).click();
        driver.findElement(By.id("passp-field-login")).sendKeys("andreymyagkov98@yandex.ru");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='passp-field-passwd']")));
        driver.findElement(By.xpath("//input[@id='passp-field-passwd']")).sendKeys("Testing98");
        //нажатие на кнопку "Войти"
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        driver.findElementByClassName("header2__nav").click();
        driver.findElementByXPath("//*[contains(text(), 'Настройки')]").click();
        String winHandleBefore = driver.getWindowHandle();

        //Perform the click operation that opens new window
        //Switch to new window opened
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
        driver.findElementByXPath("//*[contains(text(), 'Регион')]");
        newcity = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[3]/noindex/div/div[1]/div/div/div[1]/span/span[2]/span/span")).getText();
        String city2 = driver.findElement(By.xpath("//*[@id=\"region\"]/div/div/h2/span/span")).getText();
        Assert.assertEquals(newcity, city2, "Города не совпадают");
        driver.close();
        driver.switchTo().window(winHandleBefore);
    }
}
