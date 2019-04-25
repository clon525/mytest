package ru.org.autotest;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import org.openqa.selenium.interactions.Actions;

public class ThirdTest extends AutoSettings {

    @Test
    public void toothbrushOrder() {
        driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);

        //перейти в каталог
        driver.findElement(By.cssSelector("div.n-topmenu-new-vertical__left > div > button")).click();
        Actions actions = new Actions(driver);

        // встать на элемент "Бытова техника"
        actions.moveToElement(driver.findElement(By.linkText("Бытовая техника"))).build().perform();

        // кликнуть на раздел "Зубные щетки и ирригаторы"
        driver.findElement(By.linkText("Зубные щетки и ирригаторы")).click();

        // выбрать подраздел "Электрические зубные щетки"
        driver.findElement(By.linkText("Электрические зубные щетки")).click();

        // ввести нижнюю границу цены
        driver.findElement(By.xpath("//input[@name='Цена от']")).sendKeys("999");

        // ввести верхнюю границу цены
        driver.findElement(By.xpath("//input[@name='Цена до']")).sendKeys("1999");

        // подождать, пока товары прогрузятся
        (new WebDriverWait(driver, 15)).until(ExpectedConditions.visibilityOfElementLocated(
                (By.cssSelector("div.NZiH_Kn8Fj"))));

        // элемент, отвечающий за следующие скрытые товары
        WebElement show_more = driver.findElement(By.xpath("//div[@class='n-pager-more__button " +
                "pager-loader_preload']"));

        //покажем все доступные щетки
        while (show_more.isDisplayed()) {
            show_more.click();

            //ждем прогрузки всех товаров
            (new WebDriverWait(driver, 15)).until(ExpectedConditions.
                    visibilityOfAllElementsLocatedBy(By.cssSelector("div.grid-snippet.grid-snippet_react.b-zone." +
                            "b-spy-visible")));
        }

        // список цен товаров
        List<WebElement> price_list = driver.findElements(By.xpath("//div[@class='search-result-snippet']" +
                "//span[starts-with(@class, '_1u3j_pk1db _1pTV0mQZJz')]/span[1]"));

        // проверяем, чтобы цены удовлетворяли введенному интервалу
        for (int i = 1; i < price_list.size(); i++) {
            int price = Integer.parseInt(price_list.get(i).getText().replaceAll("\\s+", ""));
            Assert.assertTrue(price >= 999 && price <= 1999);
        }

        //список кнопок "В корзину на товарах"
        List<WebElement> item_list = driver.findElements(By.cssSelector("button._4qhIn2-ESi._3OWdR9kZRH." +
                "THqSbzx07u"));

        // выберем предпоследнюю
        item_list.get(item_list.size() - 2).click();

        //переход в корзину - ожидание открытия окна и нажатие на кнопку "Перейти в корзину"
        (new WebDriverWait(driver, 15)).until(ExpectedConditions.
                visibilityOfAllElementsLocatedBy(By.linkText("Перейти в корзину")));
        driver.findElement(By.xpath("//span[text()='Перейти в корзину']")).click();

        // ожидание открытия окна оформоения заказа
        (new WebDriverWait(driver, 15)).until(ExpectedConditions.
                visibilityOfAllElementsLocatedBy(By.cssSelector("div._3AlSA6AOKL")));

        //проверка элемента "до бесплатной доставки осталось"
        WebElement delivery = driver.findElement(By.cssSelector("span._3EX9adn_xp"));
        Assert.assertTrue(delivery.getText().contains("До бесплатной доставки"));

        //проверяем, что итоговая стоимость = цена товара + доставка:
        // полная цена
        int total_price = Integer.parseInt(driver.findElement(By.cssSelector("span._1oBlNqVHPq")).getText().
                replaceAll("\\s+|\\D", ""));

        //цена товаров
        int total_item = Integer.parseInt(driver.findElement(By.xpath("//div[contains(@data-auto, " +
                "'total-items')]//span[2]")).getText().replaceAll("\\s+|\\D", ""));

        // цена доставки
        int total_delivery = Integer.parseInt(driver.findElement(By.xpath("//div[contains(@data-auto, " +
                "'total-delivery')]//span[2]")).getText().replaceAll("\\s+|\\D", ""));

        //проверка на эквивалентность
        Assert.assertEquals(total_item + total_delivery, total_price);

        // добавляем товары, пока не получим бесплатную доставку
        int free_delivery = total_item; // берем цену товаров
        while (free_delivery < 2999) {
            // если бесплатной доставки еще нет, то добавляем еще одну щетку
            driver.findElement(By.cssSelector("button._4qhIn2-ESi._2sJs248D-A._18c2gUxCdP._3hWhO4rvmA")).click();
            free_delivery += total_item;
        }

        // ждем появления надписи о бесплатной доставке
        (new WebDriverWait(driver, 15)).until(ExpectedConditions.
                visibilityOfAllElementsLocatedBy(By.xpath("//span[text()='бесплатно']")));

        //проверяем инфомацию о бесплатной доставке
        delivery = driver.findElement(By.cssSelector("span._3EX9adn_xp"));
        Assert.assertTrue(delivery.getText().contains("бесплатную доставку"));

        //проверяем, что итоговая стоимость = цена товара + доставка
        //полная стоимость
        total_price = Integer.parseInt(driver.findElement(By.cssSelector("span._1oBlNqVHPq")).getText().
                replaceAll("\\s+|\\D", ""));

        //стоимость товаров
        total_item = Integer.parseInt(driver.findElement(By.xpath("//div[contains(@data-auto, " +
                "'total-items')]//span[2]")).getText().replaceAll("\\s+|\\D", ""));

        //сравнение
        Assert.assertEquals(total_item, total_price);

        //возврат к исходному состоянию
        driver.close();
    }
}
