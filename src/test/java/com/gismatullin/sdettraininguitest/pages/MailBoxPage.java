package com.gismatullin.sdettraininguitest.pages;

import static com.gismatullin.sdettraininguitest.testhelper.TestHelper.getEmailTheme;
import static com.gismatullin.sdettraininguitest.testhelper.TestHelper.getLogin;
import static com.gismatullin.sdettraininguitest.testhelper.TestHelper.getTimeout;
import static com.gismatullin.sdettraininguitest.testhelper.TestHelper.setTimeout;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MailBoxPage {

    // composing email

    @FindBy(css = "a.mail-ComposeButton")
    private WebElement composeButton;

    @FindBy(css = "div.composeYabbles")
    private WebElement addressInput;

    @FindBy(css = "input.ComposeSubject-TextField")
    private WebElement themeInput;

    @FindBy(css = "div.cke_wysiwyg_div")
    private WebElement messageBody;

    // sending email

    @FindBy(css = "div.ComposeSendButton button")
    private WebElement sendButton;

    @FindBy(css = "a.ComposeDoneScreen-Link")
    private WebElement doneLink;

    @FindBy(css = "span.mail-ComposeButton-Refresh")
    private WebElement refreshButton;

    // searching emails

    @FindBy(css = "input.textinput__control")
    private WebElement searchInput;

    private final By searchEmailsBy = By.xpath(
        String.format("//span[@title='%s']", getEmailTheme()));

    // logout

    @FindBy(css = "a.legouser__current-account")
    private WebElement userMenu;

    @FindBy(css = "a.legouser__menu-item_action_exit")
    private WebElement logoutMenuItem;

    private static final int SHORT_TIMEOUT = 200;
    private static final String SEARCH_QUERY = "папка:Входящие тема:";
    private WebDriver driver;

    public MailBoxPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void sendEmail(String body) {
        composeButton.click();
        addressInput.sendKeys(getLogin());
        themeInput.sendKeys(getEmailTheme());
        messageBody.click(); // focus to message body
        messageBody.sendKeys(body);
        sendButton.click();
        doneLink.click();
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
        refreshButton.click();
    }
    
    private void clearSearchInput() {
        searchInput.clear();
    }

    private void sendSearchQuery(String query) {
        searchInput.sendKeys(query + Keys.ENTER);
    }

    private int countEmailsByTheme(String theme) {
        return getListOfEmails(theme).size();
    }

    public void logout() {
        userMenu.click();
        logoutMenuItem.click();
    }

    private List<WebElement> getListOfEmails(String theme) {
        int timeoutBackup = getTimeout();
        setTimeout(driver, SHORT_TIMEOUT); // helps to avoid long waiting in case of absence of emails
        List<WebElement> list = driver.findElements(searchEmailsBy);
        setTimeout(driver, timeoutBackup);
        return list;
    }
    
}
