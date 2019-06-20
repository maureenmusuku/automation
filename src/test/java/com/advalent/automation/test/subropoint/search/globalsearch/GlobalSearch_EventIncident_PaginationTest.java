package com.advalent.automation.test.subropoint.search.globalsearch;

import com.advalent.automation.api.constants.TimeOuts;
import com.advalent.automation.impl.pages.search.globalsearch.EventIncidentSearchTab;
import com.advalent.automation.impl.pages.search.globalsearch.GlobalSearchPage;
import com.advalent.automation.test.common.AbstractPaginationTest;
import com.advalent.automation.test.utils.testdata.DataFile;
import com.advalent.automation.test.utils.testdata.TestDataReader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created By: sumit
 * Created Date :12/7/2018
 * Note:
 */

@Test(groups = "Pagination", description = "Global Search Event/Incident Pagination Test")
public class GlobalSearch_EventIncident_PaginationTest extends AbstractPaginationTest {


    @BeforeClass
    public void navigateToGlobalSearch() {
        super.setUp();
        this.inputMap = TestDataReader.readSection(DataFile.GLOBAL_SEARCH, "Event_Incident Search");
        GlobalSearchPage globalSearchPage = this.navigationBar.navigateTo(GlobalSearchPage.class, TimeOuts.THIRTY_SECONDS);
        EventIncidentSearchTab eventIncidentSearchTab = (EventIncidentSearchTab) globalSearchPage.getDefaultTab();
        eventIncidentSearchTab.doWaitTillFullyLoaded(TimeOuts.SIXTY_SECONDS);
        eventIncidentSearchTab.selectMajorClient("United Health Group");
        eventIncidentSearchTab.clickOnSearchButton();
        dataGrid = eventIncidentSearchTab.getDataGrid();
        paginationComponent = dataGrid.getPaginationComponent();

    }


}
