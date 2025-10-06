package com.clarusways.tests;

import com.clarusways.utilities.DriverManager;
import com.clarusways.utilities.ConfigReader;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;

public class T05_TestBase {

    @BeforeMethod
    public void setUp() {
        DriverManager.getDriver().get(ConfigReader.getUrl());
    }

    @AfterSuite
    public void tearDown() {
        DriverManager.closeDriver();
    }
}