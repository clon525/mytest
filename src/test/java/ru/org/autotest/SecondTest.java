package ru.org.autotest;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.ITestContext;

import java.util.concurrent.TimeUnit;


public class SecondTest extends AutoSettings {
    @DataProvider(name = "cityName")

    public Object[] createCities(ITestContext context) {
        return new Object[]{
                "Хвалынск", "Энгельс", "Балаково"};
    }

    @Test(dataProvider = "cityName")
    public void changeCity(String cityName) {
        driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);
        driver.findElement(By.xpath("//span[@class='link__inner']")).click();
        WebElement city = driver.findElement(By.cssSelector("[class*='region-suggest']")).
                findElement(By.cssSelector("[class*='input__control']"));
        city.click();
        city.sendKeys(cityName);
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.region-suggest__list-item")));
        driver.findElement(By.cssSelector("div.region-suggest__list-item")).click();
        //нажатие на кнопку "продолжить с новым регионом"
        driver.findElement(By.cssSelector("div.header2-region-popup > button")).click();
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.textToBe(By.cssSelector("span.link__inner"), cityName));
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




























































































































































































        (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.header2-nav__user")));
        driver.findElement(By.cssSelector("div.header2-nav__user")).click();
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
