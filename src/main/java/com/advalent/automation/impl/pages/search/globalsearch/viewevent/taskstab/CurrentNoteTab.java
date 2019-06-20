package com.advalent.automation.impl.pages.search.globalsearch.viewevent.taskstab;

import com.advalent.automation.api.components.tab.ITab;
import com.advalent.automation.impl.pages.common.AbstractWebComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CurrentNoteTab extends AbstractWebComponent implements ITab {

    @FindBy(id = "taskTab1")
    WebElement curretntNoteTab;

    public CurrentNoteTab(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public String getTabName() {
        return "Current Note";
    }

    @Override
    public String getTabTitle() {
        return curretntNoteTab.findElement(By.xpath("./span/strong")).getText();
    }

    @Override
    public boolean isFullyLoaded() {
        return false;
    }

    @Override
    public AbstractWebComponent doWaitTillFullyLoaded(int waitTimeInSecs) {
        return null;
    }

    @Override
    public WebDriver getDriver() {
        return null;
    }

    public String getNoteDetails() {
        return curretntNoteTab.findElement(By.xpath("./span/strong")).getText();
    }

    public String getNote() {
        try {
            return curretntNoteTab.findElement(By.xpath("./span/span")).getText().trim();
        } catch (NoSuchElementException ex) {
            return curretntNoteTab.findElement(By.xpath("./span")).getText().split(":")[2].trim();
        }
    }

    public String getAllContent() {
        return curretntNoteTab.findElement(By.xpath("//*[@id='taskTab1']")).getText();
    }

}
