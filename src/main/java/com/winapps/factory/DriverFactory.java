package com.winapps.factory;

import com.winapps.utils.ConfigFileReader;
import com.winapps.utils.FileCleanser;
import com.winapps.utils.LogUtil;
import io.appium.java_client.windows.WindowsDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Time;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class DriverFactory {

    private static final ConfigFileReader cfgReader = new ConfigFileReader();
    private static final Properties prop = cfgReader.readProperty();

    private static final String IEDRIVER_EXE_PATH = prop.getProperty("iedriver_exe_path");
    private static final String GECKODRIVER_EXE_PATH = prop.getProperty("geckodriver_exe_path");
    private static final String EDGEDRIVER_EXE_PATH = prop.getProperty("edgedriver_exe_path");
    private static final String CHROMEDRIVER_EXE_PATH = prop.getProperty("chromedriver_exe_path");
    public static WindowsDriver winApp;
    public static WebDriver webApp;

    private static Process wadProcess;

    public static ThreadLocal<WebDriver> threadLocal = new ThreadLocal<>();

    public void startWinAppDriver() throws IOException, InterruptedException {

        //Start of WinAppDriver
        String wadBatFilePath = cfgReader.readProperty().getProperty("wad_batfile_path");
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(wadBatFilePath);
            processBuilder.inheritIO();
            wadProcess = processBuilder.start();
            wadProcess.waitFor(Long.parseLong(prop.getProperty("wad_startup_wait")), TimeUnit.MILLISECONDS);
            if (wadProcess.isAlive()) {
                LogUtil.info("Windows Application Driver has successfully started and running in the background!");
            } else {
                LogUtil.error("Windows Application Driver failed to start!");
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * This method will create a new session of Windows App and attached it to the WinAppDriver running in the background.
     *
     * @param app_Id Takes Application ID for UWP apps or executable file path for Win32 applications
     * @return It will return a WindowsDriver object to be used in the scenario / workflow of test.
     */
    public WindowsDriver startWinAppSession(String app_Id) {

        String appId = prop.getProperty(app_Id);
        String wadUrl = prop.getProperty("wad_protocol") + "://" + prop.getProperty("wad_host_ip") + ":" + prop.getProperty("wad_port") + "/wd/hub";
        DesiredCapabilities descap = new DesiredCapabilities();
        descap.setCapability("app", appId);
        descap.setCapability("platformName", "Windows");
        descap.setCapability("deviceName", "WindowsPC");

        try {
            winApp = new WindowsDriver(new URL(wadUrl), descap);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        winApp.manage().timeouts().implicitlyWait(Long.parseLong(prop.getProperty("implicit_wait_time")), TimeUnit.MILLISECONDS);
        winApp.manage().window().maximize();
        return winApp;
    }

    public void closeWinAppDriver() throws IOException, InterruptedException {
        //TODO Write code for closing winAppDriver and call this method to be used in hooks to run as teardown after all tests are run for winapps
        Thread.sleep(Long.parseLong(prop.getProperty("wad_closedown_wait"))); // Configurable static wait time
        Runtime.getRuntime().exec("taskkill /F /IM WinAppDriver.exe");
        LogUtil.info("Windows Application Driver has successfully shutdown!");
    }

    public void cleanWadLogFile() throws InterruptedException {
        Thread.sleep(Long.parseLong(prop.getProperty("wad_fileclean_preset_wait"))); // A a static wait to allow the file to be freely accessed for manipulation and it can be configured to be read from config.properties file
        FileCleanser fileCleanser = new FileCleanser();
        fileCleanser.removeUnwantedChars(prop.getProperty("wad_logfile_path"), "\\x00", ""); // Removes NUL characters
        fileCleanser.removeUnwantedChars(prop.getProperty("wad_logfile_path"), "(?m)^[ \t]*\r?\n", ""); // Removes extra lines and spaces
        System.out.println("WindowsApplication Log file is available");
    }

    public WebDriver startWebBrowserDriver(String browserName) {
        //TODO Following code might need impacting and tweaking against version of browsers available on test machine. Change could be to use local drivers set-up (example shown commented out below)

        System.out.println("Selected browser is: " + browserName);
        if (browserName.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            webApp = new ChromeDriver();
//            threadLocal.set(new ChromeDriver());
        } else if (browserName.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            webApp = new FirefoxDriver();
//            threadLocal.set(new FirefoxDriver());
        } else if (browserName.equals("internetexplorer")) {
            WebDriverManager.iedriver().setup();
            webApp = new InternetExplorerDriver();
//            threadLocal.set(new InternetExplorerDriver());
        } else if (browserName.equals("opera")) {
            WebDriverManager.operadriver().setup();
            webApp = new OperaDriver();
//            threadLocal.set(new OperaDriver());
        } else if (browserName.equals("edge")) {
            WebDriverManager.edgedriver().setup();
            webApp = new EdgeDriver();
//            threadLocal.set(new EdgeDriver());
        } else {
            System.out.println("Please pass the correct browser value. " + browserName + " is not correct value chosen or configured.");
        }
// Below section to be used when using locally set browser drivers (above will then need to be removed or commented out)

//        if (browserName.equals("chrome")) {
//            System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/webdrivers/chromedriver.exe");
//            webApp = new ChromeDriver();
//        } else if (browserName.equals("firefox")) {
//        } else if (browserName.equals("internetexplorer")) {
//        } else if (browserName.equals("opera")) {
//        } else if (browserName.equals("edge")) {
//        } else {
//            System.out.println("Please pass the correct browser value. " + browserName + " is not correct value chosen or configured.");
//        }

//        getWebDriver().manage().deleteAllCookies();
//        getWebDriver().manage().window().maximize();
//        return getWebDriver();
        webApp.manage().deleteAllCookies();
        webApp.manage().window().maximize();
        return webApp;
    }

    public static synchronized WebDriver getWebDriver() {
        //TODO Following code might need impacting and tweaking against version of browsers available on test machine
        return threadLocal.get();
    }

}