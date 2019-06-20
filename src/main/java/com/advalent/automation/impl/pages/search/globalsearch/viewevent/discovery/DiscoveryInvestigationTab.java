package com.advalent.automation.impl.pages.search.globalsearch.viewevent.discovery;

import com.advalent.automation.api.annotations.LogStep;
import com.advalent.automation.api.annotations.inputfield.CustomElement;
import com.advalent.automation.api.components.tab.ITab;
import com.advalent.automation.impl.component.inputelements.CheckBox;
import com.advalent.automation.impl.component.inputelements.DropDown;
import com.advalent.automation.impl.component.inputelements.TextBox;
import com.advalent.automation.impl.pages.common.AbstractWebComponent;
import com.advalent.automation.impl.pages.common.AdvalentPage;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.overviewtab.OverviewTab;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DiscoveryInvestigationTab extends AbstractWebComponent implements ITab {
    @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div/form/div[2]/div[2]/div/div/div/div[1]/div/ng-form/div/h4[1]")
    WebElement callerInformationSectionTitle;

    @FindBy(xpath = "//*[@id=\"Discovery Investigation\"]")
    WebElement patientInformationTitle;

    @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div/form/div[2]/div[2]/div/div/div/div[1]/div/ng-form/div/h4[2]")
    WebElement switchToEventCaseViewBtn;

    //Caller Information Section
    @CustomElement(xpath = "//*[@id=\"CallerType\"]")
    public DropDown callerType;

    @CustomElement(xpath = "//*[@id=\"CallerName\"]")
    public TextBox callerName;

    @CustomElement(xpath = "//*[@id=\"CallerPhoneNumber\"]")
    public TextBox callerPhoneNumber;

    @CustomElement(xpath = "//*[@id=\"CallerRelationship\"]")
    public DropDown callerRelationship;

    //Patient Information Section
    @CustomElement(xpath = "//*[@id=\"DamagedPartyFirstName\"]")
    public TextBox patientFirstName;

    @CustomElement(xpath = "//*[@id=\"DamagedPartyMiddleName\"]")
    public TextBox patientMiddleName;

    @CustomElement(xpath = "//*[@id=\"DamagedPartyLastName\"]")
    public TextBox patientLastName;

    @CustomElement(xpath = "//*[@id=\"DamagedPartySuffix\"]")
    public TextBox patientSuffix;


    @CustomElement(xpath = "//*[@id=\"DamagedPartyBirthDate\"]")
    public TextBox patientDOB;

    @CustomElement(xpath = "//*[@id=\"DamagedPartyRelationship\"]")
    public DropDown relationshipCode;

    @CustomElement(xpath = "//*[@id=\"DamagedPartySSN\"]")
    public TextBox patientSSN;

    @CustomElement(xpath = "//*[@id=\"DamagedPartyAge\"]")
    public TextBox patientAge;

    @CustomElement(xpath = "//*[@id=\"DamagedPartyID\"]")
    public DropDown patientId;

    @CustomElement(xpath = "//*[@id=\"DamagedPartyGender\"]")
    public DropDown patientGender;

    @CustomElement(xpath = "//*[@id=\"DamagedPartyIsPolicy\"]")
    public CheckBox patientIsPolicy;

    @CustomElement(xpath = "//*[@id=\"PayerKey\"]")
    public CheckBox clientPartyId;

    @CustomElement(xpath = "//*[@id=\"IsMemberDeceased\"]")
    public CheckBox patientIsDeceased;


    @CustomElement(xpath = "//*[@id=\"EventStatus\"]")
    public DropDown eventStatus;

    public DiscoveryInvestigationTab(WebDriver driver) {
        super(driver);
        PageFactory.initElements(getDriver(), this);
    }


    @Override
    public boolean isFullyLoaded() {
        return callerInformationSectionTitle.isDisplayed() && patientInformationTitle.isDisplayed();
    }


    @LogStep(step = "Clicking On Switch To Event/Case View Button")
    public OverviewTab clickOnSwitchToEventCaseViewBtn() {
        switchToEventCaseViewBtn.click();
        return new OverviewTab(getDriver());
    }

    @Override
    public String getTabName() {
        return "Discovery Investigation";
    }

    @Override
    public String getTabTitle() {
        return callerInformationSectionTitle.getText();
    }

    @LogStep(step = "Selecting Caller Type")
    public DiscoveryInvestigationTab selectCallerType(String callerType) {
        this.callerType.selectOption(callerType);
        return this;

    }

    @LogStep(step = "Entering Caller Name")
    public DiscoveryInvestigationTab enterCallerName(String callerName) {
        this.callerName.clearValue();
        this.callerName.enterValue(callerName);
        return this;

    }

    @LogStep(step = "Entering Caller Number")
    public DiscoveryInvestigationTab enterCallerNumber(String callerNumber) {
        this.callerPhoneNumber.clearValue();
        this.callerPhoneNumber.enterValue(callerNumber);
        return this;

    }
}
