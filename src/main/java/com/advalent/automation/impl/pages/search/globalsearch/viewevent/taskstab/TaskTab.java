package com.advalent.automation.impl.pages.search.globalsearch.viewevent.taskstab;

import com.advalent.automation.api.annotations.LogStep;
import com.advalent.automation.api.components.tab.ITab;
import com.advalent.automation.impl.component.datagrid.DataGrid;
import com.advalent.automation.impl.component.datagrid.DataGridBuilder;
import com.advalent.automation.impl.component.datagrid.DataGridProperties;
import com.advalent.automation.impl.pages.common.AbstractWebComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TaskTab extends AbstractWebComponent implements ITab {

    @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div/form/div[2]/div[1]/div/div/div/div[2]/div/div/div/h4")
    public WebElement pageTitle;

    @FindBy(xpath = "//*[@id=\"addnewTask\"]")
    public WebElement addNewTaskBtn;

    TaskNotePanel taskNotePanel;

    DataGrid taskDataGrid;

    @LogStep(step = "Clicking on Add New Task Button")
    public TaskNotePanel clickOnAddNewTaskBtn() {
        addNewTaskBtn.click();
        return new TaskNotePanel(getDriver());
    }

    public TaskTab(WebDriver driver) {
        super(driver);
        taskDataGrid = DataGridBuilder.createDataGridWithXpath("//*[@id=\"Table-task\"]")
                .addRowProperties(DataGridProperties.Drillable)
                .setRowDrillPage(TaskNotePanel.class).build(driver);
        PageFactory.initElements(getDriver(), this);
    }

    @Override
    public String getTabName() {
        return "Tasks";
    }

    @Override
    public String getTabTitle() {
        return pageTitle.getText();
    }


    @Override
    public boolean isFullyLoaded() {
        return this.pageTitle.isDisplayed();
    }

    public boolean isAddNewTaskButtonAvailable() {
        return addNewTaskBtn.isDisplayed();

    }

    public DataGrid getDataGrid() {
        return taskDataGrid;
    }

}
