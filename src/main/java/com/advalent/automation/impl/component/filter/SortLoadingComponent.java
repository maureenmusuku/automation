package com.advalent.automation.impl.component.filter;

import com.advalent.automation.impl.component.loadingcomponent.AbstractLoadingComponent;
import org.openqa.selenium.WebDriver;

/**
 * Created By: sumit
 * Created Date :12/7/2018
 * Note:
 */
public class SortLoadingComponent extends AbstractLoadingComponent {
    public SortLoadingComponent(WebDriver driver) {
        super(driver, "//sortable-column/div/div/span[1]/span");
    }
}
