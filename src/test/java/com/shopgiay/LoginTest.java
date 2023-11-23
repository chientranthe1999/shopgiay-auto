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

    @Test(enabled = true, priority = 1)
    public void TC01_Login_Success() {
        String testId = getCurrentMethodName();
        LoginModel loginData = getDataByTestName(testId);

        try {
            initLoginPage();
            enterUserName(loginData.getUsername());
            enterPassword(loginData.getPassword());

            clickLogin();

            System.out.println("Verify display homepage");
            Assert.assertEquals(driver.getTitle(), "TRANG CHỦ");
            loginData.setResult("Pass");
            writeResultToFile();
        } catch (Exception e) {
            loginData.setResult("Fail");
            writeResultToFile();
            throw new RuntimeException("Has error occur: " + e.getMessage());
        }

    }

    @Test(enabled = true, priority = 2)
    public void TC02_LoginInvalid_BlankUserName() throws InterruptedException{
        String testId = getCurrentMethodName();
        LoginModel loginData = getDataByTestName(testId);

        initLoginPage();

        enterUserName("");
        enterPassword(loginData.getPassword());

        clickLogin();

        WebElement errorMessage = driver.findElement(By.xpath("//input[@id='username']/following-sibling::div"));
        if(errorMessage.isDisplayed()) {
            System.out.println("Error message: " + errorMessage.getText());
            if(errorMessage.getText().equals(loginData.getExpected())) {
                loginData.setResult("Pass");
            } else loginData.setResult("Fail");
            writeResultToFile();
            Assert.assertEquals(errorMessage.getText(), loginData.getExpected());
            return;
        }
        Assert.fail();
    }

    @Test(enabled = true, priority = 3)
    public void DN04_LoginInvalid_Empty_User_Password() throws InterruptedException{
        String testId = getCurrentMethodName();
        LoginModel loginData = getDataByTestName(testId);

        initLoginPage();

        enterUserName(loginData.getUsername());
        enterPassword("");

        clickLogin();

        WebElement errorUser = driver.findElement(By.xpath("//input[@id='username']/following-sibling::div"));
        WebElement errorPassword = driver.findElement(By.xpath("//input[@id='password']/following-sibling::div"));

        String[] expected = loginData.getExpected().split(";");

        if(errorUser.isDisplayed() && errorPassword.isDisplayed()) {
            System.out.println("Error user message: " + errorUser.getText());
            System.out.println("Error password message: " + errorPassword.getText());
            if(errorUser.getText().equals(expected[0]) && errorPassword.getText().equals(expected[1])) {
                loginData.setResult("Pass");
            } else loginData.setResult("Fail");
            writeResultToFile();
            Assert.assertEquals(errorUser.getText(), expected[0]);
            Assert.assertEquals(errorPassword.getText(), expected[1]);
            return;
        }
        Assert.fail();
    }
    @Test(enabled = true, priority = 4)
    public void DN04_LoginInvalid_BlackUserNamePassword() throws InterruptedException{
        String testId = getCurrentMethodName();
        LoginModel loginData = getDataByTestName(testId);

        initLoginPage();

        enterUserName("");
        enterPassword("");

        clickLogin();

        WebElement errorMessage = driver.findElement(By.xpath("//input[@id='password']/following-sibling::div"));
        if(errorMessage.isDisplayed()) {
            System.out.println("Error message: " + errorMessage.getText());
            if(errorMessage.getText().equals(loginData.getExpected())) {
                loginData.setResult("Pass");
            } else loginData.setResult("Fail");
            writeResultToFile();
            Assert.assertEquals(errorMessage.getText(), loginData.getExpected());
        }
    }
    @Test(enabled = true, priority = 5)
    public void DN05_LoginInvalid_BlankPass() throws InterruptedException{
        //Test data
        String username = "admin";
        String password = "";

        System.out.println("Open to Login page");
        driver.get(Constant.LOGIN_URL);
        Thread.sleep(5000);

        System.out.println("Enter valid username & password");
        WebElement iptUserNameLogin = driver.findElement(By.id("username"));
        iptUserNameLogin.clear();
        iptUserNameLogin.sendKeys(username);

        WebElement iptPassLogin = driver.findElement(By.id("password"));
        iptPassLogin.clear();
        iptPassLogin.sendKeys(password);

        System.out.println("Click on 'Login' button");
        driver.findElement(By.xpath("//button[@class='btn-login']")).click();

        System.out.println("Verify: Login failed. Still on the login page");
        driver.findElement(By.xpath("//h4[contains(text(),'Sign In')]")).isDisplayed();

    }
    @Test(enabled = true, priority = 6)
    public void DN06_LoginInvalid_WrongUserName() throws InterruptedException{
        //Test data
        String username = "adminnn";
        String password = "111";

        System.out.println("Open to Login page");
        driver.get(Constant.LOGIN_URL);
        Thread.sleep(5000);

        System.out.println("Enter valid username & password");
        WebElement iptUserNameLogin = driver.findElement(By.id("username"));
        iptUserNameLogin.clear();
        iptUserNameLogin.sendKeys(username);

        WebElement iptPassLogin = driver.findElement(By.id("password"));
        iptPassLogin.clear();
        iptPassLogin.sendKeys(password);

        System.out.println("Click on 'Login' button");
        driver.findElement(By.xpath("//button[@class='btn-login']")).click();

        System.out.println("Verify: Login failed. Still on the login page");
        driver.findElement(By.xpath("//h4[contains(text(),'Sign In')]")).isDisplayed();

    }
}
