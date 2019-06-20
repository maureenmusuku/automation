package com.advalent.automation.impl.component.pagination;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public interface IHavePaginationComponent {

    WebDriver getDriver();

    default boolean isPaginationComponentDisplayed() {
        try {
            return getDriver().findElement(By.className("ar5")).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    PaginationComponent getPaginationComponent();
}
