package com.advalent.automation.test.subropoint.search.globalsearch;

import com.advalent.automation.api.constants.TimeOuts;
import com.advalent.automation.impl.component.datagrid.DataGrid;
import com.advalent.automation.impl.component.inputelements.InputElement;
import com.advalent.automation.impl.pages.common.AbstractSearchPage;
import com.advalent.automation.impl.pages.search.globalsearch.GlobalSearchPage;
import com.advalent.automation.impl.pages.search.globalsearch.MemberSearchTab;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.memberinformation.MemberInformationTab;
import com.advalent.automation.test.common.AbstractSearchTest;
import com.advalent.automation.test.utils.testdata.DataFile;
import com.advalent.automation.test.utils.testdata.TestDataReader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

/**
 * @author sshrestha
 */

@Test(groups = {"Global Search", "Search"}, description = "Global Search - Member Search Tab Test")
public class GlobalSearch_MemberSearch_PageTest extends AbstractSearchTest {
    public static final String EVENT_ID = "eventId";
    public static final String EVENT_OWNER = "eventOwner";
    public static final String CLIENT = "client";
    public static final String MAJOR_CLIENT = "majorClient";
    private GlobalSearchPage globalSearchPage;
    private MemberSearchTab memberSearchTab;
    private DataGrid memberSearchDataGrid;

    @BeforeClass
    public void navigateToGlobalSearch() {
        super.setUp();
        this.inputMap = TestDataReader.readSection(DataFile.GLOBAL_SEARCH, "Member Search");
        globalSearchPage = this.navigationBar.navigateTo(GlobalSearchPage.class, TimeOuts.THIRTY_SECONDS);
        globalSearchPage.doWaitTillFullyLoaded(TimeOuts.SIXTY_SECONDS);
    }

    @Test(description = "Test That Member search tab is displayed After Clicking On Member Search Tab Pill", priority = 1)
    public void test() {
        memberSearchTab = (MemberSearchTab) globalSearchPage.clickOnTab(MemberSearchTab.class);
        memberSearchDataGrid = memberSearchTab.getDataGrid();
        String tabTitle = memberSearchTab.getTabTitle();
        String expectedTabTitle = memberSearchTab.getTabName();
        assertThat(tabTitle).contains(expectedTabTitle).as("Panel Title Should Be Displayed");
    }

    @Test(description = "Test Search By Patient Name Parameters", priority = 2)
    public void testSearchByPatientName() {
        memberSearchDataGrid = memberSearchTab.getDataGrid();
        String initialData = memberSearchDataGrid.getDataAsString();
        memberSearchTab.enterPatientFirstName((String) inputMap.get("patientFirstName"),
                (String) inputMap.get("patientLastName"));
        memberSearchDataGrid = memberSearchTab.clickOnSearchButton();
        String finalData = memberSearchDataGrid.getDataAsString();
        assertThat(finalData).isNotEqualTo(initialData);
    }

    @Test(description = "Test Search By Multiple Parameters", priority = 2)
    public void testSearchByMultipleParameters() {
        memberSearchDataGrid = memberSearchTab.getDataGrid();
        String initialData = memberSearchDataGrid.getDataAsString();
        memberSearchTab.selectPatientState((String) inputMap.get("patientState"));
        memberSearchTab.selectGender((String) inputMap.get("gender"));
        memberSearchDataGrid = memberSearchTab.clickOnSearchButton();
        String finalData = memberSearchDataGrid.getDataAsString();
        assertThat(finalData).isNotEqualTo(initialData);
    }

    @Test(description = "Test Drill To Member  Information Page By Clicking On Member Id Column", priority = 9)
    public void testDrillToPatientInformationPage() {
        memberSearchDataGrid = memberSearchTab.selectMajorClient("United Health Group").clickOnSearchButton();
        MemberInformationTab memberInformationPage = memberSearchDataGrid.getRow(2)
                .getCell("Member Id").click();
        memberInformationPage.doWaitTillFullyLoaded(TimeOuts.SIXTY_SECONDS);
        boolean isPageDisplayed = memberInformationPage.isFullyLoaded();
        assertThat(isPageDisplayed).isTrue();
        globalSearchPage = this.navigationBar.navigateTo(GlobalSearchPage.class, TimeOuts.THIRTY_SECONDS);
    }

    @Test(description = "Test Drill To Member  Information Page By Clicking On Member Name Column", priority = 9)
    public void testDrillToPatientInformationPageThroughMemberName() {
        memberSearchDataGrid = memberSearchTab.selectMajorClient("United Health Group").clickOnSearchButton();
        MemberInformationTab memberInformationPage = memberSearchDataGrid.getRow(2)
                .getCell("Member Name").click();
        memberInformationPage.doWaitTillFullyLoaded(TimeOuts.SIXTY_SECONDS);
        boolean isPageDisplayed = memberInformationPage.isFullyLoaded();
        assertThat(isPageDisplayed).isTrue();
        globalSearchPage = this.navigationBar.navigateTo(GlobalSearchPage.class, TimeOuts.THIRTY_SECONDS);
    }

    @Override
    public AbstractSearchPage getSearchPage() {
        return memberSearchTab;
    }

    @Override
    public List<InputElement> getInputElementList() {
        return Arrays.asList(
                memberSearchTab.getPatientState(),
                memberSearchTab.getClient(),
                memberSearchTab.getPatientId(),
                memberSearchTab.getGender(),
                memberSearchTab.getEmployerGroup(),
                memberSearchTab.getPatientDOB(),
                memberSearchTab.getMajorClient(),
                memberSearchTab.getLineOfBusiness()
        );
    }

    @Override
    public List<String> getInputValueList() {
        return Arrays.asList(
                "patientState",
                "client",
                "patientID",
                "gender",
                "employerGroup",
                "patientDOB",
                MAJOR_CLIENT,
                "lineOfBusiness"
        );
    }
}
