package com.advalent.automation.impl.pages.search.globalsearch.viewevent.activitylog;

import com.advalent.automation.api.annotations.LogStep;
import com.advalent.automation.api.annotations.inputfield.CustomElement;
import com.advalent.automation.api.components.tab.ITab;
import com.advalent.automation.api.constants.TimeOuts;
import com.advalent.automation.impl.component.datagrid.Column;
import com.advalent.automation.impl.component.datagrid.ColumnBuilder;
import com.advalent.automation.impl.component.datagrid.DataGrid;
import com.advalent.automation.impl.component.datagrid.DataGridBuilder;
import com.advalent.automation.impl.component.inputelements.AutoSuggest;
import com.advalent.automation.impl.component.inputelements.CheckBox;
import com.advalent.automation.impl.component.inputelements.DateRange;
import com.advalent.automation.impl.component.inputelements.DropDown;
import com.advalent.automation.impl.pages.common.AbstractWebComponent;
import com.advalent.automation.impl.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ActivityLogTab extends AbstractWebComponent implements ITab {
    @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div/form/div[2]/div[1]/div/div/div/div[9]/div/div/div/div[2]/activity-log/h4")
    private WebElement pageTitle;

    @FindBy(xpath = "//*[@id=\"searchClear\"]")
    private WebElement clearBtn;

    @FindBy(xpath = "//*[@id=\"searchMember\"]")
    private WebElement searchBtn;

    @FindBy(xpath = "//*[@id=\"addActivity\"]")
    private WebElement addNewActivityBtn;


    @CustomElement(ids = {"FromDate", "ThruDate"})
    private DateRange activityDate;

    @CustomElement(xpath = "//*[@id=\"narrative\"]")
    private CheckBox displayNarrativeOnly;

    @CustomElement(xpath = "//*[@id=\"ActivityType\"]")
    private DropDown activityType;

    @CustomElement(xpath = "//*[@id=\"ActivitySource\"]")
    private AutoSuggest activitySource;

    DataGrid activityLogDataGrid;

    ActivityDetailsSection activityDetailsSection;

    public ActivityLogTab(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        Column taskNoteColumn = ColumnBuilder.createColumn("Note").ofClass(TaskNoteCell.class).build();
        activityLogDataGrid = DataGridBuilder.createDataGridWithXpath("//*[@id=\"logTable\"]")
                .setColumn(5, taskNoteColumn)
                .build(driver);

    }


    @Override
    public String getTabName() {
        return "Activity Log";
    }

    @Override
    public String getTabTitle() {
        return pageTitle.getText().split("\\n")[0];
    }

    @Override
    public boolean isFullyLoaded() {
        return pageTitle.isDisplayed();
    }


    public DataGrid getDataGrid() {
        return activityLogDataGrid;
    }


    @LogStep(step = "Clicking On Add New Activity Log Button")
    public ActivityDetailsSection clickOnAddNewActivityButton() {
        addNewActivityBtn.click();
        activityDetailsSection = new ActivityDetailsSection(getDriver());
        return activityDetailsSection;
    }

    @LogStep(step = "Entering Date Range Button")
    public ActivityLogTab enterDateRange(Object dateFrom, Object dateTo) {
        activityDate.from((String) dateFrom).to((String) dateTo);
        return this;
    }

    @LogStep(step = "Click On Filter Button")
    public DataGrid clickOnFilterButton() {
        searchBtn.click();
        waitUntilDataGridIsDisplayed(TimeOuts.SIXTY_SECONDS);

        return activityLogDataGrid;
    }

    @LogStep(step = "Select Activity Type Button")
    public ActivityLogTab selectActivityType(String activityType) {
        this.activityType.selectOption(activityType);
        return this;
    }

    @LogStep(step = "Entering Activity Source")
    public ActivityLogTab enterActivitySource(String activitySource) {
        this.activitySource.enterValue(activitySource);
        return this;
    }

    @LogStep(step = "Clicking On Clear Filter Button")
    public ActivityLogTab clickOnClearFilterButton() {
        this.clearBtn.click();
        waitUntilDataGridIsDisplayed(TimeOuts.SIXTY_SECONDS);
        return this;
    }

    public void waitUntilDataGridIsDisplayed(int timeOut) {
        WaitUtils.waitUntil(getDriver(), timeOut,
                o -> getDriver().findElement(By.xpath("//*[@id=\"logTable\"]/tbody/tr")).isDisplayed());
    }
}
