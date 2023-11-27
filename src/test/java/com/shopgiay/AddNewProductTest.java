package com.shopgiay;

import core.utils.Constant;
import org.testng.Assert;
import org.testng.annotations.Test;
import core.utils.BasicTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.Select;

import java.io.File;

public class AddNewProductTest extends BasicTest {

    public void loginAdminRoleAndOpenAddProductPage() {
        String username = "admin";
        String password = "111";

        System.out.println("Login admin role");
        System.out.println("Open login page: " + Constant.LOGIN_URL);
        driver.get(Constant.LOGIN_URL);

        System.out.println("Enter username: " + username);
        WebElement iptUserNameLogin = driver.findElement(By.id("username"));
        iptUserNameLogin.clear();
        iptUserNameLogin.sendKeys(username);

        System.out.println("Enter password: " + password);
        WebElement iptPassLogin = driver.findElement(By.id("password"));
        iptPassLogin.clear();
        iptPassLogin.sendKeys(password);

        System.out.println("Click on 'Login' button");
        driver.findElement(By.xpath("//button[@class='btn-login']")).click();
        Assert.assertEquals(driver.getCurrentUrl(), Constant.ADMIN_HOME_URL);

        System.out.println("2. Chọn Sản phẩm");
        driver.findElement(By.xpath("//span[@class='nav_name' and contains(text(),'Sản phẩm')]")).click();

        System.out.println("3. Chọn Thêm mới");
        driver.findElement(By.xpath("//a[@href='addproduct']")).click();
        driver.findElement(By.xpath("//p[contains(text(),'Thêm/cập nhật sản phẩm')]")).isDisplayed();
    }

    public String enterPrdName() {
        String productName = faker.commerce().productName();
        enterPrdName(productName);
        return productName;
    }

    public void enterPrdName(String prdName) {
        System.out.println("Enter product name: " + prdName);
        WebElement iptProductName = driver.findElement(By.xpath("//input[@id='tieude']"));
        iptProductName.clear();
        iptProductName.sendKeys(prdName);
    }

    public void enterPrice() {
        enterPrice(Double.toString(faker.random().nextDouble()));
    }

    public void enterPrice(String price) {
        System.out.println("Enter price: " + price);
        WebElement iptPrice = driver.findElement(By.name("price"));
        iptPrice.clear();
        iptPrice.sendKeys(price);
    }

    public void enterDescription() {
        String desc = faker.lorem().paragraph();
        enterDescription(desc);
    }

    public void enterDescription(String desc) {
        System.out.println("Enter description :" + desc);
        driver.switchTo().frame("editor_ifr");
        driver.findElement(By.id("tinymce")).sendKeys(desc);
        driver.switchTo().defaultContent();
    }

    public void enterQuantity() {
        String quantity = Integer.toString(faker.number().randomDigitNotZero());
        enterQuantity(quantity);
    }

    public void enterQuantity(String quantity) {
        System.out.println("Enter quantity: " + quantity);
        WebElement iptQuantity = driver.findElement(By.name("quantity"));
        iptQuantity.clear();
        iptQuantity.sendKeys(quantity);
    }

    public void enterProductImage() {
        System.out.println("Choose product details image");
        WebElement productDetailsFile = driver.findElement(By.name("anhphu"));
        String productDetailsPath = System.getProperty("user.dir")+ File.separator + "src/test/resources/testdata/product/productDetails.jpg";
        productDetailsFile.sendKeys(productDetailsPath);
    }

    public void selectSize(String size) {
        System.out.println("Select size: " + size);
        Select sizeDropdown = new Select(driver.findElement(By.name("listcolor")));
        sizeDropdown.selectByVisibleText(size);
    }

    public void selectCategory(String category) {
        System.out.println("Select category: " + category);
        WebElement eleCategory = driver.findElement(By.name("danhmuc"));
        js.executeScript("arguments[0].scrollIntoView(true);", eleCategory);
        new Select(eleCategory).selectByVisibleText(category);
    }

    public void selectBrand(String brand) {
        System.out.println("Select brand");
        WebElement eleBrand = driver.findElement(By.name("thuonghieu"));
        js.executeScript("arguments[0].scrollIntoView(true);", eleBrand);
        new Select(eleBrand).selectByVisibleText(brand);
    }

    public void enterPrdMainImage() {
        System.out.println("Choose product image'");
        WebElement anhNenFile = driver.findElement(By.name("anhnen"));
        String andNenPath = System.getProperty("user.dir")+ File.separator + "src/test/resources/testdata/product/product1.jpg";
        anhNenFile.sendKeys(andNenPath);
    }

