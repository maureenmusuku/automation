package com.advalent.automation.impl.pages.search.globalsearch.viewevent.externalpartytab;

import com.advalent.automation.api.annotations.LogStep;
import com.advalent.automation.api.annotations.inputfield.CustomElement;
import com.advalent.automation.api.components.tab.ITab;
import com.advalent.automation.api.constants.TimeOuts;
import com.advalent.automation.api.exceptions.AutomationException;
import com.advalent.automation.impl.component.datagrid.DataGrid;
import com.advalent.automation.impl.component.loadingcomponent.LoadingComponent;
import com.advalent.automation.impl.component.datagrid.DataGridBuilder;
import com.advalent.automation.impl.component.inputelements.AutoSuggest;
import com.advalent.automation.impl.component.inputelements.CheckBox;
import com.advalent.automation.impl.component.inputelements.DropDown;
import com.advalent.automation.impl.component.inputelements.TextBox;
import com.advalent.automation.impl.pages.common.AbstractWebComponent;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.casecorrespondence.CaseCorrespondenceTab;
import com.advalent.automation.impl.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.SkipException;

public class ExternalPartyTab extends AbstractWebComponent implements ITab {

    public static final String TABLE_XPATH = "//*[@id=\"content\"]/div[3]/div/form/div[2]/div[1]/div/div/div/div[5]/div/div/div/span/div/table";
    public static final String FUND_SOURCE_XPATH = "//*[@id=\"content\"]/div[3]/div/form/div[2]/div[1]/div/div/div/div[5]/div/div/div/div[2]/external-party/form/div[2]/div/div[1]/div[1]/div[1]/div[2]/div[4]/span";
    public static final String PARTY_TYPE_XPATH = "//*[@id=\"content\"]/div[3]/div/form/div[2]/div[1]/div/div/div/div[5]/div/div/div/div[2]/external-party/form/div[2]/div/div[1]/div[1]/div[1]/div[2]/div[2]/span";
    public static final String LOADING_COMPONENT_XPATH = "//*[@id=\"content\"]/div[3]/div/form/div[2]/div[1]/div/div/div/div[5]/div/div/div/div[2]/external-party/form/div[1]/dloadingicon/div/span/i";
    public static final String ORGANIZATION = "Organization";
    public static final String FUND_SOURCE_NO = "No";
    public static final String INDIVIDUAL = "Individual";
    public static final String FUND_SOURCE_YES = "Yes";
    public static final String INSURANCE = "Insurance";
    public static final String VIEW_CORROSPONDANCE_COLUMN = "View Corr.";
    DataGrid externalPartyDataGrid;

    @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div/form/div[2]/div[1]/div/div/div/div[5]/div/div/div/h4")
    WebElement pageTitle;


    @FindBy(xpath = "//*[@id=\"content\"]/div[2]/div/form/div[2]/div[1]/div/div/div/div[5]/div/div/div/div[2]/external-party/form/div[2]/div/div[1]/div[1]/div[2]")
    WebElement sectionTitle;

    @FindBy(xpath = "//*[@id=\"addParty\"]")
    WebElement addNewExternalPartyBtn;


    @FindBy(xpath = "(//*[@id=\"saveParty\"])[1]")
    WebElement saveBtn;

    @FindBy(xpath = "(//*[@id=\"saveParty\"])[2]")
    WebElement cancelBtn;

    @CustomElement(xpath = "//*[@id=\"PartyRole\"]")
    public DropDown partyRole;

    @FindBy(xpath = PARTY_TYPE_XPATH)
    WebElement partyType;

    @FindBy(xpath = FUND_SOURCE_XPATH)
    WebElement fundSource;

//Info Section common field

    @CustomElement(xpath = "//*[@id=\"PartyName\"]")
    public TextBox partyName;

    @CustomElement(xpath = "//*[@id=\"PartyFirstName\"]")
    public TextBox partyFirstName;

    @CustomElement(xpath = "//*[@id=\"PartyMiddleName\"]")
    public TextBox partyMiddleName;

