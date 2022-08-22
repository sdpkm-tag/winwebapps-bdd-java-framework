package com.winapps.stepdefs.sample.winweb;

import com.winapps.apphooks.ApplicationHooks;
import com.winapps.factory.DriverFactory;
import com.winapps.pageobjects.sample.onlinecalc.BasicCalculatorPage;
import com.winapps.pageobjects.sample.web.LoginPage;
import com.winapps.utils.ConfigFileReader;
import com.winapps.utils.Helper;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import static com.winapps.utils.ConfigFileReader.properties;

public class OnlineBasicCalcStepDef {

    private ApplicationHooks applicationHooks = new ApplicationHooks();
    private BasicCalculatorPage basicCalculatorPage = new BasicCalculatorPage(DriverFactory.webApp);

    @Then("I note down result and close Windows Calculator App and Open WebApp")
    public void i_note_down_result_and_close_windows_calculator_app_and_open_web_app() throws InterruptedException {
        DriverFactory.webApp.get(properties.getProperty("calc_basic_web_app"));
        Thread.sleep(5000);
    }

    @When("I did a sum {string} operation of {string} and {string} and clicked on {string} button")
    public void i_did_a_sum_operation_of_and_and_clicked_on_button(String operation, String operand1, String operand2, String result) throws InterruptedException {
        basicCalculatorPage.clickOnButton("4");
        Thread.sleep(2000);
        basicCalculatorPage.clickOnButton("+");
        Thread.sleep(2000);
        basicCalculatorPage.clickOnButton("3");
        Thread.sleep(2000);
        basicCalculatorPage.clickOnButton("=");
        Thread.sleep(2000);

    }

    @Then("I get {string} as result and this is same as result shown by Windows calculator App")
    public void i_get_as_result_and_this_is_same_as_result_shown_by_windows_calculator_app(String expectedResult) throws Throwable {
        String webResultValue = basicCalculatorPage.getDisplayValue();
        String winAppSavedValue = Helper.getDataFromSavedTextFile("calresult.txt").trim();
        Assert.assertEquals("Actual displayed value on web didn't match with saved value from windows app!", winAppSavedValue, webResultValue);
        Assert.assertEquals("Actual displayed value on web didn't match with expected value!", "Display is " + expectedResult, webResultValue);
    }

}
