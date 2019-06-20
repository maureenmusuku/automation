package com.advalent.automation.impl.component.breadcrumb;

import com.advalent.automation.impl.pages.common.AbstractWebComponent;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class BreadCrumb extends AbstractWebComponent {

    @FindBy(className = "breadcrumbs")
    WebElement breadCrumbContainer;

    public BreadCrumb(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    public List<WebElement> getAvailableLinks() {
        return breadCrumbContainer.findElements(By.xpath("./li/a"));
    }


    public WebElement getHomeLink() {
        return getAvailableLinks().get(0);
    }

    public boolean isLinksVisible() {
        try {
            for (WebElement link : getAvailableLinks()) {
                if (link.isDisplayed()) {
                    continue;
                } else {
                    return false;
                }
            }
            return true;
        } catch (ElementNotVisibleException ex) {
            return false;
        }
    }


    public WebElement getCrrentPageElement() {
        List<WebElement> breadCrumbLinks = getAvailableLinks();
        if (breadCrumbLinks.size() == 1) {
            return getHomeLink();
        }
        return breadCrumbLinks.stream()
                .filter(a -> a.getText().trim().equalsIgnoreCase("Home"))
                .findFirst().get();
    }

    /**
     * Checks if this component is fully loaded. Each web component should
     * provide it's own logic of being fully loaded. If returned true then this
     * component is ready for any events, interactions & LIVE query.
     *
     * @return
     */

    public boolean isBreadCrumbVisible() {
        return getAvailableLinks().size() > 0;
    }

    @Override
    public boolean isFullyLoaded() {
        return isLinksVisible();
    }
}
