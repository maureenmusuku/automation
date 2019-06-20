package com.advalent.automation.api.annotations;


import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


/*
* Used to log the step for reporting purpose
* @author sshrestha
* */
@Retention(RUNTIME)
@Target({METHOD})
public @interface LogStep {
    String step();
}
