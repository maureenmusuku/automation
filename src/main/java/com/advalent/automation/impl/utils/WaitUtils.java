package com.advalent.automation.impl.utils;

import com.advalent.automation.api.constants.TimeOuts;
import com.google.common.base.Predicate;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * @author sshrestha
 */
public class WaitUtils {

    public static void waitForSeconds(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException ignored) {
        }

    }

    public static void waitForMilliSeconds(long millis) {
        try {
            TimeUnit.MILLISECONDS.sleep(millis);
        } catch (InterruptedException ignored) {
        }
    }

    public static void waitUntil(WebDriver webDriver, Integer waitTimeSeconds, Predicate predicate) {
        new WebDriverWait(webDriver, waitTimeSeconds)
                .ignoring(ElementNotVisibleException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(WebDriverException.class)
                .until(predicate);
    }

    public static void waitUntilEnabled(WebDriver driver, final WebElement element) {
        WaitUtils.waitUntil(driver, TimeOuts.FIVE_SECONDS, new Predicate<WebDriver>() {
            @Override
            public boolean apply(WebDriver input) {
                return element.isEnabled();
            }
        });
    }

    public static void waitUntilDisplayed(WebDriver driver, final WebElement element, int timeOut) {
        WaitUtils.waitUntil(driver, timeOut, (Predicate<WebDriver>) input -> element.isDisplayed());
    }

    public static void waitUntilDisappear(WebDriver driver, final WebElement element, int timeOut) {
        WaitUtils.waitUntil(driver, timeOut, new Predicate<WebDriver>() {
            @Override
            public boolean apply(WebDriver input) {
                return !element.isDisplayed();
            }
        });
    }

}
