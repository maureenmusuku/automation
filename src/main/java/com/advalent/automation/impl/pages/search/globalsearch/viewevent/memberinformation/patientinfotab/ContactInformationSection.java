package com.advalent.automation.impl.pages.search.globalsearch.viewevent.memberinformation.patientinfotab;

import com.advalent.automation.api.annotations.LogStep;
import com.advalent.automation.api.annotations.inputfield.CustomElement;
import com.advalent.automation.impl.component.datagrid.DataGridBuilder;
import com.advalent.automation.impl.component.datagrid.DataGrid;
import com.advalent.automation.impl.component.inputelements.AutoSuggest;
import com.advalent.automation.impl.component.inputelements.DropDown;
import com.advalent.automation.impl.component.inputelements.Phone;
import com.advalent.automation.impl.component.inputelements.TextBox;
import com.advalent.automation.impl.pages.common.AbstractWebComponent;
import com.advalent.automation.selenium.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInformationSection extends AbstractWebComponent {

    @FindBy(xpath = "//*[@id=\"addContact\"]")
    WebElement addNewContactButton;

    private DataGrid dataGrid;
    @CustomElement(xpath = "//*[@id=\"Street1\"]")
    public TextBox patientAddress1;

    @CustomElement(xpath = "//*[@id=\"Street2\"]")
    public TextBox patientAddress2;

    @CustomElement(xpath = "//*[@id=\"City\"]")
    public AutoSuggest patientCity;

    @CustomElement(xpath = "//*[@id=\"State\"]")
    public DropDown patientState;

    @CustomElement(xpath = "//*[@id=\"Zip\"]")
    public AutoSuggest patientZip;

    @CustomElement(xpath = "//*[@id=\"PhoneNumber\"]")
    public TextBox patientHomePhone;

    @CustomElement(xpath = "//*[@id=\"CellPhoneNumber\"]")
    public TextBox patientMobilePhone;

    @CustomElement(ids = {"WorkPhone", "WorkPhoneExtension"})
    public Phone patientBusinessPhone;

    @CustomElement(xpath = "//*[@id=\"AddressSource\"]")
    public DropDown source;

    @CustomElement(xpath = "//*[@id=\"EmailAddress\"]")
    public TextBox patientEmail;

    public ContactInformationSection(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);


    }


    @Override
    public boolean isFullyLoaded() {
        return getDriver().findElement(By.xpath("//*[@id=\"patientInfo\"]/div/form/div/div/div[3]/div[1]")).isDisplayed();
    }

    public boolean isDataGridVisible() {
        return SeleniumUtils.isElementVisible(driver, "//*[@id=\"Table-Contact\"]");

    }

    public DataGrid getDataGrid() {
        if (dataGrid == null) {
            dataGrid = DataGridBuilder.createDataGridWithXpath("//*[@id=\"Table-Contact\"]").build(driver);
        }
        return dataGrid;

    }

    public ContactInformationSection clickOnAddNewContactInformationButton() {
        addNewContactButton.click();
        return this;

    }

    @LogStep(step = "Entering Address 1")
    public ContactInformationSection enterAddress1(String address1) {
        patientAddress1.enterValue(address1);
        return this;
    }

    @LogStep(step = "Entering Address 2")
    public ContactInformationSection enterAddress2(String address2) {
        patientAddress1.enterValue(address2);
        return this;
    }

    public int getTotalContactRecord() {
        if (isDataGridVisible()) {
            return getDataGrid().getPaginationComponent().getNumberOfRecordsDisplayed();
        } else {
            return 0;
        }

    }
}
