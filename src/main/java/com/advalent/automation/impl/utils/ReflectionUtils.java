package com.advalent.automation.impl.utils;

import com.advalent.automation.api.components.tab.ITab;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReflectionUtils {


    public static String getMethodParameterFromJointPoint(JoinPoint thisJoinPoint, int indexOfParameter) {
        String methodName = getMethodNameFromJointPoint(thisJoinPoint);
        List<Method> methods = getAllMethods(thisJoinPoint);
        Method methodOfInterest = methods.stream().filter(method -> method.getName() == methodName).findFirst().get();
        return methodOfInterest.getParameters()[indexOfParameter].getName();

    }

    private static List<Method> getAllMethods(JoinPoint thisJoinPoint) {
        List<Method> methods = new ArrayList<>();
        Class<?> aClass = thisJoinPoint.getTarget().getClass();
        methods.addAll(Arrays.asList(aClass.getDeclaredMethods()));
        aClass = aClass.getSuperclass();

        methods.addAll(Arrays.asList(aClass.getDeclaredMethods()));
        return methods;
    }

    private static String getMethodNameFromJointPoint(JoinPoint thisJoinPoint) {
        Method method = getMethodFromJointPoint(thisJoinPoint);
        return method.getName();
    }

    private static Method getMethodFromJointPoint(JoinPoint thisJoinPoint) {
        return ((MethodSignature) thisJoinPoint.getSignature()).getMethod();
    }

    public static <T extends ITab> Object getTabObject(Class<T> tabClass, WebDriver driver) {
        try {
            Constructor c = tabClass.getConstructor(WebElement.class);
            return c.newInstance(driver);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
