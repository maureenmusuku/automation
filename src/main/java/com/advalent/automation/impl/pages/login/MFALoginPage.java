package com.advalent.automation.impl.pages.login;

import com.advalent.automation.api.annotations.inputfield.CustomElement;
import com.advalent.automation.api.dto.User;
import com.advalent.automation.api.exceptions.AutomationException;
import com.advalent.automation.api.pages.dashboard.IDashboardPage;
import com.advalent.automation.api.pages.login.ILoginPage;
import com.advalent.automation.impl.component.inputelements.TextBox;
import com.advalent.automation.impl.pages.common.AbstractWebComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MFALoginPage extends AbstractLoginPage implements ILoginPage {

    UsernamePanel usernamePanel;

    public MFALoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        usernamePanel = new UsernamePanel(driver);
    }

    @Override
    TextBox getUserNameTextBox() {
        return usernamePanel.usernameTextBox;
    }

    @Override
    TextBox getPasswordTextBox() {
        return null;
    }

    @Override
    WebElement getLoginButton() {
        return usernamePanel.loginButton;
    }

    @Override
    protected WebElement getNextBtn() {
        return null;
    }

    @Override
    public IDashboardPage loginAndGoToDashBoard(User user) {
        return null;
    }

    @Override
    public IDashboardPage loginAndGoToDashBoard(String automationId) {
        return null;
    }

    @Override
    public String getErrorMessage() {
        return null;
    }

    @Override
    public User getCurrentUser() {
        return null;
    }

    @Override
    public boolean isLoginPanelDisplayed() {
        return false;
    }

    @Override
    public void waitTillLoginPanelIsLoaded(int waitTime) {
        throw new AutomationException("MFALoginPage.waitTillLoginPanelIsLoaded method not implemented");
    }

    @Override
    public boolean isFullyLoaded() {
        return usernamePanel.isFullyLoaded();
    }


    @Override
    public WebDriver getDriver() {
        return null;
    }

    static class UsernamePanel extends AbstractWebComponent {

        @CustomElement(xpath = "//*[@id=\"content\"]/div[2]/form/div/div[2]/div/div[1]/input")
        TextBox usernameTextBox;
        @FindBy(xpath = "//*[@id=\"content\"]/div[2]/form/div/div[2]/div/div[1]/button")
        WebElement loginButton;
        @FindBy(xpath = "//*[@id=\"content\"]/div[2]/form/div/div[2]/div")
        WebElement panelTitle;

        public UsernamePanel(WebDriver driver) {
            super(driver);
            PageFactory.initElements(driver, this);
        }

        @Override
        public boolean isFullyLoaded() {
            return panelTitle.isDisplayed();
        }


    }
}
