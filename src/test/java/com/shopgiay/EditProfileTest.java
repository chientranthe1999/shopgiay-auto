package com.shopgiay;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.sat.utils.BasicTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

public class EditProfileTest extends BasicTest{

    @Test(enabled = true,priority = 0)
    public void Profile_01(){
        //Test data
        String username = "ducn";
        String password = "1";

        System.out.println("1. Đăng nhập vào website bằng account: username (ducn), password (1)");

        driver.get("http://localhost:8080/Shopgiay/login");

        WebElement iptUserNameLogin = driver.findElement(By.id("username"));
        iptUserNameLogin.clear();
        iptUserNameLogin.sendKeys(username);

        WebElement iptPassLogin = driver.findElement(By.id("password"));
        iptPassLogin.clear();
        iptPassLogin.sendKeys(password);

        System.out.println("Click on 'Login' button");
        driver.findElement(By.xpath("//button[@class='btn-login']")).click();

        System.out.println("2. Open profile");
        driver.findElement(By.xpath("//a[@href='account']")).click();
        driver.findElement(By.xpath("//h4[contains(text(),'THÔNG TIN TÀI KHOẢN')]")).isDisplayed();

        System.out.println("Change full name to 'Test1'");
        WebElement iptFullName = driver.findElement(By.id("fullname"));
        iptFullName.clear();
        iptFullName.sendKeys("Test1");

        System.out.println("Click on 'Luu Thay Doi' button");
        WebElement btnLuuThayDoi = driver.findElement(By.xpath("//button[contains(text(),'Lưu thay đổi')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnLuuThayDoi);

        System.out.println("Display Popup  thông báo: 'Cập nhật thông tin thành công.'' ");
        driver.findElement(By.xpath("//p[contains(text(),'Cập nhật thông tin thành công')]")).isDisplayed();

    }

    @Test(enabled = true, priority = 1)
    public void Profile_02(){
        //Test data
        String username = "ducn";
        String password = "1";

        System.out.println("1. Đăng nhập vào website bằng account: username (ducn), password (1)");

        driver.get("http://localhost:8080/Shopgiay/login");

        WebElement iptUserNameLogin = driver.findElement(By.id("username"));
        iptUserNameLogin.clear();
        iptUserNameLogin.sendKeys(username);

        WebElement iptPassLogin = driver.findElement(By.id("password"));
        iptPassLogin.clear();
        iptPassLogin.sendKeys(password);

        System.out.println("Click on 'Login' button");
        driver.findElement(By.xpath("//button[@class='btn-login']")).click();

        System.out.println("2. Open  profile");
        driver.findElement(By.xpath("//a[@href='account']")).click();
        driver.findElement(By.xpath("//h4[contains(text(),'THÔNG TIN TÀI KHOẢN')]")).isDisplayed();

        System.out.println("3. Delete username");
        WebElement iptFullName = driver.findElement(By.id("fullname"));
        iptFullName.clear();

        System.out.println("4. Clik on 'Luu thay doi' button");
        WebElement btnLuuThayDoi = driver.findElement(By.xpath("//button[contains(text(),'Lưu thay đổi')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnLuuThayDoi);

        System.out.println("4. Xuất hiện text dưới trường họ tên: 'Họ tên không được trống'");
        WebElement fullNameError = driver.findElement(By.xpath("//span[@id='fullname.errors']"));
        fullNameError.isDisplayed();
        Assert.assertEquals(fullNameError.getText(), "Họ tên không được trống");
    }
    @Test(enabled = true, priority = 2)
    public void Profile_03(){
        //Test data
        String username = "ducn";
        String password = "1";

        System.out.println("1. Đăng nhập vào website bằng account: username (ducn), password (1)");

        driver.get("http://localhost:8080/Shopgiay/login");

        WebElement iptUserNameLogin = driver.findElement(By.id("username"));
        iptUserNameLogin.clear();
        iptUserNameLogin.sendKeys(username);

        WebElement iptPassLogin = driver.findElement(By.id("password"));
        iptPassLogin.clear();
        iptPassLogin.sendKeys(password);

        System.out.println("Click on 'Login' button");
        driver.findElement(By.xpath("//button[@class='btn-login']")).click();

        System.out.println("2. Open  profile");
        driver.findElement(By.xpath("//a[@href='account']")).click();
        driver.findElement(By.xpath("//h4[contains(text(),'THÔNG TIN TÀI KHOẢN')]")).isDisplayed();

        System.out.println("3. Delete số điện thoại");
        WebElement iptFullName = driver.findElement(By.id("phone"));
        iptFullName.clear();

        System.out.println("4. Clik on 'Luu thay doi' button");
        WebElement btnLuuThayDoi = driver.findElement(By.xpath("//button[contains(text(),'Lưu thay đổi')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnLuuThayDoi);

        System.out.println("4. Xuất hiện text dưới trường số điện thoại: 'Số điện thoại không được trống'");
        WebElement fullNameError = driver.findElement(By.xpath("//span[@id='phone.errors']"));
        fullNameError.isDisplayed();
        Assert.assertEquals(fullNameError.getText(), "Số điện thoại không được trống");
    }
    @Test(enabled = true, priority = 3)
    public void Profile_04(){
        //Test data
        String username = "ducn";
        String password = "1";

        System.out.println("1. Đăng nhập vào website bằng account: username (ducn), password (1)");

        driver.get("http://localhost:8080/Shopgiay/login");

        WebElement iptUserNameLogin = driver.findElement(By.id("username"));
        iptUserNameLogin.clear();
        iptUserNameLogin.sendKeys(username);

        WebElement iptPassLogin = driver.findElement(By.id("password"));
        iptPassLogin.clear();
        iptPassLogin.sendKeys(password);

        System.out.println("Click on 'Login' button");
        driver.findElement(By.xpath("//button[@class='btn-login']")).click();

        System.out.println("2. Open  profile");
        driver.findElement(By.xpath("//a[@href='account']")).click();
        driver.findElement(By.xpath("//h4[contains(text(),'THÔNG TIN TÀI KHOẢN')]")).isDisplayed();

        System.out.println("3. Delete Email");
        WebElement iptEmail = driver.findElement(By.id("email"));
        iptEmail.clear();

        System.out.println("4. Clik on 'Luu thay doi' button");
        WebElement btnLuuThayDoi = driver.findElement(By.xpath("//button[contains(text(),'Lưu thay đổi')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnLuuThayDoi);

        System.out.println("4. Xuất hiện text dưới trường Email: 'email không được trống'");
        WebElement emailError = driver.findElement(By.xpath("//span[@id='email.errors']"));
        emailError.isDisplayed();
        Assert.assertEquals(emailError.getText(), "email không được trống");
    }
    @Test(enabled = true, priority = 4)
    public void Profile_05(){
        //Test data
        String username = "ducn";
        String password = "1";

        System.out.println("1. Đăng nhập vào website bằng account: username (ducn), password (1)");

        driver.get("http://localhost:8080/Shopgiay/login");

        WebElement iptUserNameLogin = driver.findElement(By.id("username"));
        iptUserNameLogin.clear();
        iptUserNameLogin.sendKeys(username);

        WebElement iptPassLogin = driver.findElement(By.id("password"));
        iptPassLogin.clear();
        iptPassLogin.sendKeys(password);

        System.out.println("Click on 'Login' button");
        driver.findElement(By.xpath("//button[@class='btn-login']")).click();

        System.out.println("2. Open  profile");
        driver.findElement(By.xpath("//a[@href='account']")).click();
        driver.findElement(By.xpath("//h4[contains(text(),'THÔNG TIN TÀI KHOẢN')]")).isDisplayed();

        System.out.println("3. Delete Địa chỉ nhận hàng");
        WebElement iptAddress = driver.findElement(By.name("address"));
        iptAddress.clear();

        System.out.println("4. Clik on 'Luu thay doi' button");
        WebElement btnLuuThayDoi = driver.findElement(By.xpath("//button[contains(text(),'Lưu thay đổi')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnLuuThayDoi);

        System.out.println("4. Xuất hiện text dưới trường Địa chỉ nhận hàng: 'address không được trống'");
        WebElement emailError = driver.findElement(By.xpath("//span[@id='address.errors']"));
        emailError.isDisplayed();
        Assert.assertEquals(emailError.getText(), "address không được trống");
    }
    @Test(enabled = true,priority = 5)
    public void Profile_06(){
        //Test data
        String username = "ducn";
        String password = "1";

        System.out.println("1. Đăng nhập vào website bằng account: username (ducn), password (1)");

        driver.get("http://localhost:8080/Shopgiay/login");

        WebElement iptUserNameLogin = driver.findElement(By.id("username"));
        iptUserNameLogin.clear();
        iptUserNameLogin.sendKeys(username);

        WebElement iptPassLogin = driver.findElement(By.id("password"));
        iptPassLogin.clear();
        iptPassLogin.sendKeys(password);

        System.out.println("Click on 'Login' button");
        driver.findElement(By.xpath("//button[@class='btn-login']")).click();

        System.out.println("2. Open profile");
        driver.findElement(By.xpath("//a[@href='account']")).click();
        driver.findElement(By.xpath("//h4[contains(text(),'THÔNG TIN TÀI KHOẢN')]")).isDisplayed();

        System.out.println("Change full name to 'Test1'");
        WebElement iptFullName = driver.findElement(By.id("fullname"));
        iptFullName.clear();
        iptFullName.sendKeys("Test1");

        // System.out.println("Click on 'Luu Thay Doi' button");
        // WebElement btnLuuThayDoi = driver.findElement(By.xpath("//button[contains(text(),'Lưu thay đổi')]"));
        // ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnLuuThayDoi);

        // System.out.println("Display Popup  thông báo: 'Cập nhật thông tin thành công.'' ");
        // driver.findElement(By.xpath("//p[contains(text(),'Cập nhật thông tin thành công')]")).isDisplayed();

    }


}
