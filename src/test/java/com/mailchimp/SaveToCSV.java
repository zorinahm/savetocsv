package com.mailchimp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SaveToCSV {
    WebDriver driver;

    @Before
    public void setup() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().fullscreen();
        driver.get("https://mailchimp.com/");
        Thread.sleep(3000);
    }

    @After
    public void closeBrowser(){
        driver.close();
        driver.quit();
    }

    @Test
    public void saveValuesToCSV() throws IOException {
        //saving csv file in csv directory with timestamp
        String timestamp = new SimpleDateFormat("yyMMddHHmmss").format(new Date());
        FileWriter writer = new FileWriter("./csv/" + timestamp + "output.csv");

        //go to About MailChimp page
        MailChimpAboutPage mailChimpAboutPage = new MailChimpAboutPage(driver);
        mailChimpAboutPage.goToAboutPage();

        writer.append("Name");
        writer.append("\n");

        //get list of names
        WebElement leaders = driver.findElement(By.xpath("(//a[contains(@class,'bio_link')])"));
        List<WebElement> names = leaders.findElements(By.xpath("(//a[contains(@class,'bio_link')])"));
        for(WebElement name : names) {
            String[] allNames = name.getAttribute("data-title").split(",");
            for (int i = 0; i < allNames.length; i++) {
                if (i != allNames.length - 1) {
                    writer.append(allNames[i]);
                } else {
                    writer.append(allNames[i]);
                }
            }
            writer.append("\n");
        }

        writer.append("\n");
        writer.append("Position");
        writer.append("\n");

        //get list of positions
        for(WebElement position : names) {
            String[] allPositions = position.getAttribute("data-position").split(",");
            for (int i = 0; i < allPositions.length; i++) {
                if (i != allPositions.length - 1) {
                    writer.append(allPositions[i]);
                } else {
                    writer.append(allPositions[i]);
                }
            }
            writer.append("\n");
        }

        writer.append("\n");
        writer.append("Description");
        writer.append("\n");

        //get list of descriptions
        for(WebElement description : names) {
            String[] allDescriptions = description.getAttribute("data-description").split(",");
            for (int i = 0; i < allDescriptions.length; i++) {
                if (i != allDescriptions.length - 1) {
                    writer.append(allDescriptions[i]);
                } else {
                    writer.append(allDescriptions[i]);
                }
            }
            writer.append("\n");
        }

        //flush the output and close
        writer.flush();
        writer.close();
    }
}
