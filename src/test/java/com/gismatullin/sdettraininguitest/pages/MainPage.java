package com.gismatullin.sdettraininguitest.pages;

import static com.gismatullin.sdettraininguitest.testhelper.TestHelper.getDriver;
import static com.gismatullin.sdettraininguitest.testhelper.TestHelper.loadProperties;
import static com.gismatullin.sdettraininguitest.testhelper.TestHelper.findElement;
import static com.gismatullin.sdettraininguitest.testhelper.TestHelper.getLogin;
import static com.gismatullin.sdettraininguitest.testhelper.TestHelper.getPassw;

import java.util.Properties;

import org.openqa.selenium.Keys;

public class MainPage {

    private final static String PAGE_URL = "https://mail.yandex.ru";

    private final static String UI_MAP_PATH = "src/test/resources/uiMaps/mainPageUIMap.properties";
    private final Properties uiMap = loadProperties(UI_MAP_PATH);

    private MainPage() {}

    public static MainPage open() {
        MainPage mainPage = new MainPage();
        getDriver().get(PAGE_URL);
        return mainPage;
    }

    public MailBoxPage login() {
        findElement(uiMap.getProperty("startLoginButton")).click();
        findElement(uiMap.getProperty("loginInput")).sendKeys(getLogin() + Keys.RETURN);
        findElement(uiMap.getProperty("passwInput")).sendKeys(getPassw() + Keys.RETURN);
        return MailBoxPage.create();
    }
    
}
