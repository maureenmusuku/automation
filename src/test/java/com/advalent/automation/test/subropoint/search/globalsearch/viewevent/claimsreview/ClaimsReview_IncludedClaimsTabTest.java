package com.advalent.automation.test.subropoint.search.globalsearch.viewevent.claimsreview;

import com.advalent.automation.api.constants.TimeOuts;
import com.advalent.automation.impl.pages.search.globalsearch.EventIncidentSearchTab;
import com.advalent.automation.impl.pages.search.globalsearch.GlobalSearchPage;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.ViewEventPage;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.claimsreview.ClaimsDataGrid;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.claimsreview.ClaimsReviewTab;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.claimsreview.IncludedClaimsTab;
import com.advalent.automation.reporting.ExtentHTMLReportManager;
import com.advalent.automation.test.base.BaseTest;
import com.advalent.automation.test.utils.testdata.DataFile;
import com.advalent.automation.test.utils.testdata.TestDataReader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.fest.assertions.Assertions.assertThat;


@Test(groups = {"Global Search", "View Event", "Claims Review Tab"}, description = "Claims Review Included Claims Tab Test")
public class ClaimsReview_IncludedClaimsTabTest extends BaseTest {

    ViewEventPage viewEventPage;
    ClaimsReviewTab claimsReviewTab;
    private IncludedClaimsTab includedClaimsTab;
    ClaimsDataGrid includedClaimsTabDataGrid;

    @BeforeClass 
    public void navigateToViewEventPage() {
        super.setUp();
        inputMap = TestDataReader.readSection(DataFile.CLAIMS_DETAILS_TAB, "Info");
        GlobalSearchPage globalSearchPage = navigationBar.navigateTo(GlobalSearchPage.class, TimeOuts.TEN_SECONDS);
        viewEventPage = ((EventIncidentSearchTab) globalSearchPage.getDefaultTab()).goToViewEventPageForEventId(Integer.parseInt((String) inputMap.get("eventId")));
        viewEventPage.doWaitTillFullyLoaded(TimeOuts.TEN_SECONDS);
        claimsReviewTab = (ClaimsReviewTab) viewEventPage.clickOnTab(ClaimsReviewTab.class);
        claimsReviewTab.doWaitTillFullyLoaded(TimeOuts.SIXTY_SECONDS);
        includedClaimsTab = (IncludedClaimsTab) claimsReviewTab.getDefaultTab();
    }


    @Test(description = "Test That Injury Description Textbox Is Disabled By Default", priority = 1)
    public void test2() {
        boolean injuryDescEnabled = includedClaimsTab.getInjuryDescription().isEnabled();
        ExtentHTMLReportManager.getInstance().addStep("Injury Description Textbox is " + (injuryDescEnabled ? "" : " Not") + " Enabled");
        assertThat(injuryDescEnabled).isFalse();
    }

    @Test(description = "Test That Included Claims Count Is Equal To The No Of Rows Displayed In Data Grid  ", priority = 2)
    public void test3() {
        includedClaimsTabDataGrid = includedClaimsTab.getDataGrid();
        int includedClaimsCount = includedClaimsTabDataGrid.getClaimsCount();
        int noOfRows = includedClaimsTabDataGrid.getRowCount();
        ExtentHTMLReportManager.getInstance().addStep("Included Claims Count", "" + includedClaimsCount);
        ExtentHTMLReportManager.getInstance().addStep("No Of Rows In DataGrid", "" + noOfRows);
        assertThat(includedClaimsCount).isEqualTo(noOfRows);
    }

    @Test(description = "Test THE Columns  Displayed In Included Claims Data Grid  ", priority = 2)
    public void test31() {

        List<String> expectedColumnName = (List<String>) inputMap.get("columns");
        System.out.println("expectedColumnName = " + expectedColumnName);
        List<String> actualColumnName = includedClaimsTabDataGrid.getColumnMap().values().stream().map(col -> col.getColumnName()).collect(Collectors.toList());
        assertThat(expectedColumnName).excludes(actualColumnName);
    }


    @Test(description = "Test That Total Billed Amount Is Equal To The Sum Of Billed Amount Of Each Included Claims ", priority = 2)
    public void test4() {
        int totalBilledAmount = 0;
        for (int i = 1; i <= includedClaimsTabDataGrid.getRowCount(); i++) {
            totalBilledAmount = totalBilledAmount + includedClaimsTabDataGrid.getBilledAmount(i);
        }
        int displayedTotalBilled = includedClaimsTabDataGrid.getTotalBilledAmount();
        ExtentHTMLReportManager.getInstance().addStep("Sum Of Billed Amount", "" + totalBilledAmount);
        ExtentHTMLReportManager.getInstance().addStep("Total Billed Amount Displayed", "" + displayedTotalBilled);
        assertThat(displayedTotalBilled).isEqualTo(totalBilledAmount);
    }

    @Test(description = "Test That Total Paid Amount Is Equal To The Sum Of Paid Amount Of Each Included Claims ", priority = 2)
    public void test5() {
        int totalPaidAmount = 0;
        for (int i = 1; i <= includedClaimsTabDataGrid.getRowCount(); i++) {
            totalPaidAmount = totalPaidAmount + includedClaimsTabDataGrid.getPaidAmount(i);
        }
        int displayedTotalPaid = includedClaimsTabDataGrid.getTotalBilledAmount();
        ExtentHTMLReportManager.getInstance().addStep("Sum Of Paid Amount", "" + totalPaidAmount);
        ExtentHTMLReportManager.getInstance().addStep("Total Paid Amount Displayed", "" + displayedTotalPaid);
        assertThat(displayedTotalPaid).isEqualTo(totalPaidAmount);
    }
}
