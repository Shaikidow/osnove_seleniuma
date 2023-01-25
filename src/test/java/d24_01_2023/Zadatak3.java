package d24_01_2023;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Zadatak3 {

    public static void main(String[] args) throws InterruptedException {

//      Napisati program koji vrsi dodavanje 5 redova
//      Maksimizirati prozor
//      Ucitati stranicu https://www.tutorialrepublic.com/snippets/bootstrap/table-with-add-and-delete-row-feature.php
//      Dodati 5 redova sa istim podacima.Jedan red u jednoj iteraciji
//      Klik na dugme Add New
//      Unesite name,departmant i phone (uvek iste vrednost)
//          Trazenje po name atributu
//          Kliknite na zeleno Add dugme.
//          PAZNJA: Pogledajte strukturu stranice i videcete da se u svakom redu poslednje kolone javljaju
//          dugmici edit, add, delete ali zbog prirode reda neki dugmici se vide a neki ne.
//          Morate da dohvatite uvek Add dugme iz poslednjeg reda tabele.
//          Mozete koristeci index iz petlje, a mozete koristeci i last() fukncionalnost za xpath. Koristan link
//          Cekanje od 0.5s
//      Na kraju programa ugasite pretrazivac.

        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get("https://www.tutorialrepublic.com/snippets/bootstrap/table-with-add-and-delete-row-feature.php");
        Thread.sleep(500);

        for (int i = 1; i <= 5; i++) {
            driver.findElement(By.xpath("//button[contains(@class, 'add-new')]"))
                    .click();
            for (int j = 1; j <= 4; j++) {
                String rowString = "//tbody/tr[" + (i + 3) + "]/td[" + j + "]//input";
                if (j == 4) {
                    rowString = rowString.substring(0, rowString.length() - 4);
                }
                WebElement rowElement = driver.findElement(By.xpath(rowString));
                rowElement.click();
                if (j < 4) {
                    rowElement.sendKeys(" ");
                }
            }
        }

        Thread.sleep(500);
        driver.quit();

    }
}
