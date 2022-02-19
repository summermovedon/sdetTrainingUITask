package com.gismatullin.sdettraininguitest.pages;

import static com.gismatullin.sdettraininguitest.testhelper.TestHelper.loadProperties;
import static com.gismatullin.sdettraininguitest.testhelper.TestHelper.findElement;
import static com.gismatullin.sdettraininguitest.testhelper.TestHelper.getLogin;
import static com.gismatullin.sdettraininguitest.testhelper.TestHelper.getPassw;

import java.util.Properties;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage {

    private final static String PAGE_URL = "https://mail.yandex.ru";
    private final static String UI_MAP_PATH = "src/test/resources/uiMaps/mainPageUIMap.properties";
    private final Properties uiMap = loadProperties(UI_MAP_PATH);
    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    private WebElement getStartLoginButton() {
        return findElement(driver, uiMap.getProperty("startLoginButton"));
    }

    private WebElement getLoginInput() {
        return findElement(driver, uiMap.getProperty("loginInput"));
    }

    private WebElement getPasswInput() {
        return findElement(driver, uiMap.getProperty("passwInput"));
    }

    public static MainPage open(WebDriver driver) {
        MainPage mainPage = new MainPage(driver);
        driver.get(PAGE_URL);
        return mainPage;
    }

    public MailBoxPage login() {
        getStartLoginButton().click();
        getLoginInput().sendKeys(getLogin() + Keys.RETURN);
        getPasswInput().sendKeys(getPassw() + Keys.RETURN);
        return MailBoxPage.create(driver);
    }
    
}
