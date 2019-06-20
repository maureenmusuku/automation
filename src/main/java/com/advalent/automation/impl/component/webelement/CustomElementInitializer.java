package com.advalent.automation.impl.component.webelement;


import com.advalent.automation.api.annotations.inputfield.CustomElement;
import com.advalent.automation.impl.component.inputelements.DateRange;
import com.advalent.automation.impl.component.inputelements.Phone;
import com.advalent.automation.selenium.DriverFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

@Aspect
public class CustomElementInitializer {
    @Before("@annotation(com.advalent.automation.api.annotations.inputfield.CustomElement)")
    public void initElement(JoinPoint thisJoinPoint) {
        Field field = Arrays.stream(thisJoinPoint.getTarget().getClass().getDeclaredFields())
                .filter(f -> f.getName() == thisJoinPoint.getSignature().getName())
                .findFirst()
                .get();
//        IDriverConfiguration dc = ExecutionContext.INSTANCE.getDriverConfiguration();
//        WebDriver driver = new DriverFactory(dc).createDriver();
        WebDriver driver = DriverFactory.getDriverInstance();
        field.setAccessible(true);

        try {
            if (field.get(thisJoinPoint.getTarget()) != null) {
                return;
            }

            if (field.getType().equals(Phone.class) || field.getType().equals(DateRange.class)) {
                String xpath1 = "";
                String xpath2 = "";
                String[] ids = field.getAnnotation(CustomElement.class).ids();
                if (ids.length != 0) {
                    xpath1 = "//*[@id='" + ids[0] + "']";
                    xpath2 = "//*[@id='" + ids[1] + "']";
                } else {
                    String[] xpaths = field.getAnnotation(CustomElement.class).xpaths();
                    xpath1 = xpaths[0];
                    xpath2 = xpaths[1];
                }
                Constructor c = null;
                try {
                    c = field.getType().getConstructor(WebDriver.class, String.class, String.class);

                    field.set(thisJoinPoint.getTarget(), c.newInstance(driver, xpath1, xpath2));
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            } else {
                String xpath = field.getAnnotation(CustomElement.class).xpath();
                if (xpath.isEmpty()) {
                    xpath = "//*[@id='" + field.getAnnotation(CustomElement.class).id() + "']";
                }
                try {
                    Constructor c = field.getType().getConstructor(WebDriver.class, String.class);
                    field.set(thisJoinPoint.getTarget(), c.newInstance(driver, xpath));
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
