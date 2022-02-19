package com.gismatullin.sdettraininguitest.testhelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TestHelper {

    private static boolean headless = false;
    private static int timeout = 5000;
    private static String login;
    private static String passw;
    private static String emailTheme;
    
    private static final String CONFIG_PATH = "src/test/resources/config.properties";

    static {
        Properties config = loadProperties(CONFIG_PATH);
        if (config.containsKey("headless")) {
            headless = Boolean.valueOf(config.getProperty("headless"));
        }
        if (config.containsKey("timeout")) {
            timeout = Integer.valueOf(config.getProperty("timeout"));
        }
        if (config.containsKey("login") && config.containsKey("passw") && config.containsKey("emailTheme")) {
            login = config.getProperty("login");
            passw = config.getProperty("passw");
            emailTheme = config.getProperty("emailTheme");
        } else {
            String msg = "Wrong config! Required parameters are absent.";
            System.err.println(msg);
            throw new RuntimeException(msg);
        }
    }

    private TestHelper() {}

    public static WebDriver prepareDriver() {
        ChromeOptions options = new ChromeOptions();
        if (headless) {
            options.addArguments("--headless");
        }
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofMillis(timeout));
        setTimeout(driver, timeout);
        return driver;
    }

    public static int getTimeout() {
        return timeout;
    }

    public static void setTimeout(WebDriver driver, int timeout) {
        TestHelper.timeout = timeout;
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(timeout));
    }

    public static String getLogin() {
        return login;
    }

    public static String getPassw() {
        return passw;
    }

    public static String getEmailTheme() {
        return emailTheme;
    }

    public static Properties loadProperties(String path) {
        try (InputStream input = new FileInputStream(new File("").getAbsolutePath() + "/" + path)) {
            Properties props = new Properties();
            props.load(input);
            return props;
        } catch (IOException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static WebElement findElement(WebDriver driver, String locator) {
        By by;
        if (locator.startsWith("//")) {
            by =  By.xpath(locator);
        } else {
            by = By.cssSelector(locator);
        }
        return driver.findElement(by);
    }

}