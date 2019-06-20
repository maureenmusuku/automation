package com.advalent.automation.impl.pages.search.globalsearch.viewevent.casecorrespondence;

import com.advalent.automation.api.annotations.LogStep;
import com.advalent.automation.impl.pages.common.AbstractModal;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CorrespondencePreviewModal extends AbstractModal {

    @FindBy(xpath = "/html/body/div[5]/div/div/d-image-view/form/div/div[1]/h4")
    public WebElement pageTitle;


    public CorrespondencePreviewModal(WebDriver driver) {
        super(driver);

    }

    @Override
    public String getModalTitle() {
        return pageTitle.getText();
    }

    @Override
    public boolean isFullyLoaded() {
        return pageTitle.isDisplayed();
    }

    @Override
    @LogStep(step = "Closing Modal From Close Icon")
    public void closeFromIcon() {
        getDriver().findElement(By.xpath("/html/body/div[5]/div/div/d-image-view/form/div/span")).click();
    }
}
