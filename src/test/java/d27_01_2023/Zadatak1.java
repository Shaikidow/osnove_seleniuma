package d27_01_2023;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Zadatak1 {

    public static void main(String[] args) throws InterruptedException {

//      Ucitati stranicu https://mdbootstrap.com/docs/standard/components/toasts/#section-basic-example
//      Klik na svako dugme od PRIMARY do DARK
//      Sacekati da se toast u desnom gornjem uglu pojavi
//      Pauza izmedju klikova 1s
//      Postavite implicitno cekanje za ucitavanje stranice i trazenje elemenata na 10s

        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://mdbootstrap.com/docs/standard/components/toasts/#section-basic-example");

        List<WebElement> buttons = driver
                .findElements(By.xpath("//section[@id='section-basic-example']//button"));
//                .findElement(By.id("section-basic-example"))
//                .findElements(By.tagName("button"));

        buttons.remove(buttons.size() - 1); // debugger mi je napisao da lista buttons sadrži 10 elemenata,
        buttons.remove(buttons.size() - 1); // ne znam zašto ako xpath prikazuje 8, pa sam obrisao dva ručno

        ArrayList<String> buttonNames = new ArrayList<>();
        for (int i = 0; i < buttons.size(); i++) {
            buttonNames.add(buttons.get(i).getText());
        }
        for (int i = 0; i < buttonNames.size(); i++) {
            buttonNames.set(i, buttonNames.get(i).toLowerCase());
        }

        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).click();
            driver.findElement
                    (By.xpath("//div[contains(@class, 'toast fade mx-auto toast-"
                            + buttonNames.get(i) + " toast-fixed show')]")); // validacija prikaza datog toast-a
            Thread.sleep(1000);
        }

        driver.quit();

    }
}
