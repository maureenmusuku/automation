package com.advalent.automation.impl.pages.login;

import com.advalent.automation.api.annotations.inputfield.CustomElement;
import com.advalent.automation.api.dto.User;
import com.advalent.automation.api.pages.dashboard.IDashboardPage;
import com.advalent.automation.api.pages.login.ILoginPage;
import com.advalent.automation.impl.component.inputelements.TextBox;
import com.advalent.automation.impl.pages.dashboard.AdvalentDashboard;
import com.advalent.automation.impl.utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LocalLoginPage extends AbstractLoginPage implements ILoginPage {


    private LoginPageWebElements webElements;

    public LocalLoginPage(WebDriver driver) {
        super(driver);
        this.webElements = new LoginPageWebElements(driver);
    }

    @Override
    TextBox getUserNameTextBox() {
        return webElements.username;
    }

    @Override
    TextBox getPasswordTextBox() {
        return webElements.password;
    }

    @Override
    WebElement getLoginButton() {
        return webElements.submitBtn;
    }

    @Override
    public IDashboardPage loginAndGoToDashBoard(User user) {
        enterUserName(user.getUserId())
                .clickOnNextBtn()
                .enterPassword(user.getPassword()).submit();
        AdvalentDashboard advalentDashboard = PageFactory.initElements(getDriver(), AdvalentDashboard.class);
        return advalentDashboard;
    }

    @Override
    public IDashboardPage loginAndGoToDashBoard(String automationId) {
        return null;
    }

    @Override
    public String getErrorMessage() {
        return webElements.errorMessage.getText();
    }

    @Override
    public User getCurrentUser() {
        return new User(webElements.username.getValue(), webElements.password.getValue());
    }

    @Override
    public boolean isFullyLoaded() {
        return isLoginPanelDisplayed();
    }

    @Override
    public boolean isLoginPanelDisplayed() {
        return webElements.loginPanel.isDisplayed();
    }

    @Override
    public void waitTillLoginPanelIsLoaded(int waitTime) {
        WaitUtils.waitUntil(getDriver(), waitTime, input -> isLoginPanelDisplayed());
    }

    @Override
    protected WebElement getNextBtn() {
        return webElements.nextBtn;
    }

    private static class LoginPageWebElements {


        private LoginPageWebElements(WebDriver driver) {
            PageFactory.initElements(driver, this);
        }

        @CustomElement(xpath = "//*[@id=\"uname\"]")
        public TextBox username;
        @CustomElement(xpath = "//*[@id=\"loginpwd\"]")
        public TextBox password;
        @FindBy(xpath = "//*[@id=\"content\"]/div[2]/div/form/div[2]/div/div[1]")
        public WebElement loginPanel;
        @FindBy(xpath = "//*[@id=\"content\"]/div[2]/form/div/div[2]/div/div/p[1]")
        private WebElement errorMessage;
        @FindBy(xpath = "//*[@id=\"login\"]")
        private WebElement submitBtn;
        @FindBy(xpath = "//*[@id=\"next\"]")
        private WebElement nextBtn;
    }
}
