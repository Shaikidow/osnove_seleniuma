package d24_01_2023;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Zadatak1 {

    public static void main(String[] args) throws InterruptedException {

//      Maksimizirati prozor
//      Ucitati stranicu https://opensource-demo.orangehrmlive.com/web/index.php/auth/login
//      Prijavite se na sistem
//      Username: Admin
//      Password: admin123
//      Cekanje od 5s
//      U input za pretragu iz navigacije unesite tekst Me
//      Kliknite na prvi rezultat pretrage (to ce biti Time)
//      Cekanje od 1s
//      Kliknite u headeru na svog avatara (to ce biti na ime: Paul Collings)
//      Klinkite na logout
//      Cekanje od 5s
//      Zatvorite pretrazivac

        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(10000));
        wait.until(ExpectedConditions.elementToBeClickable(By.name("username")));

        WebElement usernameInput = driver.findElement(By.name("username"));
        usernameInput.click();

        usernameInput.sendKeys("Admin");

        WebElement passwordInput = driver.findElement(By.name("password"));
        passwordInput.click();
        passwordInput.sendKeys("admin123");

        driver.findElement(By.xpath("//div[contains(@class, 'oxd-form-actions')]//button"))
                        .click();

        Thread.sleep(5000);

        WebElement searchInput = driver.findElement
                (By.xpath("//div[contains(@class, 'oxd-main-menu-search')]//input"));

        searchInput.click();
        searchInput.sendKeys("Me");

        driver.findElement(By.xpath("//a[contains(@class, 'oxd-main-menu-item')]"))
                        .click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("//li[contains(@class, 'oxd-userdropdown')]"))
                        .click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("//a[@href='/web/index.php/auth/logout']"))
                        .click();

        Thread.sleep(5000);

        driver.quit();

    }
}
