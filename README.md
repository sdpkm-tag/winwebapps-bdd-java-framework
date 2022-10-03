# winwebapps-bdd-java-framework
A BDD based Test Framework for Testing multiple Windows and Web Apps using WinAppDriver, Selenium, Appium, Cucumber and Java. 

## Key tools used
- Java - JDK 8+
- Maven - As a build tool
- Cucumber - As a BDD tool
- Selenium - To interact with Web Elements
- Appium Java Client - to interact with Windows App Elements (internally uses selenium libraries)
- Test Reporting - Extent Spark, PDF, Cluecumber, Cucumber JVM
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
1. Takes screenshot on failure and attaches to the configured reports
2. Screen Recording (Feature to record the test run and save that in avi format. Caution! Keep cleaning the directory for better disk space)
3. Excel utility - Data driven (Testing run time data can be read from and written to excel - The excel file must exist as a pre-req)
4. Cache data / runtime read write into a text file (small runtime test data utility - recommended to use if data passing required within the same test scenario)
5. Supports testing of multiple win apps and web apps (Single framework to have package of all WinApps and associated WebApps - organised via packaging structure as shown for sample apps)
6. Test Reporting (Currently has cucumber basic html, cluecumber report, cucumber jvm report, extent-spark, extent-html and pdf reports enabled)
7. Launches Web browser (Can lauch different web browsers using `DriverFactory.java`) and made configurable.
8. Add dynamic waiting (Explicit, implict and local) - (Can be hardcoded to start for debug with but later to be configured to be read from config file using explicit or fluent waits). Also allows to use custom waits (explicit and fluent) from `ActionFactory.java` based on requirements. 
9. Can support parallel runs (Future Development) - Using Threadlocal for any parallel test needs (Has capability to extend the framework for parallel runs using multi-threading)
10. Distributed Runs (Future Development)- can be extended to set-up to run with node/server structure (Using of selenium grid, WD hub etc)
11. Has logging capability using Apache Log4j2 api

### Framework Technical Architecture:
- Following is a high level architecture of the BDD based test framework for Windows and Web Applications.

![image](https://user-images.githubusercontent.com/29902309/193563104-49b558ad-3e83-472e-9934-0eada5893d78.png)


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

- Cluecumber and cucumber jvm report is generated using `verify` goal of mvn so can be run either alone (`mvn clean verify`) or along with `test` as below example command.

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

### Additional Resources: 
>- Cluecumber report - https://github.com/trivago/cluecumber-report-plugin
>- Cucumber JVM Report - https://github.com/damianszczepanik/cucumber-reporting
>- WebDriverManager - https://bonigarcia.dev/webdrivermanager/
>- Extent Report adapter (Cucumber JVM 7) - https://ghchirp.online/3196/
>- PDF Reports - https://github.com/grasshopper7/cucumber-pdf-plugin

### Pre-requisites: 

- Ensure that `Developer Mode` is `enabled` on the machine(s) where Windows Applications are required to be interacted / tested using the WinAppDriver based framework. Developer settings can be found in Windows Settings menu.
- Ensure the exe file path for `WindowsApplicationDriver` is correct in `WinAppDriver.bat` file located in `src/test/resources/drivers/`. To set-up and install Windows Application driver refer to latest [official microsoft releases](https://github.com/Microsoft/WinAppDriver/releases) and install using instructions provided [here](https://github.com/microsoft/WinAppDriver#install--run-winappdriver).
- Ensure that directory exists `test-output/screen-recordings` in the root for screen-recording fies to save
- Configure (config.properties), all file paths and web browser driver exe paths
- Ensure that screen resolution of host machine (from where remote machines are accessed) are set to have standard resolution based on screen size and set to 100% scale for optimum performance and screen-recording. Recommended resolution is 1920x1080. 


### Guidelines:
- **Feature Level Tagging recommendations**
  - User Feature level tagging to connect with Cucumber Hooks defined in `ApplicationHooks.java` class. Example approach:
    - If testing for`Windows Applications` only, then use `@WinApp` tag at feature level along with application name tag which will start the application session, e.g. `@Calculator`
    - If Testing for `Web Applications` only, then use `@WebApp` tag at feature level along with application name tag, e.g. `@CalculatorWeb`. `@WebApp` tag will ensure that a web browser has been started in the background of tests steps run.
    - If Testing for both `Windows` and `Web` Applications, then use both `@WinApp` and `@WebApp` tags at feature level along with application name tag (e.g. `@Calculator`, it will ensure both Windows Application and Web Browser sessions are started in the background before steps are run. 
- **Recommended reports generation**
  - **Single runs**: In most cases, by default the framework will run using single junit runner class and generate all the configured reports in `test-output/reports` directory which will have runtime timestamp embedded to them.The recommended reoprts are:
    - Cucumber JVM Report (Uses cucumber json output, has merging capability of different outcome of same tests)
    - Cluecumber Report (Uses cucumber json output)
    - Extent ported HTML report (Uses cucumber jvm 7 extent adapter)
    - Extent spark report (Uses cucumber jvm 7 extent adapter)
    - PDF Report (Uses cucumber json output and cucumber pdf plugin)
  - **Multiple runs** (Using multiple runners, e.g. failed runs or parallel runs): In case of using running failed tests automatically, it is recommended to enable the Failed Test runner class in `maven-surefire-plugin` configuration in `pom.xml` file. Also ensure `rerunFailingTestsCount` is either set to 0 or disabled. The following reports are recommended if running failed tests automatically:
    - Cucumber JVM Report (Can be configured to merge the latest outcome of rerun tests in single report using `mergeFeaturesWithRetest` option in the `maven-cucumber-reporting` plugin configuration in `pom.xml` file)
    - Due to current constraints of dependency related to use of the latest cucumber 7.x.x in the framework, hence remaining reports (as follows) will show aggregated view outcome of all runs, including re-runs for failed tests. The extent adapter only has capability to create merged report upto JVM 6 of cucumber.
      - Cluecumber Report
      - Extent ported HTML report
      - Extent Spark report
      - PDF Report
  - **PDF Reports**: These can be configured either through `extent.properties` file by enabling PDF reporting (currently set to `false`) or via the specific plugin. It is recommended to use the PDF reports generation using `cucumber-pdf-plugin` in `pom.xml` file, since it is independent of Cucumber JVM version, whereas one generated through cucumber-jvm7 adapter would cause errors in case of reruns or parallel runs)
  - **Offline HTML Reports**: Extent ported and spark HTML reports can be configured to allow reports generation in offline mode using their specific config.xml files. To enable or disable this change the values for `offlineMode` parameter in `src/test/resources/config/extent-html-config.xml` and `src/test/resources/config/extent-spark-config.xml` files.  
  - **Note**: Currently all the reports are configured to create reports in separate directories within `test-output/reports/` using timestamps to allow archival of test run reports. 

### Future Enhancement TODOs:
- Parallel runs currently not enabled due to limitations on WinAppDriver (following its current recommendation - Ref [issue#592](https://github.com/microsoft/WinAppDriver/issues/592), however can be flexibly added using `ThreadLocal` and `maven surefire` or `maven failsafe` plugins. 
- For Dependency Injection `pico-container` can be used while modifying the framework. 
- Additional reporting using Serenity or Allure can be introduced (depending upon future requirements for publishing reports in CI or JIRA/confluence etc)
- Code quality checks can be added using SonarQube at later stages (dependeing upon requirements) 
