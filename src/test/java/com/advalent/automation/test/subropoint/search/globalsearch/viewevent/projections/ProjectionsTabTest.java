package com.advalent.automation.test.subropoint.search.globalsearch.viewevent.projections;

import com.advalent.automation.api.constants.TimeOuts;
import com.advalent.automation.impl.pages.search.globalsearch.GlobalSearchPage;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.ViewEventPage;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.projections.ProjectionsInformationSection;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.projections.ProjectionsTab;
import com.advalent.automation.test.base.BaseTest;
import com.advalent.automation.test.utils.testdata.DataFile;
import com.advalent.automation.test.utils.testdata.TestDataReader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;

@Test(groups = {"Global Search Page", "View Event"}, description = "Projection Tab Test")
public class ProjectionsTabTest extends BaseTest {

    public static final String PROJECTIONS_TAB_TITLE = "Projections";
    private ViewEventPage viewEventPage;
    private ProjectionsTab projectionsTab;

    @BeforeClass
    public void goToViewEvent() {
        super.setUp();
        this.pageInputMap = TestDataReader.read(DataFile.PROJECTIONS_TAB);
        GlobalSearchPage globalSearchPage = navigationBar.navigateTo(GlobalSearchPage.class, TimeOuts.FIVE_SECONDS);
        Map<String, Object> info = this.pageInputMap.get("Info");
        int eventId = Integer.parseInt((String) info.get("eventId"));
        viewEventPage = globalSearchPage.goToViewEventPageForEventId(eventId);
        viewEventPage.doWaitTillFullyLoaded(TimeOuts.TEN_SECONDS);
    }


    @Test(description = "Test That Clicking On Projections Link Navigates To Projections Tab",  priority = 1)
    public void projectionsTabTest1() {
        projectionsTab = (ProjectionsTab) viewEventPage.clickOnTab(ProjectionsTab.class);
        String tabTitle = projectionsTab.getTabTitle();
        reportManager.addStep("Actual Tab Title ", tabTitle);
        reportManager.addStep("Expected Tab Title ", PROJECTIONS_TAB_TITLE);
        assertThat(tabTitle).contains(PROJECTIONS_TAB_TITLE);
    }

    @Test(description = "Test That Clicking On Add New Projections Button Displays Projection Information Section",priority = 2)
    public void projectionsTabTest2() {
        ProjectionsInformationSection projectionsInformationSection = projectionsTab.clickOnAddNewProjectionsButton();
        boolean isProjectionInformationSectionDisplayed = projectionsInformationSection.isProjectionInformationSectionDisplayed();
        reportManager.addStep("Projection Information Section Is " + (isProjectionInformationSectionDisplayed ? "" : " Not ") + " Displayed");
        assertThat(isProjectionInformationSectionDisplayed).isTrue();
    }

    @Test(description = "Test That Clicking On Add New Projections Button Also Displays Authorization Levels Section",priority = 3)
    public void projectionsTabTest3() {
        ProjectionsInformationSection projectionsInformationSection = projectionsTab.clickOnAddNewProjectionsButton();
        boolean isAuthorizationLevelSectionDisplayed = projectionsInformationSection.isAuthorizatonLevelSectionDisplayed();
        reportManager.addStep(" Authorization Levels Section Is " + (isAuthorizationLevelSectionDisplayed ? "" : " Not ") + " Displayed");
        assertThat(isAuthorizationLevelSectionDisplayed).isTrue();
    }

    @Test(description = "Test That Link Claims To Projection Link Is Available",priority = 4)
    public void projectionsTabTest4() {
        boolean isLinkAvailable = projectionsTab.isLinkClaimsToProjectionLinkAvailable();
        reportManager.addStep("  Link Claims To Projection Link Is " + (isLinkAvailable ? "" : " Not ") + " Available");
        assertThat(isLinkAvailable).isTrue();

    }

    @Test(description = "Test That Generate Settlement Link Is Available",priority = 5)
    public void projectionsTabTest5() {
        boolean isLinkAvailable = projectionsTab.isGenerateSettlementLinkAvailable();
        assertThat(isLinkAvailable).isTrue();

    }

    @Test(description = "Test That Add A Recovery Link Is Available",priority = 6)
    public void projectionsTabTest6() {
        boolean isLinkAvailable = projectionsTab.isAddARecoveryLinkAvailable();
        assertThat(isLinkAvailable).isTrue();

    }

    @Test(description = "Test That Generate Client Auth Letter Link Is Available",priority = 7)
    public void projectionsTabTest7() {
        boolean isLinkAvailable = projectionsTab.isGenerateClientAuthLetterLinkAvailable();
        assertThat(isLinkAvailable).isTrue();

    }

    @Test(description = "Test That Recovery Rules Link Is Available",priority = 8)
    public void projectionsTabTest8() {
        boolean isLinkAvailable = projectionsTab.isRecoveryRuleLinkAvailable();
        assertThat(isLinkAvailable).isTrue();

    }

    @Test(description = "Test That Client Rules Link Is Available",priority = 9)
    public void projectionsTabTest9() {
        boolean isLinkAvailable = projectionsTab.isClientRuleLinkAvailable();
        assertThat(isLinkAvailable).isTrue();

    }

    @Test(description = "Test That Group Rules Link Is Available",priority = 10 )
    public void projectionsTabTest910() {
        boolean isLinkAvailable = projectionsTab.isGroupRuleLinkAvailable();

    }


}
