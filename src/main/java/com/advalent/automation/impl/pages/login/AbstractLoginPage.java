package com.advalent.automation.impl.pages.login;

import com.advalent.automation.api.annotations.LogStep;
import com.advalent.automation.api.annotations.documentation.ThreadSafe;
import com.advalent.automation.api.constants.TimeOuts;
import com.advalent.automation.api.dto.User;
import com.advalent.automation.api.pages.login.ILoginPage;
import com.advalent.automation.impl.component.inputelements.TextBox;
import com.advalent.automation.impl.pages.common.AbstractWebComponent;
import com.advalent.automation.impl.utils.WaitUtils;
import com.advalent.automation.selenium.SeleniumUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Strings.isNullOrEmpty;

@ThreadSafe
public abstract class AbstractLoginPage extends AbstractWebComponent implements ILoginPage {

    TextBox userName;
    TextBox passwordTextBox;

    public AbstractLoginPage(WebDriver driver) {
        super(driver);

    }

    abstract TextBox getUserNameTextBox();

    abstract TextBox getPasswordTextBox();

    abstract WebElement getLoginButton();


    @LogStep(step = "Enter username")
    protected AbstractLoginPage enterUserName(String userId) {
        userName = getUserNameTextBox();
        checkArgument(!isNullOrEmpty(userId));
        userName.clearValue();
        userName.enterValue(userId);
        return this;
    }

    @LogStep(step = "Enter Password")
    protected AbstractLoginPage enterPassword(String password) {
        passwordTextBox = getPasswordTextBox();
        checkArgument(!isNullOrEmpty(password));
        passwordTextBox.clearValue();
        passwordTextBox.enterValue(password);
        return this;
    }

    /**
     * Click Submit button.
     */
    @LogStep(step = "Click On Login Button")
    void submit() {
        SeleniumUtils.click(getLoginButton(), getDriver());
    }


    public AbstractLoginPage clickOnNextBtn() {
        if (this instanceof LocalLoginPage) {
            this.getNextBtn().click();
            WaitUtils.waitForSeconds(TimeOuts.FIVE_SECONDS);
        }
        return this;
    }

    protected abstract WebElement getNextBtn();

    @Override
    public ILoginPage loginWithWrongCredentials(User user) {
        enterUserName(user.getUserId()).enterPassword(user.getPassword()).submit();
        return this;
    }

}
