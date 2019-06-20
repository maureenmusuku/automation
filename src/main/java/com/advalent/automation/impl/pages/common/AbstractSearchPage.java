package com.advalent.automation.impl.pages.common;

import com.advalent.automation.api.annotations.LogStep;
import com.advalent.automation.api.components.datagrid.IDataGrid;
import com.advalent.automation.api.components.search.ISearchPage;
import com.advalent.automation.api.constants.TimeOuts;
import com.advalent.automation.impl.component.datagrid.DataGrid;
import com.advalent.automation.impl.component.inputelements.*;
import com.advalent.automation.selenium.SeleniumUtils;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractSearchPage extends AbstractWebComponent implements ISearchPage {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    IDataGrid dataGrid;

    @FindBy(id = "warnOk")
    WebElement warningOkBtn;
    @FindBy(id = "warnCancel")
    WebElement warningCancelBtn;

    public AbstractSearchPage(WebDriver driver) {
        super(driver);
    }

    /**
     * @return WebElement for search button
     */
    protected abstract WebElement getSearchButton();

    /**
     * @return WebElement for clear button
     */
    protected abstract WebElement getClearButton();

    /**
     * @return WebElement for clear button
     */
    protected abstract WebElement getAddButton();

    /**
     * click on search button
     */
    @Override
    @LogStep(step = "Clicking On Search Button")
    public DataGrid clickOnSearchButton() {
        logger.info("clicking on search button Of {} page", this.getClass().getSimpleName());
        SeleniumUtils.click(getSearchButton(), driver);
        if (isWarningDailogDisplayed()) {
            this.clickOnOkOfWarning();
        }
        return this.getDataGrid().waitTillDataIsLoaded(TimeOuts.SIXTY_SECONDS);

    }


    /**
     * click on search button
     */
    @Override
    @LogStep(step = "Clicking On Clear Button")
    public DataGrid clearSearch() {
        logger.info("clicking on clear search button of {} page", this.getClass());
        SeleniumUtils.click(getClearButton(), driver);
        return this.getDataGrid().waitTillDataIsCleared(TimeOuts.THREE_SECONDS);
    }

    @Override
    @LogStep(step = "Search ")
    public void searchBy(InputElement inputElement, String value, int waitTime) {
        if (inputElement instanceof TextBox) {
            inputElement = new TextBox(getDriver(), inputElement.getLocator());
            inputElement.clearValue();
            inputElement.enterValue(value);
        } else if (inputElement instanceof DropDown) {
            inputElement = new DropDown(getDriver(), inputElement.getLocator());
            inputElement.selectOption(value);
        } else if (inputElement instanceof MultipleAutoComplete) {
            inputElement = new MultipleAutoComplete(getDriver(), inputElement.getLocator());
            inputElement.enterValue(value);
        } else if (inputElement instanceof AutoSuggest) {
            inputElement = new AutoSuggest(getDriver(), inputElement.getLocator());
            inputElement.enterValue(value);
        }
        SeleniumUtils.click(getSearchButton(), driver);
        if (isWarningDailogDisplayed())
            clickOnOkOfWarning();
        this.getDataGrid().waitTillDataIsLoaded(waitTime);
    }

    @Override
    public DataGrid clickOnOkOfWarning() {
        warningOkBtn.click();
        return this.getDataGrid();
    }

    @Override
    public DataGrid clickOnCancelOfWarning() {
        warningCancelBtn.click();
        return this.getDataGrid();

    }

    public boolean isWarningDailogDisplayed() {
        try {
            return warningOkBtn.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
