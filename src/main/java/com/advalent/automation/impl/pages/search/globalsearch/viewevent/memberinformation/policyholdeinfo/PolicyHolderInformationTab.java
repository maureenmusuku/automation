package com.advalent.automation.impl.pages.search.globalsearch.viewevent.memberinformation.policyholdeinfo;

import com.advalent.automation.api.components.tab.ITab;
import com.advalent.automation.impl.component.datagrid.DataGrid;
import com.advalent.automation.impl.component.datagrid.DataGridBuilder;
import com.advalent.automation.impl.pages.common.AbstractWebComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PolicyHolderInformationTab extends AbstractWebComponent implements ITab {

    public DataGrid eventDataGrid;

    public DataGrid incidentReportDataGrid;


    @FindBy(xpath = "//*[@id=\"policyHolderInfo\"]/div/form/div/div/div[1]/div[1]")
    WebElement pageTitle;

    @FindBy(xpath = "//*[@id=\"viewSummary\"]")
    WebElement viewUpdatesBtn;

    @FindBy(xpath = "//*[@id=\"saveQA\"]")
    WebElement saveBtn;

    @FindBy(xpath = "//*[@id=\"resetQA\"]")
    WebElement clearBtn;

    DemographicSection demographicSection;

    ContactInformationSection contactInformationSection;

    public PolicyHolderInformationTab(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        eventDataGrid = DataGridBuilder.createDataGridWithXpath("//*[@id=\"policyHolderInfo\"]/div/form/div/div/div[3]/table").build(driver);
        demographicSection = new DemographicSection(getDriver());
        contactInformationSection = new ContactInformationSection(getDriver());
        incidentReportDataGrid = DataGridBuilder
                .createDataGridWithXpath("(//*[@id=\"Table-incident\"])[2]")
                .build(driver);
    }

    @Override
    public String getTabName() {
        return "Policy Holder Information";
    }

    @Override
    public String getTabTitle() {
        return pageTitle.getText();
    }


    @Override
    public boolean isFullyLoaded() {
        return pageTitle.isDisplayed();
    }

    public DemographicSection getDemographicInformationScreen() {
        return demographicSection;
    }

    public ContactInformationSection getContactInformationScreen() {
        return contactInformationSection;
    }


}
