package com.advalent.automation.api.components.datagrid;

import com.advalent.automation.impl.component.datagrid.IRow;
import com.advalent.automation.impl.component.pagination.PaginationComponent;

import java.util.List;

public interface IDataGrid {

    PaginationComponent getPaginationComponent();

    <T extends IRow> T getRow(int rowIndex);

    IDataGrid waitTillDataIsLoaded(int waitTimeInSecs);

    List<IRow> getRows();

    int getRowCount();

    String getDataAsString();

    IDataGrid sort(String sortType, String sortBy);

}
