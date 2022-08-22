package com.winwebapps.stepdefs.sample.notepad;

import com.winwebapps.factory.DriverFactory;
import com.winwebapps.pageobjects.sample.notepad.NotepadScreen;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class NotepadStepDefinitions {

    private final NotepadScreen notepadScreen = new NotepadScreen(DriverFactory.winApp);

    @Given("I have launched Notepad application")
    public void iHaveLaunchedOnNotepadScreen() throws InterruptedException {
        Thread.sleep(2000);
    }

    @And("I select {string} option")
    @When("I click on {string} button")
    @When("I click on {string} menu option")
    public void iClickOnMenuOption(String selectedMenuOrButtonOption) throws InterruptedException {
        notepadScreen.selectMenuOptionAndButton(selectedMenuOrButtonOption);
    }

    @Then("I view {string} text appearing on screen")
    public void iViewTextAppearingOnScreen(String titleString) {
        String actualTitleText = notepadScreen.getAboutNotepadWindowTitle(titleString);
        Assert.assertEquals(titleString, actualTitleText);
    }

    @Then("Mouse cursor goes to the {string} window on notepad application")
    public void mouseCursorGoesToTheWindowOnNotepadApplication(String activeWindowText) {
        String actualActiveWindowText = notepadScreen.getActiveWindow();
        System.out.println("Actual Active Window Text is: "+activeWindowText);
        Assert.assertEquals(activeWindowText, actualActiveWindowText);
    }


    @When("I enter text {string}")
    public void iEnterText(String enteredText) {
        notepadScreen.enterTextInNotepad(enteredText);
    }

    @And("I enter file name as {string}")
    public void iEnterFileNameAs(String fileNameText) {
        notepadScreen.keyFileNameToSave(fileNameText);
    }

    @And("I click on {string} menu option from menu bar")
    public void iClickOnMenuOptionFromMenuBar(String menuBarOption) {
        notepadScreen.selectFileSaveAsOption(menuBarOption);
    }
}
