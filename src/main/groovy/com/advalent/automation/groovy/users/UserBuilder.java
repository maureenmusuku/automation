package com.advalent.automation.groovy.users;

import com.advalent.automation.api.config.ExecutionContext;
import com.advalent.automation.api.dto.User;
import groovy.util.BuilderSupport;

import java.util.*;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Builder Class to parse environment.groovy
 * <p>
 * Will parse usersAndRoles.groovy file and populate the usersMap map
 *
 * @author sshrestha
 */
public class UserBuilder extends BuilderSupport {

    public static final String STAGE = "stage";
    public static final String PRODUCT = "product";
    public static final String NAME_KEY = "name";
    public static final String USER = "user";
    public static final String LOGIN_NAME = "loginName";
    public static final String PASSWORD = "password";
    public static final String ROLE = "role";
    public static final String AUTOMATION_ID = "automationId";
    public static final String EMAIL = "email";
    public static final String EMAIL_PASSWORD = "emailPassword";
    public final String ACTIVE_APP_STAGE;public final String ACTIVE_APP_PRODUCT;
    Map<String, User> availableUserMap;
    String currentProductNodeName = null;
    String currentStageNodeName = null;
    private String currentUserId;


    UserBuilder() {
        ACTIVE_APP_PRODUCT = ExecutionContext.INSTANCE.getApplication().getProduct();
        ACTIVE_APP_STAGE = ExecutionContext.INSTANCE.getApplication().getStage();
    }




    //called for defaultValues{ tag
    @Override
    protected Object createNode(Object name) {
        return null;
    }

    //called for keyValue pairs
    @Override
    protected Object createNode(Object name, Object value) {
        if ((ACTIVE_APP_PRODUCT == currentProductNodeName) && ACTIVE_APP_STAGE == currentStageNodeName) {
            switch (name.toString()) {
                case LOGIN_NAME:
                    availableUserMap.get(currentUserId).setUserId(value.toString());
                    break;
                case PASSWORD:
                    availableUserMap.get(currentUserId).setPassword(value.toString());
                    break;
                case ROLE:
                    availableUserMap.get(currentUserId).setRoles(Arrays.asList(value.toString()));
                    break;
                 case EMAIL:
                    availableUserMap.get(currentUserId).setEmail(value.toString());
                    break;
                case EMAIL_PASSWORD:
                    availableUserMap.get(currentUserId).setEmailPassword(value.toString());
                    break;
                default:
                    throw new RuntimeException("Unexpected name " + name);
            }
        }
        return null;

    }

    @Override
    protected Object createNode(Object name, Map attributes) {
        switch (name.toString()) {
            case PRODUCT:
                assertThat(attributes.containsKey(NAME_KEY)).as("name should be provided").isTrue();
                currentProductNodeName = attributes.get(NAME_KEY).toString();
                break;

            case STAGE:
                assertThat(attributes.containsKey(NAME_KEY)).as("name should be provided").isTrue();

                currentStageNodeName = attributes.get(NAME_KEY).toString();
                break;
            case USER:
                if ((ACTIVE_APP_PRODUCT == currentProductNodeName) && ACTIVE_APP_STAGE == currentStageNodeName) {
                    assertThat(attributes.containsKey(AUTOMATION_ID)).as("name should be provided").isTrue();
                    currentUserId = attributes.get(AUTOMATION_ID).toString();
                    availableUserMap = new HashMap<>();
                    User user = new User();
                    user.setAutomationId(currentUserId);
                    availableUserMap.put(currentUserId, user);
                }
                break;
        }
        return null;
    }

    @Override
    protected Object createNode(Object name, Map attributes, Object value) {
        return null;
    }

    @Override
    protected void setParent(Object parent, Object child) {
        return;
    }


    public Map<String, User> getUsersMap() {
        return availableUserMap;
    }


}
