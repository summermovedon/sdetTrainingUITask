package com.gismatullin.sdettraininguitest.pages;

import static com.gismatullin.sdettraininguitest.testhelper.TestHelper.getDriver;
import static com.gismatullin.sdettraininguitest.testhelper.TestHelper.loadProperties;
import static com.gismatullin.sdettraininguitest.testhelper.TestHelper.getLogin;
import static com.gismatullin.sdettraininguitest.testhelper.TestHelper.getEmailTheme;
import static com.gismatullin.sdettraininguitest.testhelper.TestHelper.getTimeout;
import static com.gismatullin.sdettraininguitest.testhelper.TestHelper.setTimeout;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import static com.gismatullin.sdettraininguitest.testhelper.TestHelper.findElement;

public class MailBoxPage {

    private final static String UI_MAP_PATH = "src/test/resources/uiMaps/mailBoxPageUIMap.properties";
    private final Properties uiMap = loadProperties(UI_MAP_PATH);
    public static final String SEARCH_QUERY = "папка:Входящие тема:";

    private MailBoxPage() {}

    public static MailBoxPage create() {
        return new MailBoxPage();
    }

    public void sendEmail(String body) {
        findElement(uiMap.getProperty("composeButton")).click();
        findElement(uiMap.getProperty("addressInput")).sendKeys(getLogin());
        findElement(uiMap.getProperty("themeInput")).sendKeys(getEmailTheme());
        findElement(uiMap.getProperty("messageBody")).click(); // focus to message body
        findElement(uiMap.getProperty("messageBody")).sendKeys(body);
        findElement(uiMap.getProperty("sendButton")).click(); // send email
        findElement(uiMap.getProperty("doneLink")).click();
    }

    public int countTestEmails() {
        String theme = getEmailTheme();
        String searchQuery = SEARCH_QUERY + "\"" + theme + "\"";
        refreshMailList();
        clearSearchInput();
        sendSearchQuery(searchQuery);
        return countEmails(theme);
    }

    private void refreshMailList() {
        findElement(uiMap.getProperty("refreshButton")).click();
    }
    
    private void clearSearchInput() {
        findElement(uiMap.getProperty("searchInput")).clear();
    }

    private void sendSearchQuery(String query) {
        findElement(uiMap.getProperty("searchInput")).sendKeys(query + Keys.ENTER);
    }

    private int countEmails(String theme) {
        String themeXPath = uiMap.getProperty("testEmailTheme").split("%s")[0] + theme + 
            uiMap.getProperty("testEmailTheme").split("%s")[1];
        int timeoutBackup = getTimeout();
        setTimeout(200); // helps to avoid long waiting in case of absence of test emails
        List<WebElement> list = getDriver().findElements(By.xpath(themeXPath));
        setTimeout(timeoutBackup);
        return list.size(); // returns number of found emails
    }

    public void logout() {
        findElement(uiMap.getProperty("userPic")).click();
        findElement(uiMap.getProperty("logoutRow")).click();
    }
    
}
