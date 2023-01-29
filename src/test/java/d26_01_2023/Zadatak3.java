package d26_01_2023;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

public class Zadatak3 {

    public static void main(String[] args) throws InterruptedException {

//      Napisati program koji:
//      Ucitava stranicu https://s.bootsnipp.com/iframe/Dq2X
//      Klikce na svaki iks da ugasi obavestenje i proverava da li se nakon klika element obrisao sa stranice
//      i ispisuje odgovarajuce poruke (OVO JE POTREBNO RESITI PETLJOM)
//      POMOC: Brisite elemente odozdo.
//      (ZA VEZBANJE)Probajte da resite da se elemementi brisu i odozgo

        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

        driver.get("https://s.bootsnipp.com/iframe/Dq2X");

        Helper helper = new Helper();

        List<WebElement> alerts = driver.findElements(By.xpath("//body/div/div/div/div"));

        System.out.println();

        for (int i = alerts.size(); i > 0; i--) {

            alerts.get(i - 1).findElement(By.tagName("button")).click();

            if (!helper.elementExist(driver, By.xpath("//body/div/div/div/div[" + i + "]"))) {
                    System.out.println("Element je obrisan.");
                } else {
                    System.out.println("Element nije obrisan.");
                }
        }

////      ZA VEŽBANJE
//
//        for (int i = 0; i < alerts.size(); i++) {
//
//            alerts.get(i).findElement(By.tagName("button")).click();
//
//            Thread.sleep(1000);
//
//            if (!helper.elementExist(driver, By.xpath("//body/div/div/div/div[" + (i + 1) + "]"))) {
//                System.out.println("Element je obrisan.");
//            } else {
//                System.out.println("Element nije obrisan.");
//            }
//        }
//
////      /ZA VEŽBANJE
//
////      Zaključak: brisanje odozgo ne funkcioniše!

        driver.quit();

    }
}
