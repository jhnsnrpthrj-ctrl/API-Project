package com.omrbranch.hooks;

import com.omrbranch.utility.BaseClass;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class HooksClass extends BaseClass  {
	
	@Before
	public void beforeScenario() {
		browserLaunch();
		enterApplnUrl();
	}
	
	@After
	public void afterScenario(Scenario scenario) {
		scenario.attach(getScreenshotAsBytes(), "image/png", "screenshot");
//		quitBrowser();
		
	}

}

