package com.advalent.automation.impl.component.datagrid;

import java.util.HashMap;

public class ColumnBuilder {
    private static ColumnBuilder instance;
    private Column columnToBuild;
    HashMap<Integer, Column> columnMap;
    private Class cellClass;



    public static ColumnBuilder createColumn(String colName) {
        instance = new ColumnBuilder();
        instance.columnToBuild = new Column();
        instance.columnToBuild.setColumnName(colName);
        return instance;
    }

    public static ColumnBuilder getInstance() {
        return instance;
    }

    public ColumnBuilder atIndex(int colIndex) {
        instance.columnToBuild.setColumnIndex(colIndex);
        return instance;
    }

    public ColumnBuilder makeClickable() {
        instance.columnToBuild.setClickable(true);
        return instance;
    }

    public ColumnBuilder setClickableChildElementXpath(String childElement) {
        instance.columnToBuild.setChildElementXpath(childElement);
        return instance;
    }

    public ColumnBuilder attributeToActivate(String attributeName, String attributeValue) {
        instance.columnToBuild.setAttributeToActivateDeactivate(attributeName, attributeValue);
        return instance;
    }

    public ColumnBuilder makeDrillable() {
        instance.columnToBuild.setClickable(true);
        instance.columnToBuild.setDrillable(true);
        return instance;
    }

    public ColumnBuilder drillsTo(Class drillPage) {
        instance.columnToBuild.setDrillPage(drillPage);
        return instance;
    }

    public <T extends Column> Column build() {
        return instance.columnToBuild;
    }

    public <T> ColumnBuilder ofClass(Class<T> cellClass) {
        instance.columnToBuild.setCellClass(cellClass);
        return this;
    }

    public DataGridBuilder buildColumnAndGetDataGridBuilder() {
        DataGridBuilder dataGridBuilderInstance = DataGridBuilder.getInstance().setColumnToBuild( instance.columnToBuild);
        instance = null;
        return dataGridBuilderInstance;
    }
}
