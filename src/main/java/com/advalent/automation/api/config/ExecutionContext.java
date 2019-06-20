package com.advalent.automation.api.config;


import com.advalent.automation.api.data.DataSourceConfiguration;
import com.advalent.automation.api.dto.Application;
import com.advalent.automation.groovy.environment.EnvironmentConfiguration;
import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.SystemConfiguration;

import static com.advalent.automation.groovy.environment.EnvironmentConfiguration.*;

/**
 * @author sshrestha
 */
public class ExecutionContext {
    private final Application application;
    private final Integer waitTime;
    private final Configuration configuration;
    private final DriverConfiguration driverConfiguration;

    public static ExecutionContext INSTANCE = null;

    public static ExecutionContext forEnvironment(String environment) {
        if (INSTANCE == null) {
            INSTANCE = new ExecutionContext(environment);
        }
        return INSTANCE;
    }


    private ExecutionContext(String environment) {
        CompositeConfiguration compositeConfiguration = new CompositeConfiguration();
        compositeConfiguration.addConfiguration(new SystemConfiguration());
        compositeConfiguration.addConfiguration(new EnvironmentConfiguration(environment));
        compositeConfiguration.setThrowExceptionOnMissing(true);

        this.configuration = compositeConfiguration;

        application = new Application(configuration);
        waitTime = configuration.getInt(DEFAULT_WAIT_TIME_OUT);
        driverConfiguration = buildDriverConfiguration();
    }


    private DriverConfiguration buildDriverConfiguration() {
        DriverConfiguration.Builder builder = DriverConfiguration.newBuilder(configuration.getString(BROWSER), configuration.getLong(DEFAULT_WAIT_TIME_OUT));

        if (configuration.containsKey(RUN_ON_GRID))
            builder.runOnGrid(configuration.getBoolean(RUN_ON_GRID));

        if (configuration.containsKey(HUB_URL))
            builder.setHubUrl(configuration.getString(HUB_URL));

        if (configuration.containsKey(IMPLICIT_WAIT_TIME))
            builder.setImplicitWaitTime(configuration.getInt(IMPLICIT_WAIT_TIME));


        return builder.build();
    }


    public Application getApplication() {
        return application;
    }


    public Boolean skipTestThatRequireDatabase() {
        return configuration.getBoolean(SKIP_TEST_THAT_REQUIRE_DATABASE);
    }

    public DataSourceConfiguration getHeuserDataSourceConfiguration() {
        return new DataSourceConfiguration.Builder()
                .withUrl(configuration.getString(DB_URL))
                .withUsername(configuration.getString(DB_USER))
                .withPassword(configuration.getString(DB_PASSWORD))
                .withDatabaseName(configuration.getString(DB_PASSWORD))
                .build();
    }

    public Integer getDefaultWaitTimeout() {
        return waitTime;
    }


    public IDriverConfiguration getDriverConfiguration() {
        return driverConfiguration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExecutionContext)) return false;

        ExecutionContext that = (ExecutionContext) o;

        if (getApplication() != null ? !getApplication().equals(that.getApplication()) : that.getApplication() != null)
            return false;
        if (waitTime != null ? !waitTime.equals(that.waitTime) : that.waitTime != null) return false;
        if (configuration != null ? !configuration.equals(that.configuration) : that.configuration != null)
            return false;
        return getDriverConfiguration() != null ? getDriverConfiguration().equals(that.getDriverConfiguration()) : that.getDriverConfiguration() == null;
    }

    @Override
    public int hashCode() {
        int result = getApplication() != null ? getApplication().hashCode() : 0;
        result = 31 * result + (waitTime != null ? waitTime.hashCode() : 0);
        result = 31 * result + (configuration != null ? configuration.hashCode() : 0);
        result = 31 * result + (getDriverConfiguration() != null ? getDriverConfiguration().hashCode() : 0);
        return result;
    }
}
