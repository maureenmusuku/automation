package com.advalent.automation.test.subropoint.search.globalsearch;

import com.advalent.automation.api.constants.TimeOuts;
import com.advalent.automation.impl.component.datagrid.DataGrid;
import com.advalent.automation.impl.component.datagrid.Indexes;
import com.advalent.automation.impl.component.inputelements.InputElement;
import com.advalent.automation.impl.pages.common.AbstractSearchPage;
import com.advalent.automation.impl.pages.search.globalsearch.EventIncidentSearchTab;
import com.advalent.automation.impl.pages.search.globalsearch.GlobalSearchPage;
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

@Test(groups = {"Global Search", "Search"}, description = "Global Search - Event/Incident Search Tab Test")
public class GlobalSearch_EventIncident_PageTest extends AbstractSearchTest {
    public static final String EVENT_ID = "eventId";
    public static final String EVENT_OWNER = "eventOwner";
    public static final String CLIENT = "client";
    public static final String MAJOR_CLIENT = "majorClient";
    GlobalSearchPage globalSearchPage;
    EventIncidentSearchTab eventIncidentSearchTab;
    DataGrid eventIncidentTabDataGrid;
    InputElement searchElement = null;

    @BeforeClass
    public void navigateToGlobalSearch() {
        super.setUp();
        this.inputMap = TestDataReader.readSection(DataFile.GLOBAL_SEARCH, "Event_Incident Search");
        globalSearchPage = this.navigationBar.navigateTo(GlobalSearchPage.class, TimeOuts.THIRTY_SECONDS);
        eventIncidentSearchTab = (EventIncidentSearchTab) globalSearchPage.getDefaultTab();
        searchPage = getSearchPage();
        inputElements = getInputElementList();
        inpuValueList = getInputValueList();
    }

    @Test(description = "Test That Event/Incident search tab is displayed by default", priority = 1)
    public void test() {
        eventIncidentSearchTab = (EventIncidentSearchTab) globalSearchPage.getDefaultTab();
        eventIncidentTabDataGrid = eventIncidentSearchTab.getDataGrid();
        String tabTitle = eventIncidentSearchTab.getTabTitle();
        String expectedTabTitle = eventIncidentSearchTab.getTabName();
        assertThat(tabTitle).contains(expectedTabTitle).as("Panel Title Should Be Displayed");
    }

    @Test(description = "Test Search By Multiple Parameters", priority = 2)
    public void testSearchByMultipleParameters() {
        eventIncidentTabDataGrid = eventIncidentSearchTab.getDataGrid();
        String initialData = eventIncidentTabDataGrid.getDataAsString();
        eventIncidentSearchTab.enterClient((String) inputMap.get("client"));
        eventIncidentSearchTab.enterEventStatus((String) inputMap.get("eventStatus"));
        eventIncidentTabDataGrid = eventIncidentSearchTab.clickOnSearchButton();
        String finalData = eventIncidentTabDataGrid.getDataAsString();
        assertThat(finalData).isNotEqualTo(initialData);
    }

    @Test(description = "Test Drill To Patient Information Page", priority = 19)
    public void testDrillToPatientInformationPage() {
        eventIncidentTabDataGrid = eventIncidentSearchTab.selectMajorClient("United Health Group").clickOnSearchButton();
        MemberInformationTab memberInformationPage = eventIncidentTabDataGrid.getRow(3)
                .getCell("Patient Name").click();
        memberInformationPage.doWaitTillFullyLoaded(TimeOuts.SIXTY_SECONDS);
        boolean isPageDisplayed = memberInformationPage.isFullyLoaded();
        assertThat(isPageDisplayed).isTrue();
        globalSearchPage = this.navigationBar.navigateTo(GlobalSearchPage.class, TimeOuts.THIRTY_SECONDS);
    }

    @Test(description = "Test That Only Incidents Are Shown When Clicking On Incident Only Search Tab", priority = 9)
    public void testIncidentOnlySearch() {
        eventIncidentTabDataGrid = eventIncidentSearchTab.selectMajorClient("United Health Group")
                .selectIncidentOnly().clickOnSearchButton();
        String eventStatus = eventIncidentTabDataGrid.getRow(Indexes.FIRST).getCell("Event Status").getValue();

        assertThat(eventStatus).isEqualTo("Incident");
    }

    @Test(description = "Test That Only Incidents From Client Portal Are Shown When Clicking On Incident Only Search Tab", priority = 9)
    public void testIncidentOnlyFromClientPortalSearch() {
        eventIncidentTabDataGrid = eventIncidentSearchTab.selectMajorClient("United Health Group")
                .selectIncidentFromClientPortal().clickOnSearchButton();
        String eventStatus = eventIncidentTabDataGrid.getRow(Indexes.FIRST).getCell("Event Status").getValue();

        assertThat(eventStatus).isEqualTo("Incident");
    }

    @Override
    public AbstractSearchPage getSearchPage() {
        return eventIncidentSearchTab;
    }

    @Override
    public List<InputElement> getInputElementList() {
        return Arrays.asList(eventIncidentSearchTab.eventId,
                eventIncidentSearchTab.majorClient,
                eventIncidentSearchTab.eventStatus,
                eventIncidentSearchTab.patientFirstName,
                eventIncidentSearchTab.patientLastName,
                eventIncidentSearchTab.patientId,
                eventIncidentSearchTab.patientDOB,
                eventIncidentSearchTab.employerGroup,
                eventIncidentSearchTab.eventOwner,
                eventIncidentSearchTab.client);
    }

    @Override
    public List<String> getInputValueList() {
        return Arrays.asList(
                EVENT_ID, MAJOR_CLIENT, "eventStatus", "patientFirstName",
                "patientLastName", "patientID", "patientDOB", "employerGroup",
                EVENT_OWNER, CLIENT);
    }
}
