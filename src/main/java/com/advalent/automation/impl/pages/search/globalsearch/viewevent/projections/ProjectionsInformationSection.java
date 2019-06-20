package com.advalent.automation.impl.pages.search.globalsearch.viewevent.projections;

import com.advalent.automation.api.annotations.inputfield.CustomElement;
import com.advalent.automation.impl.component.datagrid.DataGrid;
import com.advalent.automation.impl.component.datagrid.DataGridBuilder;
import com.advalent.automation.impl.component.inputelements.DropDown;
import com.advalent.automation.impl.component.inputelements.TextBox;
import com.advalent.automation.impl.component.multiselectdestination.MultiSelectDestination;
import com.advalent.automation.impl.component.multiselectdestination.MultiSelectDestinationBuilder;
import com.advalent.automation.impl.component.notificationpanel.NotificationPanel;
import com.advalent.automation.impl.pages.common.AbstractWebComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProjectionsInformationSection extends AbstractWebComponent {

    @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div/form/div[2]/div[1]/div/div/div/div[7]/div/ng-form/div/div[2]/div[1]/div/div[1]")
    private WebElement projectionInfoTitle;
    @CustomElement(id = "agmtWith")
    private DropDown agreementWith;

    @CustomElement(id = "ExpectedDueDate")
    private TextBox expectedDueDate;

    @CustomElement(id = "probability")
    private DropDown probability;

    @CustomElement(id = "checkRecievedFrom")
    private DropDown checkReceivedFrom;

    @CustomElement(id = "agmtType")
    private DropDown agreementType;

    @CustomElement(id = "agmtAmount")
    private TextBox agreementAmount;

    @CustomElement(id = "interestAmt")
    private TextBox interestAmount;


    @CustomElement(id = "settlementRatio")
    private TextBox settlementRatio;


    @CustomElement(id = "fundSource")
    private DropDown fundSource;

    @CustomElement(id = "creditForRecovery")
    private DropDown creditForRecovery;

    @CustomElement(id = "fundSrcCoverage")
    private DropDown fundSourceCoverage;


    @CustomElement(id = "taskReviewDate")
    private TextBox taskReviewDate;

    @CustomElement(id = "ProjectionNote")
    private TextBox projectionNote;


    @FindBy(xpath = "//*[@id=\"viewProjDetIcon\"]")
    private WebElement projectionDetailsColapse;


    private MultiSelectDestination reductionsReasons;

    private TextBox coverageLimit;
    private TextBox legalExpenses;
    private TextBox settelmentsAmount;
    private TextBox additionalMedicalExpenses;
    private TextBox lostWages;
    private TextBox otherOutOfPocketExpenses;
    private TextBox additionalClaimants;

    @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div/form/div[2]/div[1]/div/div/div/div[7]/div/ng-form/div/div[3]/div[1]/div[1]/div[1]")
    private WebElement authorizationLevelsTitle;


    @CustomElement(id = "ApprovalNote")
    private TextBox approvalNote;

    private DataGrid authorizationLevelsDataGrid;

    @FindBy(id = "delProj")
    private WebElement deleteButton;

    @FindBy(id = "saveproj")
    private WebElement saveButton;


    @FindBy(id = "clear")
    private WebElement clearsaveButton;


    public ProjectionsInformationSection(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        authorizationLevelsDataGrid = DataGridBuilder.createDataGridWithXpath("//*[@id=\"amountstable\"]")
                .build(driver);
    }

    @Override
    public boolean isFullyLoaded() {
        return projectionInfoTitle.isDisplayed() && authorizationLevelsTitle.isDisplayed();
    }


    public ProjectionsInformationSection expandProjectionsDetails() {
        if (projectionDetailsColapse.getAttribute("class").contains("icon-plus-sign"))
            projectionDetailsColapse.click();

        reductionsReasons = new MultiSelectDestinationBuilder.Builder(getDriver())
                .leftDropDown("//*[@id=\"from_select_list3\"]")
                .rightDropDown("//*[@id=\"to_select_list3\"]")
                .leftSearchInput("//*[@id=\"searchRedReason\"]")
                .rightSearchInput("//*[@id=\"selReductionReason\"]")
                .moveLeftBtn("//*[@id=\"moveleft3\"]")
                .moveRightBtn("//*[@id=\"moveright3\"]")
                .build();

        return this;
    }

    public NotificationPanel clickOnSaveBtn() {
        this.saveButton.click();
        return new NotificationPanel(getDriver());
    }


    public boolean isProjectionInformationSectionDisplayed() {
        return this.projectionInfoTitle.isDisplayed();
    }

    public boolean isAuthorizatonLevelSectionDisplayed() {
        return this.authorizationLevelsTitle.isDisplayed();
    }
}
