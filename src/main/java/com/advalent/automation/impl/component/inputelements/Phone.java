package com.advalent.automation.impl.component.inputelements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Phone extends InputElement {


    public final String PHONE_XPATH;
    public final String EXT_XPATH;


    public Phone(WebDriver driver, String phoneXpath, String extXpath) {
        super(driver, phoneXpath);
        this.PHONE_XPATH = phoneXpath;
        this.EXT_XPATH = extXpath;

    }

    public Phone enterNumber(String phoneNo, String ext) {
        this.clearValue();
        this.element = getElement(PHONE_XPATH);
        this.enterValue(phoneNo);
        this.element = getElement(EXT_XPATH);
        this.enterValue(phoneNo);
        return this;
    }


    public String getValue() {
        this.element = getElement(PHONE_XPATH);
        String ph = this.getValue();
        this.element = getElement(EXT_XPATH);
        String phExt = this.getValue();
        return phExt + "-" + ph;
    }

    public Phone clearValue() {
        this.element = getElement(PHONE_XPATH);
        this.clearValue();
        this.element = getElement(EXT_XPATH);
        this.clearValue();
        return this;
    }

    private WebElement getElement(String locator) {
        return getDriver().findElement(By.xpath(locator));
    }

    @Override
    public boolean isFullyLoaded() {
        return this.element.isDisplayed();
    }
}
