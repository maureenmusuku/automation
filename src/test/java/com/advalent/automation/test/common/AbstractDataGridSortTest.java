package com.advalent.automation.test.common;

import com.advalent.automation.api.components.datagrid.IDataGrid;
import com.advalent.automation.test.base.BaseTest;
import com.advalent.automation.test.utils.testdata.DataProviderUtils;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created By: sumit
 * Created Date :12/7/2018
 * Note:
 */

public abstract class AbstractDataGridSortTest extends BaseTest {
    protected List<String> sortType;
    protected List<String> sortBy;
    protected IDataGrid dataGrid;
    private String initialData;


    @Test(description = "Test Sort Functionality", priority = 1, dataProvider = "sortTypeAndSortBy")
    public void test(String sortBy, String sortType) {
        int noOfRows = dataGrid.getRowCount();
        if (noOfRows < 2) {
            throw new SkipException("Data Grid Have Less Than Two Row Hence Filter Functionality Cannot Be Tested");
        }
        initialData = dataGrid.getDataAsString();
        dataGrid.sort(sortType, sortBy);
        String dataAfterSearch = dataGrid.getDataAsString();
        assertThat(initialData).isNotEqualTo(dataAfterSearch);
    }

    @DataProvider()
    public Object[][] sortTypeAndSortBy() {
        return DataProviderUtils.getObjects(sortType, sortBy);
    }
}
