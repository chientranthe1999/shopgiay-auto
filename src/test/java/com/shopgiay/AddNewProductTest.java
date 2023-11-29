package com.shopgiay;

import core.utils.Constant;
import org.testng.Assert;
import org.testng.annotations.Test;
import core.utils.BasicTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
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
        enterPrice(Double.toString(Math.ceil(Math.random() * 100000)));
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

    public void enterDetailProductImage() {
        enterDetailProductImage("productDetails.jpg");
    }

    public void enterDetailProductImage(String fileName) {
        System.out.println("Choose product details image");
        WebElement productDetailsFile = driver.findElement(By.name("anhphu"));
        String productDetailsPath = System.getProperty("user.dir")+ File.separator + "src/test/resources/testdata/product/" + fileName;
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
        enterPrdMainImage("product1.jpg");
    }

    public void enterPrdMainImage(String fileName) {
        System.out.println("Choose product image");
        WebElement anhNenFile = driver.findElement(By.name("anhnen"));
        String andNenPath = System.getProperty("user.dir")+ File.separator + "src/test/resources/testdata/product/" + fileName;
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

    public void validateError(String field, String expected) {
        String xpath = "//span[@id='" + field + ".errors']";
        WebElement error = driver.findElement(By.xpath(xpath));
        Assert.assertTrue(error.isDisplayed());

        System.out.println("Error message: " + error.getText());
        Assert.assertEquals(error.getText(), expected);
    }

    String username = "admin";
    String password = "111";

    @Test(priority = 1)
    public void TC01_AddProductSuccessfully() throws InterruptedException {
        loginAdminRoleAndOpenAddProductPage();

        String size = "36";
        String category = "giày thể thao";
        String brand = "nike";

        enterDetailProductImage();
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
    @Test(priority = 7)
    public void TC07() {
        loginAdminRoleAndOpenAddProductPage();
        selectSize("36");
        clickAdd();
        validateError("price", "Giá sản phẩm không được trống");
    }
    @Test(priority = 8)
    public void TC08_BlankPrice() {
        loginAdminRoleAndOpenAddProductPage();
        selectSize("36");
        enterPrice("            ");
        clickAdd();

        validateError("price", "Giá sản phẩm không được trống");
    }
    @Test(priority = 9)
    public void TC09_EmptyDescription() {
        loginAdminRoleAndOpenAddProductPage();
        selectSize("36");
        clickAdd();

        validateError("description", "Mô tả không được trống");
    }

    @Test(priority = 10)
    public void TC10_EmptyDescription() {
        loginAdminRoleAndOpenAddProductPage();
        selectSize("36");
        enterDescription("                   ");
        clickAdd();

        validateError("description", "Mô tả không được trống");
    }
    
    @Test(priority = 19)
    public void TC19_SpaceQuantity() throws InterruptedException {
        loginAdminRoleAndOpenAddProductPage();
        selectSize("36");
        enterPrice("         ");
        clickAdd();
        validateError("quantity", "Số lượng sản phẩm không được trống");
    }

    @Test(priority = 20)
    public void TC20_BlankQuantity() throws InterruptedException {
        loginAdminRoleAndOpenAddProductPage();
        selectSize("36");
        clickAdd();
        validateError("quantity", "Số lượng sản phẩm không được trống");
    }

    @Test(priority = 21)
    public void TC21_BlankMainPicture() {
        loginAdminRoleAndOpenAddProductPage();

        enterPrice();
        enterPrdName();
        enterQuantity();
        enterDescription();
        selectSize("36");
        clickAdd();
        validateError("anhnen", "Ảnh sản phẩm không được để trống");
    }

    @Test(priority = 22)
    public void TC22_WrongMainPictureFormat() {
        loginAdminRoleAndOpenAddProductPage();

        enterPrice();
        enterPrdName();
        enterQuantity();
        enterDescription();
        selectSize("36");
        enterPrdMainImage("image.txt");
        clickAdd();
        validateError("anhnen", "Ảnh sản phẩm phải ở định dạng png, jpg, jpeg");
    }

    @Test(priority = 23)
    public void TC23_BlankMainPicture() {
        loginAdminRoleAndOpenAddProductPage();

        enterPrice();
        enterPrdName();
        enterQuantity();
        enterDescription();
        selectSize("36");
        clickAdd();
        validateError("anhphu", "Ảnh chi tiết sản phẩm không được để trống");
    }

    @Test(priority = 24)
    public void TC24_WrongMainPictureFormat() {
        loginAdminRoleAndOpenAddProductPage();

        enterPrice();
        enterPrdName();
        enterQuantity();
        enterDescription();
        selectSize("36");
        enterDetailProductImage("image.txt");
        clickAdd();
        validateError("anhphu", "Ảnh phải ở định dạng png, jpg, jpeg");
    }
}