    public void clickAdd() {
        System.out.println("Click button add");
        WebElement addProductBtn = driver.findElement(By.xpath("//button[contains(text(),'Thêm/ cập nhật sản phẩm')]"));
        js.executeScript("arguments[0].click();", addProductBtn);
    }

    public void verifyAddSuccess(String productName) {
        System.out.println("Verify: Thêm sản phẩm thành công. Quay lại trang quản lý sản phẩm");
        Assert.assertEquals(driver.getCurrentUrl(), Constant.ADMIN_PRODUCT_URL);
        WebElement searchBar = driver.findElement(By.xpath("//input[@type='search']"));
        searchBar.clear();
        searchBar.sendKeys(productName);

        if(driver.findElement(By.xpath("//tbody[@id='listproduct']//td[contains(text(),'" + productName +"')]")).isDisplayed()) {
            System.out.println("Prd is display on list page :" + productName);
        }

        // Clean up data
        driver.findElement(By.xpath("//a[@class='btn btn-danger']")).click();
    }

    String username = "admin";
    String password = "111";

    @Test(priority = 1)
    public void TC01_AddProductSuccessfully() throws InterruptedException {
        loginAdminRoleAndOpenAddProductPage();

        String size = "36";
        String category = "giày thể thao";
        String brand = "nike";

        enterProductImage();
        String prdName = enterPrdName();
        enterPrice();
        enterQuantity();
        enterDescription();
        selectSize(size);
        selectCategory(category);
        selectBrand(brand);
        enterPrdMainImage();

        clickAdd();

        verifyAddSuccess(prdName);
    }
    @Test(priority = 2)
    public void TC02() {
        loginAdminRoleAndOpenAddProductPage();
        selectSize("36");
        clickAdd();
        WebElement tenspError = driver.findElement(By.xpath("//span[@id='name.errors']"));
        tenspError.isDisplayed();
        Assert.assertEquals(tenspError.getText(), "Tên sản phẩm không được trống");
    }
    @Test(priority = 3)
    public void TC03() {
        loginAdminRoleAndOpenAddProductPage();
        selectSize("36");
        enterPrdName("           ");
        clickAdd();
        WebElement tenspError = driver.findElement(By.xpath("//span[@id='name.errors']"));
        tenspError.isDisplayed();
        Assert.assertEquals(tenspError.getText(), "Tên sản phẩm không được trống");
    }
    @Test(priority = 6)
    public void TC06() {
        loginAdminRoleAndOpenAddProductPage();

        enterPrice("testPrice");
        clickAdd();

        WebElement errorMessage = driver.findElement(By.xpath("//span[@id='price.errors']"));
        errorMessage.isDisplayed();
        Assert.assertEquals(errorMessage.getText(), "số lượng sản phẩm không được trống");
    }
    @Test(priority = 7)
    public void TC07() {
        loginAdminRoleAndOpenAddProductPage();

        enterPrice("@#$123");
        clickAdd();

        WebElement errorMessage = driver.findElement(By.xpath("//span[@id='price.errors']"));
        errorMessage.isDisplayed();
        Assert.assertEquals(errorMessage.getText(), "số lượng sản phẩm không được trống");
    }
    @Test(priority = 8)
    public void TC08() {
        loginAdminRoleAndOpenAddProductPage();
        enterPrice("     ");
        clickAdd();

        WebElement errorMessage = driver.findElement(By.xpath("//span[@id='price.errors']"));
        errorMessage.isDisplayed();
        Assert.assertEquals(errorMessage.getText(), "số lượng sản phẩm không được trống");
    }

    @Test(priority = 9)
    public void TC09() {
        loginAdminRoleAndOpenAddProductPage();
        clickAdd();
        WebElement errorMessage = driver.findElement(By.xpath("//span[@id='price.errors']"));
        errorMessage.isDisplayed();
        Assert.assertEquals(errorMessage.getText(), "số lượng sản phẩm không được trống");
    }
    
    @Test(priority = 6)
    public void SP07_AddInvalidProductAnhphu() throws InterruptedException {
        System.out.println("Thêm sản phẩm không thành công: Không chọn file ảnh phụ");
        System.out.println("1. Đăng nhập bằng account admin");
        driver.get(Constant.LOGIN_URL);
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
        Assert.assertEquals(driver.getCurrentUrl(), Constant.ADMIN_HOME_URL);

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
    @Test(priority = 7)
    public void SP08_AddInvalidProductMota() throws InterruptedException {
        System.out.println("Thêm sản phẩm không thành công: Không nhập mô tả");
        System.out.println("1. Đăng nhập bằng account admin");
        driver.get(Constant.LOGIN_URL);
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
        Assert.assertEquals(driver.getCurrentUrl(), Constant.ADMIN_HOME_URL);

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
