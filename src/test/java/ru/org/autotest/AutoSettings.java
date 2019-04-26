package ru.org.autotest;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class AutoSettings {
    public ChromeDriver driver;

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Andrew\\Downloads\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://beru.ru/");
    }

    @AfterMethod
    public void signOut() {
        // возвращение системы в исходное состояние
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.header2-nav__user")));
        if (driver.findElement(By.cssSelector("div.header2-nav__user")).getText() == "Мой профиль") {
            driver.findElementByClassName("header2-nav__user").click();
            driver.findElementByXPath("/html/body/div[8]/div[2]/div/ul[4]/li[2]/a").click();
        }
        driver.quit();
    }
}