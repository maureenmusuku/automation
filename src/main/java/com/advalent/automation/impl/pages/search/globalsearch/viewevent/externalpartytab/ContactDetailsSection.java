package com.advalent.automation.impl.pages.search.globalsearch.viewevent.externalpartytab;

import com.advalent.automation.api.annotations.LogStep;
import com.advalent.automation.api.annotations.inputfield.CustomElement;
import com.advalent.automation.impl.component.datagrid.DataGrid;
import com.advalent.automation.impl.component.datagrid.DataGridBuilder;
import com.advalent.automation.impl.component.inputelements.TextBox;
import com.advalent.automation.impl.component.datagrid.CheckBoxCell;
import com.advalent.automation.impl.pages.common.AbstractWebComponent;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

public class ContactDetailsSection extends AbstractWebComponent {

    public static final String CONTACT_DATAGRID_XPATH = "//*[@id=\"content\"]/div[3]/div/form/div[2]/div[1]/div/div/div/div[6]/div/div/div/div[2]/external-party/form/div[2]/div/div[1]/div[5]/table";
    DataGrid contactDataGrid;

    @CustomElement(xpath = "//*[@id=\"ContactFirstName\"]")
    public TextBox contactFirstName;

    @CustomElement(xpath = "//*[@id=\"ContactMiddleName\"]")
    public TextBox contactMiddleName;

    @CustomElement(xpath = "//*[@id=\"ContactLastName\"]")
    public TextBox contactLastName;

    @CustomElement(xpath = "//*[@id=\"ContactTitle\"]")
    public TextBox contactTitle;

    @FindBy(xpath = "//*[@id=\"IsPrimaryContact\"]")
    public WebElement primary;

    @CustomElement(xpath = "//*[@id=\"contactrecordstatus\"]")
    public TextBox inactive;

    @CustomElement(xpath = "//*[@id=\"ContactPhone1\"]")
    public TextBox contactBusinessPhone;

    @CustomElement(xpath = "//*[@id=\"ContactPhone1Ext\"]")
    public TextBox contactBusinessPhoneExt;

    @CustomElement(xpath = "//*[@id=\"ContactFax\"]")
    public TextBox contactFax;

    @CustomElement(xpath = "//*[@id=\"ContactPhone2\"]")
    public TextBox contactCell;

    @CustomElement(xpath = "//*[@id=\"ContactEmail\"]")
    public TextBox contactEmail;
    @CustomElement(xpath = "//*[@id=\"addContact\"]")
    WebElement addNewContactBtn;


    public ContactDetailsSection(WebDriver driver) {
        super(driver);
        if (this.isContactDataGridAvailable()) {
            initContactDataGrid();
        }
    }

    public boolean isContactDataGridAvailable() {
        try {
            return this.getDriver().findElement(By.xpath(CONTACT_DATAGRID_XPATH)).isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }


    @LogStep(step = "Clicking Add New Contact Button To Display the filled details in the grid")
    public DataGrid clickOnAddNewContactBtn() {
        addNewContactBtn.click();
        initContactDataGrid();
        return contactDataGrid;
    }

    private void initContactDataGrid() {
        contactDataGrid = DataGridBuilder.createDataGridWithXpath(CONTACT_DATAGRID_XPATH)
                .buildColumn("Delete").ofClass(CheckBoxCell.class).buildColumnAndGetDataGridBuilder()
                .build(driver);
    }


    @Override
    public boolean isFullyLoaded() {
        return contactFirstName.isFullyLoaded();
    }

    public String getPartyName() {
        return (this.contactFirstName.getValue() + "  " + contactMiddleName.getValue() + " " + contactLastName.getValue()).replace(" ", " ");
    }
}
