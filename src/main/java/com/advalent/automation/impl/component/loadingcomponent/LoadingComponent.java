package com.advalent.automation.impl.component.loadingcomponent;

import org.openqa.selenium.WebDriver;

public class LoadingComponent extends AbstractLoadingComponent {
    public LoadingComponent(WebDriver driver, String compId) {
        super(driver, compId);
    }

    public LoadingComponent(WebDriver driver, String compId, int timeout) {
        super(driver, compId, timeout);
    }



    public void waitTillAppear() {

    }
}
