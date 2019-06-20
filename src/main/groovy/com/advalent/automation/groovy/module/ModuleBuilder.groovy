package com.advalent.automation.groovy.module

/**
 * @author sshrestha
 */
class ModuleBuilder {
    private String name
    private String linkId
    private String pageTitle

    Module parentModule;
    List<Module> children;


    ModuleBuilder() {
    }


    ModuleBuilder parent(Module parentModule) {
        this.parentModule = parentModule
        return this

    }

    ModuleBuilder children(List<Module> children) {
        this.children = children
        return this
    }

    ModuleBuilder linkId(String linkId) {
        this.linkId = linkId
        return this
    }

    ModuleBuilder name(String name) {
        this.name = name
        return this
    }

    ModuleBuilder pageTitle(String pageTitle) {
        this.pageTitle = pageTitle
        return this
    }


    Module build() {
        assert name && linkId && switchBoard

        def map = [
                'name'        : name,
                'linkId'      : linkId,
                'pageTitle'   : pageTitle,
                'children'    : children,
                'parentModule': parentModule
        ]
        return new Module(map)


    }
}
