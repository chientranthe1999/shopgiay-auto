package com.shopgiay;

import org.testng.annotations.Test;
import core.utils.BasicTest;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.io.File;
import org.openqa.selenium.JavascriptExecutor;
import core.utils.Constant;

public class RegisterTest extends BasicTest {
    public void initLoginPage() {
        System.out.println("Open register page: " + Constant.REGISTER_URL);
        driver.get(Constant.REGISTER_URL);
    }

    public String enterUserName() {
        String username = faker.name().username();
        System.out.println("Enter username: " + username);
        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.clear();
        usernameField.sendKeys(username);

        return username;
    }

    public void enterFullName() {
        String fullName = faker.name().fullName();
        System.out.println("Enter full name: " + fullName);
        WebElement inputFullName = driver.findElement(By.id("fullname"));
        inputFullName.clear();
        inputFullName.sendKeys(fullName);
    }

    public void enterEmail() {
        String email = faker.internet().emailAddress();
        System.out.println("Enter email: " + email);
        WebElement inputEmail = driver.findElement(By.id("email"));
        inputEmail.clear();
        inputEmail.sendKeys(email);
    }

    public void enterPhone() {
        String phone = faker.phoneNumber().phoneNumber();
        System.out.println("Enter phone number: " + phone);
        WebElement inputPhone = driver.findElement(By.id("phone"));
        inputPhone.clear();
        inputPhone.sendKeys(phone);
    }

