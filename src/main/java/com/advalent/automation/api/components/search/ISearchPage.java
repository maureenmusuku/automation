package com.advalent.automation.api.components.search;

import com.advalent.automation.impl.component.datagrid.DataGrid;
import com.advalent.automation.impl.component.inputelements.InputElement;

public interface ISearchPage {

    void searchBy(InputElement inputElement, String value, int waitTime);

    DataGrid clearSearch();

    DataGrid clickOnSearchButton();

    DataGrid getDataGrid();

    <T> T clickOnAddButton();

    DataGrid clickOnOkOfWarning();

    DataGrid clickOnCancelOfWarning();

    String getErrorMessage();
}
