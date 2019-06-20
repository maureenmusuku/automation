package com.advalent.automation.groovy.users

import com.advalent.automation.api.dto.User
import com.advalent.automation.api.exceptions.NotImplementedException
import org.apache.commons.configuration.AbstractConfiguration
import org.apache.commons.configuration.Configuration


/**
 * @author sshrestha
 * */

class UserConfiguration {

    private static final String USER_FILE = "conf" + File.separator + "usersAndRoles.groovy"

    Map<String, User> users;


    UserConfiguration() {

        //read in configuration FROM_DATE automationUserId.groovy
        def builder = new UserBuilder()

        Binding binding = new Binding() {
            @Override
            Object getVariable(String name) {
                return { Object... args -> builder.invokeMethod(name, args) }
            }
        }
        def script = new GroovyShell(binding).parse(getConfigFile())

        script.run()
        users = builder.getUsersMap();

    }

/**
 * Two different directories are provided because the working
 * directory changes based on whether you are runnning via maven
 * or directly running a groovy script. Need to find a better
 * way to find resources
 * @return
 */
    File getConfigFile() {
        File configFile = new File(USER_FILE)
        if (configFile.exists()) return configFile

        configFile = new File("usersAndRoles.groovy")
        if (configFile.exists()) return configFile;
        throw new Error("Could not find usersAndRoles.groovy. The current working directory is " + System.getProperty("user.dir"))
    }

    Map<String, User> getUsers() {
        return users
    }
}