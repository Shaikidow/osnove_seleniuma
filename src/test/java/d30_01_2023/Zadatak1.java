package d30_01_2023;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.WheelInput;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Zadatak1 {

    public static void main(String[] args) throws InterruptedException {

//      Napisati program koji testira infinity scroll.
//      Ucitati stranu https://web.dev/patterns/web-vitals-patterns/infinite-scroll/infinite-scroll/demo.html
//      Selektujte delay od 2000ms, koristeci Select klasu.
//      Skrol do Show more dugmeta koje se nalazi na dnu stranice
//      Sacekajte da dugme bude klikljivo
//      Klik na Show more dugme
//      Sacekjate da broj elemenata bude X (X je koliko se kod vas ucitava)
//      Sacekajte da dugme vise ne bude klikljivo

        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));

        driver.get("https://web.dev/patterns/web-vitals-patterns/infinite-scroll/infinite-scroll/demo.html");
        driver.manage().window().maximize();

        new Select(driver.findElement(By.id("delay-select"))).selectByValue("2000");

        WebElement isb = driver.findElement(By.id("infinite-scroll-button"));

        List<WebElement> elements = driver.findElements(By.className("item"));
        for (int i = 0; i < elements.size(); i++) {
            new Actions(driver).scrollToElement(elements.get(i)).perform();
        }

        new Actions(driver).scrollToElement(isb).perform(); // moglo bi bez te liste elemenata iznad da sajt nije klošar

        wait.until(ExpectedConditions.elementToBeClickable(isb));
        isb.click();

        wait.until(ExpectedConditions.numberOfElementsToBe(By.className("item"), 8)); // 5 + doda još 3 kod mene

        Thread.sleep(5000);
        driver.quit();

    }
}
