package com.advalent.automation.impl.component.webelement;

import org.openqa.selenium.WebDriver;

public class WebElements {
    protected WebDriver driver;

    protected WebElements(WebDriver driver) {
        this.driver = driver;
    }

    public final WebDriver getDriver() {
        return this.driver;
    }

}
