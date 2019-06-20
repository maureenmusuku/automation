package com.advalent.automation.impl.pages.search.globalsearch;

import com.advalent.automation.api.annotations.LogStep;
import com.advalent.automation.api.components.tab.ITab;
import com.advalent.automation.api.components.tab.ITabPanel;
import com.advalent.automation.api.constants.TimeOuts;
import com.advalent.automation.api.features.IAmLandingPage;
import com.advalent.automation.impl.pages.common.AdvalentPage;
import com.advalent.automation.impl.pages.search.globalsearch.newincidentreport.AddIncidentReportPage;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.ViewEventPage;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.externalpartytab.ExternalPartyTab;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.Map;

public class GlobalSearchPage extends AdvalentPage implements ITabPanel, IAmLandingPage {
    @FindBy(xpath = "//*[@id='content']/div[3]/div/div/h1")
    private WebElement pageTitle;
    @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div/form/div/div/ul/li[1]/a/tab-heading/span")
    private WebElement eventIncidentSearchTabPill;
    @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div/form/div/div/ul/li[2]/a/tab-heading/span")
    private WebElement memberSearchTabPill;
    @FindBy(id = "addNewIncident")
    private WebElement addNewIncidentBtn;


    EventIncidentSearchTab eventIncidentSearchTab;
    private ViewEventPage viewEventPage;

    public GlobalSearchPage(WebDriver driver) {
        super(driver);
    }

    @LogStep(step = "Getting Default Tab In Global Search Page")
    public ITab getDefaultTab() {
        eventIncidentSearchTab = new EventIncidentSearchTab(getDriver());
        eventIncidentSearchTab.doWaitTillFullyLoaded(TimeOuts.THIRTY_SECONDS);
        return eventIncidentSearchTab;
    }

    @Override
    public String getPageName() {
        return "Global Search";
    }

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
        availableTabsAndTabsPillXpath.put(EventIncidentSearchTab.class, eventIncidentSearchTabPill);
        availableTabsAndTabsPillXpath.put(MemberSearchTab.class, memberSearchTabPill);
        return availableTabsAndTabsPillXpath;
    }

    @Override
    public String getPageUrl() {
        return "/#/members/globalsearch";
    }

    public ExternalPartyTab goToExternalPartyTabForEventWithStatus(String status) {
        this.eventIncidentSearchTab = (EventIncidentSearchTab) getDefaultTab();
        this.viewEventPage = eventIncidentSearchTab.goToViewEventPageForEventStatus(status);
        return (ExternalPartyTab) viewEventPage.clickOnTab(ExternalPartyTab.class);
    }

    public ViewEventPage goToViewEventPageForEventWithStatus(String status) {
        this.eventIncidentSearchTab = (EventIncidentSearchTab) getDefaultTab();
        this.viewEventPage = eventIncidentSearchTab.goToViewEventPageForEventStatus(status);
        return this.viewEventPage;
    }

    public ViewEventPage goToViewEventPageForEventWithMajorClient(String majorClientName) {
        this.eventIncidentSearchTab = (EventIncidentSearchTab) getDefaultTab();
        this.viewEventPage = eventIncidentSearchTab.goToViewEventPageForMajorClient(majorClientName);
        return this.viewEventPage;
    }

    public ViewEventPage goToViewEventPageForEventId(int eventId) {
        this.eventIncidentSearchTab = (EventIncidentSearchTab) getDefaultTab();
        this.viewEventPage = eventIncidentSearchTab.goToViewEventPageForEventId(eventId);
        return this.viewEventPage;
    }

    @LogStep(step = "Clicking On Add New Incident Button")
    public AddIncidentReportPage clickOnAddNewIncidentReportButton() {
        addNewIncidentBtn.click();
        return new AddIncidentReportPage(driver);
    }
}
