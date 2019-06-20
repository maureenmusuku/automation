package com.advalent.automation.test.subropoint.search.globalsearch.incidentreport;

import com.advalent.automation.api.constants.TimeOuts;
import com.advalent.automation.impl.pages.search.globalsearch.EventIncidentSearchTab;
import com.advalent.automation.impl.pages.search.globalsearch.GlobalSearchPage;
import com.advalent.automation.impl.pages.search.globalsearch.newincidentreport.AddIncidentReportPage;
import com.advalent.automation.test.base.BaseTest;
import com.advalent.automation.test.utils.testdata.DataFile;
import com.advalent.automation.test.utils.testdata.TestDataReader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created By: sumit
 * Created Date :12/12/2018
 * Note:
 */
public class AddIncidentReportPageTest extends BaseTest {
    private GlobalSearchPage globalSearchPage;
    @BeforeClass
    public void navigateToGlobalSearch() {
        super.setUp();
        this.inputMap = TestDataReader.readSection(DataFile.GLOBAL_SEARCH, "New Incident");
        GlobalSearchPage globalSearchPage = this.navigationBar.navigateTo(GlobalSearchPage.class, TimeOuts.THIRTY_SECONDS);

    }

    @Test(description = "Test Create New Incident Report Functionality")
    public void createNewIncidentReport() {
        AddIncidentReportPage addIncidentReportPage = globalSearchPage.clickOnAddNewIncidentReportButton();
        addIncidentReportPage.doWaitTillFullyLoaded(TimeOuts.SIXTY_SECONDS);
        addIncidentReportPage.getClientSection().selectMajorClient((String) this.inputMap.get("majorClient"));
        addIncidentReportPage.getEventInformationSection().enterInvestigationSource((String) this.inputMap.get("investigationSource"))
        .enterEventType((String) this.inputMap.get("eventType"))
        .enterDateOfLoss((String) this.inputMap.get("lossDate"))
        ;
    }
}
