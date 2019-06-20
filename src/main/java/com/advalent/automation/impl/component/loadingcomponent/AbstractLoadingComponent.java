package com.advalent.automation.impl.component.loadingcomponent;

import com.advalent.automation.api.annotations.logging.LogMethodExecutionTime;
import com.advalent.automation.api.components.loadingcomponent.ILoadingComponent;
import com.advalent.automation.api.constants.TimeOuts;
import com.advalent.automation.impl.pages.common.AbstractWebComponent;
import com.google.common.base.Predicate;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AbstractLoadingComponent extends AbstractWebComponent implements ILoadingComponent {
    private final WebDriver driver;
    private final int timeoutInSeconds;
    String locator;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private boolean alreadyDisplayed;

    public AbstractLoadingComponent(WebDriver driver, String compXpath) {
        this(driver, compXpath, TimeOuts.TEN_SECONDS);
    }

    public AbstractLoadingComponent(WebDriver driver, String compXpath, int timeout) {
        super(driver);
        this.driver = driver;
        locator = compXpath;
        this.timeoutInSeconds = timeout;
    }

    @LogMethodExecutionTime
    private void waitTillAppears(int waitTimeInSecs) {
        logger.debug("Waiting for __{}__ component to appear ...", this.getClass().getSimpleName());

        if (isFullyLoaded()) {
            new WebDriverWait(driver, waitTimeInSecs)
                    .ignoring(NoSuchElementException.class)
                    .ignoring(NoSuchElementException.class)
                    .until(new Predicate<WebDriver>() {
                        @Override
                        public boolean apply(WebDriver arg0) {
                            return isFullyLoaded();
                        }
                    });
        }
    }

    @Override
    @LogMethodExecutionTime
    public void waitTillDisappears(int waitTimeInSecs) {
        waitTillAppears(waitTimeInSecs);
        logger.debug("Waiting for __{}__ component to disappear ...", this.getClass().getSimpleName());
        if (isFullyLoaded()) {
            new WebDriverWait(driver, waitTimeInSecs).ignoring(StaleElementReferenceException.class).until(new Predicate<WebDriver>() {
                @Override
                public boolean apply(WebDriver arg0) {
                    return !isFullyLoaded();
                }
            });
        }
    }


    @Override
    public boolean isFullyLoaded() {
        boolean displayed;
        try {
            displayed = driver.findElement(By.xpath(locator)).isDisplayed();
            alreadyDisplayed = true;
        } catch (NoSuchElementException ex) {
            alreadyDisplayed = true;
            displayed = false;
        }
        return displayed;
    }

    public AbstractWebComponent waitTillLoaded() {
//        this.waitTillAppears(TimeOuts.TEN_SECONDS);
        this.waitTillDisappears(TimeOuts.TEN_SECONDS);
        return this;
    }
}
