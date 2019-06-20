package com.advalent.automation.groovy.testinputreader;

import com.advalent.automation.api.config.ExecutionContext;
import groovy.util.BuilderSupport;

import java.util.HashMap;
import java.util.Map;

class TestDataBuilder extends BuilderSupport {

    Map<String, String> inputMap;
    Map<String, Map<String, String>> pageInputMap;
    private String currentEnvironmet;
    private String currentPage;

    @Override
    protected void setParent(Object parent, Object child) {


    }

    @Override
    protected Object createNode(Object name) {
        return null;

    }

    @Override
    protected Object createNode(Object name, Object value) {
        if (currentEnvironmet == ExecutionContext.INSTANCE.getApplication().getStage()) {
            if (name == "section") {
                currentPage = value.toString();
                inputMap = new HashMap<>();
                pageInputMap.put(currentPage, inputMap);
            } else {
                pageInputMap.get(currentPage).put(name.toString(), value.toString());
            }
        }
        return new Object();
    }

    @Override
    protected Object createNode(Object name, Map attributes) {
        currentEnvironmet = attributes.get("name").toString();
        if (currentEnvironmet == ExecutionContext.INSTANCE.getApplication().getStage()) {
            pageInputMap = new HashMap<>();
        }
        return new Object();

    }

    @Override
    protected Object createNode(Object name, Map attributes, Object value) {
        return null;
    }
}
