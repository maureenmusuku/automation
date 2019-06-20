package com.advalent.automation.impl.component.notificationpanel;

import com.advalent.automation.api.constants.TimeOuts;
import com.advalent.automation.impl.pages.common.AbstractWebComponent;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.overviewtab.EventOwnershipModal;
import com.google.common.base.Predicate;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NotificationPanel extends AbstractWebComponent {

    public static final String UPDATE_SUCCESS_MSG = "Record was updated successfully";
    public static final String CREATE_SUCCESS_MESSAGE = "Record was successfully created";
    public static final String NOTIFICATION_MESSAGE_DOM_ID = "notificationMessage";
    private long waitTime = TimeOuts.SIXTY_SECONDS;
    private String displayedMessage;

    public NotificationPanel(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isFullyLoaded() {
        return false;
    }

    /**
     * method to wait until the notification panal is displayed
     * or wait for five seconds which ever comes first,
     * ignoring  {@link StaleElementReferenceException},
     * {@link NoSuchElementException} and {@link WebDriverException}.
     *
     * @return current instance buildDataGridAs  {@link NotificationPanel}
     */
    public NotificationPanel waitTillDisplayed() {
        logger.debug("Waiting for __{}__ component to display ...", this.getClass().getSimpleName());
        new WebDriverWait(getDriver(), this.waitTime)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class)
                .ignoring(WebDriverException.class)
                .until((Predicate<WebDriver>) arg0 -> isDisplayed());

        return this;
    }

    /**
     * method to wait until the notification panel is disappeared or
     * wait for five seconds which ever comes first, ignoring  {@link StaleElementReferenceException}.
     *
     * @return current instance buildDataGridAs  {@link NotificationPanel}
     */
    public NotificationPanel waitTillDisappears() {
        logger.debug("Waiting for __{}__ component to disappear ...", this.getClass().getSimpleName());

        if (isDisplayed()) {
            new WebDriverWait(driver, this.waitTime)
                    .ignoring(StaleElementReferenceException.class)
                    .until((Predicate<WebDriver>) arg0 -> {
                        return !isDisplayed();
                    });
        }
        return this;
    }

    private boolean isDisplayed() {
        try {
            return getDriver().findElement(By.id("notificationMessage")).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * method to return the notification message displayed
     * in notification panel.First it waits until the notification panel is
     * visible then pulls the displayed message
     *
     * @return: notification message
     */
    public String getDisplayedMessage() {
        this.waitTillDisplayed();
        return getDriver().findElement(By.id(NOTIFICATION_MESSAGE_DOM_ID)).getText();
    }

    private boolean isMessageDisplayed() {
        return (this.displayedMessage != null);
    }

    public <T> T waitTillDisappearsOnPage(Class<T> classTOLandAfterNotificationDisapears) {
        this.waitTillDisappears();
        return PageFactory.initElements(getDriver(), classTOLandAfterNotificationDisapears);
    }
}
