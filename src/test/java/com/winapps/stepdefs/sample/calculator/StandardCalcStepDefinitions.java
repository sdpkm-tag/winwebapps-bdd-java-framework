package com.winapps.stepdefs.sample.calculator;

import com.winapps.pageobjects.sample.calculator.StandardCalculatorScreen;
import com.winapps.factory.DriverFactory;
import com.winapps.utils.Helper;
import com.winapps.utils.LogUtil;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class StandardCalcStepDefinitions {

    private final StandardCalculatorScreen standardCalculatorScreen = new StandardCalculatorScreen(DriverFactory.winApp);

    @Given("I am on standard calculator screen")
    public void i_am_on_standard_calculator_screen() throws InterruptedException {
        Thread.sleep(5000);
        String actualText = standardCalculatorScreen.getCalculatorModeTitle();
        System.out.println("I am on the calculator screen with mode: " + actualText);
        Assert.assertEquals("Standard Calculator mode", actualText);
    }

    @When("I press on {string} button to get the calculation result")
    @And("I have clicked on {string} button")
    @When("I clicked on {string} button")
    public void i_clicked_on_button(String operator) throws InterruptedException {
        Thread.sleep(2000);
        switch (operator) {
            case "+":
                standardCalculatorScreen.clickOnPlus();
                break;
            case "-":
                standardCalculatorScreen.clickOnMinus();
                break;
            case "x":
                standardCalculatorScreen.clickOnMultiplyBy();
                break;
            case "÷":
                standardCalculatorScreen.clickOnDivideBy();
                break;
            case "=":
                standardCalculatorScreen.clickOnEqualsTo();
                break;
            default:
                System.out.println("Keyed input is not a valid operator");
                break;
        }
        System.out.println("Keyed operator is: " + operator);
    }

    @When("I press result {string} button")
    public void i_press_result_button(String string) {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("I am still here - Calculator");
    }

    @Then("<output> should be displayed on the result screen")
    @Then("{string} should be displayed on the result screen")
    public void shouldBeDisplayedOnTheResultScreen(String expectedResult) throws Throwable {
        Thread.sleep(2000);
        String actualResult = standardCalculatorScreen.getCalculatorResultsValue();
        Helper.saveDataInATextFile(actualResult, "calresult.txt");
        LogUtil.info("Result saved from windows calculator app to the text file as: " + actualResult);
        String errorMessage = "Expected value of element is 'Display is " + expectedResult + "' but actual value of element is '" + actualResult + "'";
        Assert.assertTrue("Values don't match: " + errorMessage, actualResult.contains(expectedResult));
        DriverFactory.winApp.quit();
    }

    @Given("I have keyed {string} into the calculator")
    @When("I clicked {string} into the calculator")
    @When("I keyed {string} into the calculator")
    public void iKeyedIntoTheCalculator(String keyedNumbers) {
        char[] character = keyedNumbers.toCharArray();
        for (char c : character) {
            switch (c) {
                case '1':
                    standardCalculatorScreen.clickOnOne();
                    break;
                case '2':
                    standardCalculatorScreen.clickOnTwo();
                    break;
                case '3':
                    standardCalculatorScreen.clickOnThree();
                    break;
                case '4':
                    standardCalculatorScreen.clickOnFour();
                    break;
                case '5':
                    standardCalculatorScreen.clickOnFive();
                    break;
                case '6':
                    standardCalculatorScreen.clickOnSix();
                    break;
                case '7':
                    standardCalculatorScreen.clickOnSeven();
                    break;
                case '8':
                    standardCalculatorScreen.clickOnEight();
                    break;
                case '9':
                    standardCalculatorScreen.clickOnNine();
                    break;
                case '0':
                    standardCalculatorScreen.clickOnZero();
                    break;
                case '.':
                    standardCalculatorScreen.clickOnDecimalSeparator();
                    break;
                default:
                    System.out.println("Keyed input is not a valid character");
                    break;
            }
        }
        System.out.println("Keyed number is: " + keyedNumbers);
    }
}
