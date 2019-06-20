package com.advalent.automation.test.subropoint.search.globalsearch.viewevent.activitylog;

import com.advalent.automation.api.constants.TimeOuts;
import com.advalent.automation.impl.pages.search.globalsearch.GlobalSearchPage;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.ViewEventPage;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.activitylog.ActivityLogTab;
import com.advalent.automation.test.base.BaseTest;
import com.advalent.automation.test.utils.testdata.DataFile;
import com.advalent.automation.test.utils.testdata.TestDataReader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.fest.assertions.Assertions.assertThat;


@Test(groups = {"View Event", "Activity Log"}, description = "Test The Entries In Activity Log")
public class ActivityLogEntryTest extends BaseTest {


    ViewEventPage viewEventPage;
    ActivityLogTab activityLogTab;
    int initialRecordInDataGrid;
    String initialDataInDataGrid;

    @BeforeClass
    public void navigateToActivityLog() {
        inputMap = TestDataReader.readSection(DataFile.ACTIVITY_LOG, "Search Activity");
        super.setUp();
        GlobalSearchPage globalSearchPage = navigationBar.navigateTo(GlobalSearchPage.class, TimeOuts.FIVE_SECONDS);
        viewEventPage = globalSearchPage.goToViewEventPageForEventWithStatus("Open");
        activityLogTab = (ActivityLogTab) viewEventPage.clickOnTab(ActivityLogTab.class);

    }


}
