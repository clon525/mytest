package ru.org.autotest;

import org.testng.annotations.Test;
import org.testng.annotations.Listeners;

@Listeners({FailScreen.class})
public class FirstTest extends AutoSettings {

    @Test
    public void userLogin() {
        HomePage home1 = new HomePage(getDriver());
        home1.skipWindow();
        home1.clickProfileButton();
        LoginPage login1 = new LoginPage(getDriver());
        login1.inputLogin();
        login1.clickSubmitButton();
        login1.waitPassword();
        login1.inputPassword();
        login1.clickSubmitButton();
        home1.waitProfile();
        home1.checkProfile();
    }
}
