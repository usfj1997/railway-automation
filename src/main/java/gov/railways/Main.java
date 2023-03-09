package gov.railways;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class Main {
    public static void main(String[] args) throws InterruptedException {
//        //Configuring the path of the chromedriver
//        String projectPath = System.getProperty("user.dir");
//        System.setProperty("webdriver.chrome.driver", projectPath + "\\src\\main\\resources\\chromedriver.exe");
//        //Opening the Chrome browser window
        WebDriver driver = new EdgeDriver();
//        ///////////////////////////////////////////////
//        T001
        //Maximizing the Chrome browser window
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //Going to the link of the railway website's landing page
        driver.get("https://railway.gov.lk/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Thread.sleep(2000);
        String landing_title = driver.getTitle();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Assert.assertEquals(landing_title,"Welome to Department of Railways");
        System.out.println("T001 ✅");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Thread.sleep(2000);
//        ////////////////////////////////////////
//        T002
        //Clicking on English
        driver.findElement(By.xpath("/html[1]/body[1]/table[1]/tbody[1]/tr[3]/td[3]/a[1]/img[1]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Thread.sleep(2000);
        String home_title = driver.getTitle();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Thread.sleep(2000);
        Assert.assertEquals(home_title, "Welcome to Sri Lanka Railways");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Thread.sleep(2000);
        System.out.println("T002 Preconditions ✅");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Thread.sleep(2000);

        //Selecting Anura'pura from the "From" dropdown menu
        Select from = new Select(driver.findElement(By.id("from")));
        from.selectByVisibleText("ANURA'PURA");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Thread.sleep(2000);

        //Selecting Aluthgama from the "To" dropdown menu
        Select to = new Select(driver.findElement(By.id("to")));
        to.selectByIndex(7);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Thread.sleep(2000);

        Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(),'Distance')]")).isDisplayed());
//        Assert.assertNotNull(distance);
        System.out.println("T002.1 ✅");

        //Selecting Fish Accompanied from the "Item" dropdown menu
        Select item = new Select(driver.findElement(By.xpath("/html/body/div[5]/div/div/div/div/div/div/div[2]/div/div/div[1]/div/div/div[5]/div/form/fieldset/div/table/tbody/tr[4]/td[2]/select")));
        item.selectByIndex(1);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Thread.sleep(2000);

        //Selecting Normal Trains from the "Train" dropdown menu
        Select train = new Select(driver.findElement(By.id("train_type")));
        train.selectByIndex(0);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Thread.sleep(2000);

        //Entering the weight
        driver.findElement(By.id("amount")).sendKeys("4");
        Thread.sleep(2000);

        WebElement calculate = driver.findElement(By.xpath("/html/body/div[5]/div/div/div/div/div/div/div[2]/div/div/div[1]/div/div/div[5]/div/form/fieldset/div/table/tbody/tr[7]/td[2]/input"));
        calculate.click();
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(),'Distance')]")).isDisplayed());
        System.out.println("T002.2 ✅");

        //Deselecting  Aluthgama from "To" Dropdown menu
        to.selectByIndex(0);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Thread.sleep(2000);
        calculate.click();
        try {
            WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(text(),'Distance')]")));
            System.out.println("T002 ✅");
        } catch (Exception e) {
            System.out.println("T002 ❌");
            e.printStackTrace();
        }

        Thread.sleep(2000);
        driver.quit();
    }
}