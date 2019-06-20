package com.advalent.automation.impl.component.datagrid;

import java.util.List;

public interface IRow {

    String getRowXpath();

    <T extends ICell> ICell getCell(String cellName);

    <T extends ICell> ICell getCell(int cellIndex);

    List<String> getDataAsList();

    <T extends IRow> T click();

    List<ICell> getCells();

    void setProperties(DataGridProperties[] rowsProperties);

    void setDrillPage(Class rowDrillPage);

}
