package com.advalent.automation.impl.component.loadingcomponent;

import org.openqa.selenium.WebDriver;

public class NavBarLoadingComponent extends AbstractLoadingComponent {
    public NavBarLoadingComponent(WebDriver driver) {
        super(driver,"//*[@id=\"routeLoad\"]" );
    }

    public NavBarLoadingComponent(WebDriver driver, int timeout) {
        super(driver, "//*[@id=\"routeLoad\"]", timeout);
    }


}
