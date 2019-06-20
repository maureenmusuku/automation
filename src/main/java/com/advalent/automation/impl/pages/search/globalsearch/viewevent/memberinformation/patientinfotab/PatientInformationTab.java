package com.advalent.automation.impl.pages.search.globalsearch.viewevent.memberinformation.patientinfotab;

import com.advalent.automation.api.components.tab.ITab;
import com.advalent.automation.impl.component.datagrid.DataGrid;
import com.advalent.automation.impl.component.datagrid.DataGridBuilder;
import com.advalent.automation.impl.component.notificationpanel.NotificationPanel;
import com.advalent.automation.impl.pages.common.AbstractWebComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PatientInformationTab extends AbstractWebComponent implements ITab {

    public DataGrid eventDataGrid;

    public DataGrid incidentReportDataGrid;


    @FindBy(xpath = "//*[@id=\"patientInfo\"]/div/form/div/div/div[1]/div[1]")
    WebElement pageTitle;

    @FindBy(xpath = "//*[@id=\"viewSummary\"]")
    WebElement viewUpdatesBtn;

    @FindBy(xpath = "//*[@id=\"addContact\"]")
    WebElement addNewContactBtn;

    @FindBy(xpath = "//*[@id=\"saveQA\"]")
    WebElement saveBtn;

    @FindBy(xpath = "//*[@id=\"resetQA\"]")
    WebElement clearBtn;

    DemographicSection demographicSection;

    ContactInformationSection contactInformationSection;

    public PatientInformationTab(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        eventDataGrid = DataGridBuilder.createDataGridWithXpath("//*[@id=\"Table-otherEvents\"]").build(driver);
        demographicSection = new DemographicSection(getDriver());
        incidentReportDataGrid = DataGridBuilder.createDataGridWithXpath("//*[@id=\"Table-incident\"]").build(driver);
    }

    @Override
    public String getTabName() {
        return "Patient Information";
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
        if (contactInformationSection == null) {
            contactInformationSection = new ContactInformationSection(getDriver());
        }
        return contactInformationSection;
    }


    public ContactInformationSection clickOnAddNewContactBtn() {
        addNewContactBtn.click();
        return getContactInformationScreen();
    }

    public boolean isContactDetailsAvailable() {
        try {
            return driver.findElement(By.xpath("//*[@id=\"patientInfo\"]/div/form/div/div/div[3]/div/div")).isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public NotificationPanel clickOnSaveBtn() {
        saveBtn.click();
        return new NotificationPanel(getDriver());
    }
}
