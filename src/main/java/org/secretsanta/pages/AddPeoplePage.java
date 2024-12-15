package org.secretsanta.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.secretsanta.base.BasePage;

import java.util.List;
import java.util.stream.Collectors;

public class AddPeoplePage extends BasePage {

    @FindBy(xpath = "//body/p[1]")
    private WebElement enterPeopleText;

    @FindBy(xpath = "//input[@name='name']")
    private WebElement name;

    @FindBy(xpath = "//input[@value='Add Person']")
    private WebElement addPersonButton;

    @FindBy(xpath = "//span[@class='first']")
    private List<WebElement> firstNameText;
    @FindBy(xpath = "//input[@value='Generate Matches!']")
    private WebElement generateMatchesButton;

    public AddPeoplePage(WebDriver driver) {
        super(driver);
    }

    public String getEnterPeopleText() {
        return enterPeopleText.getText();
    }

    public void enterName(String name) {
        this.name.sendKeys(name);
    }

    public boolean isAddPersonButtonClickable() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(addPersonButton));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isGenerateMatchesButtonClickable() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(generateMatchesButton));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void clickAddPersonButton() {
        addPersonButton.click();
    }

    public void clickGenerateMatchesButton() {
        generateMatchesButton.click();
    }

    public List<String> getPeoplesName() {
        return firstNameText.stream().map(WebElement::getText).collect(Collectors.toList());
    }

}
