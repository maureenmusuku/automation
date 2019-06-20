package com.advalent.automation.groovy.module

import java.util.function.Predicate

/**
 * @author sshrestha
 */
class ModuleInfo implements IModuleInformer {
    private Map<String, Module> moduleMap

    private ModuleInfo() {

    }
    public static ModuleInfo INSTANCE = new ModuleInfo()




    Module getModuleByPageName(String pageTitle) {
        initializeModuleMap()

        Module module = moduleMap.values().find { it.pageTitle.equals(pageTitle) }
        if (module == null)
            throw new RuntimeException("No module found with page title ${pageTitle}")
        return module
    }

    @Override
    Module getModule(Predicate<Module> condition) {
        initializeModuleMap()

        Module module = moduleMap.values().find{condition} ;
        if (module == null)
            throw new RuntimeException("module with condition ${condition.toString()} not found ")
        return module

    }

    void initializeModuleMap() {
        if (moduleMap != null)
            return; //already initialized

        def builder = new NavigationPanelBuilder()
        Binding binding = new Binding() {
            @Override
            Object getVariable(String name) {
                return { Object... args -> builder.invokeMethod(name, args) }
            }
        }
        def script = new GroovyShell(binding).parse(new File("conf" + File.separator + "navigationPanel.groovy"))
        moduleMap = script.run()
    }

//    public static void main(String[] args) {
//        new ModuleInfo().initializeModuleMap()
//
//    }
}
