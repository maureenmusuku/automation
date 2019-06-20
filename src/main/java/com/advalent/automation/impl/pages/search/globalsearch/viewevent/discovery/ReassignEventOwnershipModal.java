package com.advalent.automation.impl.pages.search.globalsearch.viewevent.discovery;

import com.advalent.automation.api.annotations.LogStep;
import com.advalent.automation.api.annotations.inputfield.CustomElement;
import com.advalent.automation.api.constants.TimeOuts;
import com.advalent.automation.impl.component.inputelements.AutoSuggest;
import com.advalent.automation.impl.component.inputelements.TextBox;
import com.advalent.automation.impl.component.notificationpanel.NotificationPanel;
import com.advalent.automation.impl.pages.common.AbstractModal;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.overviewtab.EventOwnershipModal;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ReassignEventOwnershipModal extends AbstractModal {

    @FindBy(xpath = "/html/body/div[6]/div/div/form/div[2]/div[2]")
    WebElement pageTitle;

    @FindBy(xpath = "(//*[@id=\"reassignModal\"])[1]")
    WebElement saveBtn;

    @FindBy(xpath = "(//*[@id=\"closeModal\"])[1]")
    WebElement cancelBtn;

    @CustomElement(xpath = "//*[@id=\"EffectiveDateTime\"]")
    TextBox effectiveAsOf;

    @CustomElement(xpath = "(//*[@id=\"DepartmentName\"])[2]")
    AutoSuggest newDepartment;

    @CustomElement(xpath = "(//*[@id=\"OwnerName\"])[2]")
    AutoSuggest newEventOwner;

    @CustomElement(xpath = "(//*[@id=\"SupervisorName\"])[2]")
    AutoSuggest newEventSupervisor;

    @CustomElement(xpath = "//*[@id=\"ReassignmentNote\"]")
    TextBox reassignmentNote;


    public ReassignEventOwnershipModal(WebDriver driver) {
        super(driver);
        PageFactory.initElements(getDriver(), this);
    }

    @LogStep(step = "Clicking on Save Button ")
    public NotificationPanel clickOnSaveBtn() {
        saveBtn.click();
        return new NotificationPanel(getDriver());
    }

    public boolean isSaveButtonEnabled() {
        return saveBtn.isEnabled();
    }

    @LogStep(step = "Clicking on Cancel Button ")
    public EventOwnershipModal clickOnCancelBtn() {
        cancelBtn.click();
        return new EventOwnershipModal(getDriver());
    }


    @Override
    public String getModalTitle() {
        return pageTitle.getText();
    }

    @Override
    public boolean isFullyLoaded() {
        return false;
    }

    public ReassignEventOwnershipModal enterNewDepartment(String newDepartmentName) {
        this.newDepartment.enterValue(newDepartmentName);
        return this;
    }

    public ReassignEventOwnershipModal enterNewEventOwner(String newEventOwner) {
        this.newEventOwner.setWaitTime(TimeOuts.TEN_SECONDS);
        this.newEventOwner.enterValue(newEventOwner);
        return this;
    }

    public ReassignEventOwnershipModal enterReassignmentNote(String reassignmentNote) {
        this.reassignmentNote.enterValue(reassignmentNote);
        return this;
    }
}
