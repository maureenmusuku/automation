package com.advalent.automation.impl.pages.search.globalsearch.viewevent.projections;

import com.advalent.automation.api.annotations.LogStep;
import com.advalent.automation.api.components.tab.ITab;
import com.advalent.automation.impl.component.datagrid.DataGrid;
import com.advalent.automation.impl.component.datagrid.DataGridBuilder;
import com.advalent.automation.impl.pages.common.AbstractWebComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProjectionsTab extends AbstractWebComponent implements ITab {

    public static final String DATAGRID_XPATH = "//*[@id=\"content\"]/div[3]/div/form/div[2]/div[1]/div/div/div/div[7]/div/ng-form/div/div/table";
    private final DataGrid dataGrid;


    @FindBy(id = "addCommunication")
    private WebElement addNewProjectionsButton;

    @FindBy(xpath = "//input[@value='Generate Settlement Letter']")
    private WebElement generateSettelmentLetterLink;


    @FindBy(xpath = "//input[@value='Link Claims to Projection']")
    private WebElement linkClaimsToProjectionsLink;


    @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div/form/div[2]/div[1]/div/div/div/div[7]/div/ng-form/div/div[2]/div[2]/div[3]/input")
    private WebElement addARecoveryLink;

    @FindBy(xpath = "//input[@value='Generate Client Auth Letter']")
    private WebElement generateClientAuthLink;

    @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div/form/div[2]/div[1]/div/div/div/div[7]/div/ng-form/div/div[3]/div[1]/div[2]/input[2]")
    private WebElement clientRulesLink;

    @FindBy(xpath = "//input[@value='Group Rules']")
    private WebElement groupRulesLink;

    @FindBy(xpath = "//input[@value='Recovery Rules']")
    private WebElement recoveryRulesLink;

    public ProjectionsTab(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        dataGrid = DataGridBuilder.createDataGridWithXpath(DATAGRID_XPATH).build(driver);
    }

    @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div/form/div[2]/div[1]/div/div/div/div[7]/div/ng-form/div/div/h4")
    WebElement pageTitle;

    @Override
    public String getTabName() {
        return "ProjectionsTab";
    }

    @Override
    public String getTabTitle() {
        return pageTitle.getText();
    }

    @Override
    public boolean isFullyLoaded() {
        return pageTitle.isDisplayed();
    }

    @LogStep(step = "Clickong On Add New Projections Button")
    public ProjectionsInformationSection clickOnAddNewProjectionsButton() {
        this.addNewProjectionsButton.click();
        return new ProjectionsInformationSection(getDriver());
    }

    public boolean isLinkClaimsToProjectionLinkAvailable() {
        return this.linkClaimsToProjectionsLink.isDisplayed();
    }

    public boolean isGenerateSettelmentLetterLink() {
        return this.generateSettelmentLetterLink.isDisplayed();
    }

    public boolean isGenerateSettlementLinkAvailable() {
        return this.generateSettelmentLetterLink.isDisplayed();
    }

    public boolean isAddARecoveryLinkAvailable() {
        return this.addARecoveryLink.isDisplayed();
    }

    public boolean isGenerateClientAuthLetterLinkAvailable() {
        return this.generateClientAuthLink.isDisplayed();
    }

    public boolean isRecoveryRuleLinkAvailable() {
        return this.recoveryRulesLink.isDisplayed();
    }

    public boolean isClientRuleLinkAvailable() {
        return this.clientRulesLink.isDisplayed();
    }

    public boolean isGroupRuleLinkAvailable() {
        return this.groupRulesLink.isDisplayed();
    }
}
