package com.gismatullin.sdettraininguitest.pages;

import static com.gismatullin.sdettraininguitest.testhelper.TestHelper.loadProperties;
import static com.gismatullin.sdettraininguitest.testhelper.TestHelper.getLogin;
import static com.gismatullin.sdettraininguitest.testhelper.TestHelper.getEmailTheme;
import static com.gismatullin.sdettraininguitest.testhelper.TestHelper.getTimeout;
import static com.gismatullin.sdettraininguitest.testhelper.TestHelper.setTimeout;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.gismatullin.sdettraininguitest.testhelper.TestHelper.findElement;

public class MailBoxPage {

    private final static String UI_MAP_PATH = "src/test/resources/uiMaps/mailBoxPageUIMap.properties";
    public static final String SEARCH_QUERY = "папка:Входящие тема:";
    private final Properties uiMap = loadProperties(UI_MAP_PATH);
    private WebDriver driver;

    private MailBoxPage(WebDriver driver) {
        this.driver = driver;
    }

    public static MailBoxPage create(WebDriver driver) {
        return new MailBoxPage(driver);
    }

    private WebElement getComposeButton() {
        return findElement(driver, uiMap.getProperty("composeButton"));
    }

    private WebElement getAddressInput() {
        return findElement(driver, uiMap.getProperty("addressInput"));
    }

    private WebElement getThemeInput() {
        return findElement(driver, uiMap.getProperty("themeInput"));
    }

    private WebElement getMessageBody() {
        return findElement(driver, uiMap.getProperty("messageBody"));
    }

    private WebElement getSendButton() {
        return findElement(driver, uiMap.getProperty("sendButton"));
    }

    private WebElement getDoneLink() {
        return findElement(driver, uiMap.getProperty("doneLink"));
    }

    private WebElement getRefreshButton() {
        return findElement(driver, uiMap.getProperty("refreshButton"));
    }

    private WebElement getSearchInput() {
        return findElement(driver, uiMap.getProperty("searchInput"));
    }

    private List<WebElement> getListOfEmails(String theme) {
        String themeXPath = uiMap.getProperty("testEmailTheme").split("%s")[0] + theme + 
            uiMap.getProperty("testEmailTheme").split("%s")[1];
        int timeoutBackup = getTimeout();
        setTimeout(driver, 200); // helps to avoid long waiting in case of absence of test emails
        List<WebElement> list = driver.findElements(By.xpath(themeXPath));
        setTimeout(driver, timeoutBackup);
        return list;
    }

    private WebElement getUserMenu() {
        return findElement(driver, uiMap.getProperty("userMenu"));
    }

    private WebElement getLogoutMenuItem() {
        return findElement(driver, uiMap.getProperty("logoutMenuItem"));
    }

    public void sendEmail(String body) {
        getComposeButton().click();
        getAddressInput().sendKeys(getLogin());
        getThemeInput().sendKeys(getEmailTheme());
        getMessageBody().click(); // focus to message body
        getMessageBody().sendKeys(body);
        getSendButton().click();
        getDoneLink().click();
    }

    public int countTestEmails() {
        String theme = getEmailTheme();
        String searchQuery = SEARCH_QUERY + "\"" + theme + "\"";
        refreshMailList();
        clearSearchInput();
        sendSearchQuery(searchQuery);
        return countEmailsByTheme(theme);
    }

    private void refreshMailList() {
        getRefreshButton().click();
    }
    
    private void clearSearchInput() {
        getSearchInput().clear();
    }

    private void sendSearchQuery(String query) {
        getSearchInput().sendKeys(query + Keys.ENTER);
    }

    private int countEmailsByTheme(String theme) {
        return getListOfEmails(theme).size();
    }

    public void logout() {
        getUserMenu().click();
        getLogoutMenuItem().click();
    }
    
}
