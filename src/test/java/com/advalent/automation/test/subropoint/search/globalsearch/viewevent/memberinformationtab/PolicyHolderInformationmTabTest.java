package com.advalent.automation.test.subropoint.search.globalsearch.viewevent.memberinformationtab;

import com.advalent.automation.api.constants.TimeOuts;
import com.advalent.automation.impl.component.datagrid.DataGrid;
import com.advalent.automation.impl.component.datagrid.Indexes;
import com.advalent.automation.impl.pages.search.globalsearch.EventIncidentSearchTab;
import com.advalent.automation.impl.pages.search.globalsearch.GlobalSearchPage;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.ViewEventPage;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.memberinformation.MemberInformationTab;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.memberinformation.policyholdeinfo.DemographicSection;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.memberinformation.policyholdeinfo.PolicyHolderInformationTab;
import com.advalent.automation.reporting.ExtentHTMLReportManager;
import com.advalent.automation.test.base.BaseTest;
import com.advalent.automation.test.utils.testdata.DataFile;
import com.advalent.automation.test.utils.testdata.TestDataReader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.fest.assertions.Assertions.assertThat;


@Test(groups = {"View Event", "Member Information Tab"}, description = "Policy Holder Information Tab Test")

public class PolicyHolderInformationmTabTest extends BaseTest {
    ViewEventPage viewEventPage;
    MemberInformationTab memberInformationTab;
    PolicyHolderInformationTab policyHolderInfoTab;
    String setupType;

    @BeforeClass
    public void beforeClass() {
        super.setUp();
        inputMap = TestDataReader.readSection(DataFile.MEMBER_INFO_TAB, "Add Contact");
        GlobalSearchPage globalSearchPage = navigationBar.navigateTo(GlobalSearchPage.class, TimeOuts.FIVE_SECONDS);

        viewEventPage = globalSearchPage.goToViewEventPageForEventId(Integer.parseInt((String) inputMap.get("eventId")));
        EventIncidentSearchTab eventIncidentSearchTab = (EventIncidentSearchTab) globalSearchPage.getDefaultTab();
        DataGrid dataGrid = eventIncidentSearchTab
                .selectEventStatus((String) inputMap.get("eventStatus"))
                .clickOnSearchButton();

        setupType = dataGrid.getRow(Indexes.FIRST).getCell("Setup Type").getValue();
        viewEventPage.doWaitTillFullyLoaded(TimeOuts.SIXTY_SECONDS);
        memberInformationTab = (MemberInformationTab) viewEventPage.clickOnTab(MemberInformationTab.class);
        memberInformationTab.doWaitTillFullyLoaded(TimeOuts.SIXTY_SECONDS);
    }


    @Test(description = "Test That Policy Holder Information Tab Is DisplayedAfter Clicking On Policy Holder Information Link", priority = 2)
    public void testThatPatientInformationTabIsDisplayedByDefault() {
        policyHolderInfoTab = (PolicyHolderInformationTab) memberInformationTab.clickOnTab(PolicyHolderInformationTab.class);
        policyHolderInfoTab.doWaitTillFullyLoaded(TimeOuts.TWO_SECOND);
        boolean isPageDisplayed = policyHolderInfoTab.isFullyLoaded();
        reportManager.addStep("Policy Holder Information Tab Is" + (isPageDisplayed ? " " : " Not") + " Displayed");

        assertThat(isPageDisplayed).isTrue();
    }


