package com.advalent.automation.test.subropoint.search.globalsearch.viewevent.memberinformationtab;

import com.advalent.automation.api.constants.TimeOuts;
import com.advalent.automation.impl.component.notificationpanel.NotificationPanel;
import com.advalent.automation.impl.pages.search.globalsearch.EventIncidentSearchTab;
import com.advalent.automation.impl.pages.search.globalsearch.GlobalSearchPage;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.ViewEventPage;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.memberinformation.MemberInformationTab;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.memberinformation.patientinfotab.ContactInformationSection;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.memberinformation.patientinfotab.DemographicSection;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.memberinformation.patientinfotab.PatientInformationTab;
import com.advalent.automation.reporting.ExtentHTMLReportManager;
import com.advalent.automation.test.base.BaseTest;
import com.advalent.automation.test.utils.testdata.DataFile;
import com.advalent.automation.test.utils.testdata.TestDataReader;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static com.advalent.automation.impl.component.datagrid.Indexes.FIRST;
import static org.fest.assertions.Assertions.assertThat;


@Test(groups = {"View Event", "Member Information Tab"}, description = "Patient Information Tab Test")

public class PatientInformationTabTest extends BaseTest {
    ViewEventPage viewEventPage;
    MemberInformationTab memberInformationTab;
    PatientInformationTab patientInformationTab;
    ContactInformationSection contactInformationScreen;

    @BeforeClass
    public void beforeClass() {
        super.setUp();
        inputMap = TestDataReader.readSection(DataFile.MEMBER_INFO_TAB, "Add Contact");
        GlobalSearchPage globalSearchPage = navigationBar.navigateTo(GlobalSearchPage.class, TimeOuts.FIVE_SECONDS);
        viewEventPage = ((EventIncidentSearchTab) globalSearchPage.getDefaultTab()).goToViewEventPageForEventStatus("Open");
        viewEventPage.doWaitTillFullyLoaded(TimeOuts.TEN_SECONDS);
    }

    @Test(description = "Test That Clicking On Member Information Tab Pill Opens Patient Information Tab", priority = 1)
    public void memberInformationTabTest() {
        memberInformationTab = (MemberInformationTab) viewEventPage.clickOnTab(MemberInformationTab.class);
        memberInformationTab.doWaitTillFullyLoaded(TimeOuts.FIVE_SECONDS);
        boolean isMemberInformationTabDisplayed = memberInformationTab.isFullyLoaded();
        assertThat(isMemberInformationTabDisplayed).isTrue();
    }

    @Test(description = "Test That Patient Information Tab Is Displayed By Default In Member Information Tab", priority = 2)
    public void testThatPatientInformationTabIsDisplayedByDefault() {
        patientInformationTab = (PatientInformationTab) memberInformationTab.getDefaultTab();
        patientInformationTab.doWaitTillFullyLoaded(TimeOuts.TWO_SECOND);
        boolean isPageDisplayed = patientInformationTab.isFullyLoaded();
        assertThat(isPageDisplayed).isTrue();
    }


    @Test(description = "Test That 4 sections are displayed in patient information screen", priority = 3)
    public void testThat4SectionsAreDisplayedInPatientInformationScreen() {
        boolean isDemographicScreenDisplayed = patientInformationTab.getDemographicInformationScreen().isFullyLoaded();
        assertThat(isDemographicScreenDisplayed).isTrue();
        ExtentHTMLReportManager.getInstance().addStep("Demographic Section Is " + (isDemographicScreenDisplayed ? "" : "Not") + "Displayed");
//        boolean isContactScreenDisplayed = patientInformationTab.getContactInformationScreen().isFullyLoaded();
//        assertThat(isContactScreenDisplayed).isTrue();
//        ExtentHTMLReportManager.getInstance().addStep("Contact Section Is " + (isContactScreenDisplayed ? "" : "Not") + "Displayed");

        boolean isEventsScreenDisplayed = patientInformationTab.eventDataGrid.isFullyLoaded();
        assertThat(isEventsScreenDisplayed).isTrue();
        ExtentHTMLReportManager.getInstance().addStep("Event Section Is " + (isEventsScreenDisplayed ? "" : "Not") + "Displayed");


        boolean isIncidentReportScreenDisplayed = patientInformationTab.incidentReportDataGrid.isFullyLoaded();
        assertThat(isIncidentReportScreenDisplayed).isTrue();
        ExtentHTMLReportManager.getInstance().addStep("Event Section Is " + (isIncidentReportScreenDisplayed ? "" : "Not") + "Displayed");


    }


