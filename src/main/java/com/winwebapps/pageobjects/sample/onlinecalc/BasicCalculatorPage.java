package com.winwebapps.pageobjects.sample.onlinecalc;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasicCalculatorPage {

    private final WebDriver driver;

    // Page Element Locators
    private final By four = By.name("four");

//    private final By four = By.xpath("/html[1]/body[1]/div[1]/div[1]/main[1]/div[3]/div[1]/div[2]/form[1]/div[5]/input[1]");

    private final By three = By.name("three");
    private final By plus = By.name("add");
    private final By result = By.name("calculate");
    private final By display = By.id("display");


    // Page Constructor

    public BasicCalculatorPage(WebDriver driver) {
        this.driver = driver;
    }

    // Page Actions

    public void clickOnButton(String buttonName) {
        switch (buttonName) {
            case "4":
                driver.findElement(four).click();
                break;
            case "3":
                driver.findElement(three).click();
                break;
            case "+":
                driver.findElement(plus).click();
                break;
            case "=":
                driver.findElement(result).click();
                break;
            default:
                System.out.println("Keyed input is not valid!");
                break;

        }
    }

    public String getDisplayValue(){
        String value = "Display is "+ driver.findElement(display).getAttribute("value");
        return value;
    }
}
