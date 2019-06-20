package com.advalent.automation.impl.pages.search.globalsearch.newincidentreport;

import com.advalent.automation.api.annotations.LogStep;
import com.advalent.automation.impl.pages.common.AbstractWebComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created By: sumit
 * Created Date :12/17/2018
 * Note:
 */
public class AddIncidentReportPage extends AbstractWebComponent {

    @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div/form/div[1]/h2/span")
    WebElement pageTitle;
    @FindBy(xpath = "//*[@id=\"save\"]")
    WebElement saveButton;
    @FindBy(xpath = "//*[@id=\"back\"]")
    WebElement backButton;
    @FindBy(xpath = "//*[@id=\"reset\"]")
    WebElement resetButton;

   private ClientSection clientSection;
   private EventInformationSection eventInformationSection;
   private PolicyHolderInformationSection policyHolderInformationSection;

    public AddIncidentReportPage(WebDriver driver) {
        super(driver);
        this.clientSection = new ClientSection(driver);
        this.eventInformationSection = new EventInformationSection(driver);
        this.policyHolderInformationSection = new PolicyHolderInformationSection(driver);

    }


    @LogStep(step = "Clicking On Save Button")
    public AddIncidentReportPage clickOnSaveButton() {
        this.saveButton.click();
        return this;
    }

    @LogStep(step = "Clicking On Back Button")
    public AddIncidentReportPage clickOnBackButton() {
        this.backButton.click();
        return this;
    }

    @LogStep(step = "Clicking On Reset Button")
    public AddIncidentReportPage clickOnResetButton() {
        this.resetButton.click();
        return this;
    }

    @Override
    public boolean isFullyLoaded() {
        return pageTitle.isDisplayed();
    }

    public ClientSection getClientSection() {
        return clientSection;
    }

    public EventInformationSection getEventInformationSection() {
        return eventInformationSection;
    }

    public PolicyHolderInformationSection getPolicyHolderInformationSection() {
        return policyHolderInformationSection;
    }
}
