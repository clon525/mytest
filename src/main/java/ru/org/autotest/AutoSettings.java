package ru.org.autotest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.concurrent.TimeUnit;

public class AutoSettings {
    private static EventFiringWebDriver driver;
    private static final String CHPATH = System.getProperty("chrome", "C:\\Users\\Andrew\\Downloads\\chromedriver_win32\\chromedriver.exe");

    public static WebDriver getDriver() {
        return driver;
    }

    @BeforeMethod
    public void setup() {
        EventScreen checker = new EventScreen();
        System.setProperty("webdriver.chrome.driver", CHPATH);
        ChromeDriver driver1 = new ChromeDriver();
        driver = new EventFiringWebDriver(driver1);
        driver.register(checker);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://beru.ru/");
    }

    @AfterMethod
    public void signOut() {
        // return system to default
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.header2-nav__user")));
        if (driver.findElement(By.cssSelector("div.header2-nav__user")).getText() == "Мой профиль") {
            driver.findElement(By.className("header2-nav__user")).click();
            driver.findElement(By.xpath("/html/body/div[8]/div[2]/div/ul[4]/li[2]/a")).click();
        }
        driver.quit();
    }
}