package com.TestRunner;



import org.junit.runner.RunWith;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.BaseClass.BaseClass;

import io.cucumber.junit.Cucumber;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/features",
glue = {"com.StepDefinition","com.Hooks"},
dryRun = false,
plugin = {"pretty","summary","html:target/cucumber-reports/cucumber.html",
		"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"})

public class TestRunner   extends AbstractTestNGCucumberTests{
	BaseClass base = new BaseClass();

	@BeforeClass
	public  void Startup() {
	base.ChromebrowserLaunch();
	base.maximizeWindow();
	
	}
	 
   @AfterClass
  	public void end() {
	 base.quitchrome();
	}

}