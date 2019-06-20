package com.advalent.automation.impl.pages.search.globalsearch.viewevent.overviewtab;

import com.advalent.automation.api.annotations.LogStep;
import com.advalent.automation.api.annotations.inputfield.CustomElement;
import com.advalent.automation.api.components.tab.ITab;
import com.advalent.automation.impl.component.inputelements.DropDown;
import com.advalent.automation.impl.component.inputelements.TextBox;
import com.advalent.automation.impl.component.notificationpanel.NotificationPanel;
import com.advalent.automation.impl.pages.common.AbstractWebComponent;
import com.advalent.automation.impl.pages.common.AdvalentPage;
import com.advalent.automation.selenium.SeleniumUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OverviewTab extends AbstractWebComponent implements ITab {
    @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div/form/div[2]/div[1]/div/div/div/div[1]/div/ng-form/h4")
    WebElement pageTitle;

    @FindBy(xpath = "//*[@id=\"changeOwnership\"]")
    WebElement eventOwnershipBtn;

    @FindBy(xpath = "//*[@id=\"lienProcessing\"]")
    WebElement lienProcessingBtn;

    @FindBy(xpath = "//*[@id=\"linkedEvents\"]")
    WebElement linkedEventsBtn;

    @FindBy(xpath = "//*[@id=\"legalGuidance\"]")
    WebElement clientlegalGuidanceBtn;

    @FindBy(xpath = "//*[@id=\"viewSummary\"]")
    WebElement viewSummaryBtn;

    //Event Details
    @CustomElement(xpath = "//*[@id=\"PolicyHolderFirstName\"]")
    public TextBox policyHolderFirstName;

    @CustomElement(xpath = "//*[@id=\"PolicyHolderLastName\"]")
    public TextBox policyHolderLastName;

    @CustomElement(xpath = "//*[@id=\"PolicyHolderID\"]")
    public TextBox policyHolderID;

    @CustomElement(xpath = "//*[@id=\"EventType\"]")
    public DropDown eventType;

    @CustomElement(xpath = "//*[@id=\"LossState\"]")
    public DropDown lossState;

    @CustomElement(xpath = "//*[@id=\"InvestigationSource\"]")
    public DropDown investigationSource;

    @CustomElement(xpath = "//*[@id=\"LegacyID\"]")
    public TextBox legacyID;

    @CustomElement(xpath = "//*[@id=\"EventSupervisor\"]")
    public TextBox eventSupervisor;

    @CustomElement(xpath = "//*[@id=\"EventSupportingOwner\"]")
    public DropDown eventSupportingOwner;

    @CustomElement(xpath = "//*[@id=\"LossDetails\"]")
    public TextBox lossDetails;

    @CustomElement(xpath = "//*[@id=\"TotalChargedAmount\"]")
    public TextBox totalChargedAmount;

    @CustomElement(xpath = "//*[@id=\"TotalIncludedAmount\"]")
    public TextBox totalIncludedAmount;

    @CustomElement(xpath = "//*[@id=\"TotalPostedRecoveries\"]")
    public TextBox totalPostedRecoveries;

    @CustomElement(xpath = "//*[@id=\"TotalRemainingBalance\"]")
    public TextBox totalRemainingBalance;

    @CustomElement(xpath = "//*[@id=\"TotalApprProjections\"]")
    public TextBox totalApprProjections;

    @CustomElement(xpath = "//*[@id=\"TotalApprExpenses\"]")
    public TextBox totalApprExpenses;

    @CustomElement(xpath = "//*[@id=\"ProtocolDate\"]")
    public TextBox protocolDate;

    @CustomElement(xpath = "//*[@id=\"EventTypeDate\"]")
    public TextBox eventTypeDate;

    //Case Details

    @CustomElement(xpath = "//*[@id=\"CreatedDate\"]")
    public TextBox createdDate;

    @CustomElement(xpath = "//*[@id=\"FinalExpRecoveryDate\"]")
    public TextBox finalExpRecoveryDate;

    @CustomElement(xpath = "//*[@id=\"PartyAtFault\"]")
    public DropDown partyAtFault;

    @CustomElement(xpath = "//*[@id=\"InjuryDescription\"]")
    public TextBox injuryDescription;

    @CustomElement(xpath = "//*[@id=\"ChargedAmount\"]")
    public TextBox chargedAmount;

    @CustomElement(xpath = "//*[@id=\"IncludedAmount\"]")
    public TextBox includedAmount;

    @CustomElement(xpath = "//*[@id=\"PostedRecoveries\"]")
    public TextBox postedRecoveries;

    @CustomElement(xpath = "//*[@id=\"ApprovedProjections\"]")
    public TextBox approvedProjections;

    @CustomElement(xpath = "//*[@id=\"ApprovedExpenses\"]")
    public TextBox approvedExpenses;

    //Client Details

    @CustomElement(xpath = "//*[@id=\"MajorClient\"]")
    public TextBox majorClient;

    @CustomElement(xpath = "//*[@id=\"Client\"]")
    public TextBox client;

    @CustomElement(xpath = "//*[@id=\"Group\"]")
    public DropDown group;

    @CustomElement(xpath = "//*[@id=\"SubGroup\"]")
    public TextBox subGroup;

    @CustomElement(xpath = "//*[@id=\"Product\"]")
    public TextBox product;

    @FindBy(xpath = "//*[@id=\"saveQA\"]")
    private WebElement saveBtn;


    @LogStep(step = "Clicking On Event Ownership Button")
    public EventOwnershipModal clickOnEventOwnershipBtn() {
        eventOwnershipBtn.click();
        return new EventOwnershipModal(getDriver());
    }

    @LogStep(step = "Clicking On Event Ownership Button")
    public LienProcessingModal clickOnLienProcessingBtn() {
        SeleniumUtils.scrollPageToElement(driver,lienProcessingBtn);
        lienProcessingBtn.click();
        return new LienProcessingModal(getDriver());
    }

    public OverviewTab(WebDriver driver) {
        super(driver);
        PageFactory.initElements(getDriver(), this);
    }


    @Override
    public boolean isFullyLoaded() {
        return pageTitle.isDisplayed();
    }


    @Override
    public String getTabName() {
        return "Overview";
    }

    @Override
    public String getTabTitle() {
        return pageTitle.getText();
    }



    @LogStep(step = "Entering Loss Details")
    public OverviewTab enterLossDetails(String lossDetails) {
        this.lossDetails.clearValue().enterValue(lossDetails);
        return this;
    }

    public NotificationPanel clickOnSaveButton() {
        this.saveBtn.click();
        return new NotificationPanel(getDriver());
    }

}
