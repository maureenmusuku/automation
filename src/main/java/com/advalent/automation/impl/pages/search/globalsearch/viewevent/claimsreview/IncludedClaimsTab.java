package com.advalent.automation.impl.pages.search.globalsearch.viewevent.claimsreview;

import com.advalent.automation.api.annotations.inputfield.CustomElement;
import com.advalent.automation.api.components.tab.ITab;
import com.advalent.automation.impl.component.datagrid.Column;
import com.advalent.automation.impl.component.datagrid.ColumnBuilder;
import com.advalent.automation.impl.component.datagrid.DataGridBuilder;
import com.advalent.automation.impl.component.inputelements.TextBox;
import com.advalent.automation.impl.pages.common.AbstractWebComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created By: sumit
 * Created Date :10/29/2018
 * Note: Page Object Class For Included Claims Tab IN Claims Review Tab Of View Event Page.
 */
public class IncludedClaimsTab extends AbstractWebComponent implements ITab {

    @CustomElement(id = "LossInjuryDescription")
    TextBox injuryDescription;

    ClaimsDataGrid dataGrid;

    public IncludedClaimsTab(WebDriver driver) {
        super(driver);

        Column detailsColumn = ColumnBuilder.createColumn("Detail").atIndex(1).makeDrillable().drillsTo(ClaimDetailsModal.class).build();
        dataGrid = (ClaimsDataGrid) DataGridBuilder
                .createDataGridWithXpath("//*[@id=\"IncludeClaimsTable\"]")
                .setColumn(1, detailsColumn)
                .build(driver);
    }

    @Override
    public String getTabName() {
        return "Included Claims";
    }

    @Override
    public String getTabTitle() {
        return null;
    }

    @Override
    public boolean isFullyLoaded() {
        return injuryDescription.isFullyLoaded();
    }

    @Override
    public boolean isTabActive() {
        String tabLiXpath = "//*[text()='Included Claims']/parent::*/parent::*/parent::*";
        return driver.findElement(By.xpath(tabLiXpath)).getAttribute("class").contains("active");
    }

    public TextBox getInjuryDescription() {
        return injuryDescription;
    }

    public void setInjuryDescription(TextBox injuryDescription) {
        this.injuryDescription = injuryDescription;
    }

    public ClaimsDataGrid getDataGrid() {
        return dataGrid;
    }
}
