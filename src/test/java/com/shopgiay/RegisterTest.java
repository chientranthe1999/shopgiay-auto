package com.shopgiay;

import org.testng.annotations.Test;
import core.utils.BasicTest;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.io.File;
import java.util.Random;

import org.openqa.selenium.JavascriptExecutor;
import core.utils.Constant;

public class RegisterTest extends BasicTest {
    public void initRegisterPage() {
        System.out.println("Open register page: " + Constant.REGISTER_URL);
        driver.get(Constant.REGISTER_URL);
    }

    public String enterUserName() {
        String username = faker.name().username();
        enterUserName(username);

        return username;
    }

    public void enterUserName(String username) {
        System.out.println("Enter username: " + username);
        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.clear();
        usernameField.sendKeys(username);
    }

    public void enterFullName() {
        String fullName = faker.name().fullName();
        enterFullName(fullName);
    }

    public void enterFullName(String fullName) {
        System.out.println("Enter full name: " + fullName);
        WebElement inputFullName = driver.findElement(By.id("fullname"));
        inputFullName.clear();
        inputFullName.sendKeys(fullName);
    }

    public void enterEmail() {
        String email = faker.internet().emailAddress();
        enterEmail(email);
    }

    public void enterEmail(String email) {
        System.out.println("Enter email: " + email);
        WebElement inputEmail = driver.findElement(By.id("email"));
        inputEmail.clear();
        inputEmail.sendKeys(email);
    }

    public void enterPhone() {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            stringBuilder.append(random.nextInt(10)); // Append random digits (0-9)
        }

