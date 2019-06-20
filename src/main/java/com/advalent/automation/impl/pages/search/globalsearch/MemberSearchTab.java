package com.advalent.automation.impl.pages.search.globalsearch;

import com.advalent.automation.api.annotations.LogStep;
import com.advalent.automation.api.annotations.inputfield.CustomElement;
import com.advalent.automation.api.components.search.ISearchPage;
import com.advalent.automation.api.components.tab.ITab;
import com.advalent.automation.impl.component.datagrid.DataGrid;
import com.advalent.automation.impl.component.datagrid.DataGridBuilder;
import com.advalent.automation.impl.component.inputelements.DropDown;
import com.advalent.automation.impl.component.inputelements.MultipleAutoComplete;
import com.advalent.automation.impl.component.inputelements.TextBox;
import com.advalent.automation.impl.pages.common.AbstractSearchPage;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.memberinformation.MemberInformationTab;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MemberSearchTab extends AbstractSearchPage implements ITab {

    public static final String Data_GRID_XPATH = "//table[contains(@class,'table table-bordered table-condensed table-striped table-hover')]";
    @CustomElement(xpath = "//*[@id=\"FirstName\"]")
    private TextBox patientFirstName;
    @CustomElement(xpath = "//*[@id=\"LastName\"]")
    private TextBox patientLastName;
    @CustomElement(xpath = "//*[@id=\"PatientState\"]")
    private DropDown patientState;
    @CustomElement(xpath = "//*[@id=\"ClientList\"]")
    private MultipleAutoComplete client;
    @CustomElement(xpath = "//*[@id=\"PatientId\"]")
    private TextBox patientId;
    @CustomElement(xpath = "//*[@id=\"gender\"]")
    private DropDown gender;
    @CustomElement(xpath = "//*[@id=\"EmployerGroupList\"]")
    private MultipleAutoComplete employerGroup;
    @CustomElement(xpath = "//*[@id=\"PatientDOB\"]")
    private TextBox patientDOB;
    @CustomElement(xpath = "//*[@id=\"MajorClient\"]")
    private DropDown majorClient;
    @CustomElement(xpath = "//*[@id=\"LineofBusiness\"]")
    private DropDown lineOfBusiness;


    @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div/form/div/div/ul/li[2]/a/tab-heading/span")
    public WebElement tabTitle;

    DataGrid dataGrid;

    public MemberSearchTab(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        dataGrid = DataGridBuilder.createDataGridOf(GlobalSearchDataGrid.class, Data_GRID_XPATH)
                .setRowClass(GlobalSearchDataGridRow.class)
                .buildColumn("Member ID")
                .makeDrillable()
                .setClickableChildElementXpath("./span/a")
                .drillsTo(MemberInformationTab.class)
                .buildColumnAndGetDataGridBuilder()
                .buildColumn("Member Name")
                .makeDrillable()
                .setClickableChildElementXpath("./span/a")
                .drillsTo(MemberInformationTab.class)
                .buildColumnAndGetDataGridBuilder()
                .build(driver);

    }

    @LogStep(step = "Selecting Patient State")
    public MemberSearchTab selectPatientState(String patientState) {
        this.patientState.selectOption(patientState);
        return this;
    }

    @LogStep(step = "Selecting Gender")
    public MemberSearchTab selectGender(String gender) {
        this.gender.selectOption(gender);
        return this;
    }

    @LogStep(step = "Selecting Major Client")
    public ISearchPage selectMajorClient(String majorClient) {
        this.majorClient.selectOption(majorClient);
        return this;
    }

    @LogStep(step = "Entering Patient Name")
    public MemberSearchTab enterPatientFirstName(String patientFirstName, String patientLastName) {
        this.patientFirstName.enterValue(patientFirstName);
        this.patientLastName.enterValue(patientLastName);
        return this;
    }

    @Override
    protected WebElement getSearchButton() {
        return driver.findElement(By.id("searchUniversal"));
    }

    @Override
    protected WebElement getClearButton() {
        return driver.findElement(By.id("searchClear"));
    }

    @Override
    protected WebElement getAddButton() {
        return null;
    }

    @Override
    public boolean isFullyLoaded() {
        return this.tabTitle.isDisplayed();
    }

    @Override
    public String getTabName() {
        return "Member Search";
    }

    @Override
    public String getTabTitle() {
        return tabTitle.getText();
    }

    @Override
    public DataGrid getDataGrid() {
        return dataGrid;
    }

    @Override
    public <T> T clickOnAddButton() {
        return null;
    }

    @Override
    public String getErrorMessage() {
        return driver.findElement(By.id("searchCriteriaReq")).getText();
    }


    public TextBox getPatientFirstName() {
        return patientFirstName;
    }

    public TextBox getPatientLastName() {
        return patientLastName;
    }

    public DropDown getPatientState() {
        return patientState;
    }

    public MultipleAutoComplete getClient() {
        return client;
    }

    public TextBox getPatientId() {
        return patientId;
    }

    public DropDown getGender() {
        return gender;
    }

    public MultipleAutoComplete getEmployerGroup() {
        return employerGroup;
    }

    public TextBox getPatientDOB() {
        return patientDOB;
    }

    public DropDown getMajorClient() {
        return majorClient;
    }

    public DropDown getLineOfBusiness() {
        return lineOfBusiness;
    }


}
