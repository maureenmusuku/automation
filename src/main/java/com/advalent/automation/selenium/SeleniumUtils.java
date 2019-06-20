package com.advalent.automation.selenium;

import com.advalent.automation.api.config.IDriverConfiguration;
import com.advalent.automation.impl.utils.WaitUtils;
import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.fest.assertions.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

/**
 * @author sshrestha
 */
public class SeleniumUtils {

    public static boolean ifElementFocused(WebDriver driver, WebElement element) {
        Preconditions.checkArgument(driver != null);
        Preconditions.checkArgument(element != null);

        return element.equals(driver.switchTo().activeElement());
    }

    /**
     * Check if the element exist in the current dom.
     * Note that this version is not effected by the implicit wait time
     * ie it does not wait for the element to be present and returns immediate even if the element is not present
     *
     * @param context The element relative to which the xpath is evaluated.
     * @param xpath   The xpath to evaluate.
     *                When using xpath be aware that webdriver follows standard conventions: a search prefixed with "//" will search the
     *                entire document, not just the children buildDataGridAs this current node. Use ".//" to limit your search to
     *                the children buildDataGridAs this WebElement.
     * @return
     */
    public static boolean elementExists(WebElement context, String xpath) {
        List<WebElement> elements = context.findElements(By.xpath(xpath));
        return elements.size() > 0;
    }

    public static void hoverOnElement(WebElement element, WebDriver driver) {
        if (isIE(driver)) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].onmouseover()", element);
        } else
            new Actions(driver).moveToElement(element).build().perform();

    }

    public static void click(WebElement element, WebDriver driver) {
        if (isIE(driver)) {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click()", element);
        } else
            try {
                element.click();
            } catch (WebDriverException ex) {
                JavascriptExecutor executor = (JavascriptExecutor) driver;
                executor.executeScript("arguments[0].click()", element);
            }
    }

    public static void scrollPage(WebDriver driver, int scrollOffset) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("window.scrollBy(0," + scrollOffset + ")");
    }

    public static void closeCurrentWindowAndSwitchToNewWindow(WebDriver webDriver) {
        String newWindowHandle = getNextWindowHandle(webDriver);
        webDriver.close();
        webDriver.switchTo().window(newWindowHandle);
    }

    //switch to new window when there are two windows
    public static void switchToNewWindow(WebDriver webDriver) {
        webDriver.switchTo().window(getNextWindowHandle(webDriver));
    }

    public static String getNextWindowHandle(WebDriver webDriver) {
        Preconditions.checkState(webDriver.getWindowHandles().size() == 2);
        String currentWindowHandle = webDriver.getWindowHandle();
        String newWindowHandle = null;
        for (String handle : webDriver.getWindowHandles()) {
            if (!handle.equals(currentWindowHandle)) {
                newWindowHandle = handle;
                break;
            }
        }
        Assertions.assertThat(newWindowHandle != null && !newWindowHandle.equals(currentWindowHandle));
        return newWindowHandle;
    }

    public static void waitUntilMoreThanOneWindowsIsOpen(WebDriver webDriver) {
        int attempts = 0;
        while (attempts < 5 && getNumberOfOpenWindows(webDriver) < 2) {
            WaitUtils.waitForSeconds(5);
            attempts++;
        }
    }

    public static int getNumberOfOpenWindows(WebDriver webDriver) {
        int numberOfWindows = 0;
        for (String s : webDriver.getWindowHandles()) {
            if (StringUtils.isNotBlank(s)) {
                numberOfWindows++;
            }
        }
        return numberOfWindows;
    }

    public static boolean isAlertPresent(WebDriver driver) {
        try {
            driver.switchTo().alert();
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public static String getAlertTextAndDismissAlert(WebDriver driver) {
        String alertText = null;
        try {
            alertText = driver.switchTo().alert().getText();
            driver.switchTo().alert().dismiss();
        } catch (Exception e) {
            //dismiss
        }
        return alertText;
    }

    public static void clickOkOnAlert(WebDriver driver) {
        try {
            driver.switchTo().alert().accept();

        } catch (Exception e) {
            //dismiss
        }
    }

    public static boolean isIE(WebDriver driver) {
        return getBrowser(driver) == IDriverConfiguration.Browser.IE;
    }

    public static IDriverConfiguration.Browser getBrowser(WebDriver driver) {
        Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();

        switch (caps.getBrowserName()) {
            case "internet explorer":
                return IDriverConfiguration.Browser.IE;
            case "chrome":
                return IDriverConfiguration.Browser.CHROME;
            case "firefox":
                return IDriverConfiguration.Browser.FIREFOX;
            default:
                throw new RuntimeException("Unknown Browser " + caps.getBrowserName());
        }

    }

    public static void refreshPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    public static void sendKeysToInput(String text, WebElement element) {
        element.sendKeys(text);
    }

    public static void click(WebElement element) {
        element.click();
    }

    public static void hover(WebDriver driver, WebElement element) {
        new Actions(driver).moveToElement(element).build().perform();
    }

    public static void selectByValue(WebElement selectElement, String value) {
        new Select(selectElement).selectByValue(value);
    }

    public static String getCurrentValueFromSelectElement(WebElement selectElement) {
        return new Select(selectElement).getFirstSelectedOption().getText();
    }

    public static WebElement findElementByXPath(WebDriver driver, String xpath) {
        return driver.findElement(By.xpath(xpath));
    }

    public static WebElement findElementById(WebDriver driver, String id) {
        return driver.findElement(By.id(id));
    }

    public static List<WebElement> findElementsByXPath(WebDriver driver, String xpath) {
        return driver.findElements(By.xpath(xpath));
    }

    public static boolean isElementVisible(WebDriver driver, String s) {
        try {
            return driver.findElement(By.xpath(s)).isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public static void scrollPageToElement(WebDriver driver, WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        String offset = element.getAttribute("offsety");
        executor.executeScript("window.scrollBy(0," + offset + ")");
    }
}
