package d24_01_2023;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Zadatak4 {

    public static void main(String[] args) throws InterruptedException {

//      Maksimizirati prozor
//      Ucitati stranicu https://artplayer.org/
//      U fokusu je player sa desne strane
//      Ceka 3-4s
//      Klik na play dugme
//      Klik na zvucnik za mute
//      Ceka 3s
//      Klik na screenshot
//      Klik na PIP mode
//      Ceka 1s
//      Klik na Exit PIP mode
//      Klik na WebFullscreen
//      Klik na Exit WebFullscreen
//      Cekanje od 5s
//      Zatvorite pretrazivac

        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get("https://artplayer.org/");
        Thread.sleep(3500);

        driver.findElement(By.xpath("//i[@aria-label='Play']"))
                .click();
        driver.findElement(By.xpath("//i[@aria-label='Mute']"))
                .click();
        Thread.sleep(3000);

        driver.findElement(By.xpath("//i[contains(@class, 'art-icon-screenshot')]"))
                .click();
        driver.findElement(By.xpath("//i[contains(@class, 'art-icon-pip')]"))
                .click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("//i[contains(@class, 'art-icon-pip')]"))
                .click();
        driver.findElement(By.xpath("//i[contains(@class, 'art-icon-fullscreenWebOn')]"))
                .click();
        driver.findElement(By.xpath("//i[contains(@class, 'art-icon-fullscreenWebOff')]"))
                .click();

        Thread.sleep(5000);
        driver.quit();

    }
}
