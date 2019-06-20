package com.advalent.automation.test.base;

import com.advalent.automation.api.annotations.LogStep;
import com.advalent.automation.api.annotations.logging.LogMethodEnter;
import com.advalent.automation.api.config.ExecutionContext;
import com.advalent.automation.api.constants.TimeOuts;
import com.advalent.automation.api.dto.User;
import com.advalent.automation.api.pages.dashboard.IDashboardPage;
import com.advalent.automation.impl.component.navigationbar.NavigationBar;
import com.advalent.automation.impl.pages.common.AdvalentApp;
import com.advalent.automation.reporting.ExtentHTMLReportManager;
import com.advalent.automation.selenium.DriverFactory;
import com.google.common.base.Preconditions;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

import java.util.Map;


/**
 * The Base Class for all UI Tests
 * Handles configuration management,login etc
 *
 * @author sshrestha
 */
public abstract class BaseTest {
    protected static final Logger logger = LoggerFactory.getLogger(BaseTest.class);
    protected final ExecutionContext context;
    protected final Integer defaultWaitTime;
    protected final DriverFactory driverFactory;
    protected WebDriver webDriver;
    protected AdvalentApp app;
    protected NavigationBar navigationBar;
    protected Map<String, Object> inputMap = null;
    protected ExtentHTMLReportManager reportManager;
    protected Map<String, Map<String, Object>> pageInputMap;


    public BaseTest() {
//        singleton objects
        context = ExecutionContext.forEnvironment(System.getProperty("environment"));
        defaultWaitTime = context.getDefaultWaitTimeout();
        driverFactory = new DriverFactory(context.getDriverConfiguration());
        reportManager = ExtentHTMLReportManager.getInstance();

    }

    @BeforeSuite
    public void beforeSuite() {

    }

    @BeforeClass
    public void beforeClass() {
    }

    @BeforeMethod
    public void beforeTest() {

    }

    @LogMethodEnter
    public void setUp() {
        if (!skipBrowserCreation()) {
            createNewBrowserInstance();
            if (!skipLogin()) {
                launchAppAndLogin(getUser(), context.getDefaultWaitTimeout());
            }
        }
    }

    @LogStep(step = "Creating New Browser Instance")
    protected final void createNewBrowserInstance() {
        webDriver = driverFactory.createDriver();
        app = new AdvalentApp(webDriver);
    }


    protected final void launchAppAndLogin(User user, Integer waitTime) {
        Preconditions.checkState(webDriver != null);
        Preconditions.checkState(user != null);
        IDashboardPage dashboardPage = app.open(context.getApplication()).loginAndGoToDashBoard(user);
        dashboardPage.doWaitTillFullyLoaded(TimeOuts.TWO_MINUTES);
        navigationBar = dashboardPage.getNavigationBar();
        navigationBar.doWaitTillFullyLoaded(TimeOuts.SIXTY_SECONDS);
    }

    @AfterMethod(alwaysRun = true)
    public final void afterMethod() {
//        closeBrowserInstance();
    }

    @AfterClass(alwaysRun = true)
    public final void tearDownTestClass() {
        closeBrowserInstance();
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {

    }

    @LogStep(step = "Browser Instance Closed")
    protected final void closeBrowserInstance() {
        if (webDriver != null) {
            webDriver.quit();
            webDriver = null;
        }
    }

    /**
     * Override this method to return true if you want to avoid
     * creating web-driver instance automatically by BaseTest
     * Used by Tests that creates new browser instance for each
     * test
     */
    public boolean skipBrowserCreation() {
        return false;
    }


    /**
     * Override this method to return true if you want
     * the web-driver instance to be created but do not
     * want to automatically login to MI
     *
     * @return true if you want to skip login,false otherwise
     */
    protected boolean skipLogin() {
        return false;
    }


    protected final User getUser() {
        return UserManager.INSTANCE.getUser();
    }

    protected final User getUser(String automationId) {
        return new User("planadmin", "AdvDemo");
    }

    /**
     * Tests do not need to (and cannot) release Users.
     * Releasing users are done through  {@link UserManagementAspect}
     */
    /*@PackagePrivate
    final void releaseUser(User user) {
        UserManager.INSTANCE.releaseUser(user);
    }
*/
    public final WebDriver getWebDriver() {
        return this.webDriver;
    }
}
