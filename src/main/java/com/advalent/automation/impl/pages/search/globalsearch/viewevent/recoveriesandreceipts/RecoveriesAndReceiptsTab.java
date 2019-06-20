package com.advalent.automation.impl.pages.search.globalsearch.viewevent.recoveriesandreceipts;

import com.advalent.automation.api.components.tab.ITab;
import com.advalent.automation.impl.pages.common.AbstractWebComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RecoveriesAndReceiptsTab extends AbstractWebComponent implements ITab {

    @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div/form/div[2]/div[1]/div/div/div/div[8]/div/div/div[1]/h4")
    WebElement pageTitle;

    public RecoveriesAndReceiptsTab(WebDriver driver) {
        super(driver);
    }

    @Override
    public String getTabName() {
        return "Recoveries & Receipts";
    }
    public  static String getTabNamea() {
        return "Recoveries & Receipts";
    }
    @Override
    public String getTabTitle() {
        return pageTitle.getText();
    }

    @Override
    public boolean isFullyLoaded() {
        return pageTitle.isDisplayed();
    }
}
