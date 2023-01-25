package d24_01_2023;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;

public class Zadatak2 {

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
//      Cekanje od 5s
//      Zatvorite pretrazivac

        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        ArrayList<String> toDo = new ArrayList<String>();
        toDo.add("Visit Paris");
        toDo.add("Visit Prague");
        toDo.add("Visit London");
        toDo.add("Visit New York");
        toDo.add("Visit Belgrade");

        driver.manage().window().maximize();
        driver.get("https://example.cypress.io/todo");
        Thread.sleep(5000); // ovo ću samostalno da ubacujem radi učitavanja stranice

        WebElement toDoInput = driver.findElement
                (By.xpath("//input[@placeholder='What needs to be done?']"));
        toDoInput.click();

        for (int i = 0; i < toDo.size(); i++) {
            toDoInput.sendKeys(toDo.get(i));
            toDoInput.sendKeys(Keys.ENTER);
        }

        Thread.sleep(5000);
        driver.quit();

    }
}
