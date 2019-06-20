package com.advalent.automation.reporting;

import com.advalent.automation.impl.utils.ScreenShotTaker;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

import static com.advalent.automation.impl.utils.ReportConstants.REPORT_FILE_NAME;
import static com.advalent.automation.impl.utils.ReportConstants.REPORT_TITLE;

public class ExtentHTMLReportManager {

    private static ExtentHTMLReportManager INSTANCE = null;
    private ExtentReports extent;
    ArrayList<String> testSteps = new ArrayList<>();
    private ExtentTest testToLog;
    private final Logger logger = LoggerFactory.getLogger(ExtentHTMLReportManager.class);

    private ExtentHTMLReportManager(String reportDir) {
        Preconditions.checkState(reportDir != null || reportDir != "");
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(reportDir);
        htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle(REPORT_TITLE);
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName(REPORT_TITLE);
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        try {
            ScreenShotTaker.cleanDir();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ExtentHTMLReportManager getInstance() {
        if (INSTANCE == null)
            INSTANCE = new ExtentHTMLReportManager(REPORT_FILE_NAME);
        return INSTANCE;
    }

    public ExtentReports getExtentObject() {
        return this.extent;
    }

    public void addStep(String step, Object... args) {
        testToLog.info(String.format(" <b>" + step + " </b>", args));
        logger.info(step);
        extent.flush();
    }

    public ArrayList<String> getTestSteps() {
        return testSteps;
    }

    public void clearStep() {
        testSteps.clear();
    }


    public void addStep(String step, String value) {
        testToLog.info(" <b>" + step + "= </b>" + value);
        logger.info(step + "=" + value);
        extent.flush();
    }


    public void setTestToLog(ExtentTest testToLog) {
        this.testToLog = testToLog;
    }

    public static void main(String[] args) {
        ScreenShotTaker.cleanDir();
    }
}