package com.advalent.automation.impl.pages.dashboard;

import com.advalent.automation.api.constants.TimeOuts;
import com.advalent.automation.api.pages.dashboard.IDashboardPage;
import com.advalent.automation.impl.component.datagrid.DataGridBuilder;
import com.advalent.automation.impl.component.datagrid.DataGrid;
import com.advalent.automation.impl.component.navigationbar.NavigationBar;
import com.advalent.automation.impl.pages.common.AbstractWebComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdvalentDashboard extends AbstractWebComponent implements IDashboardPage {

    @FindBy(xpath = "//*[@id=\"charts-container\"]/div[1]/div/dash-menu/div/div")
    WebElement myTeamPanel;
    DataGrid inventoryDataGrid;
    DataGrid taskDataGrid;


    public AdvalentDashboard(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
//        this.doWaitTillFullyLoaded(TimeOuts.SIXTY_SECONDS);
    }

    @Override
    public boolean isFullyLoaded() {
        return myTeamPanel.isDisplayed();
    }

    @Override
    public NavigationBar getNavigationBar() {
        return new NavigationBar(getDriver());
    }

    @Override
    public boolean isPageDisplayed() {
        return isPageDisplayed();
    }

    @Override
    public void logOut() {

    }


}
