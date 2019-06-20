package com.advalent.automation.impl.component.datagrid;

import com.advalent.automation.impl.pages.common.AbstractWebComponent;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Row extends AbstractWebComponent implements IRow {
    private final int index;
    private final WebDriver driver;
    private HashMap<Integer, Column> columnMap;
    private DataGridProperties[] properties;
    private Class drillPage;
    private String xpath;


    public Row(WebDriver driver, int rowIndexToOperate, HashMap<Integer, Column> columnMap) {
        super(driver);
        this.index = rowIndexToOperate;
        this.driver = driver;
        this.columnMap = columnMap;
    }

    public Row(WebDriver driver, int rowIndex) {
        super(driver);
        this.index = rowIndex;
        this.driver = driver;
    }


    public int getIndex() {
        return index;
    }


    public WebDriver getDriver() {
        return driver;
    }


    public void setColumnMap(LinkedHashMap<Integer, Column> columnMap) {
        this.columnMap = columnMap;
    }


    public List<String> getDataAsList() {
        return getCells().stream().map(cell -> cell.getValue()).collect(Collectors.toList());
    }

    public String getDataAsString() {
        return getRowElement().getText();
    }

    private WebElement getRowElement() {
        return this.driver.findElement(By.xpath(this.getDataGridLocator() + getRowXpath()));
    }


    public <T extends ICell> ICell getCell(String columnName) {
        return getCell(col -> col.getColumnName().trim().equalsIgnoreCase(columnName.trim()));
    }

    private <T extends ICell> T getCell(Predicate<Column> columnPredicate) {
        Column col = this.columnMap.values().stream().filter(columnPredicate).findFirst().get();
        T c = null;
        if (col.getCellClass() != null) {

            try {
                Constructor cu = col.getCellClass().getConstructor(WebDriver.class, int.class, int.class);
                c = (T) cu.newInstance(getDriver(), col.getColumnIndex(), this.getIndex(), col);
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
            c = (T) new Cell(driver, col.getColumnIndex(), this.index, col);
        }
        c.setRowXpath(getRowXpath());
        return c;
    }

    public <T extends ICell> ICell getCell(int columnIndex) {
        return getCell(col -> col.getColumnIndex() == columnIndex);
    }

    public <T extends IRow> T click() {
        try {
            getRowElement().click();
        } catch (StaleElementReferenceException ex) {
            getRowElement().click();
        }

        if (Arrays.asList(properties).contains(DataGridProperties.Clickable)) {
            return null;
        }
        if (Arrays.asList(properties).contains(DataGridProperties.Drillable) || (Arrays.asList(properties).contains(DataGridProperties.Drillable) && this.drillPage != null)) {
            Class<T> clazz = drillPage;
            return PageFactory.initElements(getDriver(), clazz);
        }
        return null;
    }

    @Override
    public List<ICell> getCells() {
        AtomicInteger i = new AtomicInteger(1);
        return getRowElement().findElements(By.xpath("./td")).stream()
                .map(td -> getCell(i.getAndIncrement())).collect(Collectors.toList());
    }

    public void setProperties(DataGridProperties[] properties) {
        this.properties = properties;
    }

    public DataGridProperties[] getProperties() {
        return properties;
    }

    public void setDrillPage(Class drillPage) {
        this.drillPage = drillPage;
    }

    public Class getDrillPage() {
        return drillPage;
    }


    public String getDataGridLocator() {
        return this.columnMap.values()
                .stream().findAny().get()
                .getDataGridLocator();
    }

    public void setXpath(String xpath) {
        this.xpath = xpath;
    }

    public String getXpath() {
        return xpath;
    }

    @Override
    public String getRowXpath() {
        return "/tbody/tr[" + this.index + "]";
    }

    @Override
    public boolean isFullyLoaded() {
        return false;
    }

}
