package com.advalent.automation.impl.pages.search.globalsearch.viewevent.claimsreview;

import com.advalent.automation.api.annotations.LogStep;
import com.advalent.automation.api.annotations.inputfield.CustomElement;
import com.advalent.automation.api.components.tab.ITab;
import com.advalent.automation.api.components.tab.ITabPanel;
import com.advalent.automation.impl.component.inputelements.DateRange;
import com.advalent.automation.impl.component.inputelements.TextBox;
import com.advalent.automation.impl.component.notificationpanel.NotificationPanel;
import com.advalent.automation.impl.pages.common.AbstractWebComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class ClaimsReviewTab extends AbstractWebComponent implements ITab, ITabPanel {

    public static final String INCLUDED_CLAIMS_TAB_XPATH = "//*[@id=\"content\"]/div[3]/div/form/div[2]/div[1]/div/div/div/div[4]/div/div/div/div[2]/claims-review/form/div[2]/div/ul/li[1]/a/tab-heading/span";
    public static final String PATIENT_HISTORY_TAB_XPATH = "//*[@id=\"content\"]/div[3]/div/form/div[2]/div[1]/div/div/div/div[4]/div/div/div/div[2]/claims-review/form/div[2]/div/ul/li[2]/a/tab-heading/span";
    public static final String INC_SERVICE_DATE_FROM = "//*[@id=\"content\"]/div[3]/div/form/div[2]/div[1]/div/div/div/div[4]/div/div/div/div[2]/claims-review/form/div[1]/div[2]/input[1]";
    public static final String TO = "//*[@id=\"content\"]/div[3]/div/form/div[2]/div[1]/div/div/div/div[4]/div/div/div/div[2]/claims-review/form/div[1]/div[2]/input[1]";
    public static final String LAST_CLAIM_LOAD = "//*[@id=\"content\"]/div[3]/div/form/div[2]/div[1]/div/div/div/div[4]/div/div/div/div[2]/claims-review/form/div[1]/div[1]/input";
    @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div/form/div[2]/div[1]/div/div/div/div[4]/div/div/div/h4")
    private WebElement tabTitle;
    @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div/form/div[2]/div[1]/div/div/div/div[4]/div/div/div/div[2]/claims-review/form/h4/span/span")
    private WebElement reviewCompleteCheckBox;


    @CustomElement(xpaths = {INC_SERVICE_DATE_FROM, TO})
    private DateRange includedClaimsDateRange;

    @CustomElement(xpath = LAST_CLAIM_LOAD)
    private TextBox lastClientClaimLoad;


    public ClaimsReviewTab(WebDriver driver) {
        super(driver);
    }

    @Override
    public String getTabName() {
        return "Claims Review";
    }

    @Override
    public String getTabTitle() {
        return tabTitle.getText();
    }

    @Override
    public Map<Class, WebElement> getAvailableTabsAndTabsPillMap() {
        Map<Class, WebElement> map = new HashMap<Class, WebElement>();
        map.put(IncludedClaimsTab.class, driver.findElement(By.xpath(INCLUDED_CLAIMS_TAB_XPATH)));
        map.put(PatientHistoryTab.class, driver.findElement(By.xpath(PATIENT_HISTORY_TAB_XPATH)));
        return map;
    }

    @Override
    public <T extends ITab> ITab getDefaultTab() {
        return new IncludedClaimsTab(driver);
    }

    @Override
    public boolean isFullyLoaded() {
        return tabTitle.isDisplayed();
    }

    public WebElement getReviewCompleteCheckBox() {
        return reviewCompleteCheckBox;
    }

    @Override
    public int getNumberOfAvailableTab() {
        List<WebElement> availableTabPanels = driver.findElements(By.xpath("//ul[contains(@class, 'nav nav-tabs')]"));
        return availableTabPanels.stream().filter(tabPanel -> !tabPanel.getAttribute("class").contains("nav-stacked"))
                .findFirst().get()
                .findElements(By.xpath("./li")).size();
    }


    public void setReviewCompleteCheckBox(WebElement reviewCompleteCheckBox) {
        this.reviewCompleteCheckBox = reviewCompleteCheckBox;
    }

    public DateRange getIncludedClaimsDateRange() {
        return includedClaimsDateRange;
    }

    public void setIncludedClaimsDateRange(DateRange includedClaimsDateRange) {
        this.includedClaimsDateRange = includedClaimsDateRange;
    }

    public TextBox getLastClientClaimLoad() {
        return lastClientClaimLoad;
    }

    public void setLastClientClaimLoad(TextBox lastClientClaimLoad) {
        this.lastClientClaimLoad = lastClientClaimLoad;
    }
}
