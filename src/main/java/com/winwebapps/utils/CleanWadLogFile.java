package com.winwebapps.utils;

import com.winwebapps.factory.DriverFactory;

public class CleanWadLogFile {

   public static void main(String[] args) throws InterruptedException {

       DriverFactory driverFactory = new DriverFactory();
       driverFactory.cleanWadLogFile();
    }
}
