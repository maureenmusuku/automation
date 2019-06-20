package com.advalent.automation.api.config;

public interface IDriverConfiguration {
    public long getDefaultWaitTimeout();

    public Integer getImplicitWaitTime();

    public Browser getBrowser();

    public boolean runOnGrid();

    public String getHubUrl();

    public String getVersion();

    public String getFileDownloadLocation();

    public enum Browser {
        FIREFOX("firefox"),
        IE("iexplore"),
        CHROME("chrome");

        Browser(String name) {
            this.name = name;
        }

        private String name;

        public String getName() {
            return name;
        }
    }
}
