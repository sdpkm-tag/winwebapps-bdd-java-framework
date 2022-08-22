package com.winwebapps.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogUtil {
    public static Logger log = LogManager.getLogger();

    public static void startTestScenario(String testScenarioName) {
        log.info("===================================== TEST SCENARIO START | " + testScenarioName + " =========================================");
    }

    public static void endTestScenario(String testScenarioName) {
        log.info("===================================== TEST SCENARIO END | " + testScenarioName + " =========================================");
    }
    public static void getTestScenarioStatus(String testScenarioName) {
        log.info("===================================== TEST SCENARIO STATUS | " + testScenarioName + " =========================================");
    }

    // TODO Tidy up following methods in case requried from logging perspective
    public static void info(String message) {
        log.info(message);
    }

    public static void warn(String message) {
        log.warn(message);
    }

    public static void error(String message) {
        log.error(message);
    }

    public static void fatal(String message) {
        log.fatal(message);
    }

    public static void debug(String message) {
        log.debug(message);
    }

    public static void trace(String message) {
        log.trace(message);
    }
}
