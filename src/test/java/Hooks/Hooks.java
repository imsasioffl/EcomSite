package Hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import utils.DriverUtils;

public class Hooks {
    @Before
    public void setup(){
        DriverUtils.initDriver();
    }

    @After
    public void tearDown(){
        DriverUtils.quitDriver();
    }

}
