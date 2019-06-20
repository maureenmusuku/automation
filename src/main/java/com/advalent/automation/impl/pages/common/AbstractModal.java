package com.advalent.automation.impl.pages.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public abstract class AbstractModal extends AbstractWebComponent {

    @FindBy(xpath = "//*[@id=\"saveModal\"]")
    WebElement saveBtn;

    @FindBy(xpath = "/html/body/div[6]/div/div/d-header-footer-template/form/span/i")
    WebElement closeIconBtn;

    @FindBy(xpath = "//*[@id=\"closeModal\"]")
    WebElement closeButton;

    public AbstractModal(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public <T> T clickOnSaveBtnExpecting(Class<T> expectedPage) {
        save();
        T expectedPageObject = PageFactory.initElements(getDriver(), expectedPage);
        return expectedPageObject;
    }

    public void save() {
        saveBtn.click();
    }

    public <T> T clickOnCancelBtnExpecting(Class<T> expectedPage) {
        close();
        T expectedPageObject = PageFactory.initElements(getDriver(), expectedPage);
        return expectedPageObject;
    }

    public void close() {
        closeButton.click();
    }

    public <T> T clickOnCloseIconBtnExpecting(Class<T> expectedPage) {
        closeFromIcon();
        T expectedPageObject = PageFactory.initElements(getDriver(), expectedPage);
        return expectedPageObject;
    }

    public void closeFromIcon() {
        closeIconBtn.click();
    }

    public abstract String getModalTitle();


}
