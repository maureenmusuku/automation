package com.advalent.automation.api.annotations.inputfield;


import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target({FIELD, METHOD})
public @interface CustomElement {

    String xpath() default "";
    String[] xpaths() default {};
    String id() default "";
    String[] ids() default { };

}
