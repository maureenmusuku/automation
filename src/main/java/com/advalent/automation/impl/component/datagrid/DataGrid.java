package com.advalent.automation.impl.component.datagrid;

import com.advalent.automation.api.components.datagrid.IDataGrid;
import com.advalent.automation.api.constants.TimeOuts;
import com.advalent.automation.api.exceptions.AutomationException;
import com.advalent.automation.impl.component.filter.FilterComponent;
import com.advalent.automation.impl.component.loadingcomponent.DataGridLoadingComponent;
import com.advalent.automation.impl.component.loadingcomponent.NavBarLoadingComponent;
import com.advalent.automation.impl.component.pagination.IHavePaginationComponent;
import com.advalent.automation.impl.component.pagination.PaginationComponent;
import com.advalent.automation.impl.pages.common.AbstractWebComponent;
import com.advalent.automation.impl.utils.WaitUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DataGrid extends AbstractWebComponent implements IHavePaginationComponent, IDataGrid {

    protected String locator;
    private HashMap<Integer, Column> columnMap = new HashMap<>();

    private PaginationComponent paginationComponent;
    private DataGridProperties[] rowsProperties;
    private Class rowDrillPage;
    private Class rowClass;
    private FilterComponent filterComponent;

    public DataGrid(WebDriver driver, String locator) {
        super(driver);
        this.driver = driver;
        this.locator = locator;
        init();
    }




    public void init() {
        initColumns();
        paginationComponent = new PaginationComponent(driver);
        filterComponent = new FilterComponent(driver);

    }

    protected com.google.common.base.Predicate<WebDriver> guavaCellWithNoDataFoundMessage = row -> (row.findElements(By.xpath("./td")).size() == 1) &&
            (!row.findElement(By.xpath("./td")).isDisplayed() ||
                    (row.findElement(By.xpath("./td")).isDisplayed() &&
                            row.findElement(By.xpath("./td")).getText().equalsIgnoreCase("No data found")));

    protected Predicate<WebElement> cellWithNoDataFoundMessage = row -> (row.findElements(By.xpath("./td")).size() == 1) &&
            (!row.findElement(By.xpath("./td")).isDisplayed() ||
                    (row.findElement(By.xpath("./td")).isDisplayed() &&
                            row.findElement(By.xpath("./td")).getText().equalsIgnoreCase("No data found")));

    private void initColumns() {
        AtomicInteger index = new AtomicInteger();
        WaitUtils.waitUntil(driver, TimeOuts.SIXTY_SECONDS, (o) -> getDriver().findElement(By.xpath(this.locator + "/thead/tr")).isDisplayed());
        WebElement headerRow = getDriver().findElements(By.xpath(this.locator + "/thead/tr")).stream().filter(r -> r.isDisplayed()).findFirst().get();
        List<WebElement> columnHeaders = headerRow.findElements(By.xpath("./th"));
        for (WebElement header : columnHeaders) {
            String colName = header.getText().trim();
            Column col = new Column(index.incrementAndGet(), colName);
            col.setDataGridLocator(this.locator);
            columnMap.put(index.intValue(), col);
        }

    }


    @Override
    public WebDriver getDriver() {
        return this.driver;
    }

    @Override
    public PaginationComponent getPaginationComponent() {
        return paginationComponent;
    }


    public void setColumns(LinkedHashMap<Integer, Column> columnMap) {
        this.columnMap = columnMap;
    }


    public Column getColumn(int columnIndex) {
        return getColumn(column -> column.getColumnIndex() == columnIndex);
    }

    public Column getColumn(String columnName) {
        return getColumn(column -> column.getColumnName().equalsIgnoreCase(columnName));
    }

    private Column getColumn(Predicate<Column> filterPredicate) {
        return columnMap.values().stream()
                .filter(filterPredicate)
                .findFirst().orElseThrow(() -> new AutomationException("cannot find column"));
    }


    public HashMap<Integer, Column> getColumnMap() {
        return this.columnMap;
    }


    public void setColumn(Column col) {
        columnMap.put(col.getColumnIndex(), col);
    }


    private boolean canClickOnPaginationNextBtn() {
        return this.isPaginationComponentDisplayed() && this.paginationComponent.isNextButtonVisible();
    }


    public <T extends IRow> T getRow(int rowIndexToOperate) {
        T row = getRowInternal(rowIndexToOperate);
        row.setProperties(this.rowsProperties);
        row.setDrillPage(this.rowDrillPage);
        return row;
    }


    private <T extends IRow> T getRowInternal(int rowIndexToOperate) {
        T c = null;
        if (getRowClass() != null) {
            try {
                Constructor cu = getRowClass().getConstructor(WebDriver.class, int.class, HashMap.class);
                c = (T) cu.newInstance(getDriver(), rowIndexToOperate, columnMap);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } else {
            c = (T) new Row(getDriver(), rowIndexToOperate, columnMap);
        }
        return c;
    }

    public String getDataAsString() {
        return getRowElements().stream()
                .map(tr -> tr.getText()).reduce("", String::concat);

    }

    protected List<WebElement> getRowElements() {
        return this.driver.findElements(By.xpath(this.locator + "/tbody/tr"));
    }

    public Row getRowWithActiveLinkInColumn(String colName) {
        return null;
    }


    public void setRowsProperties(DataGridProperties[] rowsProperties) {
        this.rowsProperties = rowsProperties;
    }

    public DataGridProperties[] getRowsProperties() {
        return rowsProperties;
    }

    public Class getRowDrillPage() {
        return rowDrillPage;
    }

    public void setRowDrillPage(Class rowDrillPage) {
        this.rowDrillPage = rowDrillPage;
    }


    private com.google.common.base.Predicate<WebDriver> hasMoreThenOneRow() {
        return arg0 -> getRowElements().size() > 1;
    }


    public int getRowCount() {
        return getRowElements().stream().filter(cellWithNoDataFoundMessage.negate()).collect(Collectors.toList()).size();
    }


    public <T extends ICell> ICell findClickableColumn(String columnName) {
        int noOfRow = getRowCount();
        for (int i = 1; i <= noOfRow; i++) {
            if (getRow(i).getCell(columnName).isActive()) {
                return getRow(i).getCell(columnName);
            }
        }
        if (canClickOnPaginationNextBtn()) {
            this.paginationComponent.clickOnNextButton();
            return findClickableColumn(columnName);
        }
        throw new AutomationException("No Active Link Found For " + columnName);
    }


    @Override
    public boolean isFullyLoaded() {
        try {
            return getDriver().findElement(By.xpath(this.locator + "/thead/tr")).isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }


    }

    @Override
    public AbstractWebComponent doWaitTillFullyLoaded(int waitTimeInSecs) {
        new WebDriverWait(getDriver(), waitTimeInSecs)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(WebDriverException.class)
                .ignoring(NoSuchElementException.class)
                .until((com.google.common.base.Predicate<WebDriver>) arg0 -> isFullyLoaded());

        return this;
    }

    public DataGrid waitTillDataIsLoaded(int waitTimeInSecs) {
        new DataGridLoadingComponent(driver).waitTillDisappears(TimeOuts.SIXTY_SECONDS);
        return this;
    }

    public DataGrid waitTillDataIsCleared(int waitTime) {
        logger.info("Waiting for __{}__ data to be cleared ...", this.getClass()
                .getSimpleName());
        new NavBarLoadingComponent(driver).waitTillDisappears(TimeOuts.SIXTY_SECONDS);
        return this;
    }

    private com.google.common.base.Predicate<WebDriver> isDataLoaded() {
        return arg0 -> getRowElements().size() > 1 || getRowElementWithNoDataFoundMessage().findElement(By.xpath("./td")).isDisplayed();
    }

    @Override
    public List<IRow> getRows() {
        AtomicInteger i = new AtomicInteger(1);
        return getRowElements().stream().filter(cellWithNoDataFoundMessage.negate())
                .map(row -> new Row(driver, i.getAndIncrement(), columnMap))
                .collect(Collectors.toList());
    }

    private WebElement getRowElementWithNoDataFoundMessage() {
        WaitUtils.waitUntil(driver, TimeOuts.SIXTY_SECONDS, guavaCellWithNoDataFoundMessage);
        return getRowElements().stream().filter(cellWithNoDataFoundMessage).findFirst().get();
    }


    public void setRowClass(Class rowClass) {
        this.rowClass = rowClass;
    }

    public Class getRowClass() {
        return rowClass;
    }


    public FilterComponent getFilterComponent() {
        return filterComponent;
    }

    public void setFilterComponent(FilterComponent filterComponent) {
        this.filterComponent = filterComponent;
    }

    public IDataGrid sort(String filterBy, String filterType) {
        filterComponent.sortBy(filterBy);
        filterComponent.sortType(filterType);
        filterComponent.clickOnSortButton();
        return this;
    }

}


