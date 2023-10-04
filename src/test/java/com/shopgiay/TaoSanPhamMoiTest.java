package com.shopgiay;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.sat.utils.BasicTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.Select;
import com.github.javafaker.Faker;
import java.util.Locale;
import java.io.File;

public class TaoSanPhamMoiTest extends BasicTest{
    Faker faker = new Faker(new Locale("vi"));

    String username = "admin";
    String password = "111";

    @Test(enabled = true, priority = 0)
    public void SP01_AddValidNewProduct() throws InterruptedException {
        System.out.println("1. Đăng nhập bằng account admin");
        driver.get("http://localhost:8080/Shopgiay/login");
        Thread.sleep(3000);

        System.out.println("Enter valid username & password");
        WebElement iptUserNameLogin = driver.findElement(By.id("username"));
        iptUserNameLogin.clear();
        iptUserNameLogin.sendKeys(username);

        WebElement iptPassLogin = driver.findElement(By.id("password"));
        iptPassLogin.clear();
        iptPassLogin.sendKeys(password);

        System.out.println("Click on 'Login' button");
        driver.findElement(By.xpath("//button[@class='btn-login']")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "http://localhost:8080/Shopgiay/admin/home");

        System.out.println("2. Chọn Sản phẩm");
        driver.findElement(By.xpath("//span[@class='nav_name' and contains(text(),'Sản phẩm')]")).click();

        System.out.println("3. Chọn Thêm mới");
        driver.findElement(By.xpath("//a[@href='addproduct']")).click();
        driver.findElement(By.xpath("//p[contains(text(),'Thêm/cập nhật sản phẩm')]")).isDisplayed();

        System.out.println("4. Nhập các thông tin hợp lệ");
        //Test data
        String productName = faker.commerce().productName();
        String price = Double.toString(faker.random().nextDouble());
        String quantity = Integer.toString(faker.number().randomDigitNotZero());
        String size = "36";
        String category = "giày thể thao";
        String brand = "nike";
        String descText = "Test enter description";

        System.out.println("Enter product name");
        WebElement iptProductName = driver.findElement(By.xpath("//input[@id='tieude']"));
        iptProductName.clear();
        iptProductName.sendKeys(productName);

        System.out.println("Choose product details image");
        WebElement productDetailsFile = driver.findElement(By.name("anhphu"));
        String productDetailsPath = System.getProperty("user.dir")+ File.separator + "src/test/resources/testdata/product/productDetails.jpg";
        productDetailsFile.sendKeys(productDetailsPath);

        System.out.println("Enter description");
        driver.switchTo().frame("editor_ifr");
        driver.findElement(By.id("tinymce")).sendKeys(descText);
        driver.switchTo().defaultContent();

        System.out.println("Enter price");
        WebElement iptPrice = driver.findElement(By.name("price"));
        iptPrice.clear();
        iptPrice.sendKeys(price);

        System.out.println("Enter quantity");
        WebElement iptQuantity = driver.findElement(By.name("quantity"));
        iptQuantity.clear();
        iptQuantity.sendKeys(quantity);

        System.out.println("Select size");
        Select sizeDropdown = new Select(driver.findElement(By.name("listcolor")));
        sizeDropdown.selectByVisibleText(size);

        System.out.println("Select category");
        WebElement eleCategory = driver.findElement(By.name("danhmuc"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", eleCategory);
        Thread.sleep(2000);
        Select categoryDropdown = new Select(eleCategory);
        categoryDropdown.selectByVisibleText(category);

        System.out.println("Select brand");
        WebElement eleBrand = driver.findElement(By.name("thuonghieu"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", eleBrand);
        Thread.sleep(2000);
        Select brandDropDown = new Select(eleBrand);
        brandDropDown.selectByVisibleText(brand);

        System.out.println("Choose product image'");
        WebElement anhNenFile = driver.findElement(By.name("anhnen"));
        String andNenPath = System.getProperty("user.dir")+ File.separator + "src/test/resources/testdata/product/product1.jpg";
        anhNenFile.sendKeys(andNenPath);

        
        System.out.println("5. Chọn button 'Thêm/cập nhật sản phẩm'");
        WebElement addProductBtn = driver.findElement(By.xpath("//button[contains(text(),'Thêm/ cập nhật sản phẩm')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addProductBtn);
        Thread.sleep(5000);

        System.out.println("Verify: Thêm sản phẩm thành công. Quay lại trang quản lý sản phẩm");
        Assert.assertEquals(driver.getCurrentUrl(), "http://localhost:8080/Shopgiay/admin/product");
        WebElement searchBar = driver.findElement(By.xpath("//input[@type='search']"));
        searchBar.clear();
        searchBar.sendKeys(productName);
        driver.findElement(By.xpath("//tbody[@id='listproduct']//td[contains(text(),'" + productName +"')]")).isDisplayed();

        // Clean up data
        driver.findElement(By.xpath("//a[@class='btn btn-danger']")).click();
    }
    @Test(enabled = true, priority = 1)
    public void SP02_AddInvalidProductNameSP() throws InterruptedException {
        System.out.println("Thêm sản phẩm không thành công: Không nhập tên sản phẩm");
        System.out.println("1. Đăng nhập bằng account admin");
        driver.get("http://localhost:8080/Shopgiay/login");
        Thread.sleep(3000);

        System.out.println("Enter valid username & password");
        WebElement iptUserNameLogin = driver.findElement(By.id("username"));
        iptUserNameLogin.clear();
        iptUserNameLogin.sendKeys(username);

        WebElement iptPassLogin = driver.findElement(By.id("password"));
        iptPassLogin.clear();
        iptPassLogin.sendKeys(password);

        System.out.println("Click on 'Login' button");
        driver.findElement(By.xpath("//button[@class='btn-login']")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "http://localhost:8080/Shopgiay/admin/home");

        System.out.println("2. Chọn Sản phẩm");
        driver.findElement(By.xpath("//span[@class='nav_name' and contains(text(),'Sản phẩm')]")).click();

        System.out.println("3. Chọn Thêm mới");
        driver.findElement(By.xpath("//a[@href='addproduct']")).click();
        driver.findElement(By.xpath("//p[contains(text(),'Thêm/cập nhật sản phẩm')]")).isDisplayed();

        System.out.println("4. Để trống không nhập tên sản phẩm");
        Select sizeDropdown = new Select(driver.findElement(By.name("listcolor")));
        sizeDropdown.selectByVisibleText("38");

        System.out.println("5. Chọn button 'Thêm/cập nhật sản phẩm'");
        WebElement addProductBtn = driver.findElement(By.xpath("//button[contains(text(),'Thêm/ cập nhật sản phẩm')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addProductBtn);
        Thread.sleep(2000);

        System.out.println("Verify: Xuất hiện text dưới tên sản phẩm: 'tên sản phẩm không được trống'");
        WebElement tenspError = driver.findElement(By.xpath("//span[@id='name.errors']"));
        tenspError.isDisplayed();
        Assert.assertEquals(tenspError.getText(), "tên sản phẩm không được trống");
    }
    @Test(enabled = true, priority = 2)
    public void SP03_AddInvalidProductPrice() throws InterruptedException {
        System.out.println("Thêm sản phẩm không thành công: Không nhập giá sản phẩm");
        System.out.println("1. Đăng nhập bằng account admin");
        driver.get("http://localhost:8080/Shopgiay/login");
        Thread.sleep(3000);

        System.out.println("Enter valid username & password");
        WebElement iptUserNameLogin = driver.findElement(By.id("username"));
        iptUserNameLogin.clear();
        iptUserNameLogin.sendKeys(username);

        WebElement iptPassLogin = driver.findElement(By.id("password"));
        iptPassLogin.clear();
        iptPassLogin.sendKeys(password);

        System.out.println("Click on 'Login' button");
        driver.findElement(By.xpath("//button[@class='btn-login']")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "http://localhost:8080/Shopgiay/admin/home");

        System.out.println("2. Chọn Sản phẩm");
        driver.findElement(By.xpath("//span[@class='nav_name' and contains(text(),'Sản phẩm')]")).click();

        System.out.println("3. Chọn Thêm mới");
        driver.findElement(By.xpath("//a[@href='addproduct']")).click();
        driver.findElement(By.xpath("//p[contains(text(),'Thêm/cập nhật sản phẩm')]")).isDisplayed();

        System.out.println("4. Để trống không nhập giá sản phẩm");
        Select sizeDropdown = new Select(driver.findElement(By.name("listcolor")));
        sizeDropdown.selectByVisibleText("38");

        System.out.println("5. Chọn button 'Thêm/cập nhật sản phẩm'");
        WebElement addProductBtn = driver.findElement(By.xpath("//button[contains(text(),'Thêm/ cập nhật sản phẩm')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addProductBtn);
        Thread.sleep(2000);

        System.out.println("Verify: Xuất hiện text dưới giá sản phẩm: 'giá sản phẩm không được trống'");
        WebElement tenspError = driver.findElement(By.xpath("//span[@id='price.errors']"));
        tenspError.isDisplayed();
        Assert.assertEquals(tenspError.getText(), "giá sản phẩm không được trống");
    }
    @Test(enabled = true, priority = 3)
    public void SP04_AddInvalidProductSoluong() throws InterruptedException {
        System.out.println("Thêm sản phẩm không thành công: Không nhập số lượng sản phẩm");
        System.out.println("1. Đăng nhập bằng account admin");
        driver.get("http://localhost:8080/Shopgiay/login");
        Thread.sleep(3000);

        System.out.println("Enter valid username & password");
        WebElement iptUserNameLogin = driver.findElement(By.id("username"));
        iptUserNameLogin.clear();
        iptUserNameLogin.sendKeys(username);

        WebElement iptPassLogin = driver.findElement(By.id("password"));
        iptPassLogin.clear();
        iptPassLogin.sendKeys(password);

        System.out.println("Click on 'Login' button");
        driver.findElement(By.xpath("//button[@class='btn-login']")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "http://localhost:8080/Shopgiay/admin/home");

        System.out.println("2. Chọn Sản phẩm");
        driver.findElement(By.xpath("//span[@class='nav_name' and contains(text(),'Sản phẩm')]")).click();

        System.out.println("3. Chọn Thêm mới");
        driver.findElement(By.xpath("//a[@href='addproduct']")).click();
        driver.findElement(By.xpath("//p[contains(text(),'Thêm/cập nhật sản phẩm')]")).isDisplayed();

        System.out.println("4. Để trống không nhập số lượng sản phẩm");
        Select sizeDropdown = new Select(driver.findElement(By.name("listcolor")));
        sizeDropdown.selectByVisibleText("38");

        System.out.println("5. Chọn button 'Thêm/cập nhật sản phẩm'");
        WebElement addProductBtn = driver.findElement(By.xpath("//button[contains(text(),'Thêm/ cập nhật sản phẩm')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addProductBtn);
        Thread.sleep(2000);

        System.out.println("Verify: Xuất hiện text dưới số lượng sản phẩm: 'số lượng sản phẩm không được trống'");
        WebElement tenspError = driver.findElement(By.xpath("//span[@id='quantity.errors']"));
        tenspError.isDisplayed();
        Assert.assertEquals(tenspError.getText(), "số lượng sản phẩm không được trống");
    }
    @Test(enabled = true, priority = 4)
    public void SP05_AddInvalidProductSize() throws InterruptedException {
        System.out.println("Thêm sản phẩm không thành công: Không nhập size sản phẩm");
        System.out.println("1. Đăng nhập bằng account admin");
        driver.get("http://localhost:8080/Shopgiay/login");
        Thread.sleep(3000);

        System.out.println("Enter valid username & password");
        WebElement iptUserNameLogin = driver.findElement(By.id("username"));
        iptUserNameLogin.clear();
        iptUserNameLogin.sendKeys(username);

        WebElement iptPassLogin = driver.findElement(By.id("password"));
        iptPassLogin.clear();
        iptPassLogin.sendKeys(password);

        System.out.println("Click on 'Login' button");
        driver.findElement(By.xpath("//button[@class='btn-login']")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "http://localhost:8080/Shopgiay/admin/home");

        System.out.println("2. Chọn Sản phẩm");
        driver.findElement(By.xpath("//span[@class='nav_name' and contains(text(),'Sản phẩm')]")).click();

        System.out.println("3. Chọn Thêm mới");
        driver.findElement(By.xpath("//a[@href='addproduct']")).click();
        driver.findElement(By.xpath("//p[contains(text(),'Thêm/cập nhật sản phẩm')]")).isDisplayed();

        System.out.println("5. Chọn button 'Thêm/cập nhật sản phẩm'");
        WebElement addProductBtn = driver.findElement(By.xpath("//button[contains(text(),'Thêm/ cập nhật sản phẩm')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addProductBtn);
        Thread.sleep(2000);

        System.out.println("Verify: Xuất hiện text dưới size sản phẩm: 'Vui lòng chọn size sản phẩm'");
        WebElement tenspError = driver.findElement(By.xpath("//span[@id='size.errors']"));
        tenspError.isDisplayed();
        Assert.assertEquals(tenspError.getText(), "Vui lòng chọn size sản phẩm");
    }
    @Test(enabled = true, priority = 5)
    public void SP06_AddInvalidProduct() throws InterruptedException {
        System.out.println("Thêm sản phẩm không thành công: Không chọn file ảnh");
        System.out.println("1. Đăng nhập bằng account admin");
        driver.get("http://localhost:8080/Shopgiay/login");
        Thread.sleep(3000);

        System.out.println("Enter valid username & password");
        WebElement iptUserNameLogin = driver.findElement(By.id("username"));
        iptUserNameLogin.clear();
        iptUserNameLogin.sendKeys(username);

        WebElement iptPassLogin = driver.findElement(By.id("password"));
        iptPassLogin.clear();
        iptPassLogin.sendKeys(password);

        System.out.println("Click on 'Login' button");
        driver.findElement(By.xpath("//button[@class='btn-login']")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "http://localhost:8080/Shopgiay/admin/home");

        System.out.println("2. Chọn Sản phẩm");
        driver.findElement(By.xpath("//span[@class='nav_name' and contains(text(),'Sản phẩm')]")).click();

        System.out.println("3. Chọn Thêm mới");
        driver.findElement(By.xpath("//a[@href='addproduct']")).click();
        driver.findElement(By.xpath("//p[contains(text(),'Thêm/cập nhật sản phẩm')]")).isDisplayed();

        System.out.println("4. Để trống không chọn file ảnh");
        Select sizeDropdown = new Select(driver.findElement(By.name("listcolor")));
        sizeDropdown.selectByVisibleText("38");

        System.out.println("5. Chọn button 'Thêm/cập nhật sản phẩm'");
        WebElement addProductBtn = driver.findElement(By.xpath("//button[contains(text(),'Thêm/ cập nhật sản phẩm')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addProductBtn);
        Thread.sleep(2000);

        System.out.println("Verify: Xuất hiện text dưới ảnh: 'file khong duoc de trong'");
        WebElement anhNenError = driver.findElement(By.xpath("//span[@id='anhnen.errors']"));
        anhNenError.isDisplayed();
        Assert.assertEquals(anhNenError.getText(), "file khong duoc de trong");
    }
    
    @Test(enabled = true, priority = 6)
    public void SP07_AddInvalidProductAnhphu() throws InterruptedException {
        System.out.println("Thêm sản phẩm không thành công: Không chọn file ảnh phụ");
        System.out.println("1. Đăng nhập bằng account admin");
        driver.get("http://localhost:8080/Shopgiay/login");
        Thread.sleep(3000);

        System.out.println("Enter valid username & password");
        WebElement iptUserNameLogin = driver.findElement(By.id("username"));
        iptUserNameLogin.clear();
        iptUserNameLogin.sendKeys(username);

        WebElement iptPassLogin = driver.findElement(By.id("password"));
        iptPassLogin.clear();
        iptPassLogin.sendKeys(password);

        System.out.println("Click on 'Login' button");
        driver.findElement(By.xpath("//button[@class='btn-login']")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "http://localhost:8080/Shopgiay/admin/home");

        System.out.println("2. Chọn Sản phẩm");
        driver.findElement(By.xpath("//span[@class='nav_name' and contains(text(),'Sản phẩm')]")).click();

        System.out.println("3. Chọn Thêm mới");
        driver.findElement(By.xpath("//a[@href='addproduct']")).click();
        driver.findElement(By.xpath("//p[contains(text(),'Thêm/cập nhật sản phẩm')]")).isDisplayed();

        System.out.println("4. Để trống không chọn file ảnh");
        Select sizeDropdown = new Select(driver.findElement(By.name("listcolor")));
        sizeDropdown.selectByVisibleText("38");

        System.out.println("5. Chọn button 'Thêm/cập nhật sản phẩm'");
        WebElement addProductBtn = driver.findElement(By.xpath("//button[contains(text(),'Thêm/ cập nhật sản phẩm')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addProductBtn);
        Thread.sleep(2000);

        System.out.println("Verify: Xuất hiện text dưới ảnh chi tiết: 'file khong duoc de trong'");
        WebElement anhNenError = driver.findElement(By.xpath("//span[@id='anhphu.errors']"));
        anhNenError.isDisplayed();
        Assert.assertEquals(anhNenError.getText(), "file khong duoc de trong");
    }
    @Test(enabled = true, priority = 7)
    public void SP08_AddInvalidProductMota() throws InterruptedException {
        System.out.println("Thêm sản phẩm không thành công: Không nhập mô tả");
        System.out.println("1. Đăng nhập bằng account admin");
        driver.get("http://localhost:8080/Shopgiay/login");
        Thread.sleep(3000);

        System.out.println("Enter valid username & password");
        WebElement iptUserNameLogin = driver.findElement(By.id("username"));
        iptUserNameLogin.clear();
        iptUserNameLogin.sendKeys(username);

        WebElement iptPassLogin = driver.findElement(By.id("password"));
        iptPassLogin.clear();
        iptPassLogin.sendKeys(password);

        System.out.println("Click on 'Login' button");
        driver.findElement(By.xpath("//button[@class='btn-login']")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "http://localhost:8080/Shopgiay/admin/home");

        System.out.println("2. Chọn Sản phẩm");
        driver.findElement(By.xpath("//span[@class='nav_name' and contains(text(),'Sản phẩm')]")).click();

        System.out.println("3. Chọn Thêm mới");
        driver.findElement(By.xpath("//a[@href='addproduct']")).click();
        driver.findElement(By.xpath("//p[contains(text(),'Thêm/cập nhật sản phẩm')]")).isDisplayed();

        System.out.println("4. Để trống không chọn file ảnh");
        Select sizeDropdown = new Select(driver.findElement(By.name("listcolor")));
        sizeDropdown.selectByVisibleText("38");

        System.out.println("5. Chọn button 'Thêm/cập nhật sản phẩm'");
        WebElement addProductBtn = driver.findElement(By.xpath("//button[contains(text(),'Thêm/ cập nhật sản phẩm')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addProductBtn);
        Thread.sleep(2000);

        System.out.println("Verify: Xuất hiện text dưới mô tả: 'Mô tả không được trống'");
        WebElement anhNenError = driver.findElement(By.xpath("//span[@id='description.errors']"));
        anhNenError.isDisplayed();
        Assert.assertEquals(anhNenError.getText(), "Mô tả không được trống");
    }
}
