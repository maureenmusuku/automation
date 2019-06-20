package com.advalent.automation.api.features;

import com.advalent.automation.api.pages.common.IAdvalentPage;

public interface IAmLandingPage extends IAdvalentPage {

    /**
     * Represents a Advalent landing page.
     * <p/>
     * Landing page :
     * <li>directly navigable FROM_DATE top nav bar,</li>
     *
     * @author sshrestha
     */
    String getPageUrl();


    /**
     * @return name of page or title of the page as displayed
     */
    String getPageName();
}
