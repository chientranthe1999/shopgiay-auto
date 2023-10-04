package com.shopgiay;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.sat.utils.BasicTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

import org.openqa.selenium.support.ui.Select;

public class DatHangTest extends BasicTest{

    @Test(enabled = true, priority = 0)
    public void DH_01() throws InterruptedException{    
        System.out.println("1. Dăng nhập vào trang web");
        driver.get("http://localhost:8080/Shopgiay/login");

        WebElement iptUserNameLogin = driver.findElement(By.id("username"));
        iptUserNameLogin.clear();
        iptUserNameLogin.sendKeys("ducn");

        WebElement iptPassLogin = driver.findElement(By.id("password"));
        iptPassLogin.clear();
        iptPassLogin.sendKeys("1");

        System.out.println("Click on 'Login' button");
        driver.findElement(By.xpath("//button[@class='btn-login']")).click();
        driver.findElement(By.xpath("//a[contains(text(),'Trang chủ')]")).isDisplayed();

        System.out.println("2. Chọn sản phẩm 'giày thể thao'. ");
        driver.findElement(By.xpath("//a[@href='product']")).click();
        driver.findElement(By.xpath("//label[contains(text(),'giày thể thao')]//input")).click();
        driver.findElement(By.xpath("//div[@id='listNewProduct']//a")).click();

        System.out.println("3. Nhập số lượng: 2");
        WebElement iptSL = driver.findElement(By.id("slnhap"));
        iptSL.clear();
        iptSL.sendKeys("2");

        System.out.println("4. Chọn size 36");
        Select size = new Select(driver.findElement(By.name("mausac")));
        size.selectByVisibleText("36");

        System.out.println("5. Chọn button 'Thêm vào giỏ hàng'");
        driver.findElement(By.xpath("//button[contains(text(),'THÊM GIỎ HÀNG')]")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//p[contains(text(),'Đã thêm vào giỏ hàng của bạn')]")).isDisplayed();
        driver.findElement(By.xpath("//button[contains(text(),'OK')]")).click();

        System.out.println("6. chọn giỏ hàng");
        driver.findElement(By.xpath("//i[@class='fa fa-shopping-cart']")).click();

        System.out.println("7. Click button 'đặt hàng'");
        driver.findElement(By.xpath("//button[contains(text(),'Đặt hàng')]")).click();
        Thread.sleep(3000);

        System.out.println("8.  Click vào button 'Thanh toán khi nhận hàng'");
        driver.findElement(By.xpath("//button[contains(text(),'Thanh toán khi nhận hàng')]")).click();

        System.out.println("Verify: Hiển thị trang thanh toán thành công");
        String paymentContentText = driver.findElement(By.xpath("//div[@class='content']/h1")).getText();    
        Assert.assertEquals(paymentContentText, "Đơn hàng đã được tạo thành công !");

    }
    
    @Test(enabled = true, priority = 2)
    public void DH_02() throws InterruptedException{    
        System.out.println("1. Dăng nhập vào trang web");
        driver.get("http://localhost:8080/Shopgiay/login");

        WebElement iptUserNameLogin = driver.findElement(By.id("username"));
        iptUserNameLogin.clear();
        iptUserNameLogin.sendKeys("ducn");

        WebElement iptPassLogin = driver.findElement(By.id("password"));
        iptPassLogin.clear();
        iptPassLogin.sendKeys("1");

        System.out.println("Click on 'Login' button");
        driver.findElement(By.xpath("//button[@class='btn-login']")).click();
        driver.findElement(By.xpath("//a[contains(text(),'Trang chủ')]")).isDisplayed();


        System.out.println("2. chọn giỏ hàng");
        driver.findElement(By.xpath("//i[@class='fa fa-shopping-cart']")).click();

        System.out.println("3. Click button 'đặt hàng'");
        driver.findElement(By.xpath("//button[contains(text(),'Đặt hàng')]")).click();
        Thread.sleep(3000);

       
        System.out.println("Verify: Hiển thị popup thông báo lỗi ");
        driver.findElement(By.xpath("//p[contains(text(),'Bạn chưa có sản phẩm nào trong giỏ hàng, không thể thanh toán')]")).isDisplayed();  
        driver.findElement(By.xpath("//button[contains(text(),'OK')]")).click();

    }
   
}

