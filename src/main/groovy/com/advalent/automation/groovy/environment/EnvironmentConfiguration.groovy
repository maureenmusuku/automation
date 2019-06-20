package com.advalent.automation.groovy.environment

import com.advalent.automation.api.exceptions.NotImplementedException
import org.apache.commons.configuration.AbstractConfiguration
import org.apache.commons.configuration.Configuration


/**
 * @author sshrestha
 * */

class EnvironmentConfiguration extends AbstractConfiguration implements Configuration {

    private static final String CONFIGURATION_FILE = "conf" + File.separator + "environment.groovy"
    public static final String APP_URL = "appUrl";
    public static final String STAGE = "stage";
    public static final String PRODUCT = "product";
    public static final String AUTH_CONFIG = "authConfig";
    public static final String SKIP_TEST_THAT_REQUIRE_DATABASE = "skipTestThatRequireDatabase";
    public static final String DEFAULT_WAIT_TIME_OUT = "waitTime";
    public static final String IMPLICIT_WAIT_TIME = "implicitWaitTime";
    public static final String DB_URL = "db_url";
    public static final String DB_NAME = "db_name";
    public static final String DB_USER = "db_user";
    public static final String DB_PASSWORD = "db_password";
    public static final String BROWSER = "browser";
    public static final String RUN_ON_GRID = "runOnGrid";
    public static final String HUB_URL = "hubUrl";

    private String environment;
    Map<String, String> properties;


    EnvironmentConfiguration(String environment) {
        this.environment = environment

        //read in configuration FROM_DATE automationUserId.groovy
        def builder = new EnvironmentBuilder()

        Binding binding = new Binding() {
            @Override
            Object getVariable(String name) {
                return { Object... args ->
                    builder.invokeMethod(name, args);
                }
            }
        }


        def script = new GroovyShell(binding).parse(getConfigFile())

        script.run()

        if (environment == null) {
            properties = builder.getDefaultEnvironmentProperties()
        } else {
            properties = builder.getProperties(environment)
        }

    }

/**
 * Two different directories are provided because the working
 * directory changes based on whether you are runnning via maven
 * or directly running a groovy script. Need to find a better
 * way to find resources
 * @return
 */
    File getConfigFile() {
        File configFile = new File(CONFIGURATION_FILE)
        if (configFile.exists()) return configFile

        configFile = new File("environment.groovy")
        if (configFile.exists()) return configFile;

        throw new Error("Could not find environment.groovy. The current working directory is " + System.getProperty("user.dir"))
    }

    @Override
    boolean isEmpty() {
        return properties.isEmpty()
    }

    @Override
    boolean containsKey(String key) {
        return properties.containsKey(key)
    }

    @Override
    Iterator getKeys() {
        return properties.keySet().iterator()
    }

    @Override
    Object getProperty(String property) {
        return properties.get(property)
    }

    @Override
    protected void addPropertyDirect(String key, Object value) {
        throw new NotImplementedException(); //not applicable
    }
}