package com.advalent.automation.test.subropoint.search.globalsearch.viewevent.claimsreview;

import com.advalent.automation.api.constants.TimeOuts;
import com.advalent.automation.impl.pages.search.globalsearch.EventIncidentSearchTab;
import com.advalent.automation.impl.pages.search.globalsearch.GlobalSearchPage;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.ViewEventPage;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.claimsreview.*;
import com.advalent.automation.reporting.ExtentHTMLReportManager;
import com.advalent.automation.test.base.BaseTest;
import com.advalent.automation.test.utils.testdata.DataFile;
import com.advalent.automation.test.utils.testdata.TestDataReader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;


@Test(groups = {"Global Search", "View Event", "Claims Review Tab"}, description = "Claims Review Tab Test")
public class ClaimsReviewTabTest extends BaseTest {

    public static final int CLAIM_ID_COL_INDEX = 8;
    public static final int FIRST_ROW = 1;
    public static final int FIRST_INDEX = 0;
    public static final int DESCRIPTION_COL = 1;
    public static final int PRIMERY_DIAG_COL = 3;
    ViewEventPage viewEventPage;
    ClaimsReviewTab claimsReviewTab;
    private IncludedClaimsTab includedClaimsTab;
    private List<String> claimsDetailsinDataGrid;

    @BeforeClass
    public void navigateToViewEventPage() {
        super.setUp();
        inputMap = TestDataReader.readSection(DataFile.CLAIMS_DETAILS_TAB, "Info");
        GlobalSearchPage globalSearchPage = navigationBar.navigateTo(GlobalSearchPage.class, TimeOuts.TEN_SECONDS);
        viewEventPage = ((EventIncidentSearchTab) globalSearchPage.getDefaultTab()).goToViewEventPageForEventId(Integer.parseInt((String) inputMap.get("eventId")));
        viewEventPage.doWaitTillFullyLoaded(TimeOuts.SIXTY_SECONDS);
    }

    @Test(description = "Test That Clicking On Claims Review Link Displays Claims Review Tab", priority = 1)
    public void testThatClickingOnClaimsReviewLinkDisplaysClaimsReviewTab() {
        claimsReviewTab = (ClaimsReviewTab) viewEventPage.clickOnTab(ClaimsReviewTab.class);
        claimsReviewTab.doWaitTillFullyLoaded(TimeOuts.SIXTY_SECONDS);
        String actualTitle = claimsReviewTab.getTabTitle();
        String expectedTitle = claimsReviewTab.getTabName();
        ExtentHTMLReportManager.getInstance().addStep("Expected Title", expectedTitle);
        ExtentHTMLReportManager.getInstance().addStep("Actual Title", actualTitle);
        assertThat(actualTitle).isEqualTo(expectedTitle);
    }

    @Test(description = "Test That Date Range Elements Are Disabled By Default", priority = 2)
    public void test11() {

        boolean isDateRangeEnabled = claimsReviewTab.getIncludedClaimsDateRange().isEnabled();
        ExtentHTMLReportManager.getInstance().addStep("Included Service Date Range Is " + (isDateRangeEnabled ? "" : " Not ") + "Enabled");
        assertThat(isDateRangeEnabled).isFalse();
    }

    @Test(description = "Test That Last Client Claims Load Is Disabled By Default", priority = 2)
    public void test12() {
        boolean isLastClaimsLoadTextboxEnabled = claimsReviewTab.getLastClientClaimLoad().isEnabled();
        ExtentHTMLReportManager.getInstance().addStep("Last Client Claims Load Is " + (isLastClaimsLoadTextboxEnabled ? "" : " Not ") + "Enabled");
        assertThat(isLastClaimsLoadTextboxEnabled).isFalse();
    }

    @Test(description = "Test That Review Complete CheckBox Is Available", priority = 2)
    public void test1() {
        boolean isCheckBoxAvailable = claimsReviewTab.getReviewCompleteCheckBox().isDisplayed();
        ExtentHTMLReportManager.getInstance().addStep(isCheckBoxAvailable ? "Check box not available" : "Check box is available");
        assertThat(isCheckBoxAvailable).isTrue();
    }

    @Test(description = "Test That Included Claims Tab Is Displayed By Default", priority = 2)
    public void test2() {
        includedClaimsTab = (IncludedClaimsTab) claimsReviewTab.getDefaultTab();
        boolean isActive = claimsReviewTab.isTabActive();
        ExtentHTMLReportManager.getInstance().addStep(claimsReviewTab.getTabName() + " Is " + (isActive ? "" : "Not") + "Active");
        assertThat(isActive).isTrue();
    }


    @Test(description = "Test That Two Tabs Are Available In Claims Review Tab", priority = 2)
    public void test3() {
        int availableTabs = claimsReviewTab.getNumberOfAvailableTab();
        ExtentHTMLReportManager.getInstance().addStep("Number Of Available Tabs", String.valueOf(availableTabs));
        assertThat(availableTabs).isEqualTo(2);
    }
}