    @Test(description = "Test That Fields In Demographic Screen Are Disabled By Default", priority = 4)
    public void testThatFieldsInDemographicSectionIsDisabledInitiallyPatientInformationScreen() {
        ExtentHTMLReportManager report = ExtentHTMLReportManager.getInstance();
        DemographicSection demographicSection = patientInformationTab.getDemographicInformationScreen();
        boolean isPatientFirstNameTextBoxEnabled = demographicSection.patientFirstName.isEnabled();
        report.addStep("Patient First Name Textbox Is " + (isPatientFirstNameTextBoxEnabled ? "" : "Not ") + "Enabled");
        assertThat(isPatientFirstNameTextBoxEnabled).isFalse();

        boolean isPatientMiddleNameTextBoxEnabled = demographicSection.patientMiddleName.isEnabled();
        report.addStep("Patient Middle Name Textbox Is " + (isPatientMiddleNameTextBoxEnabled ? "" : "Not ") + "Enabled");
        assertThat(isPatientMiddleNameTextBoxEnabled).isFalse();

        boolean isPatientLastNameTextBoxEnabled = demographicSection.patientLastName.isEnabled();
        report.addStep("Patient Last Name Textbox Is " + (isPatientLastNameTextBoxEnabled ? "" : "Not ") + "Enabled");
        assertThat(isPatientLastNameTextBoxEnabled).isFalse();

        boolean isPatientSuffixTextBoxEnabled = demographicSection.patientSuffix.isEnabled();
        report.addStep("Patient Suffix Textbox Is " + (isPatientSuffixTextBoxEnabled ? "" : "Not ") + "Enabled");
        assertThat(isPatientSuffixTextBoxEnabled).isFalse();

        boolean isPatientIdTextBoxEnabled = demographicSection.patientID.isEnabled();
        report.addStep("Patient Id Textbox Is " + (isPatientIdTextBoxEnabled ? "" : "Not ") + "Enabled");
        assertThat(isPatientIdTextBoxEnabled).isFalse();

        boolean isSSNTextBoxEnabled = demographicSection.patientSSN.isEnabled();
        report.addStep("Patient SSN Textbox Is " + (isSSNTextBoxEnabled ? "" : "Not ") + "Enabled");
        assertThat(isSSNTextBoxEnabled).isFalse();

        boolean isAltIdTextBoxEnabled = demographicSection.patientSSN.isEnabled();
        report.addStep("Patient Alt Id Textbox Is " + (isAltIdTextBoxEnabled ? "" : "Not ") + "Enabled");
        assertThat(isAltIdTextBoxEnabled).isFalse();

        boolean isMajorClientTextBoxEnabled = demographicSection.majorClient.isEnabled();
        report.addStep("Major Client Textbox Is " + (isMajorClientTextBoxEnabled ? "" : "Not ") + "Enabled");
        assertThat(isMajorClientTextBoxEnabled).isFalse();

        boolean isClientTextBoxEnabled = demographicSection.clientName.isEnabled();
        report.addStep(" Client Textbox Is " + (isClientTextBoxEnabled ? "" : "Not ") + "Enabled");
        assertThat(isClientTextBoxEnabled).isFalse();
    }

    @Test(description = "Test That Contact Details Of Previously Existing Contact Is Shown In Contact Details Section ", priority = 5)
    public void testThatContactDetailsOfPreviousExistingContactIsShownInContactDetailsSection() {
        if (patientInformationTab.isContactDetailsAvailable()) {
            patientInformationTab.getContactInformationScreen();
            List<String> firstRowData = contactInformationScreen.getDataGrid().getRow(FIRST).getDataAsList();
            boolean dataGridContainsData = firstRowData.contains(contactInformationScreen.patientAddress1 + " " + contactInformationScreen.patientAddress2);
            reportManager.addStep(dataGridContainsData ? "Contact Details Are Available" : "Contact Details Are Not Available");
            assertThat(firstRowData).contains(contactInformationScreen.patientAddress1 + " " + contactInformationScreen.patientAddress2);
        } else {
            throw new SkipException("Contact Details Screen Is Not Available");
        }
    }


    @Test(description = "Test That Contact Details Section Is Displayed After Clicking On Add New Contact Information Button", priority = 6)
    public void testThatContactDetailsSectionIsDisplayedAfterClickingOnAddNewContactInformationButton() {
        contactInformationScreen = patientInformationTab.clickOnAddNewContactBtn();
        contactInformationScreen.doWaitTillFullyLoaded(TimeOuts.FIVE_SECONDS);
        reportManager.addStep("Contact Details Section Is " + (contactInformationScreen.isFullyLoaded() ? " " : " Not") + " Displayed");
        assertThat(contactInformationScreen.isFullyLoaded()).isTrue();
//        patientInformationTab.getContactInformationScreen().clickOn
    }

    @Test(description = "Test That Added Contact Details Is Displayed In Data Grid", priority = 7)
    public void testThatAddedContactDetailsIsDisplayedInContactDetails() {
        int noOfContact = contactInformationScreen.getTotalContactRecord();
        contactInformationScreen.enterAddress1((String) inputMap.get("address1"))
                .enterAddress2((String) inputMap.get("address2"));
        NotificationPanel notificationPanel = patientInformationTab.clickOnSaveBtn();
        String message = notificationPanel.getDisplayedMessage();
        notificationPanel.waitTillDisappears();

        assertThat(message).isEqualTo(NotificationPanel.CREATE_SUCCESS_MESSAGE);


        int noOfContactAfterAdd = contactInformationScreen.getTotalContactRecord();
        reportManager.addStep("Number Of Record Initially", noOfContact + "");
        reportManager.addStep("Number Of Record After Adding Contact", noOfContactAfterAdd + "");
        assertThat(noOfContact).isLessThan(noOfContactAfterAdd);
//        patientInformationTab.getContactInformationScreen().clickOn
    }

}
