package com.advalent.automation.test.subropoint;

import com.advalent.automation.api.constants.TimeOuts;
import com.advalent.automation.api.dto.User;
import com.advalent.automation.api.pages.dashboard.IDashboardPage;
import com.advalent.automation.api.pages.login.ILoginPage;
import com.advalent.automation.test.base.BaseTest;
import org.fest.assertions.Assertions;
import org.testng.annotations.*;


/**
 * @author sshrestha
 */

@Test(groups = "Web Security", description = "Auth0 Login Page Test")
public class LoginPageTest extends BaseTest {
    public static final String INVALID_USER_ID = "invalidUserId";
    public static final String INVALID_PASSWORD = "invalidPassword";
    private static final String ERROR_MESSAGE = "Invalid User ID or Password";
    private ILoginPage loginPage;


    public boolean skipBrowserCreation() {
        return true;
    }


    @BeforeClass
    public void setUp() {
        super.setUp();
        createNewBrowserInstance();

    }

    @BeforeMethod
    public void launchApp() {
        loginPage = app.open(context.getApplication());
        loginPage.doWaitTillFullyLoaded(context.getDefaultWaitTimeout());
    }

    @Test(description = "Test that user cannot login to application with incorrect credentials", priority = 1)
    public void test_login_failure() {
        loginPage.loginWithWrongCredentials(new User(INVALID_USER_ID, INVALID_PASSWORD));
        loginPage.waitTillLoginPanelIsLoaded(TimeOuts.SIXTY_SECONDS);
        String message = loginPage.getErrorMessage();
        Assertions.assertThat(message).isEqualTo(ERROR_MESSAGE).as("Error Message Should Be Displayed");
    }

    @Test(description = "Test that user can login to application successfully with correct credentials", priority = 2)
    public void test_login_success() {
        IDashboardPage dashboardPage = loginPage.loginAndGoToDashBoard(getUser());
        dashboardPage.doWaitTillFullyLoaded(TimeOuts.SIXTY_SECONDS);
        Assertions.assertThat(dashboardPage.isFullyLoaded()).isEqualTo(true).as("Dashboard Page Should Be Displayed After Successful Login");
    }


}
