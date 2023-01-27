package com.destacame.config;

import io.cucumber.java.BeforeAll;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import static com.destacame.config.LoggerPage.*;

public class ConfigPage {

    private static final Logger logger = logger(ConfigPage.class);

    private static WebDriver driver;

    public ConfigPage(WebDriver driver) {
        ConfigPage.driver = driver;
    }

    @BeforeAll
    public static void setup(String webBrowser) {

        if (webBrowser.toLowerCase().contentEquals("chrome")) {
            driver = WebDriverManager.chromedriver().create();
            logger.info("-------------------------------------");
            logger.info("Setup Chrome OK");
        } else if (webBrowser.toLowerCase().contentEquals("firefox")) {
            driver = WebDriverManager.firefoxdriver().create();
            logger.info("Setup Firefox OK");
        } else {
            logger.error("Browser not supported");
        }
    }

    public static void waitElement(By locator, int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void waitAndClick(By locator) {
        waitElement(locator, 5);
        isDisplayed(locator);
        click(locator);
    }

    public static void moveToElement(By locator){
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView()", driver.findElement(locator));
        click(locator);
    }

    public static void click(By locator) {
        driver.findElement(locator).click();
    }

    public static String getText(By locator) {
        return driver.findElement(locator).getText();
    }

    public static void type(String inputText, By locator) {
        driver.findElement(locator).sendKeys(inputText);
    }

    public static boolean isDisplayed(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            logger.error(e);
            return false;
        }
    }

    public static void visit(String url) {
        driver.get(url);
        driver.manage().window().maximize();
    }

    public static void quit() {
        driver.quit();
    }

    @Attachment(value = "Screenshot", fileExtension = ".png")
    public static byte[] screenshot(String step) {
        File scr = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(scr, new File("screenshots/step-" + step + ".png"));
        } catch (IOException ignored) {
        }

        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
