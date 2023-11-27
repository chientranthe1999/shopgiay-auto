package com.sat.utils;

import com.github.javafaker.Faker;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.Locale;
import java.util.concurrent.TimeUnit;


public abstract class BasicTest {
    protected static WebDriver driver;
    protected static WebDriverWait wait;
    protected static Faker faker;
    protected static JavascriptExecutor js;

    @BeforeMethod
    public void preCondition() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
        faker = new Faker(new Locale("en"));
        js = (JavascriptExecutor) driver;
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void postCondition(){
        // Quit the Browser
//        driver.quit();
    }

    public void waitElementPresent(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitElementClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitUrlTobe(String url) {
        wait.until(ExpectedConditions.urlContains(url));
    }
}