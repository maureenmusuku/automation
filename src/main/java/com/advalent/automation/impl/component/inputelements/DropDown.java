package com.advalent.automation.impl.component.inputelements;

import com.advalent.automation.api.annotations.inputfield.validation.Validation;
import com.advalent.automation.api.components.inputelements.validations.IHaveValidations;
import com.advalent.automation.api.exceptions.AutomationException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.stream.Collectors;

public class DropDown extends InputElement implements IHaveValidations {
    public DropDown(WebDriver driver, String locator) {
        super(driver, locator);
    }


    @Override
    public String getInputFormatValidationMessage(String input) {
        Validation validation = getClass().getAnnotation(Validation.class);
        String xpath = validation.inputFormatMessageXpath();
        return null;
    }

    @Override
    public String getInputFieldValidationMessage(String input) {
        throw new AutomationException("method not implemented for DropDown");

    }

    @Override
    public String getRequiredFieldValidationMessage(String input) {
        throw new AutomationException("method not implemented for DropDown");

    }

    @Override
    public InputElement enterValue(String value) {
        throw new AutomationException("method not implemented for DropDown");
    }

    @Override
    public InputElement clearValue() {
        throw new AutomationException("method not implemented for DropDown");
    }

    public boolean isFullyLoaded() {
        return this.element.isDisplayed();
    }

    public List<String> getAvailableOptions() {
        Select dropdown = new Select(this.element);
        return dropdown.getOptions().stream()
                .filter(option -> !option.getText().contains("Select"))
                .map(option -> option.getText())
                .collect(Collectors.toList());
    }

    @Override
    public String getValue() {
        refreshElement();
        Select dropdown = new Select(this.element);
        return dropdown.getFirstSelectedOption().getText().trim();
    }


    @Override
    protected void refreshElement() {
        this.element = driver.findElement(By.xpath(getLocator()));
    }
}
