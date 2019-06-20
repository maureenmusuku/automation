package com.advalent.automation.impl.pages.common;

import com.advalent.automation.api.annotations.LogStep;
import com.advalent.automation.api.config.ExecutionContext;
import com.advalent.automation.api.constants.TimeOuts;
import com.advalent.automation.api.dto.Application;
import com.advalent.automation.api.exceptions.AutomationException;
import com.advalent.automation.api.pages.login.ILoginPage;
import com.advalent.automation.impl.pages.login.Auth0LoginPage;
import com.advalent.automation.impl.pages.login.LocalLoginPage;
import com.advalent.automation.impl.pages.login.MFALoginPage;
import com.advalent.automation.impl.utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Strings.isNullOrEmpty;

/**
 * @author sshrestha
 */
public class AdvalentApp extends AbstractWebComponent {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public final WebDriver driver;

    public AdvalentApp(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }


    @LogStep(step = " Application Launched")
    public ILoginPage open(Application application) {
        String url = application.getUrl();
        checkArgument(!isNullOrEmpty(url));
        getDriver().get(url);
        ILoginPage loginPage = getLoginPage();
        logger.info("Opening {} For {}", url, application.getProduct());
        loginPage.doWaitTillFullyLoaded(TimeOuts.SIXTY_SECONDS);
        return loginPage;
    }

    private ILoginPage getLoginPage() {
        String authConfig = ExecutionContext.INSTANCE.getApplication().getAuthConfig();
        if (authConfig == "Auth0") {
            return new Auth0LoginPage(getDriver());
        } else if (authConfig == "MFA") {
            return new MFALoginPage(getDriver());
        } else if (authConfig == "Local") {
            return new LocalLoginPage(getDriver());
        } else {
            throw new AutomationException("Config '" + authConfig + "' not recognized");
        }

    }

    @Override
    public boolean isFullyLoaded() {
        return true;
    }

    public void quit() {
        getDriver().quit();
    }
}
