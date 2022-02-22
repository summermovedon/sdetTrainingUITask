package com.gismatullin.sdettraininguitest.tests;

import static com.gismatullin.sdettraininguitest.testhelper.TestHelper.prepareDriver;

import com.gismatullin.sdettraininguitest.pages.MailBoxPage;
import com.gismatullin.sdettraininguitest.pages.MainPage;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;

public abstract class BaseTest {

    protected static WebDriver driver;

    @BeforeAll
    public static void tearUp() {
        driver = prepareDriver();
        new MainPage(driver).open().login();
    }

    @AfterAll
    public static void tearDown() {
        new MailBoxPage(driver).logout();
        driver.quit();
    }
    
}
