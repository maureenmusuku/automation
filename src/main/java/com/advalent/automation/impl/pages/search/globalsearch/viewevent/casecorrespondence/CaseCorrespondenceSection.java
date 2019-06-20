package com.advalent.automation.impl.pages.search.globalsearch.viewevent.casecorrespondence;

import com.advalent.automation.api.annotations.LogStep;
import com.advalent.automation.api.annotations.inputfield.CustomElement;
import com.advalent.automation.api.constants.TimeOuts;
import com.advalent.automation.impl.component.inputelements.AutoSuggest;
import com.advalent.automation.impl.component.inputelements.DropDown;
import com.advalent.automation.impl.component.inputelements.RadioButton;
import com.advalent.automation.impl.component.inputelements.TextBox;
import com.advalent.automation.impl.pages.common.AbstractWebComponent;
import com.google.common.base.Predicate;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CaseCorrespondenceSection extends AbstractWebComponent {

    @FindBy(xpath = "//*[@id=\"preview\"]")
    public WebElement previewBtn;

    @FindBy(xpath = "//*[@id=\"saveModal\"]")
    public WebElement approveBtn;

    @FindBy(xpath = "//*[@id=\"closeModal\"]")
    public WebElement rejectBtn;

    //Correspondence Specifics
    @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div/form/div[2]/div[1]/div/div/div/div[6]/div/div[2]/ng-form/div/div/div/div[1]/div[1]")
    WebElement sectionTitle;


    @CustomElement(xpath = "//*[@id=\"content\"]/div[3]/div/form/div[2]/div[1]/div/div/div/div[6]/div/div[2]/ng-form/div/div/div/div[1]/div[2]/div[1]/div[2]")
    RadioButton eventCase;


    @CustomElement(xpath = "//*[@id=\"EventCaseMasterKey\"]")
    private DropDown caseNumber;

    @CustomElement(xpath = "//*[@id=\"CommunicationDirection\"]")
    private DropDown direction;

    @CustomElement(xpath = "//*[@id=\"CommunicationType\"]")
    private DropDown communicationMethod;

    @CustomElement(xpath = "//*[@id=\"RelatedCaseCommunicationKey\"]")
    private DropDown relatedToPriorCommunication;

    @CustomElement(xpath = "//*[@id=\"LetterTemplateKey\"]")
    private DropDown correspondence;

    @CustomElement(xpath = "//*[@id=\"CommunicationSubject\"]")
    private TextBox subject;

    @CustomElement(id = "ContactDateTime")
    private TextBox date;

    @CustomElement(id = "FollowupDate")
    private TextBox taskDate;

    @CustomElement(id = "SignatureUserKey")
    private DropDown signature;

    @CustomElement(xpath = "//*[@id=\"CommunicationNote\"]")
    private TextBox notes;

    @FindBy(xpath = "//*[@id=\"multipleupload\"]")
    private WebElement chooseFilesBtn;

    @FindBy(xpath = "//*[@id=\"upload\"]")
    private WebElement uploadBtn;

    //need to implement checkbox
    /*@CustomElement(xpath =  "//*[@id=\"IncludeMPR\"]")
    private CheckBox includeCSB;*/

    //addressee section
    @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div/form/div[2]/div[1]/div/div/div/div[6]/div/div[2]/ng-form/div/div/div/div[2]/div[1]")
    WebElement addresseeTitle;

    @CustomElement(xpath = "//*[@id=\"PartyRole\"]")
    private DropDown partyRole;

    @CustomElement(xpath = "//*[@id=\"EventCasePartyKey\"]")
    private DropDown partyName;

    @CustomElement(xpath = "//*[@id=\"EventCasePartyContactKey\"]")
    private DropDown contactName;

    @CustomElement(xpath = "//*[@id=\"ContactTitle\"]")
    private TextBox title;

    @CustomElement(xpath = "//*[@id=\"ContactAddress1\"]")
    private TextBox addressLine1;

    @CustomElement(xpath = "//*[@id=\"ContactAddress2\"]")
    private TextBox addressLine2;

    @CustomElement(xpath = "//*[@id=\"ContactCity\"]")
    private AutoSuggest city;

    @CustomElement(xpath = "//*[@id=\"ContactState\"]")
    private DropDown state;

    @CustomElement(xpath = "//*[@id=\"ContactZip\"]")
    private TextBox zip;

    @CustomElement(xpath = "//*[@id=\"ContactEmail\"]")
    private TextBox email;

    @CustomElement(xpath = "//*[@id=\"ContactPhone1\"]")
    private TextBox businessPhone;

    @CustomElement(xpath = "//*[@id=\"ContactPhone1Ext\"]")
    private TextBox businessPhoneExt;

    @CustomElement(xpath = "//*[@id=\"ContactPhone2\"]")
    private TextBox cellPhone;

    @CustomElement(xpath = "//*[@id=\"ContactFax\"]")
    private TextBox fax;


    //Implementation on post page after button clicks
    /*@LogStep(step = "Clicking On Choose Files Button")
    public a clickOnChooseFilesBtn(){
        chooseFilesBtn.click();
        return new a(getDriver());
    }

    @LogStep(step = "Clicking On Upload Button")
    public a clickOnUploadBtn(){
        uploadBtn.click();
        return new a(getDriver());
    }
    */

    @LogStep(step = "Clicking On Preview Button")
    public CorrespondencePreviewModal clickOnPreviewBtn() {
        previewBtn.click();
        return new CorrespondencePreviewModal(getDriver());
    }

    @LogStep(step = "Clicking On Approve Button")
    public CaseCorrespondenceTab clickOnApproveBtn() {
        approveBtn.click();
        return new CaseCorrespondenceTab(getDriver());
    }

    @LogStep(step = "Clicking On Reject Button")
    public CaseCorrespondenceTab clickOnRejectBtn() {
        rejectBtn.click();
        return new CaseCorrespondenceTab(getDriver());
    }

    public CaseCorrespondenceSection(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public boolean isFullyLoaded() {
        return false;
    }

    public boolean isCorrespondanceSpecificsSectionDisplayed() {
        return sectionTitle.isDisplayed();
    }

    public boolean isAddresseeSectionDisplayed() {
        return addresseeTitle.isDisplayed();
    }

    @LogStep(step = "Selecting Case Or Event")
    public CaseCorrespondenceSection selectEventCase(String caseEvent) {
        this.eventCase.select(caseEvent);
        return this;
    }

    @LogStep(step = "Selecting Correspondence Direction")
    public CaseCorrespondenceSection selectDirection(String direction) {
        this.direction.selectOption(direction);
        return this;
    }

    @LogStep(step = "Selecting Communication Method")
    public CaseCorrespondenceSection selectCommunicationMethod(String communicationMethod) {
        this.communicationMethod.selectOption(communicationMethod);
        return this;
    }

    @LogStep(step = "Selecting Related To Prior Communication")
    public CaseCorrespondenceSection selectRelatedToPriorCommunication(String priorCommunication) {
        this.relatedToPriorCommunication.selectOption(priorCommunication);
        return this;
    }

    @LogStep(step = "Selecting Corrospondence")
    public CaseCorrespondenceSection selectCorrespondence(String correspondance) {
        this.correspondence.selectOption(correspondance);
        return this;
    }

    @LogStep(step = "Entering Subject")
    public CaseCorrespondenceSection enterSubject(String subject) {
        this.subject.enterValue(subject);
        return this;
    }

    @LogStep(step = "Entering Date")
    public CaseCorrespondenceSection enterDate(String date) {
        this.date.enterValue(date);
        return this;
    }

    @LogStep(step = "Entering Task Date")
    public CaseCorrespondenceSection enterTaskDate(String date) {
        this.taskDate.enterValue(date);
        return this;
    }

    @LogStep(step = "Select Signature")
    public CaseCorrespondenceSection selectSignature(String signature) {
        this.signature.selectOption(signature);
        return this;
    }

    @LogStep(step = "Entering Task Date")
    public CaseCorrespondenceSection enterNotes(String notes) {
        this.notes.enterValue(notes);
        return this;
    }

    @LogStep(step = "Select Party Role")
    public CaseCorrespondenceSection selectPartyRole(String partyRole) {
        this.partyRole.selectOption(partyRole);
        return this;
    }

    @LogStep(step = "Select Party Name")
    public CaseCorrespondenceSection selectPartyName(String partyName) {
        this.partyName.selectOption(partyName);
        return this;
    }

    @LogStep(step = "Select Contact Name")
    public CaseCorrespondenceSection selectContactName(String contactName) {
        this.contactName.selectOption(contactName);
        return this;
    }


    public RadioButton getEventCase() {
        return eventCase;
    }

    public DropDown getCaseNumber() {
        return caseNumber;
    }

    public DropDown getDirection() {
        return direction;
    }

    public DropDown getCommunicationMethod() {
        return communicationMethod;
    }

    public DropDown getRelatedToPriorCommunication() {
        return relatedToPriorCommunication;
    }

    public DropDown getCorrespondence() {
        return correspondence;
    }

    public TextBox getSubject() {
        return subject;
    }

    public TextBox getDate() {
        return date;
    }

    public TextBox getTaskDate() {
        return taskDate;
    }

    public DropDown getSignature() {
        return signature;
    }

    public TextBox getNotes() {
        return notes;
    }

    public WebElement getUploadBtn() {
        return uploadBtn;
    }

    public DropDown getPartyRole() {
        return partyRole;
    }

    public DropDown getPartyName() {
        return partyName;
    }

    public DropDown getContactName() {
        return contactName;
    }

    public TextBox getTitle() {
        return title;
    }

    public TextBox getAddressLine1() {
        return addressLine1;
    }

    public TextBox getAddressLine2() {
        return addressLine2;
    }

    public AutoSuggest getCity() {
        return city;
    }

    public DropDown getState() {
        return state;
    }

    public TextBox getZip() {
        return zip;
    }

    public TextBox getEmail() {
        return email;
    }

    public TextBox getBusinessPhone() {
        return businessPhone;
    }

    public TextBox getBusinessPhoneExt() {
        return businessPhoneExt;
    }

    public TextBox getCellPhone() {
        return cellPhone;
    }

    public TextBox getFax() {
        return fax;
    }

    public void waitTilSubjectIsUpdated() {
        TextBox subject = this.subject;
        String initialValue = subject.getValue();
        new WebDriverWait(getDriver(), TimeOuts.FIVE_SECONDS)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class)
                .ignoring(WebDriverException.class)
                .until(new Predicate<WebDriver>() {

                    @Override
                    public boolean apply(WebDriver arg0) {
                        return initialValue.equalsIgnoreCase(subject.getValue());
                    }
                });
    }


    public static enum CorrespondenceMethods {
        MAIL("Mail"), EMAIL("E-Mail"), FAX("Fax");

        private final String method;

        CorrespondenceMethods(String method) {
            this.method = method;
        }

        public String getValue() {
            return method;
        }
    }
}
