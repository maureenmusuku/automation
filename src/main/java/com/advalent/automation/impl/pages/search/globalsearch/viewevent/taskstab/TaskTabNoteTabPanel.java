package com.advalent.automation.impl.pages.search.globalsearch.viewevent.taskstab;

import com.advalent.automation.api.components.tab.ITab;
import com.advalent.automation.api.components.tab.ITabPanel;
import com.advalent.automation.impl.pages.common.AbstractWebComponent;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.activitylog.ActivityLogTab;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.casecorrespondence.CaseCorrespondenceTab;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.claimsreview.ClaimsReviewTab;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.externalpartytab.ExternalPartyTab;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.memberinformation.MemberInformationTab;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.overviewtab.OverviewTab;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.projections.ProjectionsTab;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.recoveriesandreceipts.RecoveriesAndReceiptsTab;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TaskTabNoteTabPanel extends AbstractWebComponent implements ITabPanel {


    @FindBy(linkText = "Current Note")
    WebElement currentNote;
    @FindBy(linkText = "Previous Notes")
    WebElement previousNote;

    public TaskTabNoteTabPanel(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public Map<Class, WebElement> getAvailableTabsAndTabsPillMap() {
        Map<Class, WebElement> availableTabsAndTabsPillXpath = new HashMap<>();
        availableTabsAndTabsPillXpath.put(CurrentNoteTab.class, currentNote);
        availableTabsAndTabsPillXpath.put(PreviousNoteTab.class, previousNote);
        return availableTabsAndTabsPillXpath;
    }


    @Override
    public <T extends ITab> ITab getDefaultTab() {
        return new CurrentNoteTab(getDriver());
    }

    @Override
    public boolean isFullyLoaded() {
        return getDefaultTab().isFullyLoaded();
    }

}
