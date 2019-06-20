package com.advalent.automation.impl.pages.search.globalsearch.viewevent.activitylog;

import com.advalent.automation.api.annotations.LogStep;
import com.advalent.automation.api.annotations.inputfield.CustomElement;
import com.advalent.automation.impl.component.inputelements.DropDown;
import com.advalent.automation.impl.component.inputelements.TextBox;
import com.advalent.automation.impl.component.notificationpanel.NotificationPanel;
import com.advalent.automation.impl.pages.common.AbstractWebComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ActivityDetailsSection extends AbstractWebComponent {

    @CustomElement(id = "ActivityType")
    private DropDown activityType;
    @CustomElement(id = "Description")
    private TextBox titleDescription;
    @CustomElement(id = "ActivityNote")
    private TextBox activityNote;
    @FindBy(id = "manualactivitylog")
    private WebElement form;
    @FindBy(id = "saveActivity")
    private WebElement saveBtn;
    @FindBy(id = "hideActivity")
    private WebElement cancelBtn;

    public ActivityDetailsSection(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public boolean isFullyLoaded() {
        return form.isDisplayed();
    }


    @LogStep(step = "Selecting Activity Type")
    public ActivityDetailsSection selectActivityType(String type) {
        this.activityType.enterValue(type);
        return this;

    }


    @LogStep(step = "Entering Title/Description")
    public ActivityDetailsSection enterTitleOrDescription(String description) {
        this.titleDescription.clearValue();
        this.titleDescription.enterValue(description);
        return this;

    }

    @LogStep(step = "Entering Activity Note")
    public ActivityDetailsSection enterActivityNote(String note) {
        this.activityNote.enterValue(note);
        return this;
    }

    @LogStep(step = "Clicking On Save Button")
    public NotificationPanel clickOnSaveButton() {
        saveBtn.click();
        return new NotificationPanel(getDriver());
    }

    @LogStep(step = "Clicking On Cancel Button")
    public ActivityDetailsSection clickOnCancelButton() {
        cancelBtn.click();
        return this;
    }


}
