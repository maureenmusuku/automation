package com.advalent.automation.impl.component.inputelements;

import com.advalent.automation.api.exceptions.AutomationException;
import org.openqa.selenium.WebDriver;

public class CheckBox extends InputElement {
    CheckBox(WebDriver driver, String locator) {
        super(driver, locator);
    }

    @Override
    public boolean isFullyLoaded() {
        return this.element.isDisplayed();
    }

    @Override
    public String getLocator() {
        return super.getLocator();
    }

    @Override
    public InputElement enterValue(String value) {
        throw new AutomationException("method not implemented for CheckBox");

    }

    @Override
    public InputElement clearValue() {
        throw new AutomationException("method not implemented for CheckBox");

    }

    @Override
    public String getValue() {
        throw new AutomationException("method not implemented for CheckBox");
    }

    @Override
    public InputElement selectOption(String option) {
        throw new AutomationException("method not implemented for CheckBox");

    }

    @Override
    public InputElement unSelectOption() {
        throw new AutomationException("method not implemented for CheckBox");

    }

    public boolean isSelected() {
        return this.element.isSelected();
    }

    public CheckBox check() {
        if (!isSelected()) {
            this.element.click();
        }
        return this;
    }

    public CheckBox unCheck() {
        if (isSelected()) {
            this.element.click();
        }
        return this;
    }

}
