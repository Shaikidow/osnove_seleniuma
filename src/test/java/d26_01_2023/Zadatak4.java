package d26_01_2023;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

public class Zadatak4 {

    public static void main(String[] args) throws InterruptedException {

//      Napisati program koji ucitava stranicu https://geodata.solutions/
//      Bira Country, State i City po vasoj zelji
//      Pritom potrebno je izvrsiti cekanje da se pojave State-ovi nakon izbora Country-a
//      I takodje je potrebno izvrsiti cekanje da se ucitaju gradovi nakon izbora State-a
//      Izaberite Country, State i City tako da imate podatke da selektujete!

        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

        driver.get("https://geodata.solutions/");

        Select select = new Select(driver.findElement(By.id("countryId")));
        select.selectByValue("Serbia");

        select = new Select(driver.findElement(By.id("stateId")));
        select.selectByValue("Central Serbia");

        select = new Select(driver.findElement(By.id("cityId")));
        select.selectByValue("Nis");

        Thread.sleep(5000);
        driver.quit();

    }
}
