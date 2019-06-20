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
public class EventInformationSection extends AbstractWebComponent {

    @CustomElement(xpath = "//*[@id=\"IncidentReceiveDate\"]")
    private TextBox incidentReceivedDate;

    @CustomElement(xpath = "//*[@id=\"InvestigationSource\"]")
    private DropDown investigationSource;


    @CustomElement(xpath = "//*[@id=\"EventType\"]")
    private DropDown eventType;

    @CustomElement(xpath = "//*[@id=\"LossStateCode\"]")
    private DropDown lossStateCode;

    @CustomElement(xpath = "//*[@id=\"LossDate\"]")
    private TextBox lossDate;

    @CustomElement(xpath = "//*[@id=\"IncidentName\"]")
    private TextBox referrerName;

    @CustomElement(xpath = "//*[@id=\"LossDetails\"]")
    private TextBox lossDetails;
    @CustomElement(xpath = "//*[@id=\"InjuryDescription\"]")
    private TextBox injuryDescription;

    public EventInformationSection(WebDriver driver) {
        super(driver);
    }


    @LogStep(step = "Entering Received Date")
    public EventInformationSection enterReceivedDate(String receivedDate) {
        this.incidentReceivedDate.enterValue(receivedDate);
        return this;
    }

    @LogStep(step = "Selecting Investigation Source")
    public EventInformationSection enterInvestigationSource(String investigationSource) {
        this.investigationSource.selectOption(investigationSource);
        return this;
    }

    @LogStep(step = "Selecting Event Type ")
    public EventInformationSection enterEventType(String eventType) {
        this.eventType.selectOption(eventType);
        return this;
    }

    @LogStep(step = "Entering Date of Loss ")
    public EventInformationSection enterDateOfLoss(String dateOfLoss) {
        this.lossDate.enterValue(dateOfLoss);
        return this;
    }

    @LogStep(step = "Entering Referrer Name")
    public EventInformationSection enterReferrerName(String dateOfLoss) {
        this.referrerName.enterValue(dateOfLoss);
        return this;
    }

    @LogStep(step = "Selecting State of Loss ")
    public EventInformationSection enterStateOfLoss(String lossState) {
        this.lossStateCode.selectOption(lossState);
        return this;
    }

    @LogStep(step = "Entering Loss Details ")
    public EventInformationSection enterLossDetails(String lossDetails) {
        this.lossDetails.enterValue(lossDetails);
        return this;
    }

    @LogStep(step = "Entering Injury Description")
    public EventInformationSection enterInjuryDescription(String injuryDescription) {
        this.injuryDescription.enterValue(injuryDescription );
        return this;
    }

    @Override
    public boolean isFullyLoaded() {
        return false;
    }
}
