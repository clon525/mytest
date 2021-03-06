package ru.org.autotest;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({FailScreen.class})
public class ThirdTest extends AutoSettings {

    @Test
    public void toothbrushOrder() {
        HomePage home3 = new HomePage(getDriver());
        home3.skipWindow();
        home3.clickCatalogButton();
        home3.chooseAppliances();
        home3.clickToothbrushes();
        ToothbrushesPage tooth3 = new ToothbrushesPage(getDriver()) ;
        tooth3.clickElectricToothbBrushes();
        ElectricToothbrushesPage electrictooth3 = new ElectricToothbrushesPage(getDriver()) ;
        electrictooth3.inputLowPrice();
        electrictooth3.inputHighPrice();
        electrictooth3.waitToothbrushes();
        electrictooth3.showToothbrushes();
        electrictooth3.checkToothbrushesPrice();
        electrictooth3.chooseToothbrush();
        electrictooth3.clickCartButton();
        CartPage cart3 = new CartPage(getDriver());
        cart3.waitCartWindow();
        cart3.checkbeforeDelivery();
        int fullProduct = cart3.comparePrice1();
        cart3.addtoFreeDelivery(fullProduct);
        cart3.waitFreeDeliveryMessage();
        cart3.checkFreeDelivery();
        cart3.comparePrice2();
    }
}
