package org.secretsanta.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.secretsanta.base.BasePage;

import java.time.Duration;

public class HomePage extends BasePage {
    @FindBy(xpath = "//h2[contains(text(),'Welcome to Cybage PALM - AkashgunSecret Santa Matc')]")
    WebElement welcomeText;
    @FindBy(xpath = "//p[normalize-space()='Click below to start!']")
    WebElement text;
    @FindBy(xpath = "//a[normalize-space()='Start!']")
    WebElement startButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public String getWelcomeText() {
        return welcomeText.getText();
    }

    public String getText() {
        return text.getText();
    }

    public AddPeoplePage gotoAddPeoplePage() {
        startButton.click();
        return new AddPeoplePage(driver);
    }


    public boolean isStartButtonClickable() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.elementToBeClickable(startButton));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
