package d31_01_2023;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Zadatak3 {

    public static void main(String[] args) throws InterruptedException, IOException {

//      Napisati program koji:
//      Ucitava stranicu https://itbootcamp.rs/
//      Skrola do slajdera na dnu stranice (u kome su slike Java, Php, Selenium, …)
//      Cita sve linkove slika iz slajdera
//      Proverava url svake slike, i za sve slike koje imaju status veci i jednak od 200 a manji od 300,
//      skida i smesta u folder itbootcamp_slider u okviru projekta
//      Azurirajte gitignore da ignorise itbootcamp_slider folder

        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));

        Helper helper = new Helper();

        driver.get("https://itbootcamp.rs/");
        new Actions(driver)
                .scrollToElement(driver.findElement(By.className("slider_bkgd")))
                .perform();

        List<WebElement> picLinks = driver
                .findElements(By.xpath("//div[contains(@class, 'slider_bkgd')]//img"));

        for (int i = 0; i < picLinks.size(); i++) {

            if (helper.getHTTPResponseStatusCode(picLinks.get(i).getAttribute("src")) >= 200
             && helper.getHTTPResponseStatusCode(picLinks.get(i).getAttribute("src")) < 300) {

                helper.downloadUsingStream((picLinks.get(i).getAttribute("src")), "itbootcamp_slider");

            }
        }

        Thread.sleep(5000);
        driver.quit();

    }
}
