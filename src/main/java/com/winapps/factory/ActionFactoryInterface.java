package com.winapps.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * This interface is implemented by main ActionFactory.class
 */
public interface ActionFactoryInterface {
//TODO To evolve and improve the layout / signature of methods as script development progresses. Aim is to have all methods in selenium customised to fit frameworks need. Following example shows how different waits can be configured and overridden for use in step defs.

    public void fluentWait(WebDriver driver, WebElement element, int timeOut);

    public void implicitWait(WebDriver driver, int timeOut);

    public void explicitWait(WebDriver driver, WebElement element, int timeOut);
}
