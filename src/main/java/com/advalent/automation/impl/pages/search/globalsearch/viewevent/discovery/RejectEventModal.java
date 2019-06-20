package com.advalent.automation.impl.pages.search.globalsearch.viewevent.discovery;

import com.advalent.automation.api.annotations.LogStep;
import com.advalent.automation.api.annotations.inputfield.CustomElement;
import com.advalent.automation.impl.component.inputelements.DropDown;
import com.advalent.automation.impl.component.inputelements.TextBox;
import com.advalent.automation.impl.pages.common.AbstractModal;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RejectEventModal extends AbstractModal{

    @FindBy(xpath = "/html/body/div[6]/div/div/form/div[1]/h4")
    WebElement pageTitle;

    @CustomElement(xpath = "//*[@id=\"RejectReason\"]")
    DropDown rejectReason;

    @CustomElement(xpath = "//*[@id=\"Comments\"]")
    TextBox comments;

    public RejectEventModal(WebDriver driver) {
        super(driver);
    }

    @Override
    public String getModalTitle() {
        return "Reject Event";
    }


    @Override
    public boolean isFullyLoaded() {
        return false;
    }

    @LogStep(step = "")
    public RejectEventModal selectRejectReason(String reason) {
        rejectReason.selectOption(reason);
        return this;
    }
    @LogStep(step = "")
    public RejectEventModal enterComment(String comment) {
        this.comments.enterValue(comment);
        return this;
    }
}
