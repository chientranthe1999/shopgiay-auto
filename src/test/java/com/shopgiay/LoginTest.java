 package com.shopgiay;

import core.models.LoginModel;
import core.utils.Constant;
import core.utils.CsvHelper;
import org.openqa.selenium.NotFoundException;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import core.utils.BasicTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

import java.util.List;
import java.util.NoSuchElementException;

 public class LoginTest extends BasicTest {
    private List<LoginModel> loginData;
    private LoginModel currentTestData;
    private final String dataFileName = "login.csv";

    @BeforeSuite
    public void initTestData() {
        loginData = CsvHelper.csvReader(dataFileName, LoginModel.class);
    }

    public void writeResultToFile() {
        if(loginData == null) {
            System.out.println("File is empty or not init file data");
        }
        CsvHelper.csvWriter(dataFileName, loginData, LoginModel.class);
        System.out.println("Save data to file successfully, filename: " + dataFileName);
    }

    public void compareExpected(String message, String expected) {
        System.out.println("Actual message display: " + message);
        if(message.equals(expected)) {
            currentTestData.setResult("Pass");
        } else currentTestData.setResult("Fail");
        writeResultToFile();
    }

    public void failTestWithNoMessageDisplay() {
        currentTestData.setResult("Fail");
        writeResultToFile();
        Assert.fail("Does not have any messages display");
    }

    public LoginModel getDataByTestName(String testId) {
        for(LoginModel loginData : this.loginData) {
            if(loginData.getId().equals(testId)) {
                return loginData;
            }
        };
        throw new NotFoundException("Cant find data with testId: " + testId);
    }

    public void initLoginPage() {
        System.out.println("Open to Login page: " + Constant.LOGIN_URL);
        driver.get(Constant.LOGIN_URL);
    }

    public void enterUserName(String username) {
        System.out.println("Enter username: " + username);
        WebElement iptUserNameLogin = driver.findElement(By.id("username"));
        iptUserNameLogin.clear();
        iptUserNameLogin.sendKeys(username);
    }

    public void enterPassword(String password) {
        System.out.println("Enter password: " + password);
        WebElement iptPassLogin = driver.findElement(By.id("password"));
        iptPassLogin.clear();
        iptPassLogin.sendKeys(password);
    }

    public void clickLogin() {
        System.out.println("Click on 'Login' button");
        driver.findElement(By.xpath("//button[@class='btn-login']")).click();
    }

    @Test( priority = 1)
    public void TC01_Login_Success() {
        String testId = getCurrentMethodName();
        currentTestData = getDataByTestName(testId);

        try {
            initLoginPage();
            enterUserName(currentTestData.getUsername());
            enterPassword(currentTestData.getPassword());

            clickLogin();

            System.out.println("Verify display homepage");
            Assert.assertEquals(driver.getTitle(), "TRANG CHỦ");
            currentTestData.setResult("Pass");
            writeResultToFile();
        } catch (Exception e) {
            currentTestData.setResult("Fail");
            writeResultToFile();
            throw new RuntimeException("Has error occur: " + e.getMessage());
        }

    }

    @Test(priority = 2)
    public void TC02_BlankUserName() {
        String testId = getCurrentMethodName();
        currentTestData = getDataByTestName(testId);

        initLoginPage();

        enterUserName("");
        enterPassword(currentTestData.getPassword());

        clickLogin();

        WebElement errorMessage = driver.findElement(By.xpath("//input[@id='username']/following-sibling::div"));
        if(errorMessage.isDisplayed()) {
            compareExpected(errorMessage.getText(), currentTestData.getExpected());
            Assert.assertEquals(errorMessage.getText(), currentTestData.getExpected());
            return;
        }
        failTestWithNoMessageDisplay();
    }

    @Test(priority = 3)
    public void TC03_BlankPassword() throws InterruptedException{
        String testId = getCurrentMethodName();
        currentTestData = getDataByTestName(testId);

        initLoginPage();

        enterUserName(currentTestData.getUsername());
        enterPassword("");

        clickLogin();

        WebElement errorUser = driver.findElement(By.xpath("//input[@id='username']/following-sibling::div"));
        WebElement errorPassword = driver.findElement(By.xpath("//input[@id='password']/following-sibling::div"));

        String[] expected = currentTestData.getExpected().split(";");

        if(errorUser.isDisplayed() && errorPassword.isDisplayed()) {
            System.out.println("Error user message: " + errorUser.getText());
            System.out.println("Error password message: " + errorPassword.getText());
            if(errorUser.getText().equals(expected[0]) && errorPassword.getText().equals(expected[1])) {
                currentTestData.setResult("Pass");
            } else currentTestData.setResult("Fail");
            writeResultToFile();
            Assert.assertEquals(errorUser.getText(), expected[0]);
            Assert.assertEquals(errorPassword.getText(), expected[1]);
            return;
        }
        failTestWithNoMessageDisplay();
    }
    @Test(enabled = true, priority = 4)
    public void TC04_BlankUsernamePassword() throws InterruptedException{
        String testId = getCurrentMethodName();
        LoginModel loginData = getDataByTestName(testId);

        initLoginPage();

        enterUserName("");
        enterPassword("");

        clickLogin();

        WebElement errorMessage = driver.findElement(By.xpath("//input[@id='password']/following-sibling::div"));
        if(errorMessage.isDisplayed()) {
            compareExpected(errorMessage.getText(), currentTestData.getExpected());
            Assert.assertEquals(errorMessage.getText(), loginData.getExpected());
            return;
        }
    }
    @Test(priority = 5)
    public void TC05_WrongUsername() throws InterruptedException{
        String testId = getCurrentMethodName();
        currentTestData = getDataByTestName(testId);

        initLoginPage();

        enterUserName(currentTestData.getUsername());
        enterPassword(currentTestData.getPassword());

        clickLogin();
        WebElement errorMessage = driver.findElement(By.xpath("//div[@role='alert']"));
        if(errorMessage.isDisplayed()) {
            compareExpected(errorMessage.getText(), currentTestData.getExpected());
            Assert.assertEquals(errorMessage.getText(), currentTestData.getExpected());
            return;
        }

        failTestWithNoMessageDisplay();
    }
    @Test(priority = 6)
    public void TC06_WrongPassword() {
        String testId = getCurrentMethodName();
        currentTestData = getDataByTestName(testId);

        initLoginPage();

        enterUserName(currentTestData.getUsername());
        enterPassword(currentTestData.getPassword());

        clickLogin();
        WebElement errorMessage = driver.findElement(By.xpath("//div[@role='alert']"));
        if(errorMessage.isDisplayed()) {
            compareExpected(errorMessage.getText(), currentTestData.getExpected());
            Assert.assertEquals(errorMessage.getText(), currentTestData.getExpected());
            return;
        }

        failTestWithNoMessageDisplay();
    }

    @Test(priority = 7)
    public void TC07_WrongUserNamePassword() {
        String testId = getCurrentMethodName();
        currentTestData = getDataByTestName(testId);

        initLoginPage();

        enterUserName(currentTestData.getUsername());
        enterPassword(currentTestData.getPassword());

        clickLogin();
        WebElement errorMessage = driver.findElement(By.xpath("//div[@role='alert']"));
        if(errorMessage.isDisplayed()) {
            compareExpected(errorMessage.getText(), currentTestData.getExpected());
            Assert.assertEquals(errorMessage.getText(), currentTestData.getExpected());
            return;
        }

        failTestWithNoMessageDisplay();
    }

    @Test(priority = 8)
    public void TC08_SpaceUsername() {
        String testId = getCurrentMethodName();
        currentTestData = getDataByTestName(testId);

        initLoginPage();

        enterUserName("     ");
        enterPassword(currentTestData.getPassword());

        clickLogin();

        WebElement errorMessage = driver.findElement(By.xpath("//input[@id='username']/following-sibling::div"));
        if(errorMessage.isDisplayed()) {
            compareExpected(errorMessage.getText(), currentTestData.getExpected());
            Assert.assertEquals(errorMessage.getText(), currentTestData.getExpected());
            return;
        }

        failTestWithNoMessageDisplay();
    }

    @Test(priority = 9)
    public void TC09_SpaceUsername() {
        String testId = getCurrentMethodName();
        currentTestData = getDataByTestName(testId);

        initLoginPage();

        enterUserName("     "  + currentTestData.getUsername());
        enterPassword(currentTestData.getPassword());

        clickLogin();

        System.out.println("Verify display homepage");
        Assert.assertEquals(driver.getTitle(), "TRANG CHỦ");
        currentTestData.setResult("Pass");
        writeResultToFile();
    }
}
