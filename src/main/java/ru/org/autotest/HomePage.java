package ru.org.autotest;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class HomePage {
    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Skip advertising window")
    public void skipWindow() {
        driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);
    }

    @Step("Click on My Profile/LogIn")
    public void clickProfileButton() {
        driver.findElement(By.className("header2-nav__user")).click();
    }

    @Step("Wait profile button")
    public void waitProfile() {
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.header2-nav__user")));
    }

    @Step("Check first test - my profile")
    public void checkProfile() {
        Assert.assertEquals(driver.findElement(By.cssSelector("div.header2-nav__user")).getText(), "Мой профиль", "Кнопка входа профиля не найдена");
    }

    @Step("Click on city")
    public void clickCityButton() {
        driver.findElement(By.xpath("//span[@class='link__inner']")).click();
    }

    @Step("Input city")
    public void inputCity(String cityName) {
        WebElement city = driver.findElement(By.cssSelector("[class*='region-suggest']")).
                findElement(By.cssSelector("[class*='input__control']"));
        city.click();
        city.sendKeys(cityName);
    }

    @Step("Waiting for the city")
    public void waitCity() {
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.region-suggest__list-item")));
    }

    @Step("Click on the drop down city")
    public void clickDropCity() {
        driver.findElement(By.cssSelector("div.region-suggest__list-item")).click();
    }

    @Step("Click 'Continue with new city' button")
    public void clickNewCityButton() {
        driver.findElement(By.cssSelector("div.header2-region-popup > button")).click();
    }

    @Step("Waiting for city change")
    public void waitCityChange(String cityName) {
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.textToBe(By.cssSelector("span.link__inner"), cityName));
    }

    @Step("Check city")
    public void checkCity1(String cityName) {
        String newcity = driver.findElement(By.className("link__inner")).getText();
        if (newcity.equals(cityName) != true) {
            Assert.fail("Неверный город " + newcity);
        }
    }

    @Step("Click setting button")
    public void clickSettingsButton() {
        driver.findElement(By.xpath("//*[contains(text(), 'Настройки')]")).click();
    }

    @Step("Switch to settings tab")
    public String switchTab() {
        String winHandleBefore = driver.getWindowHandle();
        //Perform the click operation that opens new window
        //Switch to new window opened
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
        return winHandleBefore;
    }

    @Step("Click catalog button")
    public void clickCatalogButton() {
        driver.findElement(By.cssSelector("div.n-topmenu-new-vertical__left > div > button")).click();
    }

    @Step("Choose appliances in catalog")
    public void chooseAppliances() {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.linkText("Бытовая техника"))).build().perform();
    }

    @Step("Click on toothbrushes")
    public void clickToothbrushes() {
        // кликнуть на раздел "Зубные щетки и ирригаторы"
        driver.findElement(By.linkText("Зубные щетки и ирригаторы")).click();
    }
}
