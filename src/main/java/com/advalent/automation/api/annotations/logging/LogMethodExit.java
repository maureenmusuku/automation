package com.advalent.automation.api.annotations.logging;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Methods that have this annotation when the method
 * is exited (either normally or abnormally)
 *
 * @author sshrestha
 */

@Retention(RUNTIME)
@Target({METHOD})
public @interface LogMethodExit {
}
