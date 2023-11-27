package com.globant.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditPage extends BasePage {

    @FindBy(id = "firstHeading")
    private WebElement title;

    public EditPage(WebDriver driver) {
        super(driver);
    }

    public boolean isTitleCorrect(String titleToCompare) {
        ignorePopup(title);
        return title.getText().contains(titleToCompare);
    }
}
