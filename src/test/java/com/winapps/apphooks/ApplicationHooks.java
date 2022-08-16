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
    @Before(order = 0)
    public void logScenarioStart(Scenario scenario) {
        LogUtil.startTestScenario(scenario.getName());
    }

    @Before(value = "@WebApp", order = 1)
    public void launchWebBrowser(Scenario scenario) {
        // TODO to be tested thoroughly and correctly implemented when webbrowser session is to be launched in the same session of scenario running steps with Window Applications first.
        String browserName = prop.getProperty("browser");
        webApp = driverFactory.startWebBrowserDriver(browserName);
        LogUtil.info("Web Browser '" + browserName + "' launched successfully for scenario: " + scenario.getName() + " with tags " + scenario.getSourceTagNames());
    }

    @Before(value = "@Calculator", order = 2)
    public void startCalculatorApp(Scenario scenario) {
        driverFactory.startWinAppSession("calc_app_id");
        LogUtil.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<----- Calculator Application has launched! ----->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }

    @Before(value = "@Netflix", order = 2)
    public void startNetflixApp(Scenario scenario) {
        driverFactory.startWinAppSession("netflix_app_id");
        LogUtil.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<----- Netflix Application has launched! ----->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }

    @Before(value = "@Notepad", order = 2)
    public void startNotepadApp(Scenario scenario) {
        driverFactory.startWinAppSession("notepad_app_exe_path");
        LogUtil.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<----- Notepad Application has launched! ----->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }

    @After(value = "@WebApp or @WinApp", order = 2)
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

    @After(value = "@WinApp", order = 1)
    public static void closeWinAppSession(Scenario scenario) throws InterruptedException, IOException {
        Thread.sleep(Long.parseLong(prop.getProperty("winapp_session_teardown_wait")));
        if (DriverFactory.winApp != null) {
            DriverFactory.winApp.quit();
            DriverFactory.winApp = null;
        }
        LogUtil.info("Windows Application has been closed");
    }

    @After(value = "@WebApp", order = 1)
    public void closeWebBrowser(Scenario scenario) throws InterruptedException {
        // TODO to be tested thoroughly and correctly implemented when webbrowser session is to be launched in the same session of scenario running steps with Window Applications first.
        Thread.sleep(Long.parseLong(prop.getProperty("webapp_session_teardown_wait")));
        if (webApp != null) {
            webApp.quit();
            webApp = null;
        }
        LogUtil.info("Web Browser has been closed");
    }

    @After(order = 0)
    public void logScenarioEndAndStatus(Scenario scenario) {
        LogUtil.endTestScenario(scenario.getName());
        LogUtil.getTestScenarioStatus(scenario.getStatus().toString());
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

    // Generic WinApp Start method (Currently not recommended to be used via hooks). TODO Check if generic method cab ve be moved to DriverFactory or removed altogether.
    public static void startWindowsApp(String app_id_or_exe_path, Scenario scenario) {
        driverFactory.startWinAppSession(app_id_or_exe_path);
        LogUtil.startTestScenario(scenario.getName());
        LogUtil.info("Windows Application has launched with AppID or exe path provided as: " + app_id_or_exe_path);
    }

    // Generic WebApps Start method (Currently not recommended to be used via hooks). TODO Check if generic method cab ve be moved to DriverFactory or removed altogether.
    public void launchWebBrowser() {
        //TODO when transition from WinApp to WebApp version of same application is to be covered in the same test scenario. This can also be placed in DriverFactory class.
        // FIXME to be correctly implemented when webbrowser session is to be launched in the same session of scenario running steps with Window Applications first.
        String browserName = prop.getProperty("browser");
        webApp = driverFactory.startWebBrowserDriver(browserName);
    }
}


