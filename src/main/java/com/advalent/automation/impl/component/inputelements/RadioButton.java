package com.advalent.automation.impl.component.inputelements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.Map;

public class RadioButton extends InputElement {


    private Map<String, WebElement> radioNameElementMap;

    protected RadioButton(WebDriver driver, Map<String, String> nameXpathMap) {
        super(driver, nameXpathMap);
        radioNameElementMap = new HashMap<>();
        this.element.findElements(By.tagName("label")).stream()
                .forEach(label -> {
                    String radioName = label.getAttribute("for");
                    WebElement radio = this.element.findElement(By.name(radioName));
                    radioNameElementMap.put(radioName, radio);
                });
    }

    public RadioButton select(String name) {
        WebElement webElement = this.radioNameElementMap.get(name);
        if (!webElement.isSelected()) {
            webElement.click();
        }
        return this;

    }

    @Override
    public boolean isFullyLoaded() {
        return this.nameElementMap.values().stream().findFirst().get().isDisplayed();
    }
}
