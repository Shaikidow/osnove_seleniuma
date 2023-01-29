package d26_01_2023;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Scanner;

public class Zadatak2 {

    public static void main(String[] args) throws InterruptedException {

//      (Za vezbanje)
//      Napisati program koji matematicku formulu koju korisnik unese izvrsava na stranici:
//      Ucitati stranicu https://www.calculatorsoup.com/calculators/math/basic.php
//      Korisnik unosi formulu, samo osnovne matematicke operacije, npr:
//      1243+329=
//      21912-4=
//      12913÷4=
//      U programu se formula unosi kao jedan string i potrebno je razbiti formulu na karaktere.
//      Za to imate metodu https://www.geeksforgeeks.org/convert-a-string-to-a-list-of-characters-in-java/
//      Zatim u odnosu na karakter uradite odredjenu logiku

        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        Scanner s = new Scanner(System.in);

        System.out.print("\nUnesite matematičku formulu: ");
        String formula = s.next();

        driver.get("https://www.calculatorsoup.com/calculators/math/basic.php");

        WebElement calcInput = driver.findElement(By.id("cs_display"));
        calcInput.click();
        for (int i = 0; i < formula.length(); i++) {
            String character = formula.substring(i, i + 1);
            if (character.equals("=")) {
                calcInput.sendKeys(Keys.ENTER);
            } else {
                calcInput.sendKeys(character);
            } // mislim da je ovo čak malo jednostavnije nego ponuđeni način, pritom ni ne mora da koristi char kao tip!
        }

        Thread.sleep(5000);
        driver.quit();

    }
}
