package com.advalent.automation.test.subropoint.search.globalsearch.viewevent.tasktab;

import com.advalent.automation.api.constants.TimeOuts;
import com.advalent.automation.impl.component.datagrid.DataGrid;
import com.advalent.automation.impl.component.notificationpanel.NotificationPanel;
import com.advalent.automation.impl.pages.search.globalsearch.EventIncidentSearchTab;
import com.advalent.automation.impl.pages.search.globalsearch.GlobalSearchPage;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.ViewEventPage;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.taskstab.*;
import com.advalent.automation.impl.utils.WaitUtils;
import com.advalent.automation.reporting.ExtentHTMLReportManager;
import com.advalent.automation.test.base.BaseTest;
import com.advalent.automation.test.utils.testdata.DataFile;
import com.advalent.automation.test.utils.testdata.TestDataReader;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;

/*
 * author sshrestha
 * */

@Test(groups = {"Global Search", "View Event", "Task Tab"}, description = "Task Tab Test")
public class TaskTabTest extends BaseTest {
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
    }

    @Test(description = "Test That Clicking On Task Tab Pill Opens Task Tab", priority = 1)
    public void testThatClickingOnTaskTabOpensTaskTab() {
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


    @Test(description = "Test That Clicking On Add New Task button Displayes Task Note Panel", priority = 3)
    public void testAddNewTaskPanelIsDisplayed() {
        if (!taskTab.isAddNewTaskButtonAvailable()) {
            throw new SkipException("Add New Task Button IS Not Available Hence Skipping Add New Task Test");
        }
        taskNotePanel = taskTab.clickOnAddNewTaskBtn();
        taskNotePanel.doWaitTillFullyLoaded(TimeOuts.TWO_SECOND);
        String actualTitle = taskNotePanel.getPanelTitle();
        String expectedTitle = (String) inputMap.get("title");
        ExtentHTMLReportManager.getInstance().addStep("Expected Title", expectedTitle);
        ExtentHTMLReportManager.getInstance().addStep("Actual Title", actualTitle);
        assertThat(actualTitle).contains(expectedTitle);

    }

    @Test(description = "Test  Add New Task Functionality", priority = 4)
    public void testAddNewTaskFunctionality() {
        if (!taskTab.isAddNewTaskButtonAvailable()) {
            throw new SkipException("Add New Task Button IS Not Available Hence Skipping Add New Task Test");
        }
        NotificationPanel notificationPanel = taskNotePanel.enterUserForTask((String) inputMap.get("assignedToUser"))
                .enterTaskType((String) inputMap.get("taskType"))
                .enterDescription((String) inputMap.get("description"))
                .enterNote((String) inputMap.get("note"))
                .clickOnSaveTaskBtn();
        notificationPanel.waitTillDisplayed();
        String displayedMessage = notificationPanel.getDisplayedMessage();
        notificationPanel.waitTillDisappears();
        taskTab.getDataGrid();
        assertThat(displayedMessage).isEqualTo(NotificationPanel.CREATE_SUCCESS_MESSAGE);
    }

    @Test(description = "Test  that added note is displayed in current note section", priority = 5)
    public void testAddedNoteIsDisplayedCurrentNoteSection() {
        if (!taskTab.isAddNewTaskButtonAvailable()) {
            throw new SkipException("Add New Task Button IS Not Available Hence Skipping Add New Task Test");
        }
        taskNotePanel = taskTab.getDataGrid().getRow(FIRST_ROW).click();
        taskNotePanel.doWaitTillFullyLoaded(TimeOuts.TEN_SECONDS);
        TaskTabNoteTabPanel taskTabNoteTabPanel = taskNotePanel.getNoteTabPanel();
        CurrentNoteTab currentNoteTab = (CurrentNoteTab) taskTabNoteTabPanel.getDefaultTab();
        String actualNote = currentNoteTab.getNote();
        String expectedNote = (String) inputMap.get("note");
        ExtentHTMLReportManager.getInstance().addStep("Expected Note", expectedNote);
        ExtentHTMLReportManager.getInstance().addStep("Actual Note", actualNote);
        assertThat(expectedNote.trim()).isEqualTo(actualNote.trim());
    }

    @Test(description = "Test  Update Task Functionality", priority = 6)
    public void testUpdateTaskFunctionality() {
        if (!taskTab.isAddNewTaskButtonAvailable()) {
            throw new SkipException("Add New Task Button IS Not Available Hence Skipping Add New Task Test");
        }
        taskNotePanel = taskTab.getDataGrid().getRow(FIRST_ROW).click();
        taskNotePanel.doWaitTillFullyLoaded(TimeOuts.FIVE_SECONDS);
        NotificationPanel notificationPanel = taskNotePanel
                .enterDescription((String) updatedInputMap.get("description"))
                .enterNote((String) updatedInputMap.get("note"))
                .clickOnSaveTaskBtn();
        notificationPanel.waitTillDisplayed();
        String displayedMessage = notificationPanel.getDisplayedMessage();
        notificationPanel.waitTillDisappears();
        assertThat(displayedMessage).isEqualTo(NotificationPanel.UPDATE_SUCCESS_MSG);
    }

    @Test(description = "Test that previous note tab is updated after updating task note", priority = 7)
    public void testUpdateTaskNoteTab() {
        if (!taskTab.isAddNewTaskButtonAvailable()) {
            throw new SkipException("Add New Task Button IS Not Available Hence Skipping Add New Task Test");
        }
        taskNotePanel = taskTab.getDataGrid().getRow(FIRST_ROW).click();
        taskNotePanel.doWaitTillFullyLoaded(TimeOuts.FIVE_SECONDS);
        PreviousNoteTab previousNoteTab = (PreviousNoteTab) taskNotePanel.getNoteTabPanel().clickOnTab(PreviousNoteTab.class);
        String actualNote = previousNoteTab.getNote();
        String expectedNote = (String) updatedInputMap.get("note");
        ExtentHTMLReportManager.getInstance().addStep("Expected Note", expectedNote);
        ExtentHTMLReportManager.getInstance().addStep("Actual Note", actualNote);
        assertThat(expectedNote).isEqualTo(actualNote);

    }

    @Test(description = "Test Complete Task Functionality", priority = 8)
    public void completeTaskTest() {
        List<String> initialRowData = taskTab.getDataGrid().getRow(FIRST_ROW).getDataAsList();
        if (!taskTab.isAddNewTaskButtonAvailable()) {
            throw new SkipException("Add New Task Button IS Not Available Hence Skipping Add New Task Test");
        }
        DataGrid dataGrid = taskTab.getDataGrid();
        taskNotePanel = dataGrid.getRow(FIRST_ROW).click();
        taskNotePanel.doWaitTillFullyLoaded(TimeOuts.FIVE_SECONDS);
        taskTab = taskNotePanel.clickOnCompleteTaskBtn();
        WaitUtils.waitForSeconds(TimeOuts.FIVE_SECONDS);
        List<String> rowData = taskTab.getDataGrid().getRow(FIRST_ROW).getDataAsList();
        assertThat(rowData).isNotEqualTo(initialRowData);
    }


}
