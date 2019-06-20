package com.advalent.automation.test.subropoint.search.globalsearch.viewevent.externalparty;

import com.advalent.automation.api.constants.TimeOuts;
import com.advalent.automation.impl.component.datagrid.Cell;
import com.advalent.automation.impl.component.datagrid.DataGrid;
import com.advalent.automation.impl.component.datagrid.ICell;
import com.advalent.automation.impl.pages.search.globalsearch.GlobalSearchPage;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.ViewEventPage;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.casecorrespondence.CaseCorrespondenceTab;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.externalpartytab.ContactDetailsSection;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.externalpartytab.ExternalPartyTab;
import com.advalent.automation.test.base.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

@Test(groups = {"Global Search Page", "View Event"}, description = "External Party Tab Test")
public class ExternalPartyTabTest extends BaseTest {

    private static final int FIRST_ROW = 1;
    private static final String ONE_P_INDIVIDUAL = "1P INDIVIDUAL";
    private static final String ONE_P_NAMED_INSURED = "1P NAMED INSURED";
    private static final String POLICY_HOLDER_EMPLOYER = "POLICY HOLDER EMPLOYER";
    private static final String THIRD_PARTY_COMPANY = "THIRD PARTY COMPANY";
    private ViewEventPage viewEventPage;
    private ExternalPartyTab externalPartyTab;
    private List<String> rowData;

    @BeforeClass
    public void goToExternalPartyTab() {
        super.setUp();
        GlobalSearchPage globalSearchPage = navigationBar.navigateTo(GlobalSearchPage.class, TimeOuts.TEN_SECONDS);
        viewEventPage = globalSearchPage.goToViewEventPageForEventId(56824);
        viewEventPage.doWaitTillFullyLoaded(TimeOuts.TEN_SECONDS);
    }

    @Test(description = "Test That Clicking On External Party Tab Displays View External Party Tab", priority = 1)
    public void externalPartyTabTest1() {
        externalPartyTab = (ExternalPartyTab) viewEventPage.clickOnTab(ExternalPartyTab.class);
        String tabTitle = externalPartyTab.getTabTitle();
        String expectedTabTitle = externalPartyTab.getTabName();
        assertThat(tabTitle).contains(expectedTabTitle);
    }

    @Test(description = "Test That External Party Details Data Grid Is Displayed In External Party Tab", priority = 2)
    public void externalPartyTabTest2() {
        boolean isDisplayed = externalPartyTab.getDataGrid().isFullyLoaded();
        assertThat(isDisplayed).isTrue();
    }

    @Test(description = "Test That Details Of Corrosponding External Party In  Data Grid Is Displayed In External Party Tab", priority = 3)
    public void externalPartyTabTest3() {
        DataGrid dataGrid = externalPartyTab.getDataGrid();
        rowData = dataGrid.getRow(FIRST_ROW).getDataAsList();
        String partyName = externalPartyTab.partyName.getValue();
        String eventCase = externalPartyTab.eventCase.getValue();
        assertThat(rowData).contains(partyName, eventCase.split("-")[1]);
    }

    @Test(description = "Test That Contacts Details Of Corrosponding Party Is Is Displayed In External Party Tab", priority = 4)
    public void externalPartyTabTest4() {
        ContactDetailsSection contactDetailsSection = externalPartyTab.getContactDetailsSection();
        String name = contactDetailsSection.getPartyName();
        assertThat(rowData).contains(name);
    }

    @Test(description = "Test That Clicking On View Corrospondance Column Navigates to Corrospondance Tab", priority = 5)
    public void externalPartyTabTest5() {
        DataGrid dataGrid = externalPartyTab.getDataGrid();
        ICell viewCorrospondanceCell = dataGrid.findClickableColumn(ExternalPartyTab.VIEW_CORROSPONDANCE_COLUMN);
        CaseCorrespondenceTab caseCorrespondenceTab = viewCorrospondanceCell.click();
        caseCorrespondenceTab.doWaitTillFullyLoaded(TimeOuts.FIVE_SECONDS);
        String title = caseCorrespondenceTab.getTabTitle();
        assertThat(title).isEqualTo(caseCorrespondenceTab.getTabTitle());

    }

