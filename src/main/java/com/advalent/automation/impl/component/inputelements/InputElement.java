package com.advalent.automation.impl.component.inputelements;

import com.advalent.automation.impl.pages.common.AbstractWebComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.HashMap;
import java.util.Map;

public abstract class InputElement extends AbstractWebComponent {
    private String locator;
    protected WebElement element;
    protected WebDriver driver;
    Map<String, WebElement> nameElementMap;

    protected InputElement(WebDriver driver, String locator) {
        super(driver);
        this.locator = locator;
        this.element = driver.findElement(By.xpath(locator));
        this.driver = driver;
    }

    protected InputElement(WebDriver driver, Map<String, String> nameXpathMap) {
        super(driver);
        nameElementMap = new HashMap<>();
        for (String name : nameXpathMap.keySet()) {
            nameElementMap.put(name, driver.findElement(By.xpath(nameXpathMap.get(name))));
        }
        this.driver = driver;
    }


    public String getLocator() {
        return locator;
    }

    public InputElement enterValue(String value) {
        element.sendKeys(value);
        return this;
    }


    public InputElement clearValue() {
        element.clear();
        return this;
    }

    public String getValue() {
        return element.getAttribute("value");
    }


    public InputElement selectOption(String option) {
        Select dropdown = null;
        try {
            this.element = this.driver.findElement(By.xpath(this.locator));
            dropdown = new Select(this.element);
        } catch (StaleElementReferenceException ex) {
            this.element = this.driver.findElement(By.xpath(this.locator));
            selectOption(option);
        }
        dropdown.selectByVisibleText(option);
        return this;
    }


    public InputElement unSelectOption() {
        Select dropdown = new Select(this.element);
        dropdown.deselectAll();
        return this;
    }

    public boolean isEnabled() {
        return this.element.isEnabled();
    }

    protected void refreshElement() {
    }

}
