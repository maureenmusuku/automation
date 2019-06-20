package com.advalent.automation.impl.pages.search.globalsearch;

import com.advalent.automation.impl.component.datagrid.DataGrid;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created By: sumit
 * Created Date :12/7/2018
 * Note:
 */
public class GlobalSearchDataGrid extends DataGrid {

    public GlobalSearchDataGrid(WebDriver driver, String locator) {
        super(driver, locator);
        guavaCellWithNoDataFoundMessage = row -> (row.findElements(By.xpath("./tr/td")).size() == 1) &&
                (!row.findElement(By.xpath("./tr/td")).isDisplayed() ||
                        (row.findElement(By.xpath("./tr/td")).isDisplayed() &&
                                row.findElement(By.xpath("./tr/td")).getText().equalsIgnoreCase("No data found")));

        cellWithNoDataFoundMessage = row -> (row.findElements(By.xpath("./tr/td")).size() == 1) &&
                (!row.findElement(By.xpath("./tr/td")).isDisplayed() ||
                        (row.findElement(By.xpath("./tr/td")).isDisplayed() &&
                                row.findElement(By.xpath("./tr/td")).getText().equalsIgnoreCase("No data found")));
    }


    @Override
    protected List<WebElement> getRowElements() {
        return this.driver.findElements(By.xpath(this.locator + "/tbody"));
    }


}
