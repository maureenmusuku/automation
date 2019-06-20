package com.advalent.automation.impl.component.pagination;

import com.advalent.automation.api.constants.TimeOuts;
import com.advalent.automation.api.exceptions.AutomationException;
import com.advalent.automation.impl.component.datagrid.DataGrid;
import com.advalent.automation.impl.component.filter.SortLoadingComponent;
import com.advalent.automation.impl.pages.common.AbstractWebComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.advalent.automation.reporting.ExtentHTMLReportManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PaginationComponent extends AbstractWebComponent {


    private final DataGrid dataGrid;
    private final int waitTime;
    WebElement paginationElementsContainer;

    WebElement nextBtn;
    WebElement prevBtn;
    private int recordShownFrom;
    private int recordShownTo;
    SortLoadingComponent loadingComponent;

    public PaginationComponent(WebDriver driver, DataGrid dataGrid, int waitTime) {

        super(driver);
        this.dataGrid = dataGrid;
        this.waitTime = waitTime;
        this.loadingComponent = new SortLoadingComponent(driver);

    }

    public PaginationComponent(WebDriver driver) {
        super(driver);
        this.dataGrid = null;
        this.waitTime = TimeOuts.TEN_SECONDS;
        this.loadingComponent = new SortLoadingComponent(driver);
    }

    public PaginationComponent clickOnNextButton() {
        if (isNextButtonVisible()) {
            nextBtn.click();
            loadingComponent.waitTillDisappears(TimeOuts.SIXTY_SECONDS);
        } else {
            throw new AutomationException("Next Button Is Not Visible");
        }
        return this;
    }

    public boolean isNextButtonVisible() {
        try {
            nextBtn = getDriver().findElement(By.id(":1wk"));
            return nextBtn.isDisplayed();
        } catch (NoSuchElementException e) {
            ExtentHTMLReportManager.getInstance().addStep("Next Button Is Not Visible In Pagination Component");
        }
        return false;
    }

    public PaginationComponent clickOnPreviousButton() {
        if (isPreviousButtonVisible()) {
            prevBtn.click();
            loadingComponent.waitTillDisappears(TimeOuts.SIXTY_SECONDS);
        } else {
            throw new AutomationException("Previous Button Is Not Displayed");
        }
        return this;
    }

    public boolean isPreviousButtonVisible() {
        try {
            prevBtn = getDriver().findElement(By.id(":1wj"));
            return prevBtn.isDisplayed();
        } catch (NoSuchElementException e) {
            ExtentHTMLReportManager.getInstance().addStep("Previous Button Is Not Visible In Pagination Component");
        }
        return false;
    }


    @Override
    public boolean isFullyLoaded() {
        if (isPaginationComponentAvailable()) {
            return paginationElementsContainer.isDisplayed();
        }
        return false;
    }

    private boolean isPaginationComponentAvailable() {
        try {
            paginationElementsContainer = getDriver().findElement(By.className("ar5"));
            return true;
        } catch (NoSuchElementException e) {
            ExtentHTMLReportManager.getInstance().addStep("Pagination Component Is Not Available");
        }
        return false;
    }


    public int getNumberOfRecordsDisplayed() {
        try {

            String displayedRecord = getDriver().findElement(By.id(":1wh")).getText()
                    .replace("Records:", "")
                    .replace(" buildDataGridAs many", "");

            Pattern p = Pattern.compile("\\d+");
            Matcher m = p.matcher(displayedRecord);
            int index = 0;
            while (m.find()) {
                if (index == 0) {
                    recordShownFrom = Integer.parseInt(m.group());
                    index++;
                } else {
                    recordShownTo = Integer.parseInt(m.group());
                }
            }
            return recordShownTo - recordShownFrom + 1;
        } catch (NoSuchElementException ex) {
            logger.info("No data Displayed");
            return 0;
        }
    }


    public String getTotalNoOfRecords() {
        return driver.findElement(By.xpath("//*[@id=\":1wh\"]/span/span[2]/span")).getText();
    }

    public int getIndexOfRecordDisplayedFrom() {
        getNumberOfRecordsDisplayed();
        return recordShownFrom;
    }

    public int getIndexOfRecordDisplayedTo() {
        getNumberOfRecordsDisplayed();
        return recordShownTo;
    }

}
