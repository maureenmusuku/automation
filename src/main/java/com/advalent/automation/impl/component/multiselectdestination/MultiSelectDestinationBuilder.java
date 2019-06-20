package com.advalent.automation.impl.component.multiselectdestination;

import org.openqa.selenium.WebDriver;

public class MultiSelectDestinationBuilder {

    private Builder builder;

    public MultiSelectDestinationBuilder(Builder builder) {
        builder = builder;
    }

    public MultiSelectDestinationBuilder() {
    }


    public static class Builder {
        String leftDropDownXpath;
        String rightDropDownXpath;
        String leftSearchInputXpath;
        String rightSearchInputXpath;
        String moveLeftButtonXpath;
        String moveRightButtonXpath;
        private final WebDriver driver;

        public Builder(WebDriver driver) {
            this.driver = driver;
        }


        public Builder rightDropDown(String rightDropDownXpath) {
            this.rightDropDownXpath = rightDropDownXpath;
            return this;
        }

        public Builder leftDropDown(String s) {
            this.leftDropDownXpath = s;
            return this;
        }

        public Builder leftSearchInput(String s) {
            this.leftSearchInputXpath = s;
            return this;
        }

        public Builder rightSearchInput(String s) {
            this.rightSearchInputXpath = s;
            return this;
        }

        public Builder moveLeftBtn(String s) {
            this.moveLeftButtonXpath = s;
            return this;
        }

        public Builder moveRightBtn(String s) {
            this.moveRightButtonXpath = s;
            return this;
        }

        public MultiSelectDestination build() {
            return new MultiSelectDestinationBuilder(this).build();
        }
    }

    private MultiSelectDestination build() {
        return new MultiSelectDestination(builder.driver, builder.leftDropDownXpath, builder.rightDropDownXpath,
                builder.leftSearchInputXpath, builder.rightSearchInputXpath,
                builder.moveLeftButtonXpath, builder.moveRightButtonXpath);
    }
}
