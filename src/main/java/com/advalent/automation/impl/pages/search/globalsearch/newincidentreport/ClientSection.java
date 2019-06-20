package com.advalent.automation.impl.pages.search.globalsearch.newincidentreport;

import com.advalent.automation.api.annotations.LogStep;
import com.advalent.automation.api.annotations.inputfield.CustomElement;
import com.advalent.automation.impl.component.inputelements.AutoSuggest;
import com.advalent.automation.impl.component.inputelements.DropDown;
import com.advalent.automation.impl.component.inputelements.TextBox;
import com.advalent.automation.impl.pages.common.AbstractWebComponent;
import org.openqa.selenium.WebDriver;

/**
 * Created By: sumit
 * Created Date :12/17/2018
 * Note:
 */
public class ClientSection extends AbstractWebComponent {


    @CustomElement(xpath = "//*[@id=\"ClientName\"]")
    private AutoSuggest majorClient;


    @CustomElement(xpath = "//*[@id=\"PayerKey\"]")
    private DropDown client;

    @CustomElement(xpath = "//*[@id=\"EmployerGroupName\"]")
    private AutoSuggest employerGroup;

    @CustomElement(xpath = "//*[@id=\"LineofBusinessName\"]")
    private AutoSuggest lob;

    public ClientSection(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isFullyLoaded() {
        return false;
    }


    @LogStep(step = "Selecting Major Client ")
    public ClientSection selectMajorClient(String majorClient) {
        this.majorClient.enterValue(majorClient);
        return this;
    }

    @LogStep(step = "Selecting  Client ")
    public ClientSection selectClient(String client) {
        this.client.enterValue(client);
        return this;
    }

    @LogStep(step = "Selecting  Employer Group ")
    public ClientSection selectEmployerGroup(String employerGroup) {
        this.employerGroup.enterValue(employerGroup);
        return this;
    }

    @LogStep(step = "Selecting  LOB ")
    public ClientSection selectLob(String lob) {
        this.lob.enterValue(lob);
        return this;
    }
}
