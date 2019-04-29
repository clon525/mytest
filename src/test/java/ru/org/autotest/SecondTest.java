package ru.org.autotest;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.ITestContext;

@Listeners({FailScreen.class})
public class SecondTest extends AutoSettings {
    @DataProvider(name = "cityName")

    public Object[] createCities(ITestContext context) {
        return new Object[]{
                "Хвалынск", "Энгельс", "Балаково"};
    }

    @Test(dataProvider = "cityName")
    public void changeCity(String cityName) {
        HomePage home2 = new HomePage(getDriver());
        home2.skipWindow();
        home2.clickCityButton();
        home2.inputCity(cityName);
        home2.waitCity();
        home2.clickDropCity();
        home2.clickNewCityButton();
        home2.waitCityChange(cityName);
        home2.checkCity1(cityName);
        home2.clickProfileButton();
        LoginPage login2 = new LoginPage(getDriver());
        login2.inputLogin();
        login2.clickSubmitButton();
        login2.waitPassword();
        login2.inputPassword();
        login2.clickSubmitButton();
        home2.waitProfile();
        home2.clickProfileButton();
        home2.clickSettingsButton();
        String winHandleBefore = home2.switchTab();
        SettingsPage settings2 = new SettingsPage(getDriver());
        settings2.checkCity2();
        settings2.rollbackHome(winHandleBefore);
    }
}
