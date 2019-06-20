package com.advalent.automation.test.subropoint.search.globalsearch.viewevent.casecorrespondance;


import com.advalent.automation.api.constants.TimeOuts;
import com.advalent.automation.impl.component.datagrid.ICell;
import com.advalent.automation.impl.component.datagrid.Row;
import com.advalent.automation.impl.pages.search.globalsearch.GlobalSearchPage;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.ViewEventPage;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.casecorrespondence.CaseCorrespondenceSection;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.casecorrespondence.CaseCorrespondenceTab;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.casecorrespondence.CorrespondencePreviewModal;
import com.advalent.automation.impl.utils.DateUtils;
import com.advalent.automation.test.base.BaseTest;
import com.advalent.automation.test.utils.testdata.DataFile;
import com.advalent.automation.test.utils.testdata.TestDataReader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

@Test(groups = {"View Event", "Activity Log"}, description = "Case Correspondance Tab Test")

public class CaseCorrespondanceTabTest extends BaseTest {


    public static final String TAB_TITLE = "Correspondence";
    public static final String VIEW_COLUMN = "View";
    public static final int THREE = 3;
    public static final int TWO = 2;
    ViewEventPage viewEventPage;
    CaseCorrespondenceTab caseCorrespondenceTab;
    CaseCorrespondenceSection caseCorrespondenceSection;

    @BeforeClass
    public void navigateToActivityLog() {
        inputMap = TestDataReader.readSection(DataFile.CASE_CORRESPONDENCE, "Add New Communication");
        super.setUp();
        GlobalSearchPage globalSearchPage = navigationBar.navigateTo(GlobalSearchPage.class, TimeOuts.FIVE_SECONDS);
        viewEventPage = globalSearchPage.goToViewEventPageForEventId(56824);
        viewEventPage.doWaitTillFullyLoaded(TimeOuts.SIXTY_SECONDS);
    }

    @Test(description = "Test That Clicking On Case Correspondence Link Displayes Case Correspondence Tab", priority = 1)
    public void caseCorrespondenceTabTest1() {
        caseCorrespondenceTab = (CaseCorrespondenceTab) viewEventPage.clickOnTab(CaseCorrespondenceTab.class);
        String tabTitle = caseCorrespondenceTab.getTabTitle();
        reportManager.addStep("Actual Tab Title", tabTitle);
        reportManager.addStep("Expected Tab Title", TAB_TITLE);
        assertThat(tabTitle).contains(TAB_TITLE);

    }

    @Test(description = "Test That Clicking Add New Communication Button Displays Correspondence Specifics and Addressee Section", priority = 2)
    public void caseCorrespondenceTabTest2() {
        caseCorrespondenceSection = caseCorrespondenceTab.clickOnAddNewCommunicationBtn();
        boolean isCorrespondanceSectionDisplayed = caseCorrespondenceSection.isCorrespondanceSpecificsSectionDisplayed();
        boolean isAddresseeSectionDisplayed = caseCorrespondenceSection.isAddresseeSectionDisplayed();
        reportManager.addStep("Correspondence Section Is" + (isCorrespondanceSectionDisplayed ? "" : "Not") + "Displayed ");
        reportManager.addStep("Addressee Section Is" + (isAddresseeSectionDisplayed ? "" : "Not") + "Displayed ");
        assertThat(isCorrespondanceSectionDisplayed && isAddresseeSectionDisplayed).isTrue();
    }

    @Test(description = "Test That Clicking On View Column Of DataGrid Shows Correspondence Preview Modal", priority = 3)
    public void caseCorrespondenceTabTest3() {
        Row row = caseCorrespondenceTab.getDataGrid().getRowWithActiveLinkInColumn(VIEW_COLUMN);

        ICell viewColumn = row.getCell(VIEW_COLUMN);
        CorrespondencePreviewModal correspondencePreviewModal = viewColumn.click();
        correspondencePreviewModal.doWaitTillFullyLoaded(TimeOuts.SIXTY_SECONDS);
        boolean isModalDisplayed = correspondencePreviewModal.isFullyLoaded();
        reportManager.addStep("Correspondence Preview Modal Is" + (isModalDisplayed ? "" : "Not") + "Displayed ");

        correspondencePreviewModal.closeFromIcon();
        assertThat(isModalDisplayed).isTrue();
    }


