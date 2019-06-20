package com.advalent.automation.impl.pages.search.globalsearch.viewevent.memberinformation;

import com.advalent.automation.api.components.tab.ITab;
import com.advalent.automation.api.components.tab.ITabPanel;
import com.advalent.automation.impl.pages.common.AbstractWebComponent;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.memberinformation.patientinfotab.PatientInformationTab;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.memberinformation.policyholdeinfo.PolicyHolderInformationTab;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.Map;

public class MemberInformationTab extends AbstractWebComponent implements ITab, ITabPanel {
    @FindAll({
            @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div/form/div[2]/div[1]/div/div/div/div[3]/div/h4"),
            @FindBy(xpath = "//*[@id=\"content\"]/div[3]/h4")
    })
    WebElement pageTitle;

    @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div/form/div[2]/div[1]/div/div/div/div[3]/div/div/div/ul/li[1]/a/h5")
    WebElement patientInfoTabPill;
    @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div/form/div[2]/div[1]/div/div/div/div[3]/div/div/div/ul/li[2]/a/h5")
    WebElement policyHolderTabPill;

    public MemberInformationTab(WebDriver driver) {
        super(driver);
    }

    @Override
    public String getTabName() {

        return "Member Information";
    }

    @Override
    public String getTabTitle() {
        return pageTitle.getText();
    }

    @Override
    public boolean isFullyLoaded() {
        return pageTitle.isDisplayed();
    }


    @Override
    public Map<Class, WebElement> getAvailableTabsAndTabsPillMap() {
        Map<Class, WebElement> map = new HashMap<>();
        map.put(PatientInformationTab.class, patientInfoTabPill);
        map.put(PolicyHolderInformationTab.class, policyHolderTabPill);
        return map;
    }


    @Override
    public <T extends ITab> ITab getDefaultTab() {
        return new PatientInformationTab(getDriver());
    }

   /* @Override
    public Map<String, String> getTabPillXpathMap() {
        return tabPillXpathMap;
    }

    @Override
    public void setTabPillXpathMap(String tabName, String tabPillXpath) {
        tabPillXpathMap.put(tabName, tabPillXpath);
    }

*/
}
