package com.advalent.automation.test.subropoint.search.globalsearch.viewevent.activitylog;

import com.advalent.automation.api.constants.TimeOuts;
import com.advalent.automation.impl.component.datagrid.Column;
import com.advalent.automation.impl.component.datagrid.DataGrid;
import com.advalent.automation.impl.component.notificationpanel.NotificationPanel;
import com.advalent.automation.impl.pages.search.globalsearch.GlobalSearchPage;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.ViewEventPage;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.activitylog.ActivityDetailsSection;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.activitylog.ActivityLogTab;
import com.advalent.automation.test.base.BaseTest;
import com.advalent.automation.test.utils.testdata.DataFile;
import com.advalent.automation.test.utils.testdata.TestDataReader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Collection;

import static org.fest.assertions.Assertions.assertThat;


@Test(groups = {"View Event", "Activity Log"}, description = "Activity Log Tab Test")
public class ActivityLogTest extends BaseTest {


    ViewEventPage viewEventPage;
    ActivityLogTab activityLogTab;

    @BeforeClass
    public void navigateToActivityLog() {
        inputMap = TestDataReader.readSection(DataFile.ACTIVITY_LOG, "Add New Activity");
        super.setUp();
        GlobalSearchPage globalSearchPage = navigationBar.navigateTo(GlobalSearchPage.class, TimeOuts.FIVE_SECONDS);
        viewEventPage = globalSearchPage.goToViewEventPageForEventId(Integer.parseInt((String) inputMap.get("eventId")));
        viewEventPage.doWaitTillFullyLoaded(TimeOuts.TEN_SECONDS);


    }

    @Test(description = "Test That Clicking On Activity Log Link Navigates To Activity Log Tab")
    public void activityLogTest1() {
        activityLogTab = (ActivityLogTab) viewEventPage.clickOnTab(ActivityLogTab.class);
        String tabTitle = activityLogTab.getTabTitle();
        assertThat(tabTitle).isEqualTo("Activity Log");
    }

    @Test(description = "Test Add Activity Log Functionality")
    public void activityLogTest2() {
        ActivityDetailsSection activityDetailsSection = activityLogTab.clickOnAddNewActivityButton();
        NotificationPanel notificationPanel = activityDetailsSection.enterTitleOrDescription((String) inputMap.get("title"))
                .enterActivityNote((String) inputMap.get("activityNote"))
                .clickOnSaveButton();
        String message = notificationPanel.getDisplayedMessage();
        assertThat(message).isEqualTo(NotificationPanel.CREATE_SUCCESS_MESSAGE);
    }

    @Test(description = "Test THe Columns In Datagrid", priority = 2)
    public void activityLogTest3() {
        DataGrid dataGrid = activityLogTab.getDataGrid();
        Collection<Column> actualColumns = dataGrid.getColumnMap().values();
        actualColumns.stream().map(column -> column.getColumnName())
                .forEach(colName -> assertThat((String) inputMap.get("columns")).contains(colName)
                        .as(String.format("column %s is not present in datagrid", colName)));
    }

}
