package com.advalent.automation.test.subropoint.search.globalsearch.viewevent.activitylog;

import com.advalent.automation.api.constants.TimeOuts;
import com.advalent.automation.impl.component.datagrid.DataGrid;
import com.advalent.automation.impl.pages.search.globalsearch.GlobalSearchPage;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.ViewEventPage;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.activitylog.ActivityLogTab;
import com.advalent.automation.impl.utils.DateUtils;
import com.advalent.automation.test.base.BaseTest;
import com.advalent.automation.test.utils.testdata.DataFile;
import com.advalent.automation.test.utils.testdata.TestDataReader;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.fest.assertions.Assertions.assertThat;


@Test(groups = {"View Event", "Activity Log", "Search"}, description = "Activity Log Tab Search Test")
public class ActivityLogSearchTest extends BaseTest {


    ViewEventPage viewEventPage;
    ActivityLogTab activityLogTab;
    int initialRecordInDataGrid;
    String initialDataInDataGrid;

    @BeforeClass
    public void navigateToActivityLog() {
        inputMap = TestDataReader.readSection(DataFile.ACTIVITY_LOG, "Search Activity");
        super.setUp();
        GlobalSearchPage globalSearchPage = navigationBar.navigateTo(GlobalSearchPage.class, TimeOuts.FIVE_SECONDS);
        viewEventPage = globalSearchPage.goToViewEventPageForEventId(Integer.parseInt((String) inputMap.get("eventId")));
        viewEventPage.doWaitTillFullyLoaded(TimeOuts.TEN_SECONDS);
        activityLogTab = (ActivityLogTab) viewEventPage.clickOnTab(ActivityLogTab.class);
        activityLogTab.waitUntilDataGridIsDisplayed(TimeOuts.SIXTY_SECONDS);
        initialRecordInDataGrid = activityLogTab.getDataGrid().getPaginationComponent().getNumberOfRecordsDisplayed();
        initialDataInDataGrid = activityLogTab.getDataGrid().getDataAsString();

    }

    @Test(description = "TestSearch By Date Functionality")
    public void activityLogTest1() {
        DataGrid dataGrid = activityLogTab
                .enterDateRange(DateUtils.daysBeforeToday(3, "MM-dd-YYYY"), DateUtils.getTodaysDate("MM-dd-YYYY"))
                .clickOnFilterButton();
        activityLogTab.waitUntilDataGridIsDisplayed(TimeOuts.SIXTY_SECONDS);
        int recordsDisplayedAfterSearch = dataGrid.getPaginationComponent().getNumberOfRecordsDisplayed();
        String recordsAfterSearch = dataGrid.getDataAsString();
        assertThat(initialRecordInDataGrid).isNotEqualTo(recordsDisplayedAfterSearch);
        assertThat(initialDataInDataGrid).isNotEqualTo(recordsAfterSearch);


    }


    @Test(description = "TestSearch By Activity Type Functionality")
    public void activityLogTest2() {
        DataGrid dataGrid = activityLogTab.selectActivityType((String) inputMap.get("activityType"))
                .clickOnFilterButton();
        activityLogTab.waitUntilDataGridIsDisplayed(TimeOuts.SIXTY_SECONDS);
        int recordsDisplayedAfterSearch = dataGrid.getPaginationComponent().getNumberOfRecordsDisplayed();
        String recordsAfterSearch = dataGrid.getDataAsString();
        assertThat(initialRecordInDataGrid).isNotEqualTo(recordsDisplayedAfterSearch);
        assertThat(initialDataInDataGrid).isNotEqualTo(recordsAfterSearch);


    }


    @Test(description = "TestSearch By Activity Source Functionality")
    public void activityLogTest3() {
        DataGrid dataGrid = activityLogTab.enterActivitySource(navigationBar.getCurrentUserName())
                .clickOnFilterButton();
        activityLogTab.waitUntilDataGridIsDisplayed(TimeOuts.SIXTY_SECONDS);
        String recordsAfterSearch = dataGrid.getDataAsString();
        assertThat(initialDataInDataGrid).isNotEqualTo(recordsAfterSearch);


    }


    @AfterMethod
    public void activityLogTest4() {
        activityLogTab = activityLogTab.clickOnClearFilterButton();
    }


}
