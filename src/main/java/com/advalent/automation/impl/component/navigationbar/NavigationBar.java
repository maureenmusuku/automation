package com.advalent.automation.impl.component.navigationbar;

import com.advalent.automation.api.annotations.logging.LogMethodExecutionTime;
import com.advalent.automation.api.config.ExecutionContext;
import com.advalent.automation.api.constants.TimeOuts;
import com.advalent.automation.api.features.IAmLandingPage;
import com.advalent.automation.api.pages.login.ILoginPage;
import com.advalent.automation.impl.component.loadingcomponent.NavBarLoadingComponent;
import com.advalent.automation.impl.component.webelement.WebElements;
import com.advalent.automation.impl.pages.login.Auth0LoginPage;
import com.advalent.automation.impl.utils.WaitUtils;
import com.advalent.automation.groovy.module.Module;
import com.advalent.automation.groovy.module.ModuleInfo;
import com.advalent.automation.impl.pages.common.AbstractWebComponent;
import com.advalent.automation.reporting.ExtentHTMLReportManager;
import com.advalent.automation.selenium.DriverFactory;
import com.advalent.automation.selenium.SeleniumUtils;
import com.google.common.base.Predicate;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.concurrent.atomic.AtomicInteger;

import static com.google.common.base.Preconditions.checkState;


public class NavigationBar extends AbstractWebComponent {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    NavBarWebElements webElements;
    NavBarLoadingComponent loadingComponent;
    private AtomicInteger retry = new AtomicInteger();


    public NavigationBar(WebDriver driver) {
        super(driver);
        webElements = new NavBarWebElements(driver);
        loadingComponent = new NavBarLoadingComponent(getDriver());

    }

    public <T> T navigateTo(Class<T> landPageClass, Integer waitTimeInSeconds) {
        T pageInstance = PageFactory.initElements(getDriver(), landPageClass);
        checkState(pageInstance instanceof IAmLandingPage, landPageClass.getSimpleName() + " is not a landing page. Make sure that it implements IAmLandingPage interface.");
        IAmLandingPage landPageInstance = (IAmLandingPage) pageInstance;
        if (isCurrentPage(landPageInstance)) {
            return pageInstance;
        }

        clickOnPageLinkAndWaitTillFullyLoaded(landPageInstance, waitTimeInSeconds);
        return pageInstance;

    }

    public static <T> T navigateTo(WebDriver driver, Class<T> landPageClass, Integer waitTimeInSeconds) {
        T pageInstance = PageFactory.initElements(driver, landPageClass);
        checkState(pageInstance instanceof IAmLandingPage, landPageClass.getSimpleName() + " is not a landing page. Make sure that it implements IAmLandingPage interface.");
        IAmLandingPage landPageInstance = (IAmLandingPage) pageInstance;
        if (new NavigationBar(driver).isCurrentPage(landPageInstance))
            return pageInstance;
        new NavigationBar(driver).clickOnPageLinkAndWaitTillFullyLoaded(landPageInstance, waitTimeInSeconds);
        return pageInstance;
    }

    private void clickOnPageLinkAndWaitTillFullyLoaded(IAmLandingPage landPageInstance, Integer waitTime) {
        Module module = ModuleInfo.INSTANCE.getModuleByPageName(landPageInstance.
                getPageName());
        if (module.hasParent())
            expandNavMenu(module.getParent());
        String moduleId = module.getName();
        clickAndWaitTillFullyLoaded(landPageInstance, moduleId, waitTime);
        logger.info("Navigated to Page {}", landPageInstance.getPageTitle());
    }


    private void expandNavMenu(Module section) {
        if (section.hasParent()) {
            expandNavMenu(section.getParent());
        }
        expandSection(section);
    }

