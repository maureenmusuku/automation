package com.advalent.automation.impl.component.inputelements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DateRange extends InputElement {


    public final String FROM_DATE;
    public final String to;


    public DateRange(WebDriver driver, String fromDateLocator, String toDateLocator) {
        super(driver, fromDateLocator);
        this.FROM_DATE = fromDateLocator;
        this.to = toDateLocator;

    }

    public DateRange from(String fromDate) {
        WebElement from = getElement(FROM_DATE);
        from.clear();
        from.sendKeys(fromDate);
        return this;
    }


    public DateRange to(String toDate) {
        WebElement to = getDriver().findElement(By.xpath(this.to));
        to.clear();
        to.sendKeys(toDate);
        return this;
    }

    public String getFromValue() {
        this.element = getElement(FROM_DATE);
        return this.getValue();
    }

    public String getToValue() {
        this.element = getElement(to);
        return this.getValue();
    }

    public DateRange clearDateValue() {
        this.element = getElement(FROM_DATE);
        this.clearValue();
        this.element = getElement(to);
        this.clearValue();
        return this;
    }

    @Override
    public boolean isEnabled() {
        return this.getElement(FROM_DATE).isEnabled() && this.getElement(FROM_DATE).isEnabled();
    }

    private WebElement getElement(String locator) {
        return getDriver().findElement(By.xpath(locator));
    }

    @Override
    public boolean isFullyLoaded() {
        return this.element.isDisplayed();
    }
}
