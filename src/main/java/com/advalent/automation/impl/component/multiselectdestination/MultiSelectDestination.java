package com.advalent.automation.impl.component.multiselectdestination;

import com.advalent.automation.impl.component.inputelements.DropDown;
import com.advalent.automation.impl.component.inputelements.TextBox;
import com.advalent.automation.impl.pages.common.AbstractWebComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MultiSelectDestination extends AbstractWebComponent {

    private TextBox leftSearchInput;
    private TextBox rightSearchInput;
    private DropDown leftDropDown;
    private DropDown rightDropDown;
    private WebElement moveLeftButton;
    private WebElement moveRightButton;

    public MultiSelectDestination(WebDriver driver, String leftDropDownXpath, String rightDropDownXpath,
                                  String leftSearchInputXpath, String rightSearchInputXpath,
                                  String moveLeftButtonXpath, String moveRightButtonXpath) {
        super(driver);
        this.leftDropDown = new DropDown(driver, leftDropDownXpath);
        this.rightDropDown = new DropDown(driver, rightDropDownXpath);
        this.leftSearchInput = new TextBox(driver, leftSearchInputXpath);
        this.rightSearchInput = new TextBox(driver, rightSearchInputXpath);
        this.moveLeftButton = driver.findElement(By.xpath(moveLeftButtonXpath));
        this.moveRightButton = driver.findElement(By.xpath(moveRightButtonXpath));
    }

    @Override
    public boolean isFullyLoaded() {
        return (this.moveRightButton.isDisplayed() && this.moveLeftButton.isDisplayed()) &&
                (this.leftDropDown.isFullyLoaded() && this.rightDropDown.isFullyLoaded()) &&
                (this.leftSearchInput.isFullyLoaded() && this.rightSearchInput.isFullyLoaded());
    }

    public MultiSelectDestination moveFromLeftToRight(String option) {
        leftSearchInput.clearValue();
        leftSearchInput.enterValue(option);
        this.leftDropDown.selectOption(option);
        this.moveRightButton.click();
        return this;
    }

    public List<String> getAvailableOptionsInRight() {
        return this.rightDropDown.getAvailableOptions();
    }

    public MultiSelectDestination moveFromRightToLeft(String option) {
        rightSearchInput.clearValue();
        rightSearchInput.enterValue(option);
        this.rightDropDown.selectOption(option);
        this.moveLeftButton.click();
        return this;
    }

    public List<String> getAvailableOptionsInLeft() {
        return this.leftDropDown.getAvailableOptions();
    }

}
