package com.advalent.automation.impl.component.filter;

import com.advalent.automation.api.annotations.inputfield.CustomElement;
import com.advalent.automation.api.constants.TimeOuts;
import com.advalent.automation.impl.component.inputelements.DropDown;
import com.advalent.automation.impl.pages.common.AbstractWebComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created By: sumit
 * Created Date :12/7/2018
 * Note:
 */
public class FilterComponent extends AbstractWebComponent {


    @CustomElement(xpath = "//*[@id=\"SortColumn\"]")
    private DropDown sortBy;

    @CustomElement(xpath = "//*[@id=\"SortType\"]")
    private DropDown sortType;


    @FindBy(xpath = "//sortable-column/div/span/span[3]/button")
    private WebElement sortButton;


    private SortLoadingComponent loadingComponent;

    public FilterComponent(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        loadingComponent = new SortLoadingComponent(driver);
    }

    @Override
    public boolean isFullyLoaded() {
        return this.sortBy.isFullyLoaded();
    }

    public FilterComponent sortBy(String sortBy) {
        if (!this.sortBy.getValue().equalsIgnoreCase(sortBy)) {
            this.sortBy.selectOption(sortBy);
        }
        return this;
    }

    public FilterComponent sortType(String sortType) {
        if (!this.sortType.getValue().equalsIgnoreCase(sortType)) {
            this.sortType.selectOption(sortType);
        }
        return this;
    }

    public FilterComponent clickOnSortButton() {
        this.sortButton.click();
        this.loadingComponent.waitTillDisappears(TimeOuts.SIXTY_SECONDS);
        return this;
    }


}
