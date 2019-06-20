package com.advalent.automation.test.common;

import com.advalent.automation.api.constants.TimeOuts;
import com.advalent.automation.impl.component.datagrid.DataGrid;
import com.advalent.automation.impl.component.pagination.PaginationComponent;
import com.advalent.automation.impl.pages.search.globalsearch.EventIncidentSearchTab;
import com.advalent.automation.impl.pages.search.globalsearch.GlobalSearchPage;
import com.advalent.automation.test.base.BaseTest;
import com.advalent.automation.test.utils.testdata.DataFile;
import com.advalent.automation.test.utils.testdata.TestDataReader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created By: sumit
 * Created Date :12/7/2018
 * Note:
 */

public class AbstractPaginationTest extends BaseTest {

    protected PaginationComponent paginationComponent;
    protected DataGrid dataGrid;


    @Test(description = "Test That No Of Rows Displayed By Pagination Match With Actual Rows In DataGrid", priority = 1)
    public void testThatNoOfRowsDisplayedByPaginationMatchWithActualRowsInDataGrid() {
        int noOfRows = dataGrid.getRowCount();
        int dataCountInPagination = paginationComponent.getNumberOfRecordsDisplayed();
        reportManager.addStep("Total No Of Record Displayed In Pagination", dataCountInPagination + "");
        reportManager.addStep("Total No Of Rows Displayed In Data Grid", noOfRows + "");
        assertThat(noOfRows).isEqualTo(dataCountInPagination);
    }

    @Test(description = "Test That Clicking On Next Button Displays Next Set Of Data In Data Grid", priority = 2)
    public void test1() {
        int noOfRecordDisplayedTo = paginationComponent.getIndexOfRecordDisplayedTo();
        reportManager.addStep(String.format(" Record From %s To %s Is Displayed Before Clicking Next Button In Pagination",
                paginationComponent.getIndexOfRecordDisplayedFrom(), paginationComponent.getIndexOfRecordDisplayedTo()));
        paginationComponent.clickOnNextButton();
        int noOfRecordDisplayedFrom = paginationComponent.getIndexOfRecordDisplayedFrom();
        reportManager.addStep(String.format(" Record From %s To %s Is Displayed After Clicking Next Button In Pagination",
                paginationComponent.getIndexOfRecordDisplayedFrom(), paginationComponent.getIndexOfRecordDisplayedTo()));
        assertThat(noOfRecordDisplayedTo + 1).isEqualTo(noOfRecordDisplayedFrom);
    }

    @Test(description = "Test That Previous Set Of Data Is Shown After Clicking On Previous Button Of Pagination Component", priority = 3)
    public void test2() {
        int noOfRecordDisplayedFrom = paginationComponent.getIndexOfRecordDisplayedFrom();
        reportManager.addStep(String.format(" Record From %s To %s Is Displayed Before Clicking Previous Button In Pagination",
                paginationComponent.getIndexOfRecordDisplayedFrom(), paginationComponent.getIndexOfRecordDisplayedTo()));
        paginationComponent.clickOnPreviousButton();
        reportManager.addStep(String.format(" Record From %s To %s Is Displayed After Clicking Previous Button In Pagination",
                paginationComponent.getIndexOfRecordDisplayedFrom(), paginationComponent.getIndexOfRecordDisplayedTo()));
        int noOfRecordDisplayedTo = paginationComponent.getIndexOfRecordDisplayedTo();
        assertThat(noOfRecordDisplayedTo).isEqualTo(noOfRecordDisplayedFrom - 1);
    }

    @Test(description = "Test That Either Total No Of Records Or String \"Many\" is Displayed In Pagination Component", priority = 4)
    public void test3() {
        int no;
        String noOfRecordDisplayedFrom = paginationComponent.getTotalNoOfRecords();
        try {
            no = Integer.parseInt(noOfRecordDisplayedFrom);
            int recordInDataGrid = 0;
            recordInDataGrid = recordInDataGrid + dataGrid.getRowCount();
            while (paginationComponent.isNextButtonVisible()) {
                paginationComponent.clickOnPreviousButton();
                recordInDataGrid = recordInDataGrid + dataGrid.getRowCount();
            }
            assertThat(no).isEqualTo(recordInDataGrid);
        } catch (NumberFormatException ex) {
            reportManager.addStep(String.format("Many Is Shown As Expected"));
            assertThat(noOfRecordDisplayedFrom).isEqualTo("many");
        }
    }


}
