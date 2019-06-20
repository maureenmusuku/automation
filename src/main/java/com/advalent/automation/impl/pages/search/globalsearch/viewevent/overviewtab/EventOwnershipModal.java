package com.advalent.automation.impl.pages.search.globalsearch.viewevent.overviewtab;

import com.advalent.automation.api.annotations.LogStep;
import com.advalent.automation.api.annotations.inputfield.CustomElement;
import com.advalent.automation.impl.component.inputelements.TextBox;
import com.advalent.automation.impl.pages.common.AbstractModal;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.discovery.ReassignEventOwnershipModal;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EventOwnershipModal extends AbstractModal {

    @FindBy(xpath = "/html/body/div[6]/div/div/form/div[1]/h4")
    WebElement pageTitle;

    @FindBy(xpath = "(//*[@id=\"reassignModal\"])[2]")
    WebElement reassignEventOwnershipBtn;

    @FindBy(xpath = "(//*[@id=\"closeModal\"])[2]")
    WebElement closeBtn;

    @CustomElement(xpath = "(//*[@id=\"OwnerName\"])[1]")
    TextBox eventOwnerName;

    @CustomElement(xpath = "(//*[@id=\"DepartmentName\"])[1]")
    TextBox eventOwnerDepartment;

    @CustomElement(xpath = "//*[@id=\"OwnerPhone\"]")
    TextBox eventOwnerPhone;

    @CustomElement(xpath = "(//*[@id=\"SupervisorName\"])[1]")
    TextBox eventSupervisorName;

    @CustomElement(xpath = "//*[@id=\"SupervisorDepartmentName\"]")
    TextBox eventSupervisorDepartmentName;

    @CustomElement(xpath = "//*[@id=\"SupervisorPhone\"]")
    TextBox eventSupervisorPhone;

    public EventOwnershipModal(WebDriver driver) {
        super(driver);
        PageFactory.initElements(getDriver(), this);
    }

    @LogStep(step = "Clicking on Reassign Event Ownership Button")
    public ReassignEventOwnershipModal clickOnReassignEventOwnershipBtn() {
        reassignEventOwnershipBtn.click();
        return new ReassignEventOwnershipModal(getDriver());
    }

    @LogStep(step = "Clicking on Reassign Event Ownership Button")
    public OverviewTab clickOnCloseBtn() {
        closeBtn.click();
        return new OverviewTab(getDriver());
    }


    @Override
    public String getModalTitle() {
        return pageTitle.getText();
    }

    @Override
    public boolean isFullyLoaded() {
        return pageTitle.isDisplayed();
    }


}