    @CustomElement(xpath = "//*[@id=\"PartyLastName\"]")
    public TextBox partyLastName;

    @CustomElement(xpath = "//*[@id=\"PartyStreet1\"]")
    public AutoSuggest addressLine1;

    @CustomElement(xpath = "//*[@id=\"PartyStreet2\"]")
    public TextBox addressLine2;

    @CustomElement(xpath = "//*[@id=\"PartyCity\"]")
    public AutoSuggest city;

    @CustomElement(xpath = "//*[@id=\"PartyStateI\"]")
    public DropDown state;

    @CustomElement(xpath = "//*[@id=\"PartyZipI\"]")
    public TextBox zip;

    @CustomElement(xpath = "//*[@id=\"PartyComment\"]")
    public TextBox comment;

    @CustomElement(xpath = "//*[@id=\"partyrecordstatus\"]")
    public CheckBox externalPartyInactive;

    @CustomElement(xpath = "//*[@id=\"EventCase2\"]")
    public DropDown eventCase;

    @CustomElement(xpath = "//*[@id=\"PartyPhone1\"]")
    public TextBox businessPhone;

    @CustomElement(xpath = "//*[@id=\"PartyPhone1Ext\"]")
    public TextBox businessPhoneExt;

    @CustomElement(xpath = "//*[@id=\"PartyPhone2\"]")
    public TextBox cellPhone;

    @CustomElement(xpath = "//*[@id=\"PartyFax\"]")
    public TextBox fax;

    @CustomElement(xpath = "//*[@id=\"PartyEmail\"]")
    public TextBox email;

    @CustomElement(xpath = "//*[@id=\"NegotiationStatus\"]")
    public DropDown negotiationStatus;

    public TextBox policyId;
    public TextBox policyHolderName;
    public TextBox policyEffectiveDate;

    private String displayedTitleOfInfoSection;

    public WebElement getAddNewCoverageTypeBtn() {
        return addNewCoverageTypeBtn;
    }

    WebElement addNewCoverageTypeBtn;

    public WebElement getAddNewContactBtn() {
        return addNewContactBtn;
    }

    WebElement addNewContactBtn;

    LoadingComponent loadingComponent;

    public TextBox getPartyName() {
        return partyName;
    }

    public CoverageTypeSection getCoverageTypeSection() {
        return coverageTypeSection;
    }

    private CoverageTypeSection coverageTypeSection;
    private ContactDetailsSection contactDetailsSection;

    public ExternalPartyTab(WebDriver driver) {
        super(driver);
        WaitUtils.waitForSeconds(TimeOuts.FIVE_SECONDS);
        PageFactory.initElements(driver, this);
        initInfoSection();
        externalPartyDataGrid = DataGridBuilder.createDataGridWithXpath(TABLE_XPATH)
                .buildColumn(VIEW_CORROSPONDANCE_COLUMN).atIndex(9).makeClickable()
                .setClickableChildElementXpath("./i")
                .attributeToActivate("class", "activatedIcon")
                .makeDrillable().drillsTo(CaseCorrespondenceTab.class).buildColumnAndGetDataGridBuilder()
                .build(driver);
    }

