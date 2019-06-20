package com.advalent.automation.test.subropoint.search.globalsearch;

import com.advalent.automation.api.constants.TimeOuts;
import com.advalent.automation.impl.pages.search.globalsearch.EventIncidentSearchTab;
import com.advalent.automation.impl.pages.search.globalsearch.GlobalSearchPage;
import com.advalent.automation.impl.pages.search.globalsearch.MemberSearchTab;
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
public class GlobalSearch_MemberSearch_DataGridSortTest extends AbstractDataGridSortTest {

    MemberSearchTab memberSearchTab;

    @BeforeClass
    public void navigateToGlobalSearch() {
        this.sortBy = Arrays.asList("Member Name", "Member Name", "Member ID", "Member ID", "Client", "Client");
        this.sortType = Arrays.asList("DESC", "ASC", "ASC", "DESC", "DESC", "ASC");
        super.setUp();
        this.inputMap = TestDataReader.readSection(DataFile.GLOBAL_SEARCH, "Member Search");
        GlobalSearchPage globalSearchPage = this.navigationBar.navigateTo(GlobalSearchPage.class, TimeOuts.THIRTY_SECONDS);
        memberSearchTab = (MemberSearchTab) globalSearchPage.clickOnTab(MemberSearchTab.class);
        memberSearchTab.doWaitTillFullyLoaded(TimeOuts.SIXTY_SECONDS);
        memberSearchTab.selectMajorClient((String) inputMap.get("majorClient"));
        memberSearchTab.clickOnSearchButton();
        this.dataGrid = memberSearchTab.getDataGrid();
    }


}