    @Test(description = "Test The Party Type and Party Role Displayed After Selecting Party Role 1P INDIVIDUAL", priority = 6)
    public void externalPartyTabTest6() {
        externalPartyTab = externalPartyTab.selectPartyRole(ONE_P_INDIVIDUAL);
        String partyType = externalPartyTab.getPartyType();
        String fundSource = externalPartyTab.getFundSource();
        assertThat(fundSource).equals(externalPartyTab.FUND_SOURCE_YES);
        assertThat(partyType).equals(externalPartyTab.INDIVIDUAL);
        assertThat(externalPartyTab.getTitleOfInfoSection()).isEqualTo(externalPartyTab.getExpectedTitleOfInfoSection())
                .as("Section Title Should Displayed According To Selected Party Role");


    }

    @Test(description = "Test The Sections Title Displayed After Selecting Party Role 1P INDIVIDUAL", priority = 7)
    public void externalPartyTabTest7() {
        assertThat(externalPartyTab.getTitleOfInfoSection()).isEqualTo(externalPartyTab.getExpectedTitleOfInfoSection())
                .as("Section Title Should Displayed According To Selected Party Role");
    }


    @Test(description = "Test Different Sections  Displayed After Selecting Party Role 1P INDIVIDUAL", priority = 9)
    public void externalPartyTabTest9() {
        String infoSectionTitle = externalPartyTab.getTitleOfInfoSection();
        assertThat(infoSectionTitle).isNotEmpty().as("Info Section Should Be Displayed");
        boolean isCoverageTypeSectionDisplayed = externalPartyTab.getCoverageTypeSection().isFullyLoaded();
        assertThat(isCoverageTypeSectionDisplayed).isTrue();
        boolean isAddNewCoverageTypeBtnDisplayed = externalPartyTab.getAddNewCoverageTypeBtn().isDisplayed();
        assertThat(isAddNewCoverageTypeBtnDisplayed).isTrue();
    }


    @Test(description = "Test Different Sections  Displayed After Selecting Party Role 1P NAMED INSURED", priority = 10)
    public void externalPartyTabTest10() {
        externalPartyTab.selectPartyRole(ONE_P_NAMED_INSURED);
        String partyType = externalPartyTab.getPartyType();
        String fundSource = externalPartyTab.getFundSource();
        assertThat(fundSource).equals(externalPartyTab.FUND_SOURCE_NO);
        assertThat(partyType).equals(externalPartyTab.INDIVIDUAL);
        assertThat(externalPartyTab.getTitleOfInfoSection()).isEqualTo("Individual");
        assertThat(externalPartyTab.getCoverageTypeSection()).isNull();

    }

    @Test(description = "Test Different Sections  Displayed After Selecting Party Role POLICY HOLDER EMPLOYER", priority = 11)
    public void externalPartyTabTest11() {
        externalPartyTab.selectPartyRole(POLICY_HOLDER_EMPLOYER);
        String partyType = externalPartyTab.getPartyType();
        String fundSource = externalPartyTab.getFundSource();
        assertThat(fundSource).equals(externalPartyTab.FUND_SOURCE_NO);
        assertThat(partyType).equals(externalPartyTab.ORGANIZATION);
        assertThat(externalPartyTab.getTitleOfInfoSection()).isEqualTo("Organization");
        assertThat(externalPartyTab.getCoverageTypeSection()).isNull();
        assertThat(externalPartyTab.getContactDetailsSection().isFullyLoaded()).isTrue();
        assertThat(externalPartyTab.getAddNewCoverageTypeBtn().isDisplayed()).isTrue();
    }

    @Test(description = "Test Different Sections  Displayed After Selecting Party Role POLICY HOLDER EMPLOYER", priority = 12)
    public void externalPartyTabTest12() {
        externalPartyTab.selectPartyRole(THIRD_PARTY_COMPANY);
        String partyType = externalPartyTab.getPartyType();
        String fundSource = externalPartyTab.getFundSource();
        assertThat(fundSource).equals(externalPartyTab.FUND_SOURCE_YES);
        assertThat(partyType).equals(externalPartyTab.ORGANIZATION);
        assertThat(externalPartyTab.getTitleOfInfoSection()).isEqualTo("Organization");
        assertThat(externalPartyTab.getContactDetailsSection()).isNull();
        assertThat(externalPartyTab.getCoverageTypeSection().isFullyLoaded()).isTrue();
        assertThat(externalPartyTab.getAddNewCoverageTypeBtn().isDisplayed()).isTrue();
        assertThat(externalPartyTab.getAddNewContactBtn().isDisplayed()).isTrue();
    }


}
