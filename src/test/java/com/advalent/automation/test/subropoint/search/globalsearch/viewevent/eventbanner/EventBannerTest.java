package com.advalent.automation.test.subropoint.search.globalsearch.viewevent.eventbanner;

import com.advalent.automation.api.constants.TimeOuts;
import com.advalent.automation.impl.component.eventbanner.EventBanner;
import com.advalent.automation.impl.component.notificationpanel.NotificationPanel;
import com.advalent.automation.impl.pages.search.globalsearch.EventIncidentSearchTab;
import com.advalent.automation.impl.pages.search.globalsearch.GlobalSearchPage;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.IncludedClaimsInventoryPage;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.ViewEventPage;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.discovery.DiscoveryEventPage;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.taskstab.TaskTab;
import com.advalent.automation.reporting.ExtentHTMLReportManager;
import com.advalent.automation.selenium.SeleniumUtils;
import com.advalent.automation.test.base.BaseTest;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.fest.assertions.Assertions.assertThat;


@Test(groups = {"Global Search", "View Event Page", "Event Banner"}, description = "Event Banner Test")
public class EventBannerTest extends BaseTest {
    ViewEventPage viewEventPage;
    EventBanner eventBanner;
    IncludedClaimsInventoryPage includedClaimsInventoryPage;

    @BeforeClass
    public void navigateToViewEventPage() {
        super.setUp();
        GlobalSearchPage globalSearchPage = navigationBar.navigateTo(GlobalSearchPage.class, TimeOuts.FIVE_SECONDS);
        viewEventPage = globalSearchPage.goToViewEventPageForEventWithStatus("Open");
        viewEventPage.doWaitTillFullyLoaded(TimeOuts.FIVE_SECONDS);
        eventBanner = viewEventPage.getEventBanner();

    }


    @Test(description = "Test That View Event Page Contains Event Banner", priority = 2)
    public void testThatViewEventPageContainsEventBanner() {
        eventBanner = viewEventPage.getEventBanner();
        boolean isEventBannerDisplayed = eventBanner.isFullyLoaded();
        ExtentHTMLReportManager.getInstance().addStep(isEventBannerDisplayed ? "Event Banner Is Displayed" : "Event Banner Is Not Displayed");
        assertThat(isEventBannerDisplayed).isTrue();
    }


    @Test(description = "Test That View Included Claims Link is Available In Event Banner", priority = 3)
    public void testThatViewIncludedClaimsLinkIsDisplayedInEventBanner() {
        boolean isViewIncludedClaimsBtnDisplayed = eventBanner.viewIncludedClaimsBtn.isDisplayed();
        boolean isViewIncludedClaimsBtnEnabled = eventBanner.viewIncludedClaimsBtn.isEnabled();
        ExtentHTMLReportManager.getInstance().addStep(isViewIncludedClaimsBtnDisplayed && isViewIncludedClaimsBtnEnabled ?
                "View Included Claims Link Is Available  " : "View Included Claims Link Is Not Available");
        assertThat(isViewIncludedClaimsBtnDisplayed && isViewIncludedClaimsBtnEnabled).isTrue();
    }

    @Test(description = "Test That Add Task Button is Available In Event Banner", priority = 4)
    public void testThatAddTaskButtonIsAvailableInEventBanner() {
        boolean isAddTaskBtnDisplayed = eventBanner.addTaskBtn.isDisplayed();
        boolean isAddTaskBtnEnabled = eventBanner.addTaskBtn.isEnabled();
        ExtentHTMLReportManager.getInstance().addStep(isAddTaskBtnDisplayed && isAddTaskBtnEnabled ?
                "Add Task Button Is Available  " : "Add Task Button Is Not Available");
        assertThat(isAddTaskBtnDisplayed && isAddTaskBtnEnabled).isTrue();
    }


    @Test(description = "Test That Clicking On View Included Claims link Opens New Window", priority = 5)
    public void viewIncludedClaims() {
        includedClaimsInventoryPage = eventBanner.clickOnViewIncludedClaimsLink();
        SeleniumUtils.waitUntilMoreThanOneWindowsIsOpen(getWebDriver());
        int noOfWindow = SeleniumUtils.getNumberOfOpenWindows(getWebDriver());
        assertThat(noOfWindow).isGreaterThan(1);

    }

    @Test(description = "Test That Clicking On View Included Claims link Opens Included Claims Inventory Page", priority = 8)
    public void viewIncludedClaimsPAge() {
        SeleniumUtils.switchToNewWindow(getWebDriver());
        includedClaimsInventoryPage.doWaitTillFullyLoaded(TimeOuts.TEN_SECONDS);
        String expectedTitle = "Included Claim Inventory";
        String actualTitle = includedClaimsInventoryPage.getPageTitle();
        assertThat(actualTitle).isEqualTo(expectedTitle);
    }


    @Test(description = "Test That Clicking On Add Task Link Opens Task Tab", priority = 6)
    public void testClickingOnAddTaskOpensTaskTab() {
        SeleniumUtils.scrollPage(getWebDriver(), -100);
        eventBanner = viewEventPage.getEventBanner();
        TaskTab taskTab = eventBanner.clickOnAddTaskBtn();
        taskTab.doWaitTillFullyLoaded(TimeOuts.TEN_SECONDS);
        String tabTitle = taskTab.getTabTitle();
        String expectedTitle = taskTab.getTabName();
        assertThat(tabTitle).contains(expectedTitle).as("Expected and Actual Title should Be Same");
    }


}
