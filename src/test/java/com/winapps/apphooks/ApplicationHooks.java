package com.winapps.apphooks;

import com.winapps.factory.DriverFactory;
import com.winapps.utils.ConfigFileReader;
import com.winapps.utils.ScreenRecorderUtil;
import io.cucumber.java.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.IOException;
import java.util.Properties;


public class ApplicationHooks {

    private static final DriverFactory driverFactory = new DriverFactory();
    private static final ConfigFileReader cfgReader = new ConfigFileReader();
    private static final Properties prop = cfgReader.readProperty();
    private static final String screenRecordingEnabled = prop.getProperty("screen_recording_enabled");
    private static final String screenRecordingFilenamePrefix = prop.getProperty("screen_recording_filename_prefix");

    @BeforeAll()
    public static void startWinAppDriverProcess() throws Exception {
        driverFactory.startWinAppDriver();
            if (screenRecordingEnabled.equalsIgnoreCase("true")) {
                ScreenRecorderUtil.startRecording(screenRecordingFilenamePrefix);
                System.out.println("Screen recording of test is enabled.");
            } else {
                System.out.println("Screen recording of test is disabled.");
            }
        }

    // Example calling methods for each of the WinApps (Recommended to use Feature level tags so that application will always be launched using the specific @Before hook

    @Before("@Calculator")
    public void startCalculatorApp() {
        driverFactory.startWinAppSession("calc_app_id");
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<----- Calculator Application has launched! ----->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }

    @Before("@Netflix")
    public void startNetflixApp() {
        driverFactory.startWinAppSession("netflix_app_id");
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<----- Netflix Application has launched! ----->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }

    @Before("@Notepad")
    public void startNotepadApp() {
        driverFactory.startWinAppSession("notepad_app_exe_path");
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<----- Notepad Application has launched! ----->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }

    @After(order = 0)
    public void closeWinAppSession() throws InterruptedException {
        Thread.sleep(2000); // Allow some static wait (can be made configurable
        DriverFactory.winApp.quit();
        System.out.println("Windows Application has been closed");
    }

    @After(order = 1)
    public void screenshotOnFailure(Scenario scenario) {
        if (scenario.isFailed()) {
            // take screenshot (Can be customised to take screenshots at all steps)
            String screenshotName = scenario.getName().replaceAll(" ", "_");
            byte[] sourcePath = ((TakesScreenshot) DriverFactory.winApp).getScreenshotAs(OutputType.BYTES);
            scenario.attach(sourcePath, "image/png", screenshotName);
        }
    }


    @AfterAll
    public static void closeWinAppDriverProcess() throws Exception {

        if (screenRecordingEnabled.equalsIgnoreCase("true")) {
            ScreenRecorderUtil.stopRecording();
            System.out.println("Screen recording of test run is successful.");
        } else {
            System.out.println("Screen recording did not take place.");
        }
//        ScreenRecorderUtil.stopRecording();
        driverFactory.closeWinAppDriver();
        driverFactory.cleanWadLogFile();
    }

    public void startWebBrowser() {
//TODO when transition from WinApp to WebApp version of same application is to be covered in the same test scenario. This can also be placed in DriverFactory class.
    }

}
