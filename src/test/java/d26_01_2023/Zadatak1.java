package d26_01_2023;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

public class Zadatak1 {

    public static void main(String[] args) throws InterruptedException {

//      Niz todo-a (niz stringova) koje treba da uneti. Niz je:
//      Visit Paris
//      Visit Prague
//      Visit London
//      Visit New York
//      Visit Belgrade
//      Maksimizirati prozor
//      Ucitati stranicu https://example.cypress.io/todo
//      Program petljom prolazi kroz niz todo-a i svaki unosi na stranicu
//      Nakon svakog unosa todo-a, unosi se enter
//      Validira da li je novi todo dodat na stranici
//      Na kraju programa proci petljom i izbrisati svaki todo sa stranice (klikom na x dugme svakog todo-a)
//      Validirati da je na kraju programa broj todo-a na stranici 0.
//      Cekanje od 5s
//      Zatvorite pretrazivac

        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        Helper helper = new Helper();

        ArrayList<String> toDoStrings = new ArrayList<String>();
        toDoStrings.add("Visit Paris");
        toDoStrings.add("Visit Prague");
        toDoStrings.add("Visit London");
        toDoStrings.add("Visit New York");
        toDoStrings.add("Visit Belgrade");

        driver.manage().window().maximize();
        driver.get("https://example.cypress.io/todo");

        WebElement toDoInput = driver.findElement
                (By.xpath("//input[@placeholder='What needs to be done?']"));
        toDoInput.click();

        for (int i = 0; i < toDoStrings.size(); i++) {
            toDoInput.sendKeys(toDoStrings.get(i));
            toDoInput.sendKeys(Keys.ENTER);
            driver.findElement
                    (By.xpath("//ul[contains(@class, 'todo-list')]/li[" + (i + 3) + "]"));
        } // helper nije potreban, jer program ima smisla nastaviti samo ako je traženi element uopšte nađen na stranici

        List<WebElement> toDo = driver.findElements(By.xpath("//ul[contains(@class, 'todo-list')]/li"));

//        for (int i = 0; i < toDo.size(); i++) {
//            driver.findElement(By.xpath("//ul[contains(@class, 'todo-list')]/li[1]"))
//                    .click();
//            driver.findElement(By.xpath("//ul[contains(@class, 'todo-list')]/li[1]//button"))
//                    .click();
//        }

        for (int i = toDo.size(); i > 0; i--) {
            driver.findElement(By.xpath("//ul[contains(@class, 'todo-list')]/li[" + i + "]"))
                    .click();
            driver.findElement(By.xpath("//ul[contains(@class, 'todo-list')]/li[" + i + "]//button"))
                    .click();
        }

        if (!helper.elementExistByList(driver, By.xpath("//ul[contains(@class, 'todo-list')]/li"))) {
            Thread.sleep(5000);
            driver.quit();
        } else {
            driver.findElement(By.xpath("//ul[contains(@class, 'todo-list')]/li")); // da bi ga slomilo xD
        }

    }
}
