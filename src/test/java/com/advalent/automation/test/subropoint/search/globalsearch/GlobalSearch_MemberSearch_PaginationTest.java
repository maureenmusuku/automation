package com.advalent.automation.test.subropoint.search.globalsearch;

import com.advalent.automation.api.constants.TimeOuts;
import com.advalent.automation.impl.pages.search.globalsearch.EventIncidentSearchTab;
import com.advalent.automation.impl.pages.search.globalsearch.GlobalSearchPage;
import com.advalent.automation.impl.pages.search.globalsearch.MemberSearchTab;
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

@Test(groups = "Pagination", description = "Global Search Member Search Pagination Test")
public class GlobalSearch_MemberSearch_PaginationTest extends AbstractPaginationTest {


    @BeforeClass
    public void navigateToGlobalSearch() {
        super.setUp();
        this.inputMap = TestDataReader.readSection(DataFile.GLOBAL_SEARCH, "Member Search");
        GlobalSearchPage globalSearchPage = this.navigationBar.navigateTo(GlobalSearchPage.class, TimeOuts.THIRTY_SECONDS);
        MemberSearchTab memberSearchTab = (MemberSearchTab) globalSearchPage.clickOnTab(MemberSearchTab.class);
        memberSearchTab.doWaitTillFullyLoaded(TimeOuts.SIXTY_SECONDS);
        memberSearchTab.selectMajorClient("United Health Group");
        memberSearchTab.clickOnSearchButton();
        dataGrid = memberSearchTab.getDataGrid();
        paginationComponent = dataGrid.getPaginationComponent();

    }


}
