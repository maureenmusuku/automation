package com.advalent.automation.impl.component.datagrid;

import com.advalent.automation.api.exceptions.AutomationException;
import com.advalent.automation.reporting.ExtentHTMLReportManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Cell implements ICell {

    private final int rowIndex;
    private final int colIndex;
    protected final WebDriver driver;

    private WebElement clickableElement;

    private Column column;
    private String rowXpath;


    public Cell(WebDriver driver, int colIndex, int rowIndex, Column column) {
        this.driver = driver;
        this.colIndex = colIndex;
        this.rowIndex = rowIndex;
        this.column = column;

    }


    public String getValue() {
        ExtentHTMLReportManager.getInstance().addStep("Reading Value Of " + getColumnName() + " Column Of Row " + this.rowIndex);
        return getCellElement().getText();
    }

    protected WebElement getCellElement() {
        return this.driver.findElement(By.xpath(this.column.getDataGridLocator() + rowXpath + getCellXpath()));
    }

    /**
     * method to click on the clickable element inside the cell
     * and return the page which is navigated after clicking
     *
     * @return object of page navigated after clicking on the cell
     */
    public <T> T click() {
        ExtentHTMLReportManager.getInstance().addStep("Clicking On " + getColumnName() + " Column Of Row " + this.rowIndex);
        if (this.hasClickableProperty()) {
            clickableElement = getCellElement().findElement(By.xpath(this.column.getChildElementXpath()));
            try {
                clickableElement.click();
                if (this.column.getDrillPage() != null) {
                    return PageFactory.initElements(this.driver, this.column.getDrillPage());
                }
            } catch (NoSuchElementException ex) {
                ex.printStackTrace();
                ExtentHTMLReportManager.getInstance().addStep("Clickable Element Not Found In" + getColumnName() + " Column Of Row " + this.rowIndex);
            }
        } else {
            throw new AutomationException("Column " + getColumnName() + " is not clickable or the clickable property of column is not set while building datagrid");
        }
        return (T) new Object();
    }


    @Override
    public String getCellXpath() {
        return "/td[" + this.colIndex + "]";
    }

    @Override
    public void setRowXpath(String rowXpath) {
        this.rowXpath = rowXpath;
    }


    public boolean hasClickableProperty() {
        return column.isLink() || column.isDrillable() || column.isClickable();
    }

    @Override
    public boolean isActive() {
        try {
            return getCellElement()
                    .findElement(By.xpath(this.column.getChildElementXpath()))
                    .getAttribute(column.getActivatingProperty()).contains(column.getAttributeValue());
        } catch (Exception ex) {
            return false;
        }
    }

    public void setColumn(Column column) {
        this.column = column;
    }


    @Override
    public String getColumnName() {
        return this.column.getColumnName();
    }
}
