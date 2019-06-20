package com.advalent.automation.groovy.module

import com.advalent.automation.api.config.ExecutionContext

class NavigationPanelBuilder extends BuilderSupport {
    Map<String, Module> modules;
    String currentProduct
    Module currentParentModule


    @Override
    protected Object createNode(Object name) {
        assert name.equals("navigationPanels")
        modules = [:]
    }

    @Override
    protected Object createNode(Object name, Object value) {
        assert name.equals("product")
        currentProduct = value
    }

    @Override
    protected Object createNode(Object name, Map map) {
        if (currentProduct == ExecutionContext.INSTANCE.application.getProduct()) {
            switch (name) {
                case "module":
                    currentParentModule = new Module(map)
                    break
                case "landingPage":
                    Module module = new Module(map)
                    module.parent = currentParentModule
                    modules.put(map['name'], module)
                    return module
                default:
                    throw new RuntimeException("Unexpected node ${name}")
            }
        }
    }

    @Override
    protected Object createNode(Object name, Map attributes, Object value) {
        throw new RuntimeException("N/A")
    }

    @Override
    protected void setParent(Object parent, Object child) {
        return;
    }
}
