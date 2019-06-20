package com.advalent.automation.impl.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * @author sshrestha
 */
public class ScreenShotTaker {
    protected static final Logger logger = LoggerFactory.getLogger(ScreenShotTaker.class);
    private final File screenShotDirectory;
    private static final Object lock = new Object();

    public ScreenShotTaker() {
        this(new File(ReportConstants.SCREEN_SHOT_DIR));
    }

    public ScreenShotTaker(File screenShotsPath) {
        this.screenShotDirectory = screenShotsPath;
    }

    public static void cleanDir() {
        try {
            File screenShotFileDir = new File(ReportConstants.SCREEN_SHOT_DIR);
            if (screenShotFileDir.exists()) {
                FileUtils.cleanDirectory(screenShotFileDir);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void takeScreenShot(WebDriver webDriver, String fileName) {
        if (webDriver == null) {
            logger.error("Cannot take screenshot. WebDriver is null");
        }

        synchronized (lock) {
            File screenShotTempFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
            File screenshotFile = getScreenshotFile(fileName);
            try {
                FileUtils.copyFile(screenShotTempFile, screenshotFile);
            } catch (IOException e) {
                logger.error("Error occured when copying {} to {}. Exception {}", screenShotTempFile.getAbsolutePath(), screenshotFile.getAbsolutePath(), e);
            }
        }
    }


    private File getScreenshotFile(String fileName) {
        fileName = removeInvalidCharacters(fileName);

        if (fileName.length() >= 200) {
            fileName = fileName.substring(0, 196) + ".png";
        }

        return new File(screenShotDirectory.getPath() + File.separator + fileName);
    }


    private String removeInvalidCharacters(String fileName) {
        return fileName.replaceAll("[\\s/?<>:*|\"'\\\\]+", "_");
    }


}
