package com.advalent.automation.impl.component.datagrid;

import java.util.Arrays;

public class Column {


    private int columnIndex;
    private String columnName;

    private DataGridProperties[] properties;

    private boolean clickable = false;
    private boolean link = false;
    private String linkXpath;

    private boolean checkBox = false;
    private String checkBoxXpath;
    private Class drillPage;
    private String childElementXpath;
    private String activatingProperty;
    private String attributeValue;


    private Class cellClass;
    private boolean rowClickable;
    private String columnXpath;
    private boolean drillable;
    private boolean highlightable;
    private String dataGridLocator;


    public Column() {
    }


    public Column(int index, String colName, DataGridProperties... properties) {
        this.columnIndex = index;
        this.columnName = colName;
        this.properties = properties;

    }

    public String getActivatingProperty() {
        return activatingProperty;
    }

    public void setActivatingProperty(String activatingProperty) {
        this.activatingProperty = activatingProperty;
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }

    public DataGridProperties[] getProperties() {
        return properties;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public void setColumnIndex(int columnIndex) {
        this.columnIndex = columnIndex;
    }

    public String getLinkXpath() {
        return linkXpath;
    }

    public void setLinkXpath(String linkXpath) {
        this.linkXpath = linkXpath;
    }

    public String getCheckBoxXpath() {
        return checkBoxXpath;
    }

    public void setCheckBoxXpath(String checkBoxXpath) {
        this.checkBoxXpath = checkBoxXpath;
    }

    public boolean isDrillable() {
        return drillable;
    }

    public Column setDrillable(boolean drillable) {
        this.drillable = drillable;
        return this;
    }

    public boolean isHighlightable() {
        return highlightable;
    }

    public void setHighlightable(boolean highlightable) {
        this.highlightable = highlightable;
    }


    public boolean isLink() {
        return link;
    }

    public void setLink(boolean link) {
        this.link = link;
    }

    public boolean isCheckBox() {
        return checkBox;
    }

    public void setCheckBox(boolean checkBox) {
        this.checkBox = checkBox;
    }


    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setProperty(DataGridProperties property) {
        switch (property) {
            case Link:
                link = true;
                break;
            case CheckBox:
                checkBox = true;
                break;
            case Drillable:
                drillable = true;
                break;
            case Highlightable:
                highlightable = true;
        }
    }


    public void setProperties(DataGridProperties[] properties) {
        this.properties = properties;
        Arrays.stream(this.properties).forEach(p -> setProperty(p));
    }

    public Column setClickable(boolean clickable) {
        this.clickable = true;
        return this;
    }

    public boolean isClickable() {
        return clickable;
    }

    public Column setDrillPage(Class drillPage) {
        this.drillPage = drillPage;
        return this;
    }

    public <T> Class<T> getDrillPage() {
        return drillPage;
    }


    public Column setChildElementXpath(String childElement) {
        this.childElementXpath = childElement;
        return this;
    }

    public String getChildElementXpath() {
        return childElementXpath;
    }

    public Column setAttributeToActivateDeactivate(String attributeName, String attributeValue) {
        this.activatingProperty = attributeName;
        this.attributeValue = attributeValue;
        return this;
    }

    public <T> void setCellClass(Class<T> cellClass) {
        this.cellClass = cellClass;
    }

    public <T> Class<T> getCellClass() {
        return cellClass;
    }

    public void setRowClickable(boolean rowClickable) {
        this.rowClickable = rowClickable;
    }

    public boolean isRowClickable() {
        return rowClickable;
    }

    public Column makeDrillable() {
        this.clickable = true;
        this.drillable = true;
        return this;
    }


    public Column setClickableChildElementXpath(String s) {
        return setChildElementXpath(s);
    }

    public Column drillsTo(Class drillPageClass) {
        return setDrillPage(drillPageClass);
    }

    public void setColumnXpath(String columnXpath) {
        this.columnXpath = columnXpath;
    }

    public String getColumnXpath() {
        return columnXpath;
    }

    public String getDataGridLocator() {
        return dataGridLocator;
    }

    public void setDataGridLocator(String dataGridLocator) {
        this.dataGridLocator = dataGridLocator;
    }
}
