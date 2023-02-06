package d02_02_2023;

import com.sun.org.glassfish.gmbal.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.time.Duration;

public class BootstrapTableTests {

    private WebDriver driver;
    private WebDriverWait wait;
    private String baseURL;

    @BeforeClass
    public void setup() {

        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");

        this.driver = new ChromeDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.baseURL = "https://s.bootsnipp.com";
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));

    }

    @BeforeMethod
    public void before() {

        driver.get(baseURL + "/iframe/K5yrx");

    }

    @Test(priority = 10)
    @Description("Test #1: Edit Row")
    public void editRow() {

        String firstName = "Dimitrije";
        String lastName = "Mandić";
        String middleName = "Aleksandar";

        Assert.assertEquals(driver.getTitle(), "Table with Edit and Update Data - Bootsnipp.com",
                "Incorrect page title.");

        driver.findElement(By.xpath("//tr[@id='d1']//button[@data-target='#edit']"))
                .click();

        WebElement updateData = driver.findElement(By.id("edit")).findElement(By.className("modal-content"));
        wait.until(ExpectedConditions.visibilityOf(updateData));

        driver.findElement(By.id("fn")).clear();
        driver.findElement(By.id("fn")).sendKeys(firstName);

        driver.findElement(By.id("ln")).clear();
        driver.findElement(By.id("ln")).sendKeys(lastName);

        driver.findElement(By.id("mn")).clear();
        driver.findElement(By.id("mn")).sendKeys(middleName);

        driver.findElement(By.id("up")).click();
        wait.until(ExpectedConditions.invisibilityOf(updateData));

        Assert.assertEquals(driver.findElement(By.id("f1")).getText(), firstName,
                "First name does not match entered string.");

        Assert.assertEquals(driver.findElement(By.id("l1")).getText(), lastName,
                "Last name does not match entered string.");

        Assert.assertEquals(driver.findElement(By.id("m1")).getText(), middleName,
                "Middle name does not match entered string.");

    }

    @Test (priority = 20)
    @Description("Test #2: Delete Row")
    public void deleteRow() {

        String firstName = "Dimitrije";
        String lastName = "Mandić";
        String middleName = "Aleksandar";

        Assert.assertEquals(driver.getTitle(), "Table with Edit and Update Data - Bootsnipp.com",
                "Incorrect page title.");

        int tableRowsOld = driver.findElement(By.tagName("tbody")).findElements(By.tagName("tr")).size();

        driver.findElement(By.xpath("//tr[@id='d1']//button[@data-target='#delete']"))
                .click();

        WebElement deleteData = driver.findElement(By.id("delete")).findElement(By.className("modal-content"));
        wait.until(ExpectedConditions.visibilityOf(deleteData));

        driver.findElement(By.id("del")).click();
        wait.until(ExpectedConditions.invisibilityOf(deleteData));

        int tableRowsNew = driver.findElement(By.tagName("tbody")).findElements(By.tagName("tr")).size();

//      Zanimljivo, ali ovo ne radi, jer iz nekog razloga, čim ovaj test obriše gornji red,
//      stranica kao da se refresh-uje i opet ima dva originalna reda. Možda i nije refresh, ali tako se ponaša.
//      Ne znam da li je to bila poenta ovog zadatka, ali ostaviću to kao bag. Screenshot reflektuje to stanje stranice.

        Assert.assertEquals(tableRowsNew, tableRowsOld - 1,
                "New number of table rows not smaller by 1.");

    }

    @Test (priority = 30)
    @Description("Test #3: Take a Screenshot")
    public void takeAScreenshot() throws IOException {

        Assert.assertEquals(driver.getTitle(), "Table with Edit and Update Data - Bootsnipp.com",
                "Incorrect page title.");

        Helper helper = new Helper();
        helper.takeScreenshot(driver, "screenshots/slike.png");

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
