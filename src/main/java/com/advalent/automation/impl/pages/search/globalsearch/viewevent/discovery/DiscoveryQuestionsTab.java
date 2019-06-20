package com.advalent.automation.impl.pages.search.globalsearch.viewevent.discovery;

import com.advalent.automation.api.components.tab.ITab;
import com.advalent.automation.impl.pages.common.AbstractWebComponent;
import org.openqa.selenium.WebDriver;

/**
 * Created By: sumit
 * Created Date :11/21/2018
 * Note:
 */
public class DiscoveryQuestionsTab extends AbstractWebComponent implements ITab {
    public DiscoveryQuestionsTab(WebDriver driver) {
        super(driver);
    }

    @Override
    public String getTabName() {
        return null;
    }

    @Override
    public String getTabTitle() {
        return null;
    }

    @Override
    public boolean isFullyLoaded() {
        return false;
    }
}
