package com.advalent.automation.api.features;

import com.advalent.automation.impl.pages.common.AbstractWebComponent;
import org.openqa.selenium.WebDriver;

/**
 * Represents a LIVE web component where events, interactions & LIVE
 * state query can happen.
 *
 * @author sshrestha
 */
public interface IAmWebComponent {



    /**
     * Checks if this component is fully loaded. Each web component should
     * provide it's own logic of being fully loaded. If returned true then this
     * component is ready for any events, interactions & LIVE query.
     *
     * @return
     */
     boolean isFullyLoaded();

    /**
     * Wait till this component is fully loaded or till the given time out
     * period expires.
     *
     * @param waitTimeInSecs
     * @return
     */
    public AbstractWebComponent doWaitTillFullyLoaded(int waitTimeInSecs);

    public WebDriver getDriver();

}
