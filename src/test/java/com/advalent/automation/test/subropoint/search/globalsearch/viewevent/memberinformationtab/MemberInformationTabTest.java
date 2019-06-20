package com.advalent.automation.test.subropoint.search.globalsearch.viewevent.memberinformationtab;

import com.advalent.automation.api.constants.TimeOuts;
import com.advalent.automation.impl.pages.search.globalsearch.EventIncidentSearchTab;
import com.advalent.automation.impl.pages.search.globalsearch.GlobalSearchPage;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.ViewEventPage;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.memberinformation.MemberInformationTab;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.memberinformation.patientinfotab.ContactInformationSection;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.memberinformation.patientinfotab.PatientInformationTab;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.memberinformation.policyholdeinfo.PolicyHolderInformationTab;
import com.advalent.automation.test.base.BaseTest;
import com.advalent.automation.test.utils.testdata.DataFile;
import com.advalent.automation.test.utils.testdata.TestDataReader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.fest.assertions.Assertions.assertThat;


@Test(groups = {"View Event", "Member Information Tab"}, description = "Member Information Tab Test")

public class MemberInformationTabTest extends BaseTest {
    ViewEventPage viewEventPage;
    MemberInformationTab memberInformationTab;
    PatientInformationTab patientInformationTab;

    @BeforeClass
    public void beforeClass() {
        super.setUp();
        inputMap = TestDataReader.readSection(DataFile.MEMBER_INFO_TAB, "Add Contact");
        GlobalSearchPage globalSearchPage = navigationBar.navigateTo(GlobalSearchPage.class, TimeOuts.FIVE_SECONDS);
        viewEventPage = globalSearchPage.goToViewEventPageForEventId(Integer.parseInt((String) inputMap.get("eventId")));
        viewEventPage.doWaitTillFullyLoaded(TimeOuts.TEN_SECONDS);

    }

    @Test(description = "Test That Clicking On Member Information Tab Pill Opens Member Information Tab", priority = 1)
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

    @Test(description = "Test That Member Information Tab Contains Two Tabs", priority = 3)
    public void testThatMemberInformationTabContainsTwoTabs() {

        PatientInformationTab patientInformationTab = (PatientInformationTab) memberInformationTab.clickOnTab(PatientInformationTab.class);
        String patientInformationTabTitle = patientInformationTab.getTabTitle();
        assertThat(patientInformationTabTitle).isEqualTo("Demographic Information");

        PolicyHolderInformationTab policyHolderInformationTab = (PolicyHolderInformationTab) memberInformationTab.clickOnTab(PolicyHolderInformationTab.class);
        String policyHolderInformationTabTitle = policyHolderInformationTab.getTabTitle();
        assertThat(policyHolderInformationTabTitle).isEqualTo("Demographic Information");
    }


}
