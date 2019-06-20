package com.advalent.automation.test.subropoint.search.globalsearch.viewevent.discovery;

import com.advalent.automation.api.constants.TimeOuts;
import com.advalent.automation.impl.component.notificationpanel.NotificationPanel;
import com.advalent.automation.impl.pages.search.globalsearch.EventIncidentSearchTab;
import com.advalent.automation.impl.pages.search.globalsearch.GlobalSearchPage;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.ViewEventPage;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.discovery.DiscoveryEventPage;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.discovery.DiscoveryInvestigationTab;
import com.advalent.automation.reporting.ExtentHTMLReportManager;
import com.advalent.automation.selenium.SeleniumUtils;
import com.advalent.automation.test.base.BaseTest;
import com.advalent.automation.test.utils.testdata.DataFile;
import com.advalent.automation.test.utils.testdata.TestDataReader;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.fest.assertions.Assertions.assertThat;

@Test(groups = {"Glabal Search", "Discovery View Event Page"}, description = "Discovery View Event Page Test")
public class DiscoveryEventPageTest extends BaseTest {
    EventIncidentSearchTab eventIncidentSearchTab;
    ViewEventPage viewEventPage;
    DiscoveryEventPage discoveryEventPage;
    DiscoveryInvestigationTab discoveryInvestigationTab;


    @BeforeClass
    public void navigateToDiscoveryEventPage() {
        super.setUp();
        this.inputMap = TestDataReader.readSection(DataFile.DISCOVERY, "Discovery Investigation");
        GlobalSearchPage globalSearchPage = navigationBar.navigateTo(GlobalSearchPage.class, TimeOuts.THREE_SECONDS);
        eventIncidentSearchTab = (EventIncidentSearchTab) globalSearchPage.getDefaultTab();
        viewEventPage = eventIncidentSearchTab.goToViewEventPageForEventStatus("Open");
        viewEventPage.doWaitTillFullyLoaded(TimeOuts.SIXTY_SECONDS);
        discoveryEventPage = viewEventPage.clickOnSwitchToDiscoveryViewBtn();
    }

    @Test(description = "Test That Discovery Investigation Page Is Loaded", priority = 1)
    public void testThatDiscoveryInvestigationPageIsLoaded() {
        String expectedPageTitle = discoveryEventPage.getPageTitle();
        String actualPageTitle = discoveryEventPage.getPageTitle();
        assertThat(expectedPageTitle).isEqualTo(actualPageTitle).as("Page Title should be as expected");
        ExtentHTMLReportManager.getInstance().addStep("Expected Page Is Loaded", actualPageTitle);
    }

    @Test(description = "Test That Discovery Investigation Tab Is Loaded By Default", priority = 2)
    public void testThatDiscoveryInvestigationIsLoadedByDefault() {
        discoveryInvestigationTab = (DiscoveryInvestigationTab) discoveryEventPage.getDefaultTab();
        String expectedTabTitle = discoveryInvestigationTab.getTabName();
        String actualTabTitle = discoveryEventPage.getActiveTab();
        ExtentHTMLReportManager.getInstance().addStep("Expected Tab Loaded By Default", expectedTabTitle);
        ExtentHTMLReportManager.getInstance().addStep("Actual Tab Loaded By Default", actualTabTitle);
        assertThat(expectedTabTitle).isEqualTo(actualTabTitle).as("Expected" + expectedTabTitle + " is not loaded");
    }

    @Test(description = "Test That Event/Case Discovery Investigation Field Are Available And Can Be Updated", priority = 3)
    public void testThatEventCaseDiscoveryInvestigationFieldAreAvailableAndInteractive() {
        NotificationPanel notificationPanel;
        discoveryEventPage.eventType.selectOption("ASSAULT");
//        discoveryEventPage.lossDate.clearValue().enterValue("01-02-2018");
        discoveryEventPage.investigationSource.selectOption((String) inputMap.get("investigationSource"));
        discoveryEventPage.lossDetails.enterValue((String) inputMap.get("lossDetails"));
        discoveryEventPage.injuryDescription.enterValue((String) inputMap.get("injuryDescription"));
        discoveryInvestigationTab.selectCallerType((String) inputMap.get("callerType"))
                .enterCallerName((String) inputMap.get("callerName"))
                .enterCallerNumber((String) inputMap.get("callerNumber"));

        if (discoveryEventPage.isSaveBtnEnabled()) {
            notificationPanel = discoveryEventPage.clickOnSaveBtn();
            String message = notificationPanel.getDisplayedMessage();
            notificationPanel.waitTillDisappears();
            ExtentHTMLReportManager.getInstance().addStep("Expected Notification Message", NotificationPanel.UPDATE_SUCCESS_MSG);
            ExtentHTMLReportManager.getInstance().addStep("Actual Notification Message", message);
            assertThat(message).isEqualTo(NotificationPanel.UPDATE_SUCCESS_MSG).as("Update Success Message Should Be Displayed");
        } else {
            throw new SkipException("Save Button Is Not Enabled ");
        }
    }

    @Test(description = "Test That Clicking on Switch to Event/Case View Opens View Event Page", priority = 8)
    public void testClickingOnSwitchToEventCaseViewOpensViewEventPage() {
        SeleniumUtils.scrollPage(getWebDriver(), -1000);
        viewEventPage = discoveryEventPage.clickOnSwitchToEventCaseViewBtn();
        viewEventPage.doWaitTillFullyLoaded(TimeOuts.SIXTY_SECONDS);
        String actualTitle = viewEventPage.getPageTitle();
        String expectedTitle = viewEventPage.getPageTitle();
        assertThat(actualTitle).contains(expectedTitle).as("Expected and Actual Title should Be Same");
    }
}
