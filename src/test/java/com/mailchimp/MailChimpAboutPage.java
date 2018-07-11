package com.mailchimp;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class MailChimpAboutPage {
    WebDriver driver;

    //constructor
    public MailChimpAboutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void goToAboutPage(){
        ((JavascriptExecutor) driver)
                .executeScript("window.scrollTo(0, document.body.scrollHeight)");
        driver.findElement(By.linkText("About MailChimp")).click();
    }
}
