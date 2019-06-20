package com.advalent.automation.impl.pages.search.globalsearch.viewevent.memberinformation.policyholdeinfo;

import com.advalent.automation.api.annotations.LogStep;
import com.advalent.automation.api.annotations.inputfield.CustomElement;
import com.advalent.automation.impl.component.datagrid.DataGridBuilder;
import com.advalent.automation.impl.component.datagrid.DataGrid;
import com.advalent.automation.impl.component.inputelements.AutoSuggest;
import com.advalent.automation.impl.component.inputelements.DropDown;
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

    DataGrid dataGrid;
    @CustomElement(xpath = "//*[@id=\"Street1\"]")
    public TextBox policyHolderAddress1;

    @CustomElement(xpath = "//*[@id=\"Street2\"]")
    public TextBox policyHolderAddress2;

    @CustomElement(xpath = "//*[@id=\"City\"]")
    public AutoSuggest policyHolderCity;

    @CustomElement(xpath = "//*[@id=\"State\"]")
    public DropDown policyHolderState;

    @CustomElement(xpath = "//*[@id=\"Zip\"]")
    public AutoSuggest policyHolderZip;

    @CustomElement(xpath = "//*[@id=\"PhoneNumber\"]")
    public TextBox policyHolderHomePhone;

    @CustomElement(xpath = "//*[@id=\"CellPhoneNumber\"]")
    public TextBox policyHolderMobilePhone;

    @CustomElement(xpath = "//*[@id=\"WorkPhone\"]")
    public TextBox policyHolderBusinessPhone;
    @CustomElement(xpath = "//*[@id=\"WorkPhoneExtension\"]")
    public TextBox policyHolderBusinessPhoneExt;

    @CustomElement(xpath = "//*[@id=\"AddressSource1\"]")
    public DropDown policyHolderSource;


    @CustomElement(xpath = "//*[@id=\"EmailAddress\"]")
    public TextBox policyHolderEmail;

    public ContactInformationSection(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        if (isDataGridVisible()) {
            dataGrid = DataGridBuilder.createDataGridWithXpath("//*[@id=\"Table-Contact\"]").build(driver);
        }
    }


    @Override
    public boolean isFullyLoaded() {
        return getDriver().findElement(By.xpath("//*[@id=\"patientInfo\"]/div/form/div/div/div[3]/div[1]")).isDisplayed();
    }


    public ContactInformationSection clickOnAddNewContactInformationButton() {
        addNewContactButton.click();
        return this;

    }

    @LogStep(step = "Entering Address 1")
    public ContactInformationSection enterAddress1(String address1) {
        policyHolderAddress1.enterValue(address1);
        return this;
    }

    @LogStep(step = "Entering Address 2")
    public ContactInformationSection enterAddress2(String address2) {
        policyHolderAddress1.enterValue(address2);
        return this;
    }

    public boolean isDataGridVisible() {
        return SeleniumUtils.isElementVisible(driver, "//*[@id=\"Table-Contact\"]");

    }

    public int getTotalContactRecord() {
        if (isDataGridVisible()) {
            return dataGrid.getPaginationComponent().getNumberOfRecordsDisplayed();
        } else {
            return 0;
        }
    }
}
