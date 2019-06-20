package com.advalent.automation.test.subropoint.search.globalsearch.viewevent.discovery;

import com.advalent.automation.api.constants.TimeOuts;
import com.advalent.automation.impl.component.datagrid.DataGrid;
import com.advalent.automation.impl.pages.search.globalsearch.EventIncidentSearchTab;
import com.advalent.automation.impl.pages.search.globalsearch.GlobalSearchPage;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.ViewEventPage;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.discovery.DiscoveryEventPage;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.discovery.DiscoveryInvestigationTab;
import com.advalent.automation.test.base.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.fest.assertions.Assertions.assertThat;


@Test(groups = {"Global Search", "Discovery View Event Page"}, description = "Discovery Investigation Page Test")

public class DiscoveryInvestigationTabTest extends BaseTest {

    EventIncidentSearchTab eventIncidentSearchTab;
    DataGrid eventIncidentTabDataGrid;
    ViewEventPage viewEventPage;
    DiscoveryEventPage discoveryEventPage;
    DiscoveryInvestigationTab discoveryInvestigationTab;

    @BeforeClass
    public void openDiscoveryViewPage() {
        super.setUp();
        GlobalSearchPage searchPage = navigationBar.navigateTo(GlobalSearchPage.class, TimeOuts.TEN_SECONDS);
        eventIncidentSearchTab = (EventIncidentSearchTab) searchPage.getDefaultTab();
        eventIncidentSearchTab.searchBy(eventIncidentSearchTab.eventId, "56824", TimeOuts.TEN_SECONDS);
        eventIncidentTabDataGrid = eventIncidentSearchTab.getDataGrid();
        viewEventPage = eventIncidentSearchTab.clickOnFirstRowOfDataGrid();
        viewEventPage.doWaitTillFullyLoaded(TimeOuts.SIXTY_SECONDS);
        discoveryEventPage = viewEventPage.clickOnSwitchToDiscoveryViewBtn();

    }

    @Test(description = "Test That Discovery Investigation Tab Is Loaded By Default")
    public void testThatDiscoveryInvestigationIsLoadedByDefault() {
        discoveryInvestigationTab = (DiscoveryInvestigationTab) discoveryEventPage.getDefaultTab();
        discoveryInvestigationTab.doWaitTillFullyLoaded(TimeOuts.SIXTY_SECONDS);
        String expectedTabTitle = "Caller Information";
        String actualTabTitle = discoveryInvestigationTab.getTabTitle();
        assertThat(expectedTabTitle).isEqualTo(actualTabTitle).as("Page Title should be as expected");
    }


}
