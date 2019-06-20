package com.advalent.automation.test.subropoint;

import com.advalent.automation.api.constants.TimeOuts;
import com.advalent.automation.impl.pages.search.globalsearch.EventIncidentSearchTab;
import com.advalent.automation.impl.pages.search.globalsearch.GlobalSearchPage;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.ViewEventPage;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.taskstab.*;
import com.advalent.automation.reporting.ExtentHTMLReportManager;
import com.advalent.automation.test.base.BaseTest;
import com.advalent.automation.test.utils.testdata.DataFile;
import com.advalent.automation.test.utils.testdata.TestDataReader;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;


/**
 * @author sshrestha
 */

@Test(groups = "Web Security", description = "Auth0 Login Page Test")
public class DashBoardTest extends BaseTest {
    public static final int FIRST_ROW = 1;
    ViewEventPage viewEventPage;
    TaskTab taskTab;
    TaskNotePanel taskNotePanel;
    Map<String, Object> updatedInputMap;

    @BeforeClass
    public void openEventViewPage() {
        inputMap = TestDataReader.readSection(DataFile.TASK_TAB, "Add");
        super.setUp();
        updatedInputMap = TestDataReader.readSection(DataFile.TASK_TAB, "Update");
        GlobalSearchPage searchPage = navigationBar.navigateTo(GlobalSearchPage.class, TimeOuts.FIVE_SECONDS);
        EventIncidentSearchTab eventIncidentSearchTab = (EventIncidentSearchTab) searchPage.getDefaultTab();
        viewEventPage = eventIncidentSearchTab.goToViewEventPageForMajorClient("United Health Group");
        viewEventPage.doWaitTillFullyLoaded(TimeOuts.TEN_SECONDS);
        taskTab = (TaskTab) viewEventPage.clickOnTab(TaskTab.class);
        taskTab.doWaitTillFullyLoaded(TimeOuts.SIXTY_SECONDS);
        String expectedTitle = taskTab.getTabName();
        String actualTitle = taskTab.getTabTitle();
        ExtentHTMLReportManager.getInstance().addStep("Expected Title", expectedTitle);
        ExtentHTMLReportManager.getInstance().addStep("Actual Title", actualTitle);
        assertThat(actualTitle).contains(expectedTitle);
    }


    @Test(description = "Test That Add New Task Button Is Available In Task Tab", priority = 2)
    public void testThatAddNewTaskButtonIsAvailable() {
        boolean isAddNewTaskButtonAvailable = taskTab.isAddNewTaskButtonAvailable();
        ExtentHTMLReportManager.getInstance().addStep(isAddNewTaskButtonAvailable ? "Add New Task Button Is Displayed" : "Add New Task Button Is Not Displayed");
        assertThat(isAddNewTaskButtonAvailable).isTrue();
    }


    @Test(description = "Test  Add New Task Functionality", priority = 4)
    public void testAddNewTaskFunctionality() {
        if (!taskTab.isAddNewTaskButtonAvailable()) {
            throw new SkipException("Add New Task Button IS Not Available Hence Skipping Add New Task Test");
        }
        taskNotePanel = taskTab.clickOnAddNewTaskBtn();
        taskNotePanel.enterUserForTask((String) inputMap.get("assignedToUser"))
                .enterTaskType((String) inputMap.get("taskType"))
                .enterDescription((String) inputMap.get("description"))
                .enterNote((String) inputMap.get("note"));


    }


}
