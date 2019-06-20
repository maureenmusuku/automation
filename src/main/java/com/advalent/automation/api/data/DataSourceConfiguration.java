package com.advalent.automation.api.data;

import com.advalent.automation.api.annotations.documentation.Immutable;
import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;

/**
 * @author sshrestha
 */
@Immutable
public class DataSourceConfiguration {
    private final String url;
    private final String username;
    private final String password;
    private final String databaseName;

    private DataSourceConfiguration(String url, String username, String password, String databaseType) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.databaseName = databaseType;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public static class Builder {
        String url;
        String username;
        String password;
        String databaseName;

        public Builder withDatabaseName(String databaseName) {
            this.databaseName = databaseName;
            return this;
        }

        public Builder withUrl(String url) {
            Preconditions.checkArgument(StringUtils.isNotBlank(url), "Data source url cannot be null or empty");
            this.url = url;
            return this;
        }

        public Builder withUsername(String username) {
            Preconditions.checkArgument(StringUtils.isNotBlank(username), "Username cannot be null or empty");
            this.username = username;
            return this;
        }

        public Builder withPassword(String password) {
            Preconditions.checkArgument(StringUtils.isNotBlank(password), "Username cannot be null or empty");
            this.password = password;
            return this;
        }

        public DataSourceConfiguration build() {
            Preconditions.checkState(StringUtils.isNotBlank(url), "Url Blank");
            Preconditions.checkState(StringUtils.isNotBlank(username), "Username Blank");
            Preconditions.checkState(StringUtils.isNotBlank(password), "Passowrd Blank");
            Preconditions.checkState(StringUtils.isNotBlank(databaseName), "DatabaseName Blank");

            return new DataSourceConfiguration(url, username, password, databaseName);
        }
    }
}
