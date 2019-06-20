package com.advalent.automation.impl.component.datagrid;

public interface ICell {


    void setColumn(Column column);

    String getValue();

    String getColumnName();

    boolean hasClickableProperty();

    boolean isActive();

    <T> T click();



    String getCellXpath();

    void setRowXpath(String rowXpath);
}
