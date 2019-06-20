package com.advalent.automation.api.components.tab;

import com.advalent.automation.api.features.IAmWebComponent;
import org.openqa.selenium.By;

public interface ITab extends IAmWebComponent {

    /**
     * @return tab Name
     * Tab name must be same as the corresponding Tab pill
     */
    String getTabName();

    /**
     * @return tab title as displayed in page
     * */
    String getTabTitle();

    /**
     * method to determine weather the tab is active or not
     *
     * @return: true if tab is active and vice varsa
     */
    default boolean isTabActive() {
        return false;
    }

}
