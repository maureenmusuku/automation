package com.advalent.automation.test.utils.testdata;

import com.advalent.automation.groovy.testinputreader.TestInputConfiguration;

import java.util.Map;

public class TestDataReader {

    static Map<String, Object> inputMapForSection = null;
    static Map<String, Map<String, Object>> inputMap = null;

    public static Map<String, Object> readSection(String fileName, String section) {
        inputMapForSection = read(fileName).get(section);
        return inputMapForSection;
    }

    public static Map<String, Map<String, Object>> read(String fileName) {
        inputMap = new TestInputConfiguration(fileName).getInputMap();
        return inputMap;
    }


}
