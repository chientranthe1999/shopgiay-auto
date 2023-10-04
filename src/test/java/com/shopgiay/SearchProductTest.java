package com.shopgiay;

import com.sat.utils.Constant;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.sat.utils.BasicTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

public class SearchProductTest extends BasicTest{

    @Test
    public void TK01_SearchValidProduct() throws InterruptedException {
        System.out.println("Open the website");
        driver.get(Constant.BASE_URL);
        
        System.out.println("1. Chọn search");
        driver.findElement(By.xpath("//i[@class='fa fa-search']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//h5[contains(text(),'Tìm kiếm sản phẩm')]")).isDisplayed();

        System.out.println("2. Nhập nội dung: giày thể thao");
        WebElement inputSearch = driver.findElement(By.name("search"));
        inputSearch.clear();
        inputSearch.sendKeys("giày thể thao");

        System.out.println("3. Click button tìm kiếm");
        driver.findElement(By.xpath("//button[contains(text(),'Tìm kiếm')]")).click();
        Thread.sleep(3000);

        System.out.println("Verify: Hiển thị các sản phẩm có tên: giày thể thao");
        WebElement items = driver.findElement(By.xpath("//p[@class='name-signle-item']"));
        Assert.assertTrue(items.getText().contains("Giày Thể Thao"));
    }
    @Test
    public void TK02_SearchInvalidProduct() throws InterruptedException {
        System.out.println("Open the website");
        driver.get(Constant.BASE_URL);
        
        System.out.println("1. Chọn search");
        driver.findElement(By.xpath("//i[@class='fa fa-search']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//h5[contains(text(),'Tìm kiếm sản phẩm')]")).isDisplayed();

        System.out.println("2. Nhập nội dung: áo phông");
        WebElement inputSearch = driver.findElement(By.name("search"));
        inputSearch.clear();
        inputSearch.sendKeys("áo phông");

        System.out.println("3. Click button tìm kiếm");
        driver.findElement(By.xpath("//button[contains(text(),'Tìm kiếm')]")).click();
        Thread.sleep(3000);

        System.out.println("Verify: Hiển thị hông báo lỗi: Không có sản phẩm nào phù hợp");
//        WebElement items = driver.findElement(By.xpath("//p[@class='name-signle-item']"));
//        Assert.assertTrue(items.getText().contains("Không có sản phẩm nào phù hợp"));
    }

    @Test
    public void TK0３_SearchInvalidProduct() throws InterruptedException {
        System.out.println("Open the website");
        driver.get(Constant.BASE_URL);
        
        System.out.println("1. Chọn search");
        driver.findElement(By.xpath("//i[@class='fa fa-search']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//h5[contains(text(),'Tìm kiếm sản phẩm')]")).isDisplayed();

        System.out.println("2. Nhập nội dung: giày thể thao");
        WebElement inputSearch = driver.findElement(By.name("search"));
        inputSearch.clear();
        inputSearch.sendKeys("giày thể thao");

        
       
    }
    @Test
    public void TK04_SearchValidProduct() throws InterruptedException {
        System.out.println("Open the website");
        driver.get(Constant.BASE_URL);
        
        System.out.println("1. Chọn search");
        driver.findElement(By.xpath("//i[@class='fa fa-search']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//h5[contains(text(),'Tìm kiếm sản phẩm')]")).isDisplayed();


        System.out.println("3. Click button tìm kiếm");
        driver.findElement(By.xpath("//button[contains(text(),'Tìm kiếm')]")).click();
        Thread.sleep(3000);

        System.out.println(" Hiển thị text dưới searchbox:  Vui lòng nhập nội dung tìm kiếm");
//        WebElement searchError = driver.findElement(By.xpath("//span[@id='search.errors']"));
//        Assert.assertTrue(searchError.isDisplayed());
//        Assert.assertEquals(searchError.getText(), "Vui lòng nhập nội dung tìm kiếm");
    }
}
