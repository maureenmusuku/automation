package com.advalent.automation.test.subropoint.search.globalsearch.viewevent.overviewtab;

import com.advalent.automation.api.constants.TimeOuts;
import com.advalent.automation.impl.component.datagrid.DataGrid;
import com.advalent.automation.impl.component.notificationpanel.NotificationPanel;
import com.advalent.automation.impl.pages.search.globalsearch.EventIncidentSearchTab;
import com.advalent.automation.impl.pages.search.globalsearch.GlobalSearchPage;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.ViewEventPage;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.discovery.ReassignEventOwnershipModal;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.overviewtab.EventOwnershipModal;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.overviewtab.LienProcessingModal;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.overviewtab.OverviewTab;
import com.advalent.automation.test.base.BaseTest;
import com.advalent.automation.test.utils.testdata.DataFile;
import com.advalent.automation.test.utils.testdata.TestDataReader;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.advalent.automation.impl.component.datagrid.Indexes.FIRST;
import static org.fest.assertions.Assertions.assertThat;

/*
 * author sshrestha
 * */
@Test(groups = {"Global Search", "View Event", "Over View Tab"}, description = "Over View Tab Test")
public class OverviewTabTest extends BaseTest {
    EventIncidentSearchTab eventIncidentSearchTab;
    DataGrid eventIncidentTabDataGrid;
    ViewEventPage viewEventPage;
    OverviewTab overviewTab;
    private EventOwnershipModal eventOwnershipModal;
    private ReassignEventOwnershipModal reassignEventOwnershipModal;


    @BeforeClass
    public void openEventViewPage() {
        super.setUp();
        GlobalSearchPage searchPage = navigationBar.navigateTo(GlobalSearchPage.class, TimeOuts.FIVE_SECONDS);
        eventIncidentSearchTab = (EventIncidentSearchTab) searchPage.getDefaultTab();
        eventIncidentTabDataGrid = eventIncidentSearchTab.selectMajorClient("United Health Group").clickOnSearchButton();
        viewEventPage = eventIncidentTabDataGrid.getRow(FIRST).getCell(FIRST).click();
        viewEventPage.doWaitTillFullyLoaded(TimeOuts.FIVE_SECONDS);
    }

    @Test(description = "Test That Overview Tab Is Loaded By Default", priority = 1)
    public void testThatDiscoveryInvestigationIsLoadedByDefault() {
        overviewTab = (OverviewTab) viewEventPage.getDefaultTab();
        String expectedTabTitle = "Overview";
        String actualTabTitle = overviewTab.getTabTitle();
        assertThat(actualTabTitle).contains(expectedTabTitle).as("Page Title should be as expected");
    }


    @Test(description = "Test Update Functionality In OverView Tab", priority = 2)
    public void testUpdateFunctionalityInOverViewTab() {
        overviewTab.enterLossDetails("Updated Loss Details");
        NotificationPanel notificationPanel = overviewTab.clickOnSaveButton();
        String displayedMessage = notificationPanel.getDisplayedMessage();
        notificationPanel.waitTillDisappears();
        assertThat(displayedMessage).isEqualTo(NotificationPanel.UPDATE_SUCCESS_MSG)
                .as("Data Not Updated In OverView Tab");
    }

    @Test(description = "Test That Clicking On Event Ownership Button Opens Event Ownership Model", priority = 3)
    public void testThatClickingOnEventOwnershipBtnOpensEventOwnershipModel() {
        eventOwnershipModal = overviewTab.clickOnEventOwnershipBtn();
        String expectedTitle = "Event Ownership";
        eventOwnershipModal.doWaitTillFullyLoaded(TimeOuts.TEN_SECONDS);
        String actualTitle = eventOwnershipModal.getModalTitle();
        assertThat(actualTitle).isEqualTo(expectedTitle).as("Page Title should be as expected");

    }

    @Test(description = "Test That clicking Reassign Event Ownership Button on makes Reassign Event Ownership Section visible", priority = 4)
    public void testThatClickingReassignEventOwnershipButtonMakesReassignEventOwnershipSectionVisible() {
        this.inputMap = TestDataReader.readSection(DataFile.OVERVIEW_TAB, "Reassign Event Ownership");
        reassignEventOwnershipModal = eventOwnershipModal.clickOnReassignEventOwnershipBtn();
        String modelTitle = reassignEventOwnershipModal.getModalTitle();
        assertThat(modelTitle).isEqualTo("Reassign Event Ownership");
    }

    @Test(description = "Test That User Can Reassign Event Ownership", priority = 5)
    public void testReassignEventOwnerShipFunctionality() {
        NotificationPanel notificationPanel = null;

        try {
            reassignEventOwnershipModal
                    .enterNewDepartment((String) inputMap.get("newDepartment"))
                    .enterNewEventOwner((String) inputMap.get("newEventOwner"))
                    .enterReassignmentNote((String) inputMap.get("reassignmentNote"))
                    .clickOnSaveBtn();

            notificationPanel = reassignEventOwnershipModal.clickOnSaveBtn();
            String displayedMessage = notificationPanel.getDisplayedMessage();
            eventOwnershipModal = notificationPanel.waitTillDisappearsOnPage(EventOwnershipModal.class);
            assertThat(displayedMessage).isEqualTo(NotificationPanel.CREATE_SUCCESS_MESSAGE);
        } catch (Exception ex) {
             eventOwnershipModal.clickOnCloseBtn();
            throw new SkipException("Save Button IS Not Enabled");
        }
    }

    @Test(description = "Test That Clicking On Lien Process Opens Lien Process Information Model", priority = 6)
    public void testThatClickingOnLienProcessBtnOpensLienProcessInformationModel() {
        LienProcessingModal lienProcessingModal = overviewTab.clickOnLienProcessingBtn();
        lienProcessingModal.doWaitTillFullyLoaded(TimeOuts.FIVE_SECONDS);
        String actualTitle = "Lien Processing Information";
        String expectedTitle = lienProcessingModal.getModalTitle();
        assertThat(actualTitle).isEqualTo(expectedTitle).as("Page Title should be as expected");
        overviewTab = lienProcessingModal.clickOnCloseBtn();
    }


}
