package ru.org.autotest;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class SecondTest extends AutoSettings {
    @Test
    public void userLogin(){
        driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);
        driver.findElement(By.xpath("//span[@class='link__inner']")).click();
        //driver.findElement(By.xpath("//input[@class='input__control']")).sendKeys(city);
        //driver.findElement(By.xpath("//input[@class='input__control']")).sendKeys("Хвалынск");
        WebElement city = driver.findElement(By.cssSelector("[class*='region-suggest']")).
                findElement(By.cssSelector("[class*='input__control']"));

        city.click();

        city.sendKeys("Хвалынск");


        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElementByClassName("suggest2-item_type_text").click();
        city.sendKeys(Keys.ENTER);
        city.sendKeys(Keys.ENTER);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String newcity = driver.findElementByClassName("link__inner").getText();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        newcity = driver.findElementByClassName("link__inner").getText();
        if (newcity.equals("Хвалынск") != true){
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
        driver.findElementByClassName("");
        driver.findElementByXPath("//*[contains(text(), 'Регион')]");
       // newcity = driver.findElementByClassName("link__inner").getText();
        //if (newcity.equals("Хвалынск") != true){
      //      Assert.fail("Неверный город " + newcity);
       // }
    }

}
