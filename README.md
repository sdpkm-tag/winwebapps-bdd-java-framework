# winapps-bdd-java-framework
A BDD based Test Framework for Testing multip Windows Apps using WinAppDriver, Selenium, Appium, Cucumber and Java. 

## Key tools used
- Java - JDK 8+
- Maven - As a build tool
- Cucumber - As a BDD tool
- Selenium - To interact with Web Elements
- Appium Java Client - to interact with Windows App Elements (internally uses selenium libraries)
- Test Reporting - Extent Spark, PDF, Cluecumber
- Logging - Log4j2
- Excel Utils - Apache POI
- Test Run orders (orchestration) and Env conditioning set-up - Using Cucumber hooks 

### Finding Application IDs
Powershell example command to get the App ID: 

```powershell
> Get-StartApps find -name "Calculator"
```

```
Name       AppID
----       -----
Calculator Microsoft.WindowsCalculator_8wekyb3d8bbwe!App
```

### Framework elements:
1. Takes screenshot on failure and attaches to the report (can be any report, currently used cluecumber)
2. Screen Recording (Feature to record the test run and save that in avi format. Caution! Keep cleaning the directory for better disk space)
3. Excel utility - Data driven (Testing run time data can be read from and written to excel - The excel file must exist as a pre-req)
4. Cache data / runtime read write into a text file (small runtime test data utility - recommended to use if data passing required within the same test scenario)
5. Supports testing of multiple win apps and web apps (Single framework to have package of all WinApps and associated WebApps - organised via packaging structure as shown for sample apps)
6. Test Reporting (Currently has cucumber basic html, cluecumber report, extent-spark, extent-html and extent-pdf reports enabled)
7. Launches Web browser (Can lauch different web browsers using `DriverFactory.java`) and made configurable.
8. Add dynamic waiting (Explicit, implict and local) - (Can be hardcoded to start for debug with but later to be configured to be read from config file using explicit or fluent waits). Also allows to use custom waits (explicit and fluent) from `ActionFactory.java` based on requirements. 
9. Parallel runs (Future Development) - Using Threadlocal for any parallel test needs (Has capability to extend the framework for parallel runs using multi-threading)
10. Distributed Runs (Future Development)- can be extended to set-up to run with node/server structure (Using of selenium grid, WD hub etc)
11. Has logging capability using Apache Log4j2 api


### Running Cucumber Tests using Maven Surefire plugin



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

### Additional Helping commands and resources

- Run following command to check on versions for dependencies and plugins:
```
> mvn versions:display-dependency-updates
```
- Pom sorting

```
> mvn sortpom:sort
```

>- Additional Configuration help for cluecumber report - https://github.com/trivago/cluecumber-report-plugin
>- Additional documentation for WebDriverManager - https://bonigarcia.dev/webdrivermanager/
>- Additional documentation for Extent Report adapter (Cucumber JVM 7) - https://ghchirp.online/3196/

### Pre-requisites: 

- Ensure that `Developer Mode` is `enabled` on the machine(s) where Windows Applications are required to be interacted / tested using the WinAppDriver based framework. Developer settings can be found in Windows Settings menu.
- Ensure the exe file path for `WindowsApplicationDriver` is correct in `WinAppDriver.bat` file located in `src/test/resources/drivers/`
- Ensure that directory exists `test-output/screen-recordings` in the root for screen-recording fies to save
- Configure (config.properties), all file paths and driver exe paths


### Guidelines:
- Feature Level Tagging recommendations
  - User Feature level tagging to connect with Cucumber Hooks defined in `ApplicationHooks.java` class. Example approach:
    - If testing for`Windows Applications` only, then use `@WinApp` tag at feature level along with application name tag which will start the application session, e.g. `@Calculator`
    - If Testing for `Web Applications` only, then use `@WebApp` tag at feature level along with application name tag, e.g. `@CalculatorWeb`. `@WebApp` tag will ensure that a web browser has been started in the background of tests steps run.
    - If Testing for both `Windows` and `Web` Applications, then use both `@WinApp` and `@WebApp` tags at feature level along with application name tag (e.g. `@Calculator`, it will ensure both Windows Application and Web Browser sessions are started in the background before steps are run. 

### Future Enhancement TODOs:
- Remove or comment TestNG (calls and dependencies from project) for mvn test to show results of only cucumber test runs
- Parallel runs currently not enabled due to limitations on WinAppDriver (following its current recommendation - Ref [issue#592](https://github.com/microsoft/WinAppDriver/issues/592), however can be flexibly added using `ThreadLocal` and `maven surefire` or `maven failsafe` plugins. 
- For Dependency Injection `pico-container` can be used while modifying the framework. 
- Additional reporting using Serenity or Allure can be introduced (depending upon future requirements for publishing reports in CI or JIRA/confluence etc)
- Code quality checks can be added using SonarQube at later stages (dependeing upon requirements) 