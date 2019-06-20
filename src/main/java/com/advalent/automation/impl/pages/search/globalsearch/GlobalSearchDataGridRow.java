package com.advalent.automation.impl.pages.search.globalsearch;

import com.advalent.automation.impl.component.datagrid.Column;
import com.advalent.automation.impl.component.datagrid.Row;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;

/**
 * Created By: sumit
 * Created Date :12/4/2018
 * Note:
 */
public class GlobalSearchDataGridRow extends Row    {


    public GlobalSearchDataGridRow(WebDriver driver, int rowIndexToOperate, HashMap<Integer, Column> columnMap) {
        super(driver, rowIndexToOperate, columnMap);
    }

    @Override
    public String getRowXpath() {
        return "/tbody[" + this.getIndex() + "]/tr";
    }
}
