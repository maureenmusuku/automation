package com.advalent.automation.impl.pages.search.globalsearch.viewevent.taskstab;

import com.advalent.automation.api.components.tab.ITab;
import com.advalent.automation.impl.pages.common.AbstractWebComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PreviousNoteTab extends AbstractWebComponent implements ITab {

    @FindBy(id = "taskTab2")
    WebElement previousNoteTab;

    public PreviousNoteTab(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public String getTabName() {
        return "Previous Note";
    }

    @Override
    public String getTabTitle() {
        return previousNoteTab.findElement(By.xpath("./span/strong")).getText();
    }

    @Override
    public boolean isFullyLoaded() {
        return previousNoteTab.isDisplayed();
    }


    public String getNoteDetails() {
        return previousNoteTab.findElement(By.xpath("./span/strong")).getText();
    }

    public String getNote() {
        return previousNoteTab.findElement(By.xpath("./span")).getText().split(":")[1];
    }

    public String getAllContent() {
        return previousNoteTab.findElement(By.xpath(".")).getText();
    }

}
