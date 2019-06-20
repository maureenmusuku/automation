package com.advalent.automation.api.exceptions;

/**
 * Thrown for exceptions resulting to the implementation
 * of the automation framework.
 * Created by sshrestha on 9/1/2014.
 */
public class AutomationException extends RuntimeException {

    public AutomationException(String message) {
        super(message);
    }
}
