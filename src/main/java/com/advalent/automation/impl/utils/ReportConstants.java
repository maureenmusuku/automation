package com.advalent.automation.impl.utils;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportConstants {
    private static final File USER_DIRECTORY = new File(System.getProperty("user.dir"));
    private static final File HOME_DIRECTORY = new File(System.getProperty("user.home"));
    private static final String PATH_SEPARATOR = File.separator;
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dMMMyyyyhhmm");

    public static final String TODAY = DATE_FORMAT.format(new Date());

    public static final File REPORT_DIR = new File(USER_DIRECTORY + PATH_SEPARATOR + "test-report" + PATH_SEPARATOR/* + TODAY + PATH_SEPARATOR*/);

    public static final String SCREEN_SHOT_FOLDER = "screenshots" + PATH_SEPARATOR;

    public static final String SCREEN_SHOT_DIR = REPORT_DIR + PATH_SEPARATOR + "screenshots" + PATH_SEPARATOR;

    public static String REPORT_FILE_NAME = REPORT_DIR + PATH_SEPARATOR + "extent.html";
    public static File REPORT_ARCHIVE_DIRECTORY = new File(HOME_DIRECTORY + PATH_SEPARATOR + "test-reports" + PATH_SEPARATOR + TODAY + PATH_SEPARATOR);

    public static String REPORT_TITLE = TODAY + "'s Test Report";


}
