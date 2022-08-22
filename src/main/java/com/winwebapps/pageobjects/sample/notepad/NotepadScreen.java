package com.winwebapps.pageobjects.sample.notepad;

import com.winwebapps.factory.DriverFactory;
import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.By;

public class NotepadScreen {

    private final WindowsDriver windowsDriver;
//    @FindBy(name = "Text Editor")
//    private WindowsElement textEditor;
//
//    @FindBy(name = "Help")
//    private WindowsElement helpMenu;
//    @FindBy(name = "About Notepad")
//    private WindowsElement aboutNoepad;
//
//    @FindBy(name = "OK")
//    private WindowsElement oK;

    private final By textEditor = By.name("Text Editor");
    private final By helpMenu = By.name("Help");
    private final By fileMenu = By.name("File");

    private final By aboutNotepadOption = By.name("About Notepad");
//    private final By saveAsOption = By.name("Save As...");
//private final By saveAsOption = By.xpath("//*{@Name=”Save As...”]");

    private final By menuBar = By.id("MenuBar");
private final By saveAsOption = By.name("Save As...");
    private final By oKBtn = By.name("OK");
    private final By saveBtn = By.name("Save");
    private final By aboutNotepadTitleString = By.name("Microsoft Windows");

    private final By fileNameToSaveString = By.name("File Name");

    public NotepadScreen(WindowsDriver windowsDriver) {
        this.windowsDriver = DriverFactory.winApp;
        //TODO Implementation of page object constructor could be slightly different for web driver testing - In case of WinApps constructor might never be utilised
    }

    public void selectMenuOptionAndButton(String menuAndButtonOption) throws InterruptedException {
        switch (menuAndButtonOption) {
            case "Help":
                windowsDriver.findElement(helpMenu).click();
                break;
            case "About Notepad":
                windowsDriver.findElement(aboutNotepadOption).click();
                break;
            case "OK":
                windowsDriver.findElement(oKBtn).click();
                break;
            case "File":
                System.out.println("File name menu is: "+windowsDriver.findElement(fileMenu));
                windowsDriver.findElement(fileMenu).click();
                break;
            case "Save As...":
                Thread.sleep(2000);
                windowsDriver.findElement(saveAsOption).click();
                break;
            case "Save":
                windowsDriver.findElement(saveAsOption).click();
                break;
            default:
                System.out.println("Not a valid input");
                break;
        }
    }

    public String getActiveWindow(){
        return windowsDriver.findElement(textEditor).getAttribute("Name");
    }
    public String getAboutNotepadWindowTitle(String titileText) {
        return windowsDriver.findElement(aboutNotepadTitleString).getText();
    }
    public void enterTextInNotepad(String textToEnter){
        windowsDriver.findElement(textEditor).sendKeys(textToEnter);
    }

    public void keyFileNameToSave(String fileNameToSave){
        windowsDriver.findElement(fileNameToSaveString).sendKeys(fileNameToSave);
    }

    public void selectFileSaveAsOption(String entryPoint){
        windowsDriver.findElement(menuBar).click();
        windowsDriver.findElement(fileMenu).click();
        windowsDriver.findElement(saveAsOption).click();
    }
}
