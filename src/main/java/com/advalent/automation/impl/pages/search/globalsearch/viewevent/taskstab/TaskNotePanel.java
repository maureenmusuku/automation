package com.advalent.automation.impl.pages.search.globalsearch.viewevent.taskstab;

import com.advalent.automation.api.annotations.LogStep;
import com.advalent.automation.api.annotations.inputfield.CustomElement;
import com.advalent.automation.api.constants.TimeOuts;
import com.advalent.automation.impl.component.inputelements.AutoSuggest;
import com.advalent.automation.impl.component.inputelements.CheckBox;
import com.advalent.automation.impl.component.inputelements.DropDown;
import com.advalent.automation.impl.component.inputelements.TextBox;
import com.advalent.automation.impl.component.notificationpanel.NotificationPanel;
import com.advalent.automation.impl.pages.common.AbstractWebComponent;
import org.jboss.netty.util.Timeout;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TaskNotePanel extends AbstractWebComponent {

    @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div/form/div[2]/div[1]/div/div/div/div[2]/div/div/div/span/dtasks/div/div/div[1]/h3")
    WebElement pageTitle;

    @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div/form/div[2]/div[1]/div/div/div/div[2]/div/div/div/span/dtasks/div/div/form/div/div/div[5]/div[2]/div/a[1]")
    WebElement completeTaskBtn;

    @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div/form/div[2]/div[1]/div/div/div/div[2]/div/div/div/span/dtasks/div/div/form/div/div/div[5]/div[2]/div/a[3]")
    WebElement saveTaskBtn;

    @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div/form/div[2]/div[1]/div/div/div/div[2]/div/div/div/span/dtasks/div/div/form/div/div/div[5]/div[2]/div/a[2]")
    WebElement exitBtn;

    @CustomElement(xpath = "//*[@id=\"TaskAssignedDate\"]")
    public TextBox assignedDate;

    @CustomElement(xpath = "//*[@id=\"TaskDueDate\"]")
    public TextBox dueDate;

    @CustomElement(xpath = "//*[@id=\"TaskTypeKey\"]")
    public DropDown taskType;

    //autosuggest
    @CustomElement(xpath = "//*[@id=\"UserName\"]")
    public AutoSuggest assignedToUser;

    @CustomElement(xpath = "//*[@id=\"AssignedByUserName\"]")
    public TextBox assignedBy;

    @CustomElement(xpath = "//*[@id=\"IsHighPriority\"]")
    public CheckBox highPriority;

    //disabled
    @CustomElement(xpath = "//*[@id=\"IsHighPriority\"]")
    public CheckBox decline;

    @CustomElement(xpath = "//*[@id=\"TaskDescription\"]")
    public TextBox description;

    @CustomElement(xpath = "//*[@id=\"TaskNote\"]")
    public TextBox notes;

    TaskTabNoteTabPanel taskTabNoteTabPanel;
    public TaskNotePanel(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        taskTabNoteTabPanel = new TaskTabNoteTabPanel(driver);
    }

    @LogStep(step = "Clicking on Complete Task Button")
    public TaskTab clickOnCompleteTaskBtn() {
        completeTaskBtn.click();
        return new TaskTab(getDriver());
    }

    @LogStep(step = "Clicking on Save Task Button")
    public NotificationPanel clickOnSaveTaskBtn() {
        saveTaskBtn.click();
        doWaitTillFullyLoaded(TimeOuts.FIVE_SECONDS);
        return new NotificationPanel(getDriver());
    }

    @LogStep(step = "Clicking on Exit Button")
    public TaskTab clickOnExitBtn() {
        exitBtn.click();
        return new TaskTab(getDriver());
    }

    @LogStep(step = "Entering user for a task")
    public TaskNotePanel enterUserForTask(String userName) {
        assignedToUser.enterValue(userName);
        return this;
    }

    @LogStep(step = "Entering due date for a task")
    public TaskNotePanel enterDueDateTask(String dueDate) {
        assignedToUser.enterValue(dueDate);
        return this;
    }

    @LogStep(step = "Toggling High Priority")
    public TaskNotePanel toggleHighPriority() {
        highPriority.check();
        return this;
    }

    @LogStep(step = "Selecting task type for a task")
    public TaskNotePanel selectTaskType(String taskType) {
        this.taskType.selectOption(taskType);
        return this;
    }

    @LogStep(step = "Adding description for a task")
    public TaskNotePanel addDescription(String description) {
        this.description.enterValue(description);
        return this;
    }

    @LogStep(step = "Adding description for a task")
    public TaskNotePanel addNotes(String notes) {
        this.notes.enterValue(notes);
        return this;
    }

    @Override
    public boolean isFullyLoaded() {
        return this.pageTitle.isDisplayed();
    }

    public String getPanelTitle() {
        return this.pageTitle.getText();
    }

    @LogStep(step = "Adding task type")
    public TaskNotePanel enterTaskType(String assignedToUser) {
        taskType.selectOption(assignedToUser);
        return this;
    }

    @LogStep(step = "Adding description")
    public TaskNotePanel enterDescription(String description) {
        this.description.clearValue();
        this.description.enterValue(description);
        return this;
    }

    @LogStep(step = "Adding note")
    public TaskNotePanel enterNote(String note) {
        this.notes.clearValue();
        this.notes.enterValue(note);
        return this;
    }

    public TaskTabNoteTabPanel getNoteTabPanel() {
        return taskTabNoteTabPanel;
    }

}
