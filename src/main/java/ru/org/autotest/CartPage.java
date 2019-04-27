package ru.org.autotest;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class CartPage {
    private ChromeDriver driver;

    public CartPage(ChromeDriver driver) {
        this.driver = driver;
    }

    @Step("wait for cart window")
    public void waitCartWindow() {
        // ожидание открытия окна оформления заказа
        (new WebDriverWait(driver, 15)).until(ExpectedConditions.
                visibilityOfAllElementsLocatedBy(By.cssSelector("div._3AlSA6AOKL")));
    }

    @Step("Check 'before free delivery' status")
    public void checkbeforeDelivery() {
        WebElement delivery = driver.findElement(By.cssSelector("span._3EX9adn_xp"));
        Assert.assertTrue(delivery.getText().contains("До бесплатной доставки"));
    }

    @Step("Compare total price and delivery + price of goods")
    public int comparePrice1() {
        int fullPrice = Integer.parseInt(driver.findElement(By.cssSelector("span._1oBlNqVHPq")).getText().
                replaceAll("\\s+|\\D", ""));

        int fullProduct = Integer.parseInt(driver.findElement(By.xpath("//div[contains(@data-auto, " +
                "'total-items')]//span[2]")).getText().replaceAll("\\s+|\\D", ""));

        int fullDelivery = Integer.parseInt(driver.findElement(By.xpath("//div[contains(@data-auto, " +
                "'total-delivery')]//span[2]")).getText().replaceAll("\\s+|\\D", ""));

        //проверка на эквивалентность
        Assert.assertEquals(fullProduct + fullDelivery, fullPrice);
        return fullProduct;
    }

    @Step("add goods until we get free shipping")
    public void addtoFreeDelivery(int fullProduct) {
        // добавляем товары, пока не получим бесплатную доставку
        int freeDelivery = fullProduct; // берем цену товаров
        while (freeDelivery < 2999) {
            // если бесплатной доставки еще нет, то добавляем еще одну щетку
            driver.findElement(By.cssSelector("button._4qhIn2-ESi._2sJs248D-A._18c2gUxCdP._3hWhO4rvmA")).click();
            freeDelivery += fullProduct;
        }
    }

    @Step("wait free delivery message")
    public void waitFreeDeliveryMessage() {
        (new WebDriverWait(driver, 15)).until(ExpectedConditions.
                visibilityOfAllElementsLocatedBy(By.xpath("//span[text()='бесплатно']")));
    }

    @Step("check free delivery message")
    public void checkFreeDelivery() {
        WebElement delivery = driver.findElement(By.cssSelector("span._3EX9adn_xp"));
        Assert.assertTrue(delivery.getText().contains("бесплатную доставку"));
    }

    @Step("Compare total price and price of goods")
    public void comparePrice2() {
        int fullPrice = Integer.parseInt(driver.findElement(By.cssSelector("span._1oBlNqVHPq")).getText().
                replaceAll("\\s+|\\D", ""));
        int fullProduct = Integer.parseInt(driver.findElement(By.xpath("//div[contains(@data-auto, " +
                "'total-items')]//span[2]")).getText().replaceAll("\\s+|\\D", ""));
        Assert.assertEquals(fullProduct, fullPrice);
    }

}
