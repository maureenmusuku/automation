package com.advalent.automation.impl.component.inputelements;

import com.advalent.automation.api.annotations.inputfield.validation.Validation;
import com.advalent.automation.api.components.inputelements.validations.IHaveValidations;
import com.advalent.automation.api.constants.TimeOuts;
import com.advalent.automation.api.exceptions.AutomationException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AutoSuggest extends InputElement implements IHaveValidations {
    private int waitTime = TimeOuts.THREE_SECONDS;


    public AutoSuggest(WebDriver driver, String locator) {
        super(driver, locator);
    }

    public int getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(int waitTime) {
        this.waitTime = waitTime;
    }

    @Override
    public String getInputFormatValidationMessage(String input) {
        Validation validation = getClass().getAnnotation(Validation.class);
        String xpath = validation.inputFormatMessageXpath();
        return null;
    }

    @Override
    public String getInputFieldValidationMessage(String input) {
        return null;
    }

    @Override
    public String getRequiredFieldValidationMessage(String input) {
        return null;
    }

    public boolean isFullyLoaded() {
        return getDriver().findElement(By.xpath("//ul[contains(@id,'typeahead')]")).isDisplayed();
    }


    @Override
    public InputElement enterValue(String value) {
        this.element.sendKeys(value);
        doWaitTillFullyLoaded(this.waitTime);
        getDriver().findElement(By.xpath("//ul/li[1]/a/strong")).click();
        return this;
    }

    @Override
    public InputElement clearValue() {
        return super.clearValue();
    }

    @Override
    public InputElement selectOption(String option) {
        throw new AutomationException("method not implemented for AutoSuggest");
    }

    @Override
    public InputElement unSelectOption() {
        throw new AutomationException("method not implemented for AutoSuggest");
    }


}