    public void enterPassword(String password) {
        System.out.println("Enter password: " + password);
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void enterRePassword(String password) {
        System.out.println("Enter Re-Password: " + password);
        WebElement passwordField = driver.findElement(By.id("repassword"));
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void enterAddress() {
        String address = faker.address().streetAddress();
        System.out.println("Enter address: " + address);
        WebElement addressField = driver.findElement(By.xpath("//input[@name='address']"));
        addressField.clear();
        addressField.sendKeys(address);
    }

    public void enterImage() {
        System.out.println("Enter profile image");
        WebElement fileInput = driver.findElement(By.id("file"));
        String imagePath = System.getProperty("user.dir")+ File.separator + "src/test/resources/testdata/profileImage/Picture1.jpg";
        fileInput.sendKeys(imagePath);
    }

    public void clickRegister() {
        System.out.println("Click btn register");
        WebElement btnRegister = driver.findElement(By.xpath("//button[@class='btn-regis']"));
        js.executeScript("arguments[0].click();", btnRegister);
    }

    @Test(priority = 0)
    public void DK01_ValidRegister() {
        initLoginPage();
        String username = enterUserName();
        enterFullName();
        enterEmail();
        enterPhone();

        String password = faker.internet().password();
        enterPassword(password);
        enterRePassword(password);

        enterAddress();
        enterImage();

        clickRegister();

        System.out.println("Verify register successfully, login with account register above");
        waitUrlTobe(Constant.LOGIN_URL);

        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.clear();
        usernameField.sendKeys(username);

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.clear();
        passwordField.sendKeys(password);

        driver.findElement(By.xpath("//button[@class='btn-login']")).click();

        Assert.assertEquals(driver.getCurrentUrl(), Constant.USER_HOME_URL);
    }

    @Test(enabled = true, priority = 1)
    public void DK02_invalidRegisterUsername() {
        initLoginPage();
        
        System.out.println("Bỏ trống trường tên đăng nhập. Nhập các giá trị khác hợp lệ");
        WebElement inputUserName = driver.findElement(By.id("username"));
        inputUserName.clear();

        System.out.println("Verify: Xuất hiện text dưới trường tên đăng nhập: username không được trống");
        WebElement userNameError = driver.findElement(By.xpath("//span[@id='username.errors']"));
        Assert.assertTrue(userNameError.isDisplayed());
        Assert.assertEquals(userNameError.getText(), "Tên đăng nhập không được trống");
    }

    @Test(enabled = true, priority = 2)
    public void DK03_invalidRegisterfullname() throws InterruptedException{
        System.out.println("Open website");
        driver.get(Constant.REGISTER_URL);
        Thread.sleep(5000);

        System.out.println("Bỏ trống trường họ tên. Nhập các giá trị khác hợp lệ");
        WebElement inputFullName = driver.findElement(By.id("fullname"));
        inputFullName.clear();

        System.out.println("Click on 'Dang ky' button");
        WebElement btnDangKy = driver.findElement(By.xpath("//button[@class='btn-regis']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnDangKy);

        System.out.println("Verify: Xuất hiện text dưới trường họ tên : Họ tên không được trống");
        WebElement fullNameError = driver.findElement(By.xpath("//span[@id='fullname.errors']"));
        Assert.assertTrue(fullNameError.isDisplayed());
        Assert.assertEquals(fullNameError.getText(), "Họ tên không được trống");
    }

    @Test(enabled = true, priority = 3)
    public void DK04_invalidRegisterEmail() throws InterruptedException{
        System.out.println("Open website");
        driver.get(Constant.REGISTER_URL);
        Thread.sleep(5000);

        System.out.println("Bỏ trống trường email. Nhập các giá trị khác hợp lệ");
        WebElement inputEmail = driver.findElement(By.id("email"));
        inputEmail.clear();

        System.out.println("Click on 'Dang ky' button");
        WebElement btnDangKy = driver.findElement(By.xpath("//button[@class='btn-regis']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnDangKy);

        System.out.println("Verify: Xuất hiện text dưới trường email : Email không được trống");
        WebElement emailError = driver.findElement(By.xpath("//span[@id='email.errors']"));
        Assert.assertTrue(emailError.isDisplayed());
        Assert.assertEquals(emailError.getText(), "Email không được trống");
    }

    @Test(enabled = true, priority = 4)
    public void DK05_invalidRegisterPhone() throws InterruptedException{
        System.out.println("Open website");
        driver.get(Constant.REGISTER_URL);
        Thread.sleep(5000);

        System.out.println("Bỏ trống trường số điện thoại. Nhập các giá trị khác hợp lệ");
        WebElement inputPhone = driver.findElement(By.id("phone"));
        inputPhone.clear();

        System.out.println("Click on 'Dang ky' button");
        WebElement btnDangKy = driver.findElement(By.xpath("//button[@class='btn-regis']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnDangKy);

        System.out.println("Verify: Xuất hiện text dưới trường Số điện thoại : Số điện thoại không được trống");
        WebElement phoneError = driver.findElement(By.xpath("//span[@id='phone.errors']"));
        Assert.assertTrue(phoneError.isDisplayed());
        Assert.assertEquals(phoneError.getText(), "Số điện thoại không được trống");
    }

    @Test(enabled = true, priority = 5)
    public void DK06_invalidRegisterAnh() throws InterruptedException{
        System.out.println("Open website");
        driver.get(Constant.REGISTER_URL);
        Thread.sleep(5000);

        System.out.println("Bỏ trống trường Ảnh đại diện. Nhập các giá trị khác hợp lệ");
        WebElement inputAnh = driver.findElement(By.id("file"));
        inputAnh.clear();

        System.out.println("Click on 'Dang ky' button");
        WebElement btnDangKy = driver.findElement(By.xpath("//button[@class='btn-regis']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnDangKy);

        System.out.println("Verify: Xuất hiện text dưới trường Ảnh đại diện : file khong duoc de trong");
        WebElement anhError = driver.findElement(By.xpath("//span[@id='file.errors']"));
        Assert.assertTrue(anhError.isDisplayed());
        Assert.assertEquals(anhError.getText(), "file khong duoc de trong");
    }

    @Test(enabled = true, priority = 6)
    public void DK07_invalidRegistePass() throws InterruptedException{
        System.out.println("Open website");
        driver.get(Constant.REGISTER_URL);
        Thread.sleep(5000);

        System.out.println("Bỏ trống trường mật khẩu. Nhập các giá trị khác hợp lệ");
        WebElement inputPass = driver.findElement(By.id("password"));
        inputPass.clear();

        System.out.println("Click on 'Dang ky' button");
        WebElement btnDangKy = driver.findElement(By.xpath("//button[@class='btn-regis']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnDangKy);

        System.out.println("Verify: Xuất hiện text dưới trường Nhập lại mật khẩu :password không được trống");
        WebElement passError = driver.findElement(By.xpath("//span[@id='password.errors']"));
        Assert.assertTrue(passError.isDisplayed());
        Assert.assertEquals(passError.getText(), "Password không được trống");
    }
    @Test(enabled = true, priority = 7)
    public void DK08_invalidRegisteRePass() throws InterruptedException{
        System.out.println("Open website");
        driver.get(Constant.REGISTER_URL);
        Thread.sleep(5000);

        System.out.println("Bỏ trống trường Nhập lại mật khẩu. Nhập các giá trị khác hợp lệ");
        WebElement inputPass = driver.findElement(By.id("repassword"));
        inputPass.clear();

        System.out.println("Click on 'Dang ky' button");
        WebElement btnDangKy = driver.findElement(By.xpath("//button[@class='btn-regis']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnDangKy);

        System.out.println("Verify: Xuất hiện text dưới trường Nhập lại mật khẩu :Repassword không được trống");
        WebElement passError = driver.findElement(By.xpath("//span[@id='password.errors']"));
        Assert.assertTrue(passError.isDisplayed());
        Assert.assertEquals(passError.getText(), "Password không được trống");
    }

    @Test(enabled = true, priority = 8)
    public void DK09_invalidRegisteAddress() throws InterruptedException{
        System.out.println("Open website");
        driver.get(Constant.REGISTER_URL);
        Thread.sleep(5000);

        System.out.println("Bỏ trống trường Địa chỉ. Nhập các giá trị khác hợp lệ");
        WebElement inputAddress = driver.findElement(By.id("tenduong"));
        inputAddress.clear();

        System.out.println("Click on 'Dang ky' button");
        WebElement btnDangKy = driver.findElement(By.xpath("//button[@class='btn-regis']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnDangKy);

        System.out.println("Verify: Xuất hiện text dưới trường Đại chỉ :address không được trống");
        WebElement addressError = driver.findElement(By.xpath("//span[@id='address.errors']"));
        Assert.assertTrue(addressError.isDisplayed());
        Assert.assertEquals(addressError.getText(), "address không được trống");
    }
   
    @Test(enabled  = true, priority=9)
    public void DK10_InvalidRegister() throws InterruptedException{
        //Test data
        String username = faker.name().username();
        String fullName = faker.name().fullName();
        String email = faker.internet().emailAddress();
        String phone = faker.phoneNumber().phoneNumber();
        String password =faker.internet().password();
        String address =faker.address().streetAddress();
        
        System.out.println("Open Register page");
        driver.get(Constant.REGISTER_URL);
        Thread.sleep(3000);

        System.out.println("Fill valid details: username, full name, email, password, re password, address");
        WebElement inputUserName = driver.findElement(By.id("username"));
        inputUserName.clear();
        inputUserName.sendKeys(username);

        WebElement inputFullName = driver.findElement(By.id("fullname"));
        inputFullName.clear();
        inputFullName.sendKeys(fullName);

        WebElement inputEmail = driver.findElement(By.id("email"));
        inputEmail.clear();
        inputEmail.sendKeys(email);

        WebElement inputPhone = driver.findElement(By.id("phone"));
        inputPhone.clear();
        inputPhone.sendKeys(phone);

        WebElement iptPassword = driver.findElement(By.id("password"));
        iptPassword.clear();
        iptPassword.sendKeys(password);

        WebElement iptConfirmPassword = driver.findElement(By.id("repassword"));
        iptConfirmPassword.clear();
        iptConfirmPassword.sendKeys(password);

        WebElement iptAddress = driver.findElement(By.xpath("//input[@name='address']"));
        iptAddress.clear();
        iptAddress.sendKeys(address);

        System.out.println("Choose profile image");
        WebElement fileInput = driver.findElement(By.id("file"));
        String imagePath = System.getProperty("user.dir")+ File.separator + "src/test/resources/testdata/profileImage/Picture1.jpg";
        fileInput.sendKeys(imagePath);

        // System.out.println("Verify register successfully, login with account register above");
        // driver.findElement(By.xpath("//h4[contains(text(),'Sign In')]")).isDisplayed();
        // WebElement iptUserNameLogin = driver.findElement(By.id("username"));
        // iptUserNameLogin.clear();
        // iptUserNameLogin.sendKeys(username);
        // WebElement iptPassLogin = driver.findElement(By.id("password"));
        // iptPassLogin.clear();
        // iptPassLogin.sendKeys(password);
        // driver.findElement(By.xpath("//button[@class='btn-login']")).click();
        // driver.findElement(By.xpath("//li[@class='nav-item']/a[@href='home']")).isDisplayed();
        
    }
}
