package com.advalent.automation.impl.pages.search.globalsearch.viewevent;

import com.advalent.automation.api.annotations.LogStep;
import com.advalent.automation.api.annotations.inputfield.CustomElement;
import com.advalent.automation.api.components.tab.ITab;
import com.advalent.automation.api.components.tab.ITabPanel;
import com.advalent.automation.impl.component.eventbanner.EventBanner;
import com.advalent.automation.impl.component.inputelements.DropDown;
import com.advalent.automation.impl.component.notificationpanel.NotificationPanel;
import com.advalent.automation.impl.pages.common.AdvalentPage;
import com.advalent.automation.impl.pages.search.globalsearch.EventIncidentSearchTab;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.activitylog.ActivityLogTab;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.casecorrespondence.CaseCorrespondenceTab;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.claimsreview.ClaimsReviewTab;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.discovery.DiscoveryEventPage;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.discovery.DiscoveryInvestigationTab;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.externalpartytab.ExternalPartyTab;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.memberinformation.MemberInformationTab;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.overviewtab.OverviewTab;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.projections.ProjectionsTab;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.recoveriesandreceipts.RecoveriesAndReceiptsTab;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.taskstab.TaskTab;
import com.advalent.automation.selenium.SeleniumUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.Map;

public class ViewEventPage extends AdvalentPage implements ITabPanel {
    @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div/form/div[2]/div[1]/div/div/div/div[1]/div/ng-form/h4")
    private WebElement pageTitle;


    @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div/form/div[2]/div[1]/div/accordion/div/div/div[2]/div/div/span/button")
    private WebElement goBtn;
    @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div/form/div[2]/div[1]/div/div/div/div[1]/div/ng-form/h4/div/i/span")
    private WebElement switchToDiscoveryViewBtn;

    private EventBanner eventBanner;


//    tab pills

    @FindBy(id = "Overview")
    WebElement overViewTabPill;
    @FindBy(id = "Tasks")
    WebElement tasksTabPill;
    @FindBy(id = "Member Information")
    WebElement memberInformationTabPill;
    @FindBy(id = "Claims Review")
    WebElement claimsReviewTabPill;

    @FindBy(id = "External Party")
    WebElement externalPartyTabPill;
    @FindBy(id = "Case Correspondence")
    WebElement caseCorrespondenceTabPill;
    @FindBy(id = "Projections")
    WebElement projectionsTabPill;
    @FindBy(id = "Recoveries & Receipts")
    WebElement recoveriesAndReceiptsTabPill;
    @FindBy(id = "Activity Log")
    WebElement activityLogTabPill;

    @FindBy(id = "back")
    WebElement backBtn;

    public ViewEventPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(getDriver(), this);
        eventBanner = new EventBanner(getDriver());
    }

    @LogStep(step = "Clicking On Go Button")
    public NotificationPanel clickOnGoBtn() {
        goBtn.click();
        return new NotificationPanel(getDriver());
    }

    @LogStep(step = "Clicking On Switch To Discovery View Button")
    public DiscoveryEventPage clickOnSwitchToDiscoveryViewBtn() {
        SeleniumUtils.click(this.switchToDiscoveryViewBtn, driver);
        return new DiscoveryEventPage(getDriver());
    }

    @CustomElement(xpath = "//*[@id=\"EventStatus\"]")
    public DropDown eventStatus;


    @Override
    public String getPageTitle() {
        return pageTitle.getText();
    }

    @Override
    public boolean isFullyLoaded() {
        return pageTitle.isDisplayed();
    }

    @Override
    public Map<Class, WebElement> getAvailableTabsAndTabsPillMap() {
        Map<Class, WebElement> availableTabsAndTabsPillXpath = new HashMap<>();
        availableTabsAndTabsPillXpath.put(OverviewTab.class, overViewTabPill);
        availableTabsAndTabsPillXpath.put(TaskTab.class, tasksTabPill);
        availableTabsAndTabsPillXpath.put(MemberInformationTab.class, memberInformationTabPill);
        availableTabsAndTabsPillXpath.put(ClaimsReviewTab.class, claimsReviewTabPill);
        availableTabsAndTabsPillXpath.put(ExternalPartyTab.class, externalPartyTabPill);
        availableTabsAndTabsPillXpath.put(CaseCorrespondenceTab.class, caseCorrespondenceTabPill);
        availableTabsAndTabsPillXpath.put(ProjectionsTab.class, projectionsTabPill);
        availableTabsAndTabsPillXpath.put(RecoveriesAndReceiptsTab.class, recoveriesAndReceiptsTabPill);
        availableTabsAndTabsPillXpath.put(ActivityLogTab.class, activityLogTabPill);
        return availableTabsAndTabsPillXpath;
    }


    @Override
    public <T extends ITab> ITab getDefaultTab() {
        return new OverviewTab(getDriver());
    }

    public EventBanner getEventBanner() {
        return eventBanner;
    }

    @LogStep(step = "Selecting Event Status")
    public void selectEventStatus(String updatedEventStatus) {
        this.eventStatus.selectOption(updatedEventStatus);
    }

    @LogStep(step = "Getting Selected Event Status")
    public String getSelectedEventStatus() {
        return this.eventStatus.getValue();
    }


    @LogStep(step = "Clicking On Back Button")
    public EventIncidentSearchTab clickOnBackButton() {
        this.backBtn.click();
        return new EventIncidentSearchTab(getDriver());
    }
}