    @LogMethodExecutionTime
    private void clickAndWaitTillFullyLoaded(IAmLandingPage landingPage, String moduleId, Integer waitTime) {
        WebElement moduleElement = getDriver().findElement(By.xpath("//*[@id='" + moduleId + "']/a"));
//        moduleElement.click();
        SeleniumUtils.click(moduleElement, getDriver());
        SeleniumUtils.click(getDriver().findElement(By.tagName("body")), getDriver());
        try {
            landingPage.doWaitTillFullyLoaded(waitTime);
        } catch (TimeoutException ex) {
            if (!landingPage.getBreadCrumb().isFullyLoaded() && retry.get() < 1) {
                ExtentHTMLReportManager.getInstance().addStep("Refreshing Page ......");
                clickAndWaitTillFullyLoaded(landingPage, moduleId, waitTime);
                retry.incrementAndGet();
            }
        }
    }

    public static void waitTillPageIsLoaded(WebDriver driver) {
        new NavBarLoadingComponent(driver).waitTillLoaded();
    }

    private void expandSection(Module section) {
        WaitUtils.waitUntil(driver, TimeOuts.SIXTY_SECONDS, o -> getDriver().findElement(By.id(section.getLinkId())).isDisplayed());
        WebElement sectionElement = getDriver().findElement(By.id(section.getLinkId()));
        boolean isDisplayed = sectionElement.findElement(By.tagName("ul")).isDisplayed();
        if (!isDisplayed) {
            sectionElement.click();
            WaitUtils.waitForSeconds(1);
        }
    }

    private boolean isCurrentPage(IAmLandingPage page) {
        final String APP_URL = ExecutionContext.INSTANCE.getApplication().getUrl();
        return (APP_URL + page.getPageUrl()).equalsIgnoreCase(getDriver().getCurrentUrl());
    }

    @Override
    public boolean isFullyLoaded() {
        return driver.findElement(By.xpath("//*[@id=\"ng-app\"]")).isDisplayed();
    }

    public String getCurrentUserName() {
        this.webElements.userIcon.click();
        String text = "";
        if (userInfoDropDownExpanded()) {
            text = webElements.userName.getText().split(" ")[0];
        } else {
            getCurrentUserName();
        }
        getDriver().findElement(By.xpath("//body")).click();

        System.out.println("text = " + text);
        return text;
    }

    private boolean userInfoDropDownExpanded() {
        WebElement dropDown = getDriver().findElement(By.xpath("//*[@id=\"sidebar\"]/div/div/div/div[3]/div/div[1]"));

        return dropDown.getAttribute("class").contains("open");
    }

    public ILoginPage logOut() {
        webElements.logOut.click();
        if (ExecutionContext.INSTANCE.getApplication().getAuthConfig() == "Auth0") {
            return new Auth0LoginPage(getDriver());
        } else {
            throw new RuntimeException("Login Page For Auth0 ");
        }
    }

    static class NavBarWebElements extends WebElements {


        protected NavBarWebElements(WebDriver driver) {
            super(driver);
            PageFactory.initElements(driver, this);
        }

        @FindBy(xpath = "//*[@id=\"sidebar\"]/div/div/div/div[3]/div/div[3]/a/i")
        WebElement globalSearchIcon;
        @FindBy(xpath = "//*[@id=\"sidebar\"]/div/div/div/div[3]/div/div[2]/a/i")
        WebElement homeIcon;
        @FindBy(xpath = "//*[@id=\"sidebar\"]/div/div/div/div[3]/div/div[1]/ul/li[1]")
        WebElement userName;
        @FindBy(xpath = "//*[@id=\"sidebar\"]/div/div/div/div[3]/div/div[1]/ul/li[1]")
        WebElement myProfile;
        @FindBy(xpath = "//*[@id=\"sidebar\"]/div/div/div/div[3]/div/div[1]/ul/li[3]")
        WebElement logOut;
        @FindBy(xpath = "//*[@id=\"sidebar\"]/div/div/div/div[3]/div/div[1]/a/i")
        WebElement userIcon;

    }
}
