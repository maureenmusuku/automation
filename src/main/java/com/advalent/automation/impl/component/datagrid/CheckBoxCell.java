package com.advalent.automation.impl.component.datagrid;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckBoxCell extends Cell implements ICell {


    private String checkBoxXpath;

    public CheckBoxCell(WebDriver driver, int colIndex, int rowIndex, Column column) {
        super(driver, colIndex, rowIndex, column);
        checkBoxXpath = "/input[@type='checkbox']";
    }


    public void check() {
        if (isChecked()) {
            return;
        } else {
            getCellElement().findElement(By.xpath("." + checkBoxXpath)).click();
        }
    }

    public void unCheck() {
        if (isChecked()) {
            getCellElement().findElement(By.xpath("." + checkBoxXpath)).click();
        } else {
            return;
        }
    }


    private boolean isChecked() {
        return getCellElement().findElement(By.xpath("." + checkBoxXpath)).isSelected();
    }


    public String getCheckBoxXpath() {
        return checkBoxXpath;
    }

    public void setCheckBoxXpath(String checkBoxXpath) {
        this.checkBoxXpath = checkBoxXpath;
    }
}
