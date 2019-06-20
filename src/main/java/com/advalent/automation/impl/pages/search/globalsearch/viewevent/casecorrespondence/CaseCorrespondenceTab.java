package com.advalent.automation.impl.pages.search.globalsearch.viewevent.casecorrespondence;

import com.advalent.automation.api.annotations.LogStep;
import com.advalent.automation.api.components.tab.ITab;
import com.advalent.automation.api.constants.TimeOuts;
import com.advalent.automation.impl.component.datagrid.DataGrid;
import com.advalent.automation.impl.component.datagrid.DataGridBuilder;
import com.advalent.automation.impl.component.datagrid.DataGridProperties;
import com.advalent.automation.impl.pages.common.AbstractWebComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CaseCorrespondenceTab extends AbstractWebComponent implements ITab {
    public static final String VIEW_COLUMN = "View";
    public static final String CASE_CORRESPONDENCE_TAB = "Case Correspondence";


    @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div/form/div[2]/div[1]/div/div/div/div[6]/div/div/div/h4")
    WebElement pageTitle;

    @FindBy(xpath = "//*[@id=\"addCommunication\"]")
    WebElement addNewCommunicationBtn;

    DataGrid caseCorrespondenceDataGrid;

    CaseCorrespondenceSection caseCorrespondenceSection;


    public CaseCorrespondenceTab(WebDriver driver) {
        super(driver);
        PageFactory.initElements(getDriver(), this);
        this.doWaitTillFullyLoaded(TimeOuts.TEN_SECONDS);
        caseCorrespondenceDataGrid = DataGridBuilder.createDataGridWithXpath("//*[@id=\"Table-Communication\"]")
                .addRowProperties(DataGridProperties.Drillable)
                .setRowDrillPage(CaseCorrespondenceSection.class)
                .buildColumn(VIEW_COLUMN).makeClickable().makeDrillable()
                .setClickableChildElementXpath("./i")
                .attributeToActivate(DataGridProperties.ATTRIBUTE_CLASS, DataGridProperties.ACTIVE_CLASS)
                .drillsTo(CorrespondencePreviewModal.class)
                .buildColumnAndGetDataGridBuilder()
                .build(driver);
    }


    @Override
    public boolean isFullyLoaded() {
        return pageTitle.isDisplayed();
    }


    @Override
    public String getTabName() {
        return CASE_CORRESPONDENCE_TAB;
    }

    @Override
    public String getTabTitle() {
        return pageTitle.getText();
    }


    @LogStep(step = "Clicking on Add New Communication Button")
    public CaseCorrespondenceSection clickOnAddNewCommunicationBtn() {
        addNewCommunicationBtn.click();
        caseCorrespondenceSection = new CaseCorrespondenceSection(getDriver());
        return caseCorrespondenceSection;
    }

    public DataGrid getDataGrid() {
        return caseCorrespondenceDataGrid;
    }

    public CaseCorrespondenceSection getCaseCorrespondenceSection() {
        return caseCorrespondenceSection;
    }

}
