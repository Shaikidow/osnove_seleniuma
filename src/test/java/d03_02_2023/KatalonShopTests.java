package d03_02_2023;

import com.sun.org.glassfish.gmbal.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class KatalonShopTests {

    private WebDriver driver;
    private WebDriverWait wait;
    private String baseURL;

    @BeforeClass
    public void setup() {

        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");

        this.driver = new ChromeDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.baseURL = "https://cms.demo.katalon.com";
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));

    }

    @BeforeMethod
    public void before() {

        driver.get(baseURL + "/product/flying-ninja/");

    }

    @Test (priority = 10)
    @Description("Test #1:  Adding product with quantity to the cart")
    public void addingProductWithQuantityToTheCart() {

        driver.findElement(By.name("quantity")).sendKeys(Keys.BACK_SPACE, Keys.DELETE, "3");
        driver.findElement(By.name("add-to-cart")).click();
        Assert.assertTrue(driver.findElement(By.className("woocommerce-message")).getText().contains("“Flying Ninja”"),
                "Message does not contain name of product.");
        driver.findElement(By.id("primary-menu")).findElement(By.tagName("a")).click();
        Assert.assertEquals(driver.getCurrentUrl(), baseURL + "/cart/", "URL does not contain '/cart'.");
        Assert.assertEquals(driver.findElements(By.className("cart_item")).size(), 1,
                "Number of different products is not 1.");

    }

    @Test (priority = 20)
    @Description("Test #2:  Removing product from cart")
    public void removingProductFromCart() {

        driver.findElement(By.id("primary-menu")).findElement(By.tagName("a")).click();
        Assert.assertEquals(driver.getCurrentUrl(), baseURL + "/cart/", "URL does not contain '/cart'.");
        Assert.assertEquals(driver.findElements(By.className("cart_item")).size(), 1,
                "Number of different products is not 1.");
        driver.findElement(By.xpath("//a[contains(@class, 'remove')]")).click();
        wait.until(ExpectedConditions.numberOfElementsToBe(By.className("cart_item"), 0));
        Assert.assertEquals(driver.findElements(By.className("cart_item")).size(), 0,
                "Number of different products is not 0.");

    }

    @Test (priority = 30)
    @Description("Test #3:  Verify error is displayed when username is missing")
    public void verifyErrorIsDisplayedWhenUsernameIsMissing() {

        driver.findElement(By.id("primary-menu")).findElement(By.linkText("MY ACCOUNT")).click();
        driver.findElement(By.name("login")).click();
        Assert.assertEquals(driver.findElement(By.className("woocommerce-error"))
                .getText(), "Error: Username is required.", "Error is not displayed correctly.");

    }

    @Test (priority = 40)
    @Description("Test #4:  Verify error is displayed when password is missing")
    public void verifyErrorIsDisplayedWhenPasswordIsMissing() {

        driver.findElement(By.id("primary-menu")).findElement(By.linkText("MY ACCOUNT")).click();
        driver.findElement(By.id("username")).sendKeys("customer");
        driver.findElement(By.name("login")).click();
        Assert.assertEquals(driver.findElement(By.className("woocommerce-error"))
                .getText(), "ERROR: The password field is empty.", "Error is not displayed correctly.");

    }

    @Test (priority = 50)
    @Description("Test #5:  Verify error is displayed when password is wrong")
    public void verifyErrorIsDisplayedWhenPasswordIsWrong() {

        driver.findElement(By.id("primary-menu")).findElement(By.linkText("MY ACCOUNT")).click();
        driver.findElement(By.id("username")).sendKeys("customer");
        driver.findElement(By.id("password")).sendKeys("invalidpassword");
        driver.findElement(By.name("login")).click();
        Assert.assertEquals(driver.findElement(By.className("woocommerce-error"))
                .getText(),
                "ERROR: The password you entered for the username customer is incorrect. Lost your password?",
                "Error is not displayed correctly.");

    }

    @Test (priority = 60)
    @Description("Test #6:  Verify error is displayed when user does not exist")
    public void verifyErrorIsDisplayedWhenUserDoesNotExist() {

        driver.findElement(By.id("primary-menu")).findElement(By.linkText("MY ACCOUNT")).click();
        driver.findElement(By.id("username")).sendKeys("invaliduser");
        driver.findElement(By.id("password")).sendKeys("pass1234");
        driver.findElement(By.name("login")).click();
        Assert.assertEquals(driver.findElement(By.className("woocommerce-error"))
                        .getText(),
                "ERROR: Invalid username. Lost your password?",
                "Error is not displayed correctly.");

    }

    @Test (priority = 70)
    @Description("Test #7:  Verify successful login")
    public void verifySuccessfulLogin() {

        driver.findElement(By.id("primary-menu")).findElement(By.linkText("MY ACCOUNT")).click();
        driver.findElement(By.id("username")).sendKeys("customer");
        driver.findElement(By.id("password")).sendKeys("crz7mrb.KNG3yxv1fbn");
        driver.findElement(By.name("login")).click();
        Assert.assertTrue(driver
                        .findElement(By.xpath("//*[contains(@class, 'woocommerce-MyAccount-content')]/p[1]"))
                        .getText().contains("Hello Katalon Parlitul_Changed"), "Login is not successful.");

    }

    @AfterMethod
    public void after() {

    }

    @AfterClass
    public void quit() throws InterruptedException {

        Thread.sleep(5000);
        driver.quit();

    }

}
