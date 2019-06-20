package com.advalent.automation.impl.component.loadingcomponent;

import org.openqa.selenium.WebDriver;

public class DataGridLoadingComponent extends AbstractLoadingComponent {

    public static final String X_PATH = "//*[@id=\"content\"]/div[3]/div/form/div/div/div/div/ng-include/div/div/dloadingicon/div/span/span";

    public DataGridLoadingComponent(WebDriver driver) {
        super(driver, X_PATH);
    }

    public DataGridLoadingComponent(WebDriver driver, String compId, int timeout) {
        super(driver, compId, timeout);
    }
}
