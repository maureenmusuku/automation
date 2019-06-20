package com.advalent.automation.impl.component.inputelements;

import com.advalent.automation.api.annotations.inputfield.validation.Validation;
import com.advalent.automation.api.components.inputelements.validations.IHaveValidations;
import com.advalent.automation.api.constants.TimeOuts;
import com.advalent.automation.api.exceptions.AutomationException;
import com.advalent.automation.selenium.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.stream.Collectors;

public class MultipleAutoComplete extends InputElement implements IHaveValidations {
    public MultipleAutoComplete(WebDriver driver, String locator) {
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
        return null;
    }

    @Override
    public String getRequiredFieldValidationMessage(String input) {
        return null;
    }

    public boolean isFullyLoaded() {
        try {
            boolean displayed = this.element.findElement(By.xpath("./div[2]/ul/li")).isDisplayed();
            return displayed;
        } catch (NoSuchElementException | StaleElementReferenceException ex) {
            return false;
        }

    }


    @Override
    public InputElement enterValue(String value) {
        try {
            this.element.findElement(By.xpath("./div[1]/ul/li/input")).sendKeys(value);
        } catch (StaleElementReferenceException ex) {
            new MultipleAutoComplete(getDriver(), getLocator()).enterValue(value);
        } catch (NoSuchElementException elementException) {
            new MultipleAutoComplete(getDriver(), getLocator()).enterValue(value);
        }
        doWaitTillFullyLoaded(TimeOuts.TEN_SECONDS);
        this.element.findElement(By.xpath("./div[2]/ul/li[contains(text(),'" + value + "')]")).click();
        while (isFullyLoaded()) {
            SeleniumUtils.click(getDriver().findElement(By.xpath("//body")), driver);
        }
        return this;
    }//*[@id="EventStatusList"]/div[2]/ul/li

    @Override
    public InputElement clearValue() {
        throw new AutomationException("method not implemented for AutoSuggest");
    }

    @Override
    public InputElement selectOption(String option) {
        throw new AutomationException("method not implemented for AutoSuggest");
    }

    @Override
    public InputElement unSelectOption() {
        throw new AutomationException("method not implemented for AutoSuggest");
    }


    public MultipleAutoComplete clearValue(String value) {
        this.element.findElement(By.xpath("./div[2]/ul/li/span[contains(text(),'" + value + "')]/span/i")).click();
        return this;
    }

    public MultipleAutoComplete clearValue(List<String> values) {
        values.stream().forEach(value -> {
            this.element.findElement(By.xpath("./div[2]/ul/li/span[contains(text(),'" + value + "')]/span/i")).click();
        });
        return this;
    }

    public List<String> getSelectedValues() {
        return this.element.findElements(By.xpath("./div[2]/ul/li/")).stream().map(li -> {
            return li.findElement(By.xpath("./span")).getText();
        }).collect(Collectors.toList());
    }

    public boolean isValueSelected(String value) {
        return this.getSelectedValues().stream().filter(val -> val.equals(value)).collect(Collectors.toList()).size() > 0;
    }

}
