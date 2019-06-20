package com.advalent.automation.impl.pages.search.globalsearch.newincidentreport;

import com.advalent.automation.api.annotations.inputfield.CustomElement;
import com.advalent.automation.impl.component.inputelements.TextBox;
import com.advalent.automation.impl.pages.common.AbstractWebComponent;
import org.openqa.selenium.WebDriver;

/**
 * Created By: sumit
 * Created Date :12/17/2018
 * Note:
 */
public class PolicyHolderInformationSection extends AbstractWebComponent {

    @CustomElement(xpath = "//*[@id=\"PolicyHolderFirstName\"]")
    TextBox firstName;
    @CustomElement(xpath = "//*[@id=\"PolicyHolderMiddleName\"]")
    TextBox middleName;
    @CustomElement(xpath = "//*[@id=\"PolicyHolderLastName\"]")
    TextBox lastName;
    @CustomElement(xpath = "//*[@id=\"PolicyHolderSuffix\"]")
    TextBox suffix;
    @CustomElement(xpath = "//*[@id=\"PolicyHolderID\"]")
    TextBox policyHolderID;
    @CustomElement(xpath = "//*[@id=\"PolicyHolderStreet1\"]")
    TextBox addressLine1;
    @CustomElement(xpath = "//*[@id=\"PolicyHolderStreet2\"]")
    TextBox addressLine2;

    public PolicyHolderInformationSection(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isFullyLoaded() {
        return false;
    }
}