    @Test(description = "Test That Clicking On View Column Of DataGrid Also Displays Correspondence Specifics Section And  Addressee Section Of Corresponding Correspondence", priority = 4)
    public void caseCorrespondenceTabTest4() {
        Row firstRow = caseCorrespondenceTab.getDataGrid().getRow(1);
        firstRow.click();
        caseCorrespondenceSection = caseCorrespondenceTab.getCaseCorrespondenceSection();
        String caseNumber = caseCorrespondenceSection.getCaseNumber().getValue();
        String caseEvent = firstRow.getCell("Case/Event").getValue().replace("(0)", "").trim();
        reportManager.addStep("Case Number In Correspondence Specifics Section", caseNumber);
        reportManager.addStep("Case/Event Number In DataGrid", caseEvent);
        assertThat(caseNumber).contains(caseEvent).as("Case Number And Case Event Doesn't Match");

        String partyRole = caseCorrespondenceSection.getPartyRole().getValue();
        String partyRoleInDataGrid = firstRow.getCell("Party Role").getValue();
        reportManager.addStep("Party Role In Addressee Section", partyRole);
        reportManager.addStep("Party Role In DataGrid", partyRoleInDataGrid);
        assertThat(partyRole).isEqualToIgnoringCase(partyRoleInDataGrid).as("Party Roles Doesn't Match");


    }

    @Test(description = "Test That Correspondence Dropdown In Case Correspondence Detail Section Is Disabled After Selecting Inbound As Communication Direction", priority = 5)
    public void caseCorrespondenceTabTest5() {
        caseCorrespondenceTab.clickOnAddNewCommunicationBtn();
        boolean isCorrespondenceDropDownEnabled = false;
        String direction = caseCorrespondenceSection.getDirection().getValue();
        if (direction.equalsIgnoreCase("Inbound")) {
            isCorrespondenceDropDownEnabled = caseCorrespondenceSection.getCorrespondence().isEnabled();
        } else {
            caseCorrespondenceSection.selectDirection("Inbound");
            isCorrespondenceDropDownEnabled = caseCorrespondenceSection.getCorrespondence().isEnabled();
        }
        reportManager.addStep("Correspondence DropDown " + (isCorrespondenceDropDownEnabled ? "Not " : "") + " Disabled");
        assertThat(isCorrespondenceDropDownEnabled).isFalse();
    }


    @Test(description = "Test That Three Communication Methods Are Available After Selecting Inbound As Communication Direction", priority = 6)
    public void caseCorrespondenceTabTest6() {
        caseCorrespondenceSection.selectDirection("Inbound");
        List<String> availableCommunicationMethods = caseCorrespondenceSection.getCommunicationMethod().getAvailableOptions();
        assertThat(availableCommunicationMethods.size()).isEqualTo(THREE);
        Arrays.stream(CaseCorrespondenceSection.CorrespondenceMethods.values()).forEach(method -> {
            assertThat(availableCommunicationMethods).contains(method.getValue());
        });
    }

    @Test(description = "Test That Only Two Communication Methods Are Available After Selecting Outbound As Communication Direction", priority = 6)
    public void caseCorrespondenceTabTest7() {
        caseCorrespondenceSection.selectDirection("Outbound");
        List<String> availableCommunicationMethods = caseCorrespondenceSection.getCommunicationMethod().getAvailableOptions();
        assertThat(availableCommunicationMethods.size()).isEqualTo(TWO);

    }

    @Test(description = "Test That Case Number In Correspondence Detail Section Is Prepopulated For Corresponding Event", priority = 6)
    public void caseCorrespondenceTabTest8() {
        String caseNumber = caseCorrespondenceSection.getCaseNumber().getValue();
        String eventId = viewEventPage.getEventBanner().getDisplayedEventId();
        assertThat(caseNumber).contains(eventId);
    }

    @Test(description = "Test That Subject Of Correspondence Is Populated According To Selected Correspondence ", priority = 6)
    public void caseCorrespondenceTabTest9() {
        caseCorrespondenceSection.selectDirection("Outbound");
        String correspondenceValue = (String) inputMap.get("correspondence");
        caseCorrespondenceSection.selectCorrespondence(correspondenceValue);
        caseCorrespondenceSection.waitTilSubjectIsUpdated();
        assertThat(correspondenceValue).contains(caseCorrespondenceSection.getSubject().getValue());
    }


    @Test(description = "Test That Date Of Correspondence Is Populated with Todays Date", priority = 6)
    public void caseCorrespondenceTabTest10() {
        String date = caseCorrespondenceSection.getDate().getValue();
        String today = DateUtils.getTodaysDate("mm-DD-YYYY");
        assertThat(date).isEqualTo(today);
    }


/*

    @Test(description = "Test Add New Communication Functionality", priority = 4)
    public void caseCorrespondenceTabTest5() {
//        todo: NEED MORE DETAILS
        caseCorrespondenceSection.selectEventCase(inputMap.get("eventCase"))
                .selectCommunicationMethod(inputMap.get("communicationMethod"))
                .selectRelatedToPriorCommunication(inputMap.get("priorCommunication"))
                .selectCorrespondance(inputMap.get("correspondance"))
                .enterSubject(inputMap.get("subject"))
                .enterTaskDate(inputMap.get("taskDate"))
                .enterNotes(inputMap.get("notes"))
                .selectPartyRole(inputMap.get("partyRole"))
//               .selectPartyName(inputMap.get("partyName"))
//               .selectContactName(inputMap.get("contactName"))
                .selectContactName(inputMap.get("contactName"));
    }
*/


}
