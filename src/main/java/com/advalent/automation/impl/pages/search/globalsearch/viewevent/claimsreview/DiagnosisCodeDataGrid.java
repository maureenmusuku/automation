package com.advalent.automation.impl.pages.search.globalsearch.viewevent.claimsreview;

import com.advalent.automation.impl.component.datagrid.DataGrid;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DiagnosisCodeDataGrid extends DataGrid {
    public DiagnosisCodeDataGrid(WebDriver driver, String locator) {
        super(driver, locator);
    }

    public DiagnosisCodeDataGrid makeDiagnosisCodePrimaryOfRow(int dataRowNum) {
        getDriver().findElement(By.xpath(this.locator +
                "/tbody/tr[" + dataRowNum + "]/td[" + 4 + "]/i")).click();
        return this;
    }


    public boolean isPrimaryDiag(int rowIndex) {
        return getDriver().findElement(By.xpath(this.locator +
                "/tbody/tr[" + rowIndex + "]/td[" + 4 + "]/i")).getAttribute("class").contains("fa-check-square-o");
    }

    public boolean isHighLighted(int rowIndex) {
        return getDriver().findElement(By.xpath(this.locator +
                "/tbody/tr[" + rowIndex + "]")).getAttribute("class").contains("primaryClr");
    }
}
