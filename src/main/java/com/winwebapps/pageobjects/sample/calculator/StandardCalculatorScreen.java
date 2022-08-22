package com.winwebapps.pageobjects.sample.calculator;

import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.By;
import com.winwebapps.factory.DriverFactory;

public class StandardCalculatorScreen {

    private final WindowsDriver windowsDriver;
    /*
    Contains 3 sections (Using Encapsulation, locators remain private, but page actions will be public)
    1. By Locators
    2. Constructors
    3. Page Actions
     */

    // 1. By Locators:
    private final By standardCalculator = By.name("Standard Calculator mode");
    private final By oneBtn = By.name("One");
    private final By twoBtn = By.name("Two");
    private final By threeBtn = By.name("Three");
    private final By fourBtn = By.name("Four");
    private final By fiveBtn = By.name("Five");
    private final By sixBtn = By.name("Six");
    private final By sevenBtn = By.name("Seven");
    private final By eightBtn = By.name("Eight");
    private final By nineBtn = By.name("Nine");
    private final By zeroBtn = By.name("Zero");
    private final By eaualsBtn = By.name("Equals");
    private final By positiveNegativeBtn = By.name("Positive Negative");
    private final By decimalBtn = By.name("Decimal Separator");
    private final By divideByBtn = By.name("Divide by");
    private final By multiplyByBtn = By.name("Multiply by");
    private final By plusBtn = By.name("Plus");
    private final By minusBtn = By.name("Minus");
    private By displayResults = By.xpath("//*[starts-with(@Name, 'Display is ')]");

    //2. Constructor of the Page class:
    public StandardCalculatorScreen(WindowsDriver windowsDriver) {
        this.windowsDriver = DriverFactory.winApp;
        //TODO Implementation of page object constructor could be slightly different for web driver testing - In case of WinApps constructor might never be utilised
    }

    //3. Page Actions: Features (behaviour) of the page the form of methods:
    public void clickOnOne() {
        windowsDriver.findElement(oneBtn).click();
    }

    public void clickOnTwo() {
        windowsDriver.findElement(twoBtn).click();
    }

    public void clickOnThree() {
        windowsDriver.findElement(threeBtn).click();
    }

    public void clickOnFour() {
        windowsDriver.findElement(fourBtn).click();
    }

    public void clickOnFive() {
        windowsDriver.findElement(fiveBtn).click();
    }

    public void clickOnSix() {
        windowsDriver.findElement(sixBtn).click();
    }

    public void clickOnSeven() {
        windowsDriver.findElement(sevenBtn).click();
    }

    public void clickOnEight() {
        windowsDriver.findElement(eightBtn).click();
    }

    public void clickOnNine() {
        windowsDriver.findElement(nineBtn).click();
    }

    public void clickOnZero() {
        windowsDriver.findElement(zeroBtn).click();
    }

    public void clickOnEqualsTo() {
        windowsDriver.findElement(eaualsBtn).click();
    }

    public void clickOnPlus() {
        windowsDriver.findElement(plusBtn).click();
    }

    public void clickOnMinus() {
        windowsDriver.findElement(minusBtn).click();
    }

    public void clickOnDivideBy() {
        windowsDriver.findElement(divideByBtn).click();
    }

    public void clickOnMultiplyBy() {
        windowsDriver.findElement(multiplyByBtn).click();
    }

    public void clickOnDecimalSeparator() {
        windowsDriver.findElement(decimalBtn).click();
    }

    public void clickOnPositiveNegative() {
        windowsDriver.findElement(positiveNegativeBtn).click();
    }

    public String getCalculatorModeTitle() {
        return windowsDriver.findElement(standardCalculator).getAttribute("Name");
    }

    public String getCalculatorResultsValue() {
        return windowsDriver.findElement(displayResults).getText();
    }

}
