package ru.org.autotest;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ElectricToothbrushesPage {
    private WebDriver driver;

    public ElectricToothbrushesPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Input low border for price")
    public void inputLowPrice(){
        driver.findElement(By.xpath("//input[@name='Цена от']")).sendKeys("999");
    }

    @Step("Input top border for price")
    public void inputHighPrice(){
        driver.findElement(By.xpath("//input[@name='Цена до']")).sendKeys("1999");
    }

    @Step("Wait for the products")
    public void waitToothbrushes(){
        (new WebDriverWait(driver, 15)).until(ExpectedConditions.visibilityOfElementLocated(
                (By.cssSelector("div.NZiH_Kn8Fj"))));
    }

    @Step("Show all toouthbrushes")
    public void showToothbrushes(){
        WebElement downList = driver.findElement(By.xpath("//div[@class='n-pager-more__button " +
                "pager-loader_preload']"));

        //show all available toothbrushes
        while (downList.isDisplayed()) {
            downList.click();

            //waiting for the loading of all goods
            (new WebDriverWait(driver, 15)).until(ExpectedConditions.
                    visibilityOfAllElementsLocatedBy(By.cssSelector("div.grid-snippet.grid-snippet_react.b-zone." +
                            "b-spy-visible")));
        }
    }

    @Step("Check price for every toothbrush")
    public void checkToothbrushesPrice(){
        // price list
        List<WebElement> priceList = driver.findElements(By.xpath("//div[@class='search-result-snippet']" +
                "//span[starts-with(@class, '_1u3j_pk1db _1pTV0mQZJz')]/span[1]"));

        //check that prices are included in the entered interval
        for (int i = 1; i < priceList.size(); i++) {
            int price = Integer.parseInt(priceList.get(i).getText().replaceAll("\\s+", ""));
            Assert.assertTrue(price >= 999 && price <= 1999);
        }
    }

    @Step("choose the penultimate toothbrush")
    public void chooseToothbrush() {
        //list of "В корзину" buttons
        List<WebElement> productList = driver.findElements(By.cssSelector("[class*='_2w0qPDYwej']"));
        productList.get(productList.size() - 2).click();
        (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.presenceOfElementLocated(By
                        .cssSelector("[class*='_1sjxYfIabK _26mXJDBxtH']")));

    }

    @Step("Click on cart button")
    public void clickCartButton(){
        driver.findElement(By.xpath("//span[text()='Перейти в корзину']")).click();
    }
}
