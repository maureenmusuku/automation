package com.advalent.automation.api.exceptions;

/**
 * Created by sshrestha on 1/19/2015.
 */
public class NotImplementedException extends AutomationException {

    public NotImplementedException(String message) {
        super(message);
    }

    public NotImplementedException() {
        super("Not Implemented");
    }
}
