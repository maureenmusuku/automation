package com.advalent.automation.api.dto;

import org.apache.commons.configuration.Configuration;

import static com.advalent.automation.groovy.environment.EnvironmentConfiguration.*;

/**
 * @author sshrestha
 */
public class Application {
    private final String url;
    private final String product;
    private final String stage;
    private final String authConfig;


    public Application(String url, String product, String stage) {
        this.url = url;
        this.product = product;
        this.stage = stage;
        this.authConfig = null;
    }


    public Application(Configuration configuration) {
        this.url = configuration.getString(APP_URL);
        this.product = configuration.getString(PRODUCT);
        this.stage = configuration.getString(STAGE);
        this.authConfig = configuration.getString(AUTH_CONFIG);
    }


    public String getUrl() {
        return url;
    }

    public String getProduct() {
        return product;
    }

    public String getStage() {
        return stage;
    }

    public String getAuthConfig() {
        return authConfig;
    }

    @Override
    public String toString() {
        return "Application{url='" + url + '\'' + ", product='" + product + '\'' + '}';
    }
}