    @Test(description = "Test That 4 sections are displayed in policy holder information screen", priority = 3)
    public void testThat4SectionsAreDisplayedInPolicyHolderInformationScreen() {
        boolean isDemographicScreenDisplayed = policyHolderInfoTab.getDemographicInformationScreen().isFullyLoaded();
        assertThat(isDemographicScreenDisplayed).isTrue();
        ExtentHTMLReportManager.getInstance().addStep("Demographic Section Is " + (isDemographicScreenDisplayed ? "" : "Not") + "Displayed");
        boolean isContactScreenDisplayed = policyHolderInfoTab.getContactInformationScreen().isFullyLoaded();
        assertThat(isContactScreenDisplayed).isTrue();
        ExtentHTMLReportManager.getInstance().addStep("Contact Section Is " + (isContactScreenDisplayed ? "" : "Not") + "Displayed");

        boolean isEventsScreenDisplayed = policyHolderInfoTab.eventDataGrid.isFullyLoaded();
        assertThat(isEventsScreenDisplayed).isTrue();
        ExtentHTMLReportManager.getInstance().addStep("Event Section Is " + (isEventsScreenDisplayed ? "" : "Not") + "Displayed");


        boolean isIncidentReportScreenDisplayed = policyHolderInfoTab.incidentReportDataGrid.isFullyLoaded();
        assertThat(isIncidentReportScreenDisplayed).isTrue();
        ExtentHTMLReportManager.getInstance().addStep("Incident Section Is " + (isIncidentReportScreenDisplayed ? "" : "Not") + "Displayed");


    }


    @Test(description = "Test That Fields In Demographic Screen Are Enabled When Setup Type Of Event Is A Placeholder", priority = 4)
    public void testThatFieldsInDemographicSectionIsDisabledInitiallyPatientInformationScreen() {
        reportManager.addStep("Setup Type Of Event Is " + setupType);

        DemographicSection demographicSection = policyHolderInfoTab.getDemographicInformationScreen();
        boolean isPatientFirstNameTextBoxEnabled = demographicSection.policyHolderFirstName.isEnabled();
        reportManager.addStep("Patient First Name Textbox Is " + (isPatientFirstNameTextBoxEnabled ? "" : "Not ") + "Enabled");

        boolean isPatientMiddleNameTextBoxEnabled = demographicSection.policyHolderMiddleName.isEnabled();
        reportManager.addStep("Patient Middle Name Textbox Is " + (isPatientMiddleNameTextBoxEnabled ? "" : "Not ") + "Enabled");

        boolean isPatientLastNameTextBoxEnabled = demographicSection.policyHolderLastName.isEnabled();
        reportManager.addStep("Patient Last Name Textbox Is " + (isPatientLastNameTextBoxEnabled ? "" : "Not ") + "Enabled");

        boolean isPatientSuffixTextBoxEnabled = demographicSection.policyHolderSuffix.isEnabled();
        reportManager.addStep("Patient Suffix Textbox Is " + (isPatientSuffixTextBoxEnabled ? "" : "Not ") + "Enabled");

        boolean isPatientIdTextBoxEnabled = demographicSection.policyHolderID.isEnabled();
        reportManager.addStep("Patient Id Textbox Is " + (isPatientIdTextBoxEnabled ? "" : "Not ") + "Enabled");

        boolean isSSNTextBoxEnabled = demographicSection.policyHolderSSN.isEnabled();
        reportManager.addStep("Patient SSN Textbox Is " + (isSSNTextBoxEnabled ? "" : "Not ") + "Enabled");


        boolean isMajorClientTextBoxEnabled = demographicSection.majorClientName.isEnabled();
        reportManager.addStep("Major Client Textbox Is " + (isMajorClientTextBoxEnabled ? "" : "Not ") + "Enabled");

        boolean isClientTextBoxEnabled = demographicSection.clientName.isEnabled();
        reportManager.addStep(" Client Textbox Is " + (isClientTextBoxEnabled ? "" : "Not ") + "Enabled");

        boolean areFieldsEnabled = isPatientFirstNameTextBoxEnabled && isPatientMiddleNameTextBoxEnabled &&
                isPatientLastNameTextBoxEnabled && isPatientSuffixTextBoxEnabled &&
                isPatientIdTextBoxEnabled && isSSNTextBoxEnabled &&
                isMajorClientTextBoxEnabled && isClientTextBoxEnabled;
        if (setupType.contains("Placeholder")) {
            assertThat(isPatientFirstNameTextBoxEnabled).isTrue();
        } else {
            assertThat(isPatientFirstNameTextBoxEnabled).isFalse();
        }


    }


}
