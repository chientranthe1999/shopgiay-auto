package com.shopgiay;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.sat.utils.BasicTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

public class DangNhapTest extends BasicTest{

    @Test(enabled = true, priority = 3)
    public void DN01_LoginValid() throws InterruptedException{
        //Test data
        String validUserName = "admin";
        String validPassword = "111";

        System.out.println("Open to Login page");
        driver.get("http://localhost:8080/Shopgiay/login");
        Thread.sleep(5000);

        System.out.println("Enter valid username & password");
        WebElement iptUserNameLogin = driver.findElement(By.id("username"));
        iptUserNameLogin.clear();
        iptUserNameLogin.sendKeys(validUserName);

        WebElement iptPassLogin = driver.findElement(By.id("password"));
        iptPassLogin.clear();
        iptPassLogin.sendKeys(validPassword);

        System.out.println("Click on 'Login' button");
        driver.findElement(By.xpath("//button[@class='btn-login']")).click();

        System.out.println("Verify display homepage");
        driver.findElement(By.xpath("//a[@href='home']//span[contains(text(),'Trang chủ')]")).isDisplayed();
        Assert.assertEquals(driver.getTitle(), "TRANG CHỦ");

    }

    @Test(enabled = true, priority = 1)
    public void DN02_LoginInvalid_BlankUserName() throws InterruptedException{
        //Test data
        String password = "111";

        System.out.println("Open to Login page");
        driver.get("http://localhost:8080/Shopgiay/login");
        Thread.sleep(5000);

        System.out.println("Enter valid username & password");
        WebElement iptUserNameLogin = driver.findElement(By.id("username"));
        iptUserNameLogin.clear();
        iptUserNameLogin.sendKeys("");

        WebElement iptPassLogin = driver.findElement(By.id("password"));
        iptPassLogin.clear();
        iptPassLogin.sendKeys(password);

        System.out.println("Click on 'Login' button");
        driver.findElement(By.xpath("//button[@class='btn-login']")).click();

        System.out.println("Verify: Login failed. Still on the login page");
        driver.findElement(By.xpath("//h4[contains(text(),'Sign In')]")).isDisplayed();

    }

    @Test(enabled = true, priority = 2)
    public void DN03_LoginInvalid_WrongPass() throws InterruptedException{
        //Test data
        String username = "admin";
        String password = "222";

        System.out.println("Open to Login page");
        driver.get("http://localhost:8080/Shopgiay/login");
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
    @Test(enabled = true, priority = 0)
    public void DN04_LoginInvalid_BlackUserNamePassword() throws InterruptedException{
        //Test data
        String username = "";
        String password = "";

        System.out.println("Open to Login page");
        driver.get("http://localhost:8080/Shopgiay/login");
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
    @Test(enabled = true, priority = 4)
    public void DN05_LoginInvalid_BlankPass() throws InterruptedException{
        //Test data
        String username = "admin";
        String password = "";

        System.out.println("Open to Login page");
        driver.get("http://localhost:8080/Shopgiay/login");
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
    @Test(enabled = true, priority = 5)
    public void DN06_LoginInvalid_WrongUserName() throws InterruptedException{
        //Test data
        String username = "adminnn";
        String password = "111";

        System.out.println("Open to Login page");
        driver.get("http://localhost:8080/Shopgiay/login");
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
