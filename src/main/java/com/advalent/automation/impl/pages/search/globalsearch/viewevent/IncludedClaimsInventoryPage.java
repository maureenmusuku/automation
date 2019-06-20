package com.advalent.automation.impl.pages.search.globalsearch.viewevent;

import com.advalent.automation.impl.pages.common.AdvalentPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class IncludedClaimsInventoryPage extends AdvalentPage {


    @FindBy(xpath = "//*[@id=\"inclClaimInventory\"]/form/div[1]/div[2]/h1")
    WebElement pageTitle;

    public IncludedClaimsInventoryPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    /**
     * @returns actual title buildDataGridAs the page being displayed
     */
    @Override
    public String getPageTitle() {
        return pageTitle.getText();
    }

    /**
     * Checks if this component is fully loaded. Each web component should
     * provide it's own logic buildDataGridAs being fully loaded. If returned true then this
     * component is ready for any events, interactions & LIVE query.
     *
     * @return
     */
    @Override
    public boolean isFullyLoaded() {
        return pageTitle.isDisplayed();
    }
}
