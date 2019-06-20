package com.advalent.automation.impl.pages.search.globalsearch;

import com.advalent.automation.api.annotations.LogStep;
import com.advalent.automation.api.annotations.inputfield.CustomElement;
import com.advalent.automation.api.components.tab.ITab;
import com.advalent.automation.api.constants.TimeOuts;
import com.advalent.automation.impl.component.datagrid.Column;
import com.advalent.automation.impl.component.datagrid.ColumnBuilder;
import com.advalent.automation.impl.component.datagrid.DataGrid;
import com.advalent.automation.impl.component.datagrid.DataGridBuilder;
import com.advalent.automation.impl.component.inputelements.*;
import com.advalent.automation.impl.pages.common.AbstractSearchPage;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.ViewEventPage;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.memberinformation.MemberInformationTab;
import com.advalent.automation.reporting.ExtentHTMLReportManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EventIncidentSearchTab extends AbstractSearchPage implements ITab {

    public static final String EVENT_ID_COLUMN = "Event ID";
    public static final String PATIENT_NAME_COLUMN = "Patient Name";
    public static final int FIRST = 1;
    public static final String DATAGRID_XPATH = "//table[contains(@class,'table table-bordered table-condensed table-striped table-hover')]";
    @CustomElement(xpath = "//*[@id=\"EventID\"]")
    public TextBox eventId;
    @CustomElement(xpath = "//*[@id=\"FirstName\"]")
    public TextBox patientFirstName;
    @CustomElement(xpath = "//*[@id=\"LastName\"]")
    public TextBox patientLastName;
    @CustomElement(xpath = "//*[@id=\"IncidentId\"]")
    public TextBox incidentId;
    @CustomElement(xpath = "//*[@id=\"PatientId\"]")
    public TextBox patientId;
    @CustomElement(xpath = "//*[@id=\"PatientDOB\"]")
    public TextBox patientDOB;
    @CustomElement(xpath = "//*[@id=\"MajorClient\"]")
    public DropDown majorClient;
    @CustomElement(xpath = "//*[@id=\"EventOwnerName\"]")
    public AutoSuggest eventOwner;
    @CustomElement(xpath = "//*[@id=\"ClientList\"]")
    public MultipleAutoComplete client;
    @CustomElement(xpath = "//*[@id=\"EventStatusList\"]")
    public MultipleAutoComplete eventStatus;
    @CustomElement(xpath = "//*[@id=\"EmployerGroupList\"]")
    public MultipleAutoComplete employerGroup;
    @CustomElement(xpath = "//*[@id=\"incidentonly\"]")
    public CheckBox incidentOnly;
    @CustomElement(xpath = "//*[@id=\"InvestigationSource\"]")
    public CheckBox clientPortalIncident;


    @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div/form/div/div/ul/li[1]/a/tab-heading/span")
    public WebElement tabTitle;
    @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div/form/div/div/div/div[1]/ng-include/div/div/form/div")
    WebElement searchInputPanel;
    @FindBy(xpath = "//*[@id=\"searchUniversal\"]")
    WebElement searchBtn;
    @FindBy(xpath = "//*[@id=\"searchClear\"]")
    WebElement clearBtn;
    @FindBy(xpath = "//*[@id=\"addNewIncident\"]")
    WebElement addIncidentreportBtn;

    @FindBy(xpath = "//*[@id=\"addNewClient\"]")
    WebElement addManualEventBtn;

    DataGrid dataGrid;

    public EventIncidentSearchTab(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        dataGrid = DataGridBuilder.createDataGridOf(GlobalSearchDataGrid.class, DATAGRID_XPATH)
                .setRowClass(GlobalSearchDataGridRow.class)
                .buildColumn(EVENT_ID_COLUMN)
                .makeDrillable()
                .setClickableChildElementXpath("./span/a")
                .drillsTo(ViewEventPage.class)
                .buildColumnAndGetDataGridBuilder()
                .buildColumn(PATIENT_NAME_COLUMN)
                .makeDrillable()
                .setClickableChildElementXpath("./span/a")
                .drillsTo(MemberInformationTab.class)
                .buildColumnAndGetDataGridBuilder()
                .build(driver);

        Column taskNoteColumn = ColumnBuilder.createColumn(EVENT_ID_COLUMN).makeDrillable()
                .setClickableChildElementXpath("./span/a")
                .drillsTo(ViewEventPage.class)
                .build();



    }

    @Override
    protected WebElement getSearchButton() {
        return searchBtn;
    }

    @Override
    protected WebElement getClearButton() {
        return clearBtn;
    }

    @Override
    protected WebElement getAddButton() {
        return addIncidentreportBtn;
    }

    @Override
    public DataGrid getDataGrid() {
        return this.dataGrid;
    }

    @Override
    @LogStep(step = "Clicking On Add Incident Report Button")
    public <T> T clickOnAddButton() {
        logger.info("Clicking On Add Incident Report Button buildDataGridAs {} page", this.getClass());
        getAddButton().click();
        return null;
    }

    @Override
    public String getErrorMessage() {
        return driver.findElement(By.xpath("//*[@id=\"searchCriteriaReq\"]")).getText().trim();
    }

    @LogStep(step = "Clicking On Add Manual Report Button")
    public <T> T clickOnAddManualEventButton() {
        logger.info("Clicking On Add Manual Report Button of {} page", this.getClass());

        addManualEventBtn.click();
        return null;
    }

    @Override
    public boolean isFullyLoaded() {
        return searchInputPanel.isDisplayed();
    }

    @Override
    public String getTabTitle() {
        return tabTitle.getText();
    }

    @Override
    public String getTabName() {
        return "Event/Incident Search";
    }


    @LogStep(step = "Enter Event Id")
    public EventIncidentSearchTab enterEventId(String eventId) {
        logger.info("Entering Event Id , Value:{}", eventId);
        this.eventId.clearValue().enterValue(eventId);
        return this;
    }

    @LogStep(step = "Selecting Major Client")
    public EventIncidentSearchTab selectMajorClient(String majorClient) {
        logger.info("Selecting Major Client, Value:{}", majorClient);
        this.majorClient.selectOption(majorClient);
        return this;
    }

    @LogStep(step = "Entering Event Owner")
    public EventIncidentSearchTab enterEventOwner(String eventOwnerName) {
        this.eventOwner.enterValue(eventOwnerName);
        return this;
    }

    @LogStep(step = "Entering Client")
    public EventIncidentSearchTab enterClient(String clientName) {
        this.client.enterValue(clientName);
        return this;
    }

    @LogStep(step = "Entering Event Status")
    public EventIncidentSearchTab enterEventStatus(String eventStatus) {
        this.eventStatus.enterValue(eventStatus);
        return this;
    }


    @LogStep(step = "Entering Employer Group")
    public EventIncidentSearchTab enterEmployerGroup(String employerGroup) {
        this.employerGroup.enterValue(employerGroup);
        return this;
    }

    @LogStep(step = "Entering Patient First Name")
    public EventIncidentSearchTab enterPatientFirstName(String patientFirstName) {
        this.patientFirstName.enterValue(patientFirstName);
        return this;
    }

    @LogStep(step = "Entering Patient Last Name")
    public EventIncidentSearchTab enterPatientLastName(String patientLastName) {
        this.patientLastName.enterValue(patientLastName);
        return this;
    }

    @LogStep(step = "Entering Incident Id")
    public EventIncidentSearchTab enterIncidentId(String incidentId) {
        this.incidentId.enterValue(incidentId);
        return this;
    }

    @LogStep(step = "Entering Patient Id")
    public EventIncidentSearchTab enterPatientId(String patientId) {
        this.patientId.enterValue(patientId);
        return this;
    }

    @LogStep(step = "Entering Patient Id")
    public EventIncidentSearchTab enterPatientDob(String patientDOB) {
        this.patientDOB.enterValue(patientDOB);
        return this;
    }


    @LogStep(step = "Clicking On First Rpw Of DataGrid")
    public ViewEventPage clickOnFirstRowOfDataGrid() {
        return this.dataGrid.getRow(FIRST).getCell(FIRST).click();

    }

    @LogStep(step = "Clicking On Incident Only CheckBox")
    public EventIncidentSearchTab selectIncidentOnly() {
        this.incidentOnly.check();
        return this;

    }

    @LogStep(step = "Clicking On Incident From Client Portal CheckBox")
    public EventIncidentSearchTab selectIncidentFromClientPortal() {
        this.clientPortalIncident.check();
        return this;

    }

    public ViewEventPage goToViewEventPageForEventId(int eventId) {
        ExtentHTMLReportManager.getInstance().addStep("Navigating To View Event Page For Event Id", "" + eventId);
        this.enterEventId("" + eventId);
        this.dataGrid = this.clickOnSearchButton();
        return clickOnFirstRowOfDataGrid();
    }

    public ViewEventPage goToViewEventPageForEventStatus(String status) {
        ExtentHTMLReportManager.getInstance().addStep("Navigating To View Event Page For Event Status", "" + status);
        this.enterEventStatus(status);
        this.dataGrid = this.clickOnSearchButton();
        this.dataGrid.waitTillDataIsLoaded(TimeOuts.SIXTY_SECONDS);
        return clickOnFirstRowOfDataGrid();
    }

    public ViewEventPage goToViewEventPageForMajorClient(String majorClientName) {
        ExtentHTMLReportManager.getInstance().addStep("Navigating To View Event Page For major client", "" + majorClientName);
        this.selectMajorClient(majorClientName);
        this.dataGrid = this.clickOnSearchButton();
        this.dataGrid.waitTillDataIsLoaded(TimeOuts.SIXTY_SECONDS);
        return clickOnFirstRowOfDataGrid();
    }

    @LogStep(step = "Selecting Event Status")
    public EventIncidentSearchTab selectEventStatus(String eventStatus) {
        this.eventStatus.enterValue(eventStatus);
        return this;
    }

}
