package com.advalent.automation.impl.pages.search.globalsearch.viewevent.memberinformation.policyholdeinfo;

import com.advalent.automation.api.annotations.inputfield.CustomElement;
import com.advalent.automation.impl.component.inputelements.DropDown;
import com.advalent.automation.impl.component.inputelements.TextBox;
import com.advalent.automation.impl.pages.common.AbstractWebComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class DemographicSection extends AbstractWebComponent {
    //Demographic Details
    @CustomElement(xpath = "//*[@id=\"PolicyHolderFirstName\"]")
    public TextBox policyHolderFirstName;

    @CustomElement(xpath = "//*[@id=\"PolicyHolderMiddleName\"]")
    public TextBox policyHolderMiddleName;

    @CustomElement(xpath = "//*[@id=\"PolicyHolderLastName\"]")
    public TextBox policyHolderLastName;

    @CustomElement(xpath = "//*[@id=\"PolicyHolderSuffix\"]")
    public TextBox policyHolderSuffix;

    @CustomElement(xpath = "//*[@id=\"PolicyHolderID\"]")
    public TextBox policyHolderID;


    @CustomElement(xpath = "//*[@id=\"MajorClientName\"]")
    public TextBox majorClientName;

    @CustomElement(xpath = "//*[@id=\"PolicyHolderSSN\"]")
    public TextBox policyHolderSSN;

    @CustomElement(xpath = "//*[@id=\"ClientName\"]")
    public TextBox clientName;

    @CustomElement(xpath = "//*[@id=\"PolicyHolderBirthDate\"]")
    public TextBox policyHolderdateOfBirth;
    @CustomElement(xpath = "//*[@id=\"PolicyHolderAge\"]")
    public TextBox policyHolderAge;

    @CustomElement(xpath = "//*[@id=\"EmployerGroupName\"]")
    public TextBox employerGroupName;

    @CustomElement(xpath = "//*[@id=\"PolicyHolderGender\"]")
    public DropDown policyHolderGender;

    @CustomElement(xpath = "//*[@id=\"LOB\"]")
    public TextBox lob;


    public DemographicSection(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public boolean isFullyLoaded() {
        return getDriver().findElement(By.xpath("//*[@id=\"patientInfo\"]/div/form/div/div/div[1]/div[1]")).isDisplayed();
    }

}
