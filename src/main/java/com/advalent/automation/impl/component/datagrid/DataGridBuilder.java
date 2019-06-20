package com.advalent.automation.impl.component.datagrid;

import org.openqa.selenium.WebDriver;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * Builder class to build instance of data grid.
 * Primary function of this class is to create
 * instance of data grid. Also used to customize the properties
 * of columns/rows of data grid and/or data data grid itself.
 */
public class DataGridBuilder<T extends DataGrid> {
    private final String dataGridXpath;
    private Class<T> dataGridClass;
    private static DataGridBuilder builder;
    private T dataGrid;
    private ArrayList<Column> columnsToBuild = new ArrayList<>();
    private DataGridProperties[] dataGridProperties;
    private Class rowDrillPageClass;
    private Class rowClass;

    public DataGridBuilder(Class<T> dataGridClass, String dataGridXpath) {
        this.dataGridClass = dataGridClass;
        this.dataGridXpath = dataGridXpath;
    }

    public static <T extends DataGrid> DataGridBuilder<T> createDataGridOf(Class<T> dataGridClass, String datagridXpath) {
        builder = new DataGridBuilder(dataGridClass, datagridXpath);
        return builder;
    }

    public static <T extends DataGrid> DataGridBuilder<T> createDataGridWithXpath(String dataGridXpath) {
        builder = new DataGridBuilder(DataGrid.class, dataGridXpath);
        return builder;
    }

    public T build(WebDriver driver) {
        builder.dataGrid = buildDataGrid(driver);
        loadColumns();
        if (dataGridProperties != null) {
            builder.dataGrid.setRowsProperties(dataGridProperties);
        }

        if (rowDrillPageClass != null) {
            builder.dataGrid.setRowDrillPage(rowDrillPageClass);
        }
        if (rowClass != null) {
            builder.dataGrid.setRowClass(rowClass);
        }
        return (T) builder.dataGrid;
    }

    private void loadColumns() {
        if (!columnsToBuild.isEmpty()) {
            columnsToBuild.forEach(col -> {
                Column column = builder.dataGrid.getColumn(col.getColumnName());
                col.setColumnIndex(column.getColumnIndex());
                col.setDataGridLocator(this.dataGridXpath);
                builder.dataGrid.setColumn(col);
            });
        }
    }

    private T buildDataGrid(WebDriver driver) {
        T dataGrid = null;
        try {
            Constructor c = builder.dataGridClass.getConstructor(WebDriver.class, String.class);
            dataGrid = (T) c.newInstance(driver, builder.dataGridXpath);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return dataGrid;
    }


    public ColumnBuilder buildColumn(String columnName) {
        return ColumnBuilder.createColumn(columnName);
    }


    public static DataGridBuilder getInstance() {
        return builder;
    }

    public T getDataGrid() {
        return this.dataGrid;
    }

    public void setDataGrid(T dataGrid) {
        this.dataGrid = dataGrid;
    }

    public DataGridBuilder setColumnToBuild(Column columnToBuild) {
        this.columnsToBuild.add(columnToBuild);
        return this;
    }

    public DataGridBuilder setColumn(int i, Column colToReplace) {
        colToReplace.setColumnIndex(i);
        columnsToBuild.add(colToReplace);
        return this;
    }

    public DataGridBuilder addRowProperties(DataGridProperties... dataGridProperties) {
        this.dataGridProperties = dataGridProperties;
        return this;
    }

    public DataGridBuilder setRowDrillPage(Class drillPageClass) {
        this.rowDrillPageClass = drillPageClass;
        return this;
    }

    public <T extends DataGrid> DataGridBuilder<T> setRowClass(Class rowClass) {
        this.rowClass = rowClass;
        return builder;
    }
}

