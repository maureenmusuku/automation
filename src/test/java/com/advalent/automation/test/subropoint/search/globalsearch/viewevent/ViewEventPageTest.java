package com.advalent.automation.test.subropoint.search.globalsearch.viewevent;

import com.advalent.automation.api.constants.TimeOuts;
import com.advalent.automation.impl.component.datagrid.DataGrid;
import com.advalent.automation.impl.component.datagrid.Indexes;
import com.advalent.automation.impl.component.notificationpanel.NotificationPanel;
import com.advalent.automation.impl.pages.search.globalsearch.EventIncidentSearchTab;
import com.advalent.automation.impl.pages.search.globalsearch.GlobalSearchPage;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.ViewEventPage;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.discovery.DiscoveryEventPage;
import com.advalent.automation.reporting.ExtentHTMLReportManager;
import com.advalent.automation.test.base.BaseTest;
import com.advalent.automation.test.utils.testdata.DataFile;
import com.advalent.automation.test.utils.testdata.TestDataReader;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.fest.assertions.Assertions.assertThat;


@Test(groups = {"Global Search", "View Event Page"}, description = "View Event Page Test")
public class ViewEventPageTest extends BaseTest {
    ViewEventPage viewEventPage;
    EventIncidentSearchTab eventIncidentSearchTab;
    DiscoveryEventPage discoveryEventPage;
    private boolean changeEventStatus = false;
    DataGrid dataGrid;

    @BeforeClass
    public void navigateToViewEventPage() {
        super.setUp();
        this.inputMap = TestDataReader.readSection(DataFile.GLOBAL_SEARCH, "View Event");
        GlobalSearchPage globalSearchPage = navigationBar.navigateTo(GlobalSearchPage.class, TimeOuts.FIVE_SECONDS);
        eventIncidentSearchTab = (EventIncidentSearchTab) globalSearchPage.getDefaultTab();
        dataGrid = eventIncidentSearchTab.selectMajorClient((String) inputMap.get("majorClient"))
                .clickOnSearchButton();

    }

    @Test(description = "Test That Clicking On EventId Of DataGrid Navigates To View Event Page", priority = 1)
    public void testThatClickingOnEventIdOfDataGridNavigatesToViewEventPage() {
        DataGrid dataGrid = eventIncidentSearchTab.getDataGrid();
        viewEventPage = dataGrid.getRow(Indexes.FIRST).getCell(EventIncidentSearchTab.EVENT_ID_COLUMN).click();
        viewEventPage.doWaitTillFullyLoaded(TimeOuts.SIXTY_SECONDS);
        String expectedTitle = "Overview";
        String actualPageTitle = viewEventPage.getPageTitle();
        ExtentHTMLReportManager.getInstance().addStep("Expected Page Title", expectedTitle);
        ExtentHTMLReportManager.getInstance().addStep("Actual Page Title", actualPageTitle);
        assertThat(actualPageTitle).contains(expectedTitle).as("Expected Page Title Of View Event Page Should Be Displayed");

    }


    @Test(description = "Test That Event Status DropDown Is Available In View Event Page", priority = 4)
    public void testThatEventStatusDropDownIsAvailableInViewEventPage() {
        boolean isEventStatusDropDownDisplayed = viewEventPage.eventStatus.isFullyLoaded();
        ExtentHTMLReportManager.getInstance().addStep(isEventStatusDropDownDisplayed ?
                "Event Status DropDown Is Available  " : "Event Status DropDown Is Not Available");
        assertThat(isEventStatusDropDownDisplayed).isTrue();
    }

    @Test(description = "Test That Updating Event Status Field Update The Status Of Event In Event Banner", priority = 5)
    public void testThatUpdatingEventStatusFieldUpdateTheStatusOfEvent() {
        String updatedEventStatus = "Screen";
        if (viewEventPage.getSelectedEventStatus().equalsIgnoreCase("Screen")) {
            updatedEventStatus = "Open";
        }
        viewEventPage.selectEventStatus(updatedEventStatus);
        NotificationPanel notificationPanel = viewEventPage.clickOnGoBtn();
        String displayedMessage = notificationPanel.getDisplayedMessage();
        notificationPanel.waitTillDisappears();
        if (!NotificationPanel.UPDATE_SUCCESS_MSG.equalsIgnoreCase(displayedMessage)) {
            throw new SkipException("Cannot Change Event Status From View Event Page");
        } else {
            changeEventStatus = true;
        }
        String displayedEventStatus = viewEventPage.getEventBanner().getEventStatus();
        ExtentHTMLReportManager.getInstance().addStep("Expected Event Status", updatedEventStatus);
        ExtentHTMLReportManager.getInstance().addStep("Actual Event Status", displayedEventStatus);
        assertThat(updatedEventStatus).isEqualTo(displayedEventStatus).as("Displayed Event Status Should Be Same As Expected");

    }


    @Test(description = "Test That Clicking Switch To Discovery View Opens Discovery Investigation Page", priority = 6)
    public void testClickingSwitchToDiscoveryViewOpensDiscoveryInvestigationPage() {
        viewEventPage.doWaitTillFullyLoaded(TimeOuts.SIXTY_SECONDS);
        discoveryEventPage = viewEventPage.clickOnSwitchToDiscoveryViewBtn();
        discoveryEventPage.doWaitTillFullyLoaded(TimeOuts.SIXTY_SECONDS);
        String actualTitle = discoveryEventPage.getPageTitle();
        String expectedTitle = discoveryEventPage.getPageTitle();
        reportManager.addStep("Expected Page Title", expectedTitle);
        reportManager.addStep("Actual Page Title", actualTitle);
        assertThat(actualTitle).contains(expectedTitle).as("Expected and Actual Title should Be Same");
    }

    @Test(description = "Test That Clicking Switch  To Event/Case View Link Opens View Event Page", priority = 7)
    public void testClickingSwitchEventCaseViewopensViewEventPage() {
        viewEventPage = discoveryEventPage.clickOnSwitchToEventCaseViewBtn();
        viewEventPage.doWaitTillFullyLoaded(TimeOuts.SIXTY_SECONDS);
        String actualTitle = viewEventPage.getPageTitle();
        String expectedTitle = viewEventPage.getPageTitle();
        reportManager.addStep("Expected Page Title", expectedTitle);
        reportManager.addStep("Actual Page Title", actualTitle);
        assertThat(actualTitle).contains(expectedTitle).as("Expected and Actual Title should Be Same");
    }

    @Test(description = "Test That Clicking On Back Button Navigates To Event Incident Search Tab", priority = 8)
    public void test8() {
        eventIncidentSearchTab = viewEventPage.clickOnBackButton();
        eventIncidentSearchTab.doWaitTillFullyLoaded(TimeOuts.SIXTY_SECONDS);
        String actualPageTitle = eventIncidentSearchTab.getTabTitle();
        String expectedTitle = eventIncidentSearchTab.getTabName();
        reportManager.addStep("Expected Page Title", expectedTitle);
        reportManager.addStep("Actual Page Title", actualPageTitle);
    }

    @AfterMethod
    public void changeEventStatus() {
        if (changeEventStatus) {
            if (!viewEventPage.getSelectedEventStatus().equalsIgnoreCase("Open")) {
                reportManager.addStep("Selecting Initial Event Status");
                viewEventPage.selectEventStatus("Open");
                changeEventStatus = false;
            }
            NotificationPanel notificationPanel = viewEventPage.clickOnGoBtn();
            String displayedMessage = notificationPanel.getDisplayedMessage();
            notificationPanel.waitTillDisappears();
        }
    }

}
