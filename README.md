# winapps-bdd-java-framework
Test Framework for Testing Windows Apps using WinAppDriver, Selenium, Appium & Java.

Powershell example command to get the App ID: 

```powershell
> Get-StartApps find -name "Calculator"
```

```
Name       AppID
----       -----
Calculator Microsoft.WindowsCalculator_8wekyb3d8bbwe!App
```

Pom sorting

```
> mvn sortpom:sort
```
Framework elemts:
1. Takes Screenshots on failure and attaches to the report (can be any report, currently used cluecumber)
2. Screen Recording (Feature to record the test run and save that in avi format. Caution! Keep cleaning the directory for better disk space)
3. Excel utility - Data driven (Testing run time data can be read from and written to excel - The excel file must exist as a pre-req)
4. Cache data / runtime read write into a text file (small runtime test data utility - recommended to use if data passing required within the same test scenario)
5. Supports multiple win apps and web apps (Single framework to have package of all WinApps and associated WebApps - organised via packaging structure as shown for sample apps)
6. Test Reporting (Currently has cucumber basic html, cluecumber report, extent-spark, extent-html and extent-pdf reports enabled)
7. Launches Web browser (Can lauch different web browsers using DriverFactory class) using configuration
8. Add dynamic waiting (Explicit, implict and local) - (Can be hardcoded to start with but later to be configured to be read from config file using explicit or fluent waits)
9. Parallel runs (Future Development) - Using Threadlocal for any parallel test needs (Has capability to extend the framework for parallel runs using multi-threading)
10. Distributed Runs (Future Development)- can be extended to set-up to run with node/server struture (Using of selenium grid, WD hub etc)
11. Has logging capability using Apache Log4j2 api

Running Cucumber Tests from Maven

- Simple mvn test (This will use all tags defined in the `TestSuiteRunner.java` file)
```
> mvn clean test
```

- Passing tags in mvn command to run specific tests (recommended to use tags at feature or scenario levels, they can be combined to make selection conditional though using `and`, `or` and `not` operators.). Following is example command to run all `@Notepad` feature tests.
```
> mvn clean test "-DargLine=-Dcucumber.filter.tags='@Notepad'"
```
or 

Following example will run only `@regression` tests from `@Calculcator` feature file(s).

```
> mvn clean verify test -D"cucumber.filter.tags=@Calculator and @regression"
```

- Cluecumber report is generated using `verify` goal of mvn so can be run either alone (`mvn clean verify`) or along with `test` as below example command.

```
> mvn clean verify test
```

Run following command to check on versions for dependencies and plugins:
```
> mvn versions:display-dependency-updates
```

>- Additional Configuration help for cluecumber report - https://github.com/trivago/cluecumber-report-plugin
>- Additional documentation for WebDriverManager - https://bonigarcia.dev/webdrivermanager/
>- Additional documentation for Extent Report adapter (Cucumber JVM 7) - https://ghchirp.online/3196/

Pre-requisites: 

- Ensure the exe file path for WindowsApplicationDriver is correct in WinAppDriver.bat file located in `src/test/resources/drivers/`
- Ensure that directory exists `test-output/screen-recordings` in the root for screen-recording fies to save
- Configure (config.properties), all file paths and driver exe paths

Future Enhancement TODOs:
- Remove or comment TestNG (calls and dependencies from project) for mvn test to show results of only cucumber test runs
- Framework to be extended to be used for linked WebApplications for testing with the same test scenario
- Parallel runs not required based on application testing iterations, however in future runs it can be added to the framework
- For Dependency Injection `pico-container` can be used while modifying the framework. 
- Additional reporting using Serenity or Allure can be introduced (depending upon future requirements for publishing reports in CI or JIRA/confluence etc)
- Code quality checks can be added using SonarQube at later stages (dependeing upon requirements) 