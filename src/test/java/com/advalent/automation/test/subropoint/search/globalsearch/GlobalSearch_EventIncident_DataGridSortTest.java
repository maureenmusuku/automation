package com.advalent.automation.test.subropoint.search.globalsearch;

import com.advalent.automation.api.constants.TimeOuts;
import com.advalent.automation.impl.pages.search.globalsearch.EventIncidentSearchTab;
import com.advalent.automation.impl.pages.search.globalsearch.GlobalSearchPage;
import com.advalent.automation.test.common.AbstractDataGridSortTest;
import com.advalent.automation.test.utils.testdata.DataFile;
import com.advalent.automation.test.utils.testdata.TestDataReader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;

/**
 * Created By: sumit
 * Created Date :12/7/2018
 * Note:
 */

@Test(groups = "Sort Test", description = "Global Search Event/Incident Page Sort Test")
public class GlobalSearch_EventIncident_DataGridSortTest extends AbstractDataGridSortTest {

    EventIncidentSearchTab eventIncidentSearchTab;

    @BeforeClass
    public void navigateToGlobalSearch() {
        this.sortBy = Arrays.asList("Event Id", "Event Id", "Event Status", "Event Status", "Patient Name", "Patient Name", "Patient ID", "Patient ID");
        this.sortType = Arrays.asList("DESC", "ASC", "ASC", "DESC", "ASC", "DESC", "DESC", "ASC");
        super.setUp();
        this.inputMap = TestDataReader.readSection(DataFile.GLOBAL_SEARCH, "Event_Incident Search");
        GlobalSearchPage globalSearchPage = this.navigationBar.navigateTo(GlobalSearchPage.class, TimeOuts.THIRTY_SECONDS);
        eventIncidentSearchTab = (EventIncidentSearchTab) globalSearchPage.getDefaultTab();
        eventIncidentSearchTab.doWaitTillFullyLoaded(TimeOuts.SIXTY_SECONDS);
        eventIncidentSearchTab.selectMajorClient((String) inputMap.get("majorClient"));
        eventIncidentSearchTab.clickOnSearchButton();
        this.dataGrid = eventIncidentSearchTab.getDataGrid();
    }


}
