package com.advalent.automation.test.utils.testdata;


import com.google.common.base.Preconditions;

import java.util.List;

/**
 * Created by sshrestha
 */
public class DataProviderUtils {
    public static final Object[][] EMPTY_OBJECT_ARRAY = new Object[0][0];


    public static Object[][] getObjects(List... lists) {
        //Precondition.check that the size buildDataGridAs all the list are same
        int count = lists[0].size();
        for (List list : lists) {
            Preconditions.checkArgument(list.size() == count);
        }

        Object[][] data = new Object[count][lists.length];
        for (int row = 0; row < count; row++) {
            for (int parameterNumber = 0; parameterNumber < lists.length; parameterNumber++) {
                data[row][parameterNumber] = lists[parameterNumber].get(row);
            }
        }
        return data;
    }
}