    private void initInfoSection() {
        WaitUtils.waitForSeconds(TimeOuts.TEN_SECONDS);
        if ((partyType.getText().trim().equalsIgnoreCase(INDIVIDUAL)) && (fundSource.getText().trim().equalsIgnoreCase(FUND_SOURCE_YES))) {
            displayedTitleOfInfoSection = INDIVIDUAL;
            showCoverageTypeBtn(true);
            coverageTypeSection = new CoverageTypeSection(getDriver());
            contactDetailsSection = null;
        } else if ((partyType.getText().trim().equalsIgnoreCase(INDIVIDUAL)) && (fundSource.getText().trim().equalsIgnoreCase(FUND_SOURCE_NO))) {
            displayedTitleOfInfoSection = INDIVIDUAL;
            coverageTypeSection = null;
            contactDetailsSection = null;
            showCoverageTypeBtn(false);
        } else if ((partyType.getText().trim().equalsIgnoreCase(ORGANIZATION)) && (fundSource.getText().trim().equalsIgnoreCase(FUND_SOURCE_YES))) {
            displayedTitleOfInfoSection = ORGANIZATION;
            showCoverageTypeBtn(true);
            coverageTypeSection = null;
            contactDetailsSection = new ContactDetailsSection(getDriver());
        } else if ((partyType.getText().trim().equalsIgnoreCase(ORGANIZATION)) && (fundSource.getText().trim().equalsIgnoreCase(FUND_SOURCE_NO))) {
            displayedTitleOfInfoSection = ORGANIZATION;
            showAddNewContactBtn(true);
            coverageTypeSection = null;
        } else if ((partyType.getText().equalsIgnoreCase(INSURANCE))) {
            displayedTitleOfInfoSection = INSURANCE;
            showAddNewContactBtn(true);
            showCoverageTypeBtn(true);
            showPolicyInfoFields();
            coverageTypeSection = new CoverageTypeSection(getDriver());
        } else {
            throw new AutomationException("Unknown Combination Of Party Type " + partyType.getText() + " And Fund Source " + fundSource.getText());
        }
    }

    private void showPolicyInfoFields() {
        this.policyId = new TextBox(getDriver(), "//*[@id=\"PolicyNumber\"]");
        this.policyHolderName = new TextBox(getDriver(), "//*[@id=\"PolicyHolderName\"]");
        this.policyEffectiveDate = new TextBox(getDriver(), "//*[@id=\"PolicyEffectiveDate\"]");
    }

    private void showAddNewContactBtn(boolean show) {
        if (show) {
            addNewContactBtn = getDriver().findElement(By.xpath("//*[@id=\"addContact\"]"));
        } else {
            addNewContactBtn = null;
        }
    }

    private void showCoverageTypeBtn(boolean show) {
        if (show) {
            addNewCoverageTypeBtn = getDriver().findElement(By.id("addCoverage"));
        } else {
            addNewCoverageTypeBtn = null;
        }
    }

    private WebElement getAddNewCoverageBtn() {
        return addNewContactBtn;
    }


    public CoverageTypeSection clickOnAddNewCoverageTypeBtn() {
        if (addNewCoverageTypeBtn != null) {
            return new CoverageTypeSection(getDriver());
        } else {
            throw new AutomationException("Add New Coverage Type Button Is Not Displayed");
        }
    }

    @LogStep(step = "Clicking on add new external party button")
    public ExternalPartyTab clickOnAddNewExternalPartyBtn() {
        addNewExternalPartyBtn.click();
        return this;
    }


    @LogStep(step = "Clicking on add new Contact button")
    public ContactDetailsSection clickOnAddNewContactBtn() {
        addNewContactBtn.click();
        return new ContactDetailsSection(getDriver());
    }

    @LogStep(step = "Select Party Role")
    public ExternalPartyTab selectPartyRole(String partyRole) {
        this.partyRole.selectOption(partyRole);
        addNewContactBtn.click();
        this.initInfoSection();
        return this;
    }


    @Override
    public String getTabName() {
        return "External Party";
    }

    @Override
    public String getTabTitle() {
        return pageTitle.getText();
    }


    @Override
    public boolean isFullyLoaded() {
        return pageTitle.isDisplayed();
    }


    public DataGrid getDataGrid() {
        return this.externalPartyDataGrid;
    }

    public ContactDetailsSection getContactDetailsSection() {
        if (contactDetailsSection != null && contactDetailsSection.isFullyLoaded()) {
            return contactDetailsSection;
        } else {
            throw new SkipException("Seems Like Contact Details Section Is Not Displayed" +
                    " Please Check Selected Party Type And Fund Source");
        }
    }

    public String getPartyType() {
        return partyType.getText().trim();

    }

    public String getFundSource() {
        return fundSource.getText().trim();
    }

    public String getTitleOfInfoSection() {
        return sectionTitle.getText();
    }

    public String getExpectedTitleOfInfoSection() {
        return displayedTitleOfInfoSection;
    }
}
