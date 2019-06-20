package com.advalent.automation.groovy.testinputreader


class TestInputConfiguration {

    Map<String, Map<String, Object>> inputMap


    TestInputConfiguration(String fileName) {
        def builder = new TestDataBuilder()

        Binding binding = new Binding() {
            @Override
            Object getVariable(String name) {
                return { Object... args -> builder.invokeMethod(name, args) }
            }
        }
        def script = new GroovyShell(binding).parse(getConfigFile(fileName))

        script.run()
        inputMap = builder.pageInputMap


    }

    File getConfigFile(String fileName) {
        File configFile = new File(fileName)
        if (configFile.exists()) return configFile


        throw new Error("Could not find ${fileName} The current working directory is " + System.getProperty("user.dir"))
    }

    Map<String, Map<String, Object>> getInputMap() {
        return inputMap
    }
}