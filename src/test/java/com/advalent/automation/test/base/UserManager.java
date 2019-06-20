package com.advalent.automation.test.base;

import com.advalent.automation.api.annotations.documentation.PackagePrivate;
import com.advalent.automation.api.annotations.documentation.Singleton;
import com.advalent.automation.api.annotations.documentation.ThreadSafe;
import com.advalent.automation.api.config.ExecutionContext;
import com.advalent.automation.api.dto.User;
import com.advalent.automation.groovy.users.UserConfiguration;
import com.advalent.automation.impl.resources.KeyedResources;
import com.advalent.automation.impl.resources.ResourcePool;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Dispenses Users to Tests that request them
 * <p/>
 * Relies on the thread safety buildDataGridAs KeyedResource and ResourcePool
 * for its thread safety
 *
 * @author sshrestha
 */
@ThreadSafe
@Singleton
@PackagePrivate
class UserManager {
    public static final String GROUPS_AND_USERS_FILE = "conf/usersAndRoles.groovy";
    private final KeyedResources<User> keyedUsers;
    private final ResourcePool<User> pooledUsers;
    final ExecutionContext context;
    final UserConfiguration userConfiguration;
    final String product;
    final String stage;
    final Map<String, User> userMap;

    @PackagePrivate
    static final UserManager INSTANCE = new UserManager();

    private UserManager() {
        context = ExecutionContext.forEnvironment(System.getProperty("environment"));
        userConfiguration = new UserConfiguration();
        userMap = userConfiguration.getUsers();
        List<User> users = getUsers();
        product = context.getApplication().getProduct();
        stage = context.getApplication().getStage();
        keyedUsers = new KeyedResources<>();
        pooledUsers = new ResourcePool<>();

        for (User user : users) {
            if (user.getAutomationId() == null) {
                pooledUsers.addResource(user);
            } else {
                keyedUsers.addResource(user.getAutomationId(), user);
            }
        }
    }

    public User getUser() {
        return getUsers().get(0);
    }

    public User getUser(String automationId) {
        return userMap.get(automationId);
    }

    public void releaseUser(User user) {
        if (user.getAutomationId() == null) {
            pooledUsers.releaseResource(user);
        } else {
            keyedUsers.releaseResource(user.getAutomationId());
        }
    }

    private List<User> getUsers() {
        return userMap.keySet().stream()
                .map(key -> {
                    return userMap.get(key);
                }).collect(Collectors.toList());
    }

    public static void main(String[] args) {
       new UserConfiguration() .getUsers();
       ;
    }
}
