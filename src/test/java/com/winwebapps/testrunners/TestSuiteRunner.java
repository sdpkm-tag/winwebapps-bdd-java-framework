package com.winwebapps.testrunners;

import com.github.mkolisnyk.cucumber.runner.ExtendedCucumber;
import com.github.mkolisnyk.cucumber.runner.ExtendedCucumberOptions;
import com.winwebapps.utils.Helper;


import cucumber.api.CucumberOptions;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

import static com.winwebapps.utils.ConfigFileReader.properties;

@RunWith(ExtendedCucumber.class)
@ExtendedCucumberOptions(jsonReport = "target/cucumber-report/cucumber.json",
        retryCount = 3,
        detailedReport = true,
        detailedAggregatedReport = true,
        overviewReport = true,
        coverageReport = true,
        jsonUsageReport = "target/cucumber-report/cucumber-usage.json",
        usageReport = true,
        toPDF = true,
        excludeCoverageTags = {"@flaky"},
        includeCoverageTags = {"@passed"},
        outputFolder = "target/ext-cucumber-report")

@CucumberOptions(
        features = "src/test/resources/features",
        tags = "@WinApp or @WebApp",
        dryRun = false,
        plugin = {"pretty",
                "summary",
                "html:target/cucumber-report/cucumber-summary-report.html",
                "json:target/cucumber-report/cucumber.json",
//                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "timeline:target/cucumber-report/timeline/",
                "rerun:target/cucumber-report/failedtests.txt"},
        glue = {"com.winwebapps.stepdefs", "com.winwebapps.apphooks"},
        monochrome = true
)
public class FunctionalTestSuiteRunner {
//    @AfterClass
//    public static void extractTestReports() {
//        String cucumberReportOutputPath = properties.getProperty("test_report_path_cluecumber");
//        String extentReportOutputPath = properties.getProperty("test_report_path_extent");
//        String timeStampFormat = properties.getProperty("test_report_timestamp_format");
//        Helper.renameFileOrFilePathWithDateTimeStamp(cucumberReportOutputPath, timeStampFormat, "", "");
//        Helper.renameFileOrFilePathWithDateTimeStamp(extentReportOutputPath, timeStampFormat, "", "");
}

