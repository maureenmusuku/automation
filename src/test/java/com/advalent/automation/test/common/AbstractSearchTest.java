package com.advalent.automation.test.common;

import com.advalent.automation.api.constants.TimeOuts;
import com.advalent.automation.impl.component.inputelements.InputElement;
import com.advalent.automation.impl.pages.common.AbstractSearchPage;
import com.advalent.automation.impl.utils.WaitUtils;
import com.advalent.automation.reporting.ExtentHTMLReportManager;
import com.advalent.automation.test.base.BaseTest;
import com.advalent.automation.test.utils.testdata.DataProviderUtils;
import com.google.common.base.Predicate;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;


public abstract class AbstractSearchTest extends BaseTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    String initialTableValue;
    String tableValueAfterSearch;
    int waitTime = TimeOuts.FIVE_SECONDS;
    protected List<InputElement> inputElements;
    protected List<String> inpuValueList;
    protected AbstractSearchPage searchPage;

    /**
     * Method TO return The list Of Input Elements
     *
     * @return list buildDataGridAs input element in search page
     */
    protected abstract List<InputElement> getInputElementList();

    /**
     * Method TO return The list Of values to be entered in input element
     *
     * @return list buildDataGridAs values to be entered in input element in search page
     */
    protected abstract List<String> getInputValueList();

    /**
     * Method to return the search page on which test is to be performed
     *
     * @return list buildDataGridAs values to be entered in input element in search page
     */
    protected abstract AbstractSearchPage getSearchPage();

    InputElement currentElement = null;

    public void setUp() {
        super.setUp();
    }

    @Test(description = "Search Test", dataProvider = "availableInputProvider", priority = 2)
    public void searchByAvailableInputs(InputElement inputElement, String value) {
        ExtentHTMLReportManager.getInstance().addStep("Test Search By", value);
        initialTableValue = getSearchPage().getDataGrid().getDataAsString();
        getSearchPage().searchBy(inputElement, (String) inputMap.get(value), waitTime);
        tableValueAfterSearch = getSearchPage().getDataGrid().getDataAsString();
        boolean isDataDifferent = !initialTableValue.equalsIgnoreCase(tableValueAfterSearch);
        reportManager.addStep("Table Data Are " + (isDataDifferent ? "Not "     : "") + "Same After Search");
        assertThat(initialTableValue).isNotEqualTo(tableValueAfterSearch).as("Table Data should not be same after search");
    }

    @Test(description = "Test That Enter Search Criteria Message Is Shown If Search Criteria Is Not Provided", priority = 2)
    public void searchWithOutSearchCriteria() {
        getSearchPage().clearSearch();
        getSearchPage().clickOnSearchButton();
        String errorMessage = getSearchPage().getErrorMessage();
        reportManager.addStep("Expected Error Message is : Please enter search criteria");
        reportManager.addStep("Actual Error Message is : %s", errorMessage);
        assertThat(errorMessage).isEqualTo("Please enter search criteria");
    }

    @AfterMethod
    public void resetSearch() {
        getSearchPage().clearSearch();
    }

    @DataProvider()
    public Object[][] availableInputProvider() {
        return DataProviderUtils.getObjects(getInputElementList(), getInputValueList());
    }
}
