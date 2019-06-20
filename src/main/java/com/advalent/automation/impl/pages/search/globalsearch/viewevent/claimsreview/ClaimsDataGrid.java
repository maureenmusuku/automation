package com.advalent.automation.impl.pages.search.globalsearch.viewevent.claimsreview;

import com.advalent.automation.api.annotations.inputfield.CustomElement;
import com.advalent.automation.impl.component.datagrid.DataGrid;
import com.advalent.automation.impl.component.inputelements.TextBox;
import org.openqa.selenium.WebDriver;

public class ClaimsDataGrid extends DataGrid {

    public ClaimsDataGrid(WebDriver driver, String locator) {
        super(driver, locator);
    }

    @CustomElement(xpath = "//*[@id=\"TotalIncCount\"]")
    TextBox claimsCount;
    @CustomElement(xpath = "//*[@id=\"TotalBilledAmt\"]")
    TextBox totalBilledAmount;
    @CustomElement(xpath = "//*[@id=\"TotalPaidAmt\"]")
    TextBox totalPaid;


    public Integer getClaimsCount() {
        return Integer.parseInt(claimsCount.getValue());
    }

    public Integer getTotalBilledAmount() {
        return Integer.parseInt(totalBilledAmount.getValue());
    }

    public String getTotalPaid() {
        return totalPaid.getValue();
    }



    public int getBilledAmount(int rowIndex) {
        return Integer.parseInt(this.getRow(rowIndex).getCell("Billed Amt.").getValue());
    }

    public int getPaidAmount(int rowIndex) {
        String value = this.getRow(rowIndex).getCell("Paid Amt.").getValue();
        return Integer.parseInt(value.isEmpty() ? "" + 0 : value);
    }
}


