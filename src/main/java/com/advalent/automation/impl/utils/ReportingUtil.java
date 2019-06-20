package com.advalent.automation.impl.utils;

import com.advalent.automation.impl.utils.aspects.AspectUtils;
import org.aspectj.lang.JoinPoint;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class ReportingUtil {
    public static final String BASE_TEST_FILE = "BaseTest.java";

    public static String getSuiteDescription(Test annotation, Class testClass) {

        if (annotation == null || annotation.description() == null) {
            return testClass.getSimpleName();
        } else if (annotation.description().isEmpty()) {
            return testClass.getSimpleName();
        } else {
            return annotation.description();
        }
    }


    public static  String getFileName(JoinPoint thisJoinPoint, Throwable exception) {

        return AspectUtils.getFullMethodNameWithParameters(thisJoinPoint) + "_exception=" + exception.getClass().getSimpleName() + ".png";
    }

    public static boolean isMethodCalledFromBaseTest(JoinPoint thisJoinPoint) {
        return thisJoinPoint.getSourceLocation().getFileName().equals(BASE_TEST_FILE);
    }
    public static boolean isAnnotatedMethodIsPresentInSubClass(Class annotationClass, JoinPoint thisJoinPoint) {
        Method[] methods = thisJoinPoint.getTarget().getClass().getDeclaredMethods();
//        List<Method> annotatedMethod = Arrays.stream(methods).sort(m -> m.isAnnotationPresent(annotationClass)).collect(Collectors.toList());
        boolean isannotatedMethodPresent = false;
        for (Method m : methods) {
            if (m.isAnnotationPresent(annotationClass)) {
                isannotatedMethodPresent = true;
                break;
            }
        }
        return isannotatedMethodPresent;
    }

}
