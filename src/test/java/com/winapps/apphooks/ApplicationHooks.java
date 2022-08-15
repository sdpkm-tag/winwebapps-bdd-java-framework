package com.winapps.apphooks;


import com.winapps.factory.DriverFactory;
import com.winapps.utils.ConfigFileReader;
import com.winapps.utils.ScreenRecorderUtil;
import io.appium.java_client.windows.WindowsDriver;
import io.cucumber.java.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.Properties;

import com.winapps.utils.LogUtil;


public class ApplicationHooks {

    //    private static final DriverFactory driverFactory = new DriverFactory();
    private static final ConfigFileReader cfgReader = new ConfigFileReader();
    private static final Properties prop = cfgReader.readProperty();
    private static final String screenRecordingEnabled = prop.getProperty("screen_recording_enabled");
    private static final String screenRecordingFilenamePrefix = prop.getProperty("screen_recording_filename_prefix");

    private WebDriver webApp;
    private WindowsDriver winApp;
    private static DriverFactory driverFactory;

    @BeforeAll()
    public static void startWinAppDriverProcess() throws Exception {
        driverFactory = new DriverFactory();
        driverFactory.startWinAppDriver();
        if (screenRecordingEnabled.equalsIgnoreCase("true")) {
            ScreenRecorderUtil.startRecording(screenRecordingFilenamePrefix);
            LogUtil.info("Screen recording of test is enabled.");
        } else {
            LogUtil.info("Screen recording of test is disabled.");
        }
    }

    // Example calling methods for each of the WinApps (Recommended to use Feature level tags so that application will always be launched using the specific @Before hooks

    @Before("@Calculator")
    public void startCalculatorApp(Scenario scenario) {
        driverFactory.startWinAppSession("calc_app_id");
        LogUtil.startTestScenario(scenario.getName());
        LogUtil.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<----- Calculator Application has launched! ----->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }

    @Before("@Netflix")
    public void startNetflixApp(Scenario scenario) {
        driverFactory.startWinAppSession("netflix_app_id");
        LogUtil.startTestScenario(scenario.getName());
        LogUtil.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<----- Netflix Application has launched! ----->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }

    @Before("@Notepad")
    public void startNotepadApp(Scenario scenario) {
        driverFactory.startWinAppSession("notepad_app_exe_path");
        LogUtil.startTestScenario(scenario.getName());
        LogUtil.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<----- Notepad Application has launched! ----->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

    }

    //    Generic WinApps Start method (Not recommended to be used via hooks. TODO Can be moved to DriverFactory or removed)
    public static void startWindowsApp(String app_id_or_exe_path, Scenario scenario) {
        driverFactory.startWinAppSession(app_id_or_exe_path);
        LogUtil.startTestScenario(scenario.getName());
        LogUtil.info("Windows Application has launched with AppID or exe path provided as: " + app_id_or_exe_path);
    }

    //
//    @After(order = 0)
    @After(value = "@WinApp", order = 0)
    public static void closeWinAppSession(Scenario scenario) throws InterruptedException, IOException {
        Thread.sleep(2000); // Allow some static wait (can be made configurable

        if (DriverFactory.winApp != null) {
            DriverFactory.winApp.quit();
            DriverFactory.winApp = null;
        }

//        DriverFactory.winApp.quit();
        Thread.sleep(2000);
        LogUtil.info("Windows Application has been closed");
        LogUtil.getTestScenarioStatus(scenario.getStatus().toString());
    }

    @After(value = "@WebApp or @WinApp", order = 1)
    public void screenshotOnFailure(Scenario scenario) {
        if (scenario.isFailed()) {
            // take screenshot (TODO Can be customised to take screenshots at all steps - Use of @AfterStep hook recommended)
            String screenshotName = scenario.getName().replaceAll(" ", "_");
            if (webApp != null) {
                byte[] sourcePath = ((TakesScreenshot) webApp).getScreenshotAs(OutputType.BYTES);
                scenario.attach(sourcePath, "image/png", screenshotName);
            } else if (DriverFactory.winApp != null) {
                byte[] sourcePath = ((TakesScreenshot) DriverFactory.winApp).getScreenshotAs(OutputType.BYTES);
                scenario.attach(sourcePath, "image/png", screenshotName);
            }
            LogUtil.info("Screenshot of Test failure is attached");
        }
    }

    @AfterAll
    public static void closeWinAppDriverProcess() throws Exception {

        if (screenRecordingEnabled.equalsIgnoreCase("true")) {
            ScreenRecorderUtil.stopRecording();
            LogUtil.info("Screen recording of test run is successful.");
        } else {
            LogUtil.info("Screen recording did not take place.");
        }
//        ScreenRecorderUtil.stopRecording();
        driverFactory.closeWinAppDriver();
        driverFactory.cleanWadLogFile();
    }

    @Before("@WebApp")
    public void launchWebBrowser(Scenario scenario) {
//TODO when transition from WinApp to WebApp version of same application is to be covered in the same test scenario. This can also be placed in DriverFactory class.
        // FIXME to be correctly implemented when webbrowser session is to be launched in the same session of scenario running steps with Window Applications first.
        String browserName = prop.getProperty("browser");
        webApp = driverFactory.startWebBrowserDriver(browserName);
        LogUtil.startTestScenario(scenario.getName());
    }

    @After(value = "@WebApp", order = 0)
    public void closeWebBrowser(Scenario scenario) throws InterruptedException {
        // FIXME to be correctly implemented when webbrowser session is to be launched in the same session of scenario running steps with Window Applications first.
        Thread.sleep(2000);
        webApp.quit();
        LogUtil.endTestScenario(scenario.getName());
        LogUtil.getTestScenarioStatus(scenario.getStatus().toString());

    }
}


