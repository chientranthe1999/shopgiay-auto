package com.shopgiay;

import core.utils.Constant;
import org.testng.Assert;
import org.testng.annotations.Test;
import core.utils.BasicTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

public class EditProfileTest extends BasicTest {

    public void loginAndGoToEditProfile() {
        String username = "ducn";
        String password = "1";

        System.out.println("Login with account: " + username);

        driver.get(Constant.LOGIN_URL);

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
        Assert.assertTrue(driver.findElement(By.xpath("//h4[contains(text(),'THÔNG TIN TÀI KHOẢN')]")).isDisplayed());
    }

    public void clickSave() {
        System.out.println("Click on 'Luu Thay Doi' button");
        WebElement btnSave = driver.findElement(By.xpath("//button[contains(text(),'Lưu thay đổi')]"));
        js.executeScript("arguments[0].click();", btnSave);
    }

    public void validateError(String field, String expected) {
        String xpath = "//span[@id='" + field + ".errors']";
        WebElement error = driver.findElement(By.xpath(xpath));
        Assert.assertTrue(error.isDisplayed());

        System.out.println("Error message: " + error.getText());
        Assert.assertEquals(error.getText(), expected);
    }

    @Test(enabled = true, priority = 0)
    public void Profile_01(){
        loginAndGoToEditProfile();

        String fullName = faker.name().fullName();
        System.out.println("Change full name to: " + fullName);
        WebElement iptFullName = driver.findElement(By.id("fullname"));
        iptFullName.clear();
        iptFullName.sendKeys(fullName);

        clickSave();

        System.out.println("Display Popup  thông báo: 'Cập nhật thông tin thành công.'");
        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Cập nhật thông tin thành công')]")).isDisplayed());
    }

    @Test(enabled = true, priority = 1)
    public void Profile_02() {
        loginAndGoToEditProfile();

        System.out.println("Delete fullname");
        WebElement iptFullName = driver.findElement(By.id("fullname"));
        iptFullName.clear();

        clickSave();

        validateError("fullname", "Họ tên không được trống");
    }
    @Test(enabled = true, priority = 2)
    public void Profile_03(){
        loginAndGoToEditProfile();

        System.out.println("Delete số điện thoại");
        WebElement iptFullName = driver.findElement(By.id("phone"));
        iptFullName.clear();
        clickSave();

        validateError("phone", "Số điện thoại không được trống");
    }

    @Test(enabled = true, priority = 3)
    public void Profile_04(){
        loginAndGoToEditProfile();

        System.out.println("Delete Email");
        WebElement iptEmail = driver.findElement(By.id("email"));
        iptEmail.clear();

        clickSave();
        validateError("email", "Email không được trống");
    }
    @Test(enabled = true, priority = 4)
    public void Profile_05(){
        loginAndGoToEditProfile();

        System.out.println("Delete Địa chỉ nhận hàng");
        WebElement iptAddress = driver.findElement(By.name("address"));
        iptAddress.clear();

        clickSave();
        validateError("address", "Địa chỉ không được trống");
    }
    @Test(enabled = true,priority = 5)
    public void Profile_06(){
        loginAndGoToEditProfile();


    }


}
