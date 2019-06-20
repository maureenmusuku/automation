package com.advalent.automation.impl.pages.search.globalsearch.viewevent.activitylog;

import com.advalent.automation.impl.component.datagrid.Cell;
import com.advalent.automation.impl.component.datagrid.Column;
import com.advalent.automation.impl.component.datagrid.DataGridBuilder;
import com.advalent.automation.impl.component.datagrid.DataGrid;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TaskNoteCell extends Cell {
    DataGrid dataGrid;

    public TaskNoteCell(WebDriver driver, int colIndex, int rowIndex, Column column) {
        super(driver, colIndex, rowIndex, column);
        String xpathExpression = "//*[@id=\"logTable\"]/tbody/tr[" + rowIndex + "]/td[" + colIndex + "]/activity-log-note/div/span/span/table[@id='NoteTable']";
        if (driver.findElements(By.xpath(xpathExpression)).size() > 0) {
            dataGrid = DataGridBuilder.createDataGridWithXpath(xpathExpression).build(driver);
        }
    }

    public String getNote() {
        return this.getCellElement().findElements(By.xpath("./activity-log-note/div/span/span/span"))
                .stream()
                .filter(s -> !(s.getText().trim().equals("")) || !(s.getText().trim().isEmpty()))
                .findFirst().get()
                .getText();
    }

    public DataGrid getFieldValuesDataGrid() {
        return dataGrid;
    }
}
