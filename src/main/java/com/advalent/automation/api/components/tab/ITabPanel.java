package com.advalent.automation.api.components.tab;

import com.advalent.automation.api.constants.TimeOuts;
import com.advalent.automation.reporting.ExtentHTMLReportManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.Map;

public interface ITabPanel {

    /**
     * @return WebDriver instance
     */
    WebDriver getDriver();

    /**
     * get map of available tab with
     * WebElement of Tab Pill in tab panel
     * e.g for tab GlobalSearchPage
     *
     * @return java.util.Map with TabClass as Key and WebElement of Tab pill as key
     * WebElement>
     * @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div/form/div/div/ul/li[1]/a/tab-heading/span")
     * WebElement eventIncidentSearchTabPill;
     * @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div/form/div/div/ul/li[2]/a/tab-heading/span")
     * WebElement memberSearchTabPill;
     * <p>
     * public Map<Class, WebElement> getAvailableTabsAndTabsPillMap() {
     * Map<Class, WebElement> availableTabsAndTabsPillXpath = new HashMap<>();
     * availableTabsAndTabsPillXpath.put(EventIncidentSearchTab.class, eventIncidentSearchTabPill);
     * availableTabsAndTabsPillXpath.put(MemberSearchTab.class, memberSearchTabPill);
     * return availableTabsAndTabsPillXpath;
     * }
     */
    Map<Class, WebElement> getAvailableTabsAndTabsPillMap();

    /**
     * click on the tab
     *
     * @param tabClass
     * @return page object of clicked tab
     */

    default <T extends ITab> ITab clickOnTab(Class<T> tabClass) {
        ExtentHTMLReportManager.getInstance().addStep("Clicking On " + tabClass.getName() + " Tab");
        WebElement tabPill = getAvailableTabsAndTabsPillMap().get(tabClass);
        tabPill.click();
        ITab tab = PageFactory.initElements(getDriver(), tabClass);
        tab.doWaitTillFullyLoaded(TimeOuts.SIXTY_SECONDS);
        return tab;
    }

    /**
     * @return the page object of tab which is active by default
     */
    <T extends ITab> ITab getDefaultTab();


    default int getNumberOfAvailableTab() {
        return -1;
    }

    default String getActiveTab() {
        ExtentHTMLReportManager.getInstance().addStep("Searching For Active Tab In " +
                this.getClass().getSimpleName() + " Tab Panel");
        return getAvailableTabsAndTabsPillMap().values().stream()
                .filter(tabPill -> tabPill.getAttribute("class").contains("active"))
                .findFirst().get()
                .getText();
    }

}