        enterPhone(stringBuilder.toString());
    }

    public void enterPhone(String phone) {
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
        enterAddress(address);
    }

    public void enterAddress(String address) {
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

    public void validateError(String field, String expected) {
        String xpath = "//span[@id='" + field + ".errors']";
        WebElement error = driver.findElement(By.xpath(xpath));
        Assert.assertTrue(error.isDisplayed());

        System.out.println("Error message: " + error.getText());
        Assert.assertEquals(error.getText(), expected);
    }

    @Test(priority = 0)
    public void TC01_ValidRegister() {
        initRegisterPage();
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

    @Test(priority = 1)
    public void TC02_BlankUsername() {
        initRegisterPage();

        enterFullName();
        enterEmail();
        enterPhone();

        String password = faker.internet().password();
        enterPassword(password);
        enterRePassword(password);

        enterAddress();
        enterImage();

        clickRegister();

        WebElement userNameError = driver.findElement(By.xpath("//span[@id='username.errors']"));
        Assert.assertTrue(userNameError.isDisplayed());

        System.out.println("Error message: " + userNameError.getText());
        Assert.assertEquals(userNameError.getText(), "Tên đăng nhập không được trống");
    }

    @Test(priority = 2)
    public void TC03_ExistUsername() {
        initRegisterPage();

        enterUserName("admin");

        clickRegister();

        WebElement errorMessage = driver.findElement(By.xpath("//span[@id='username.errors']"));
        Assert.assertTrue(errorMessage.isDisplayed());
        Assert.assertEquals(errorMessage.getText(), "Tên đăng nhập đã tồn tại");
    }

    @Test(priority = 3)
    public void TC04_SpaceUsername() throws InterruptedException{
        initRegisterPage();
        String username = "     " + faker.name().username() + "       ";
        enterUserName(username);
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

    @Test(priority = 4)
    public void TC05_SpaceUsername() throws InterruptedException{
        initRegisterPage();
        enterUserName("              ");
        clickRegister();
        validateError("username", "Tên đăng nhập không được trống");
    }

    @Test(priority = 5)
    public void TC06_InvalidUsername() {
        initRegisterPage();
        enterUserName("test^&**");
        clickRegister();
        validateError("username", "Tên đăng nhập chỉ được chứa ký tự a-z, A-Z, số và các ký tự . - _");
    }

    @Test(priority = 6)
    public void TC07_InvalidUsername() throws InterruptedException{
        initRegisterPage();
        enterUserName("te st^&**");
        clickRegister();
        validateError("username", "Tên đăng nhập chỉ được chứa ký tự a-z, A-Z, số và các ký tự . - _");
    }
    @Test(priority = 7)
    public void TC08_BlankName() throws InterruptedException{
        initRegisterPage();
        String username = enterUserName();
        enterEmail();
        enterPhone();
        String password = faker.internet().password();
        enterPassword(password);
        enterRePassword(password);
        enterAddress();
        enterImage();
        clickRegister();
        validateError("fullname", "Họ tên không được trống");
    }

    @Test(priority = 8)
    public void TC09_BlankName() {
        initRegisterPage();
        String username = enterUserName();
        enterFullName("             ");
        enterEmail();
        enterPhone();
        String password = faker.internet().password();
        enterPassword(password);
        enterRePassword(password);
        enterAddress();
        enterImage();
        clickRegister();
        validateError("fullname", "Họ tên không được trống");
    }
   
    @Test(priority = 9)
    public void TC10_BlankName() throws InterruptedException{
        initRegisterPage();
        enterUserName();
        String fullName = faker.name().fullName();
        enterFullName("             " + fullName + "        ");
        enterEmail();
        enterPhone();
        String password = faker.internet().password();
        enterPassword(password);
        enterRePassword(password);
        enterAddress();
        enterImage();
        clickRegister();
    }

    @Test(priority = 11)
    public void TC12_BlankEmail() {
        initRegisterPage();
        enterUserName();
        enterFullName();
        enterPhone();
        String password = faker.internet().password();
        enterPassword(password);
        enterRePassword(password);
        enterAddress();
        enterImage();
        clickRegister();
        validateError("email", "Email không được trống");
    }

    @Test(priority = 12)
    public void TC13_WrongEmailFormat() {
        initRegisterPage();
        enterUserName();
        enterEmail("test@");
        enterFullName();
        enterPhone();
        String password = faker.internet().password();
        enterPassword(password);
        enterRePassword(password);
        enterAddress();
        enterImage();
        clickRegister();
        validateError("email", "Email không đúng định dạng");
    }

    @Test(priority = 13)
    public void TC14_WrongEmailFormat() {
        initRegisterPage();
        enterUserName();
        enterFullName();
        enterEmail("@gmail.com");
        enterPhone();
        String password = faker.internet().password();
        enterPassword(password);
        enterRePassword(password);
        enterAddress();
        enterImage();
        clickRegister();
        validateError("email", "Email không đúng định dạng");
    }

    @Test(priority = 14)
    public void TC15_WrongEmailFormat() {
        initRegisterPage();
        enterUserName();
        enterFullName();
        enterEmail("test@gmail");
        enterPhone();
        String password = faker.internet().password();
        enterPassword(password);
        enterRePassword(password);
        enterAddress();
        enterImage();
        clickRegister();
        validateError("email", "Email không đúng định dạng");
    }

    @Test(priority = 15)
    public void TC16_WrongEmailFormat() {
        initRegisterPage();
        enterUserName();
        enterFullName();
        enterEmail("test$%@gmail");
        enterPhone();
        String password = faker.internet().password();
        enterPassword(password);
        enterRePassword(password);
        enterAddress();
        enterImage();
        clickRegister();
        validateError("email", "Email không đúng định dạng");
    }

    @Test(priority = 17)
    public void TC18_ExistEmail() {
        initRegisterPage();
        enterUserName();
        enterFullName();
        enterEmail("t@gm.co");
        enterPhone();
        String password = faker.internet().password();
        enterPassword(password);
        enterRePassword(password);
        enterAddress();
        enterImage();
        clickRegister();
        validateError("email", "Email đã tồn tại");
    }

    @Test(priority = 18)
    public void TC19_WrongPhoneFormat() {
        initRegisterPage();
        enterUserName();
        enterFullName();
        enterEmail();
        enterPhone("dfdfdf");
        String password = faker.internet().password();
        enterPassword(password);
        enterRePassword(password);
        enterAddress();
        enterImage();
        clickRegister();
        validateError("phone", "Số điện thoại bao gồm 10 chữ số");
    }

//    TODO: TC20

    @Test(priority = 20)
    public void TC21_WrongPhoneFormat() {
        initRegisterPage();
        enterUserName();
        enterFullName();
        enterEmail();
        enterPhone("-987654321");
        String password = faker.internet().password();
        enterPassword(password);
        enterRePassword(password);
        enterAddress();
        enterImage();
        clickRegister();
        validateError("phone", "Số điện thoại bao gồm 10 chữ số");
    }

    @Test(priority = 21)
    public void TC22_WrongPhoneFormat() {
        initRegisterPage();
        enterUserName();
        enterFullName();
        enterEmail();
        enterPhone("98,76543210");
        String password = faker.internet().password();
        enterPassword(password);
        enterRePassword(password);
        enterAddress();
        enterImage();
        clickRegister();
        validateError("phone", "Số điện thoại bao gồm 10 chữ số");
    }

    @Test(priority = 22)
    public void TC23_WrongPhoneFormat() {
        initRegisterPage();
        enterUserName();
        enterFullName();
        enterEmail();
        enterPhone("@098765432");
        String password = faker.internet().password();
        enterPassword(password);
        enterRePassword(password);
        enterAddress();
        enterImage();
        clickRegister();
        validateError("phone", "Số điện thoại bao gồm 10 chữ số");
    }

    @Test(priority = 23)
    public void TC24_WrongPhoneFormat() {
        initRegisterPage();
        enterUserName();
        enterFullName();
        enterEmail();
        enterPhone("093434");
        String password = faker.internet().password();
        enterPassword(password);
        enterRePassword(password);
        enterAddress();
        enterImage();
        clickRegister();
        validateError("phone", "Số điện thoại bao gồm 10 chữ số");
    }

    @Test(priority = 24)
    public void TC25_WrongPhoneFormat() {
        initRegisterPage();
        enterUserName();
        enterFullName();
        enterEmail();
        enterPhone("093432342344");
        String password = faker.internet().password();
        enterPassword(password);
        enterRePassword(password);
        enterAddress();
        enterImage();
        clickRegister();
        validateError("phone", "Số điện thoại bao gồm 10 chữ số");
    }

    @Test(priority = 25)
    public void TC26_EmptyPhone() {
        initRegisterPage();
        enterUserName();
        enterFullName();
        enterEmail();
        String password = faker.internet().password();
        enterPassword(password);
        enterRePassword(password);
        enterAddress();
        enterImage();
        clickRegister();
        validateError("phone", "Số điện thoại không được trống");
    }

    @Test(priority = 26)
    public void TC27_EmptyAvt() {
        initRegisterPage();
        enterUserName();
        enterFullName();
        enterEmail();
        enterPhone();
        String password = faker.internet().password();
        enterPassword(password);
        enterRePassword(password);
        enterAddress();
        clickRegister();
        validateError("file", "Ảnh đại diện không được để trống");
    }

//    TODO: TC28, TC29

    @Test(priority = 29)
    public void TC30_EmptyPassword() {
        initRegisterPage();
        enterUserName();
        enterFullName();
        enterEmail();
        enterPhone();
        enterAddress();
        clickRegister();
        validateError("password", "Password không được trống");
    }

    @Test(priority = 31)
    public void TC32_MinLengthPassword() {
        initRegisterPage();
        enterUserName();
        enterFullName();
        enterEmail();
        enterPhone();
        enterPassword("1234567");
        enterRePassword("1234567");
        enterAddress();
        clickRegister();
        validateError("password", "Password ko được nhỏ hơn 8 ký tự");
    }

    @Test(priority = 32)
    public void TC33_BlankRePassword() {
        initRegisterPage();
        enterUserName();
        enterFullName();
        enterEmail();
        enterPhone();
        String password = faker.internet().password();
        enterPassword(password);
        enterAddress();
        clickRegister();
        validateError("repassword", "Nhập lại mật khẩu không được trống");
    }

    @Test(priority = 34)
    public void TC35_WrongRePassword() {
        initRegisterPage();
        enterUserName();
        enterFullName();
        enterEmail();
        enterPhone();
        enterPassword(faker.internet().password());
        enterRePassword(faker.internet().password());
        enterAddress();
        clickRegister();
        validateError("repassword", "Nhập lại mật khẩu không trùng khớp");
    }

    @Test(priority = 35)
    public void TC36_EmptyAddress() {
        initRegisterPage();
        enterUserName();
        enterFullName();
        enterEmail();
        enterPhone();
        enterPassword(faker.internet().password());
        enterRePassword(faker.internet().password());
        clickRegister();
        validateError("address", "Địa chỉ không được trống");
    }

    @Test(priority = 36)
    public void TC37_EmptyAddress() {
        initRegisterPage();
        enterUserName();
        enterFullName();
        enterEmail();
        enterPhone();
        enterPassword(faker.internet().password());
        enterRePassword(faker.internet().password());
        clickRegister();
        enterAddress("              ");
        validateError("address", "Địa chỉ không được trống");
    }
}
