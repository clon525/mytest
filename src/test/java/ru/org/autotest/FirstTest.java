package ru.org.autotest;

import org.testng.Assert;
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
            //driver.findElement(By.xpath("//button[@data-lego='react]")).click();

//проверка соответствия названия заголовка
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.header2-nav__user")));
        Assert.assertEquals(driver.findElement(By.cssSelector("div.header2-nav__user")).getText(),"Мой профиль", "Кнопка входа профился не найдена");
        driver.close();
    }


}
