package com.gismatullin.sdettraininguitest.pages;

import static com.gismatullin.sdettraininguitest.testhelper.TestHelper.getLogin;
import static com.gismatullin.sdettraininguitest.testhelper.TestHelper.getPassw;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {

    private final static String PAGE_URL = "https://mail.yandex.ru";

    @FindBy(css = "a.HeadBanner-Button-Enter")
    public WebElement startLoginButton;

    @FindBy(css = "input#passp-field-login")
    public WebElement loginInput;

    @FindBy(css = "input#passp-field-passwd")
    public WebElement passwInput;

    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public MainPage open() {
        driver.get(PAGE_URL);
        return this;
    }

    public MailBoxPage login() {
        startLoginButton.click();
        loginInput.sendKeys(getLogin() + Keys.RETURN);
        passwInput.sendKeys(getPassw() + Keys.RETURN);
        return new MailBoxPage(driver);
    }
    
}
