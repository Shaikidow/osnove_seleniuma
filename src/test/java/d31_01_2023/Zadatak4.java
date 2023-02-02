package d31_01_2023;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;

public class Zadatak4 {

    public static void main(String[] args) throws InterruptedException {

//      Napisati program koji:
//      Ucitava stranicu https://blueimp.github.io/jQuery-File-Upload/
//      Uploaduje sve cetiri slike odjednom (slike iz prvog zadatka)
//      Ceka da se prikazu 4 item-a a upload
//      Klik na upload
//      Ceka da se upload zavrsi

        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));

        driver.get("https://blueimp.github.io/jQuery-File-Upload/");

        ArrayList<File> cubeImages = new ArrayList<>();
        cubeImages.add(new File("test_data/front.jpg"));
        cubeImages.add(new File("test_data/left.jpg"));
        cubeImages.add(new File("test_data/right.jpg"));
        cubeImages.add(new File("test_data/back.jpg"));

        for (int i = 0; i < cubeImages.size(); i++) {

            driver.findElement(By.className("fileinput-button"))
                    .sendKeys(cubeImages.get(i).getAbsolutePath() + "\n");


        }

        wait.until(ExpectedConditions.numberOfElementsToBe(By.className("files"), 4));
        driver.findElement
                (By.xpath("//div[contains(@class, 'col-lg-7')]//button[contains(@class, 'btn-primary')]"))
                .click();
        wait.until(ExpectedConditions
                .numberOfElementsToBe(By.xpath("//tbody//button[contains(@class, 'delete')]"), 4));

        Thread.sleep(5000);
        driver.quit();

    }
}
