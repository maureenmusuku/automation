package com.advalent.automation.impl.pages.search.globalsearch.viewevent.claimsreview;

import com.advalent.automation.api.annotations.inputfield.CustomElement;
import com.advalent.automation.impl.component.datagrid.*;
import com.advalent.automation.impl.component.inputelements.TextBox;
import com.advalent.automation.impl.pages.common.AbstractModal;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ClaimDetailsModal extends AbstractModal {
    public static final String DIAGNOSIS_CODES_DATA_GRID_XPATH = "//*[@id=\"DiagnosisCodesTable\"]";
    @FindBy(xpath = "/html/body/div[5]/div/div/form/div[1]/h4")
    WebElement modelTitle;

    @CustomElement(xpath = "//*[@id=\"InjuryDescModal\"]")
    TextBox injuryDescription;
    @CustomElement(xpath = "//*[@id=\"claimid\"]")
    TextBox claimNumber;
    @CustomElement(xpath = "//*[@id=\"provider\"]")
    TextBox provider;
    @CustomElement(xpath = "//*[@id=\"totalpaid\"]")
    TextBox paidAmount;
    @CustomElement(xpath = "//*[@id=\"totalbilled\"]")
    TextBox billedAmount;


    DiagnosisCodeDataGrid diagnosisCodeDataGrid;

    public ClaimDetailsModal(WebDriver driver) {
        super(driver);
//        Column primaryColumn = new Column(1, "Primary", DataGridProperties.CheckBox);
        diagnosisCodeDataGrid = DataGridBuilder.createDataGridOf(DiagnosisCodeDataGrid.class, DIAGNOSIS_CODES_DATA_GRID_XPATH)
                .build(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public String getModalTitle() {
        return modelTitle.getText();
    }


    @Override
    public boolean isFullyLoaded() {
        return modelTitle.isDisplayed();
    }

    public String getClaimNumber() {
        return claimNumber.getValue();
    }

    public String getInjuryDescription() {
        return injuryDescription.getValue();
    }

    public DiagnosisCodeDataGrid getClaimsDetailsDataGrid() {
        return diagnosisCodeDataGrid;
    }

    public String getBilledAmount() {
        return billedAmount.getValue();
    }

    public String getPaidAmount() {
        return paidAmount.getValue();
    }

}
