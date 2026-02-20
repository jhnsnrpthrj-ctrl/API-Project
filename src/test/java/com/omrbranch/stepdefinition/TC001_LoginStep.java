package com.omrbranch.stepdefinition;

import java.awt.AWTException;

import org.junit.Assert;

import com.omrbranch.manager.PageObjectManager;
import com.omrbranch.utility.BaseClass;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TC001_LoginStep extends BaseClass {

	PageObjectManager pom = new PageObjectManager();

	@Given("User is on the OMR Branch hotel page")
	public void userIsOnTheOMRBranchHotelPage() {

		
	}

	@When("User enters {string} and {string}")
	public void userEntersAnd(String username, String password) {

		pom.getLoginPage().login(username, password);
	}

	@Then("User should verify success message after login {string}")
	public void userShouldVerifySuccessMessageAfterLogin(String expectedLoginMessage) {

		String actLoginMessageText = pom.getExploreHotelPage().getLoginMessageText();
		Assert.assertEquals("verify after login success message", expectedLoginMessage, actLoginMessageText);
	}

	@When("User enters {string} and {string} with enter key")
	public void userEntersAndWithEnterKey(String username, String password) throws AWTException {
		pom.getLoginPage().loginWithEnterKey(username, password);
	}

	@Then("User should verify error message after login {string}")
	public void userShouldVerifyErrorMessageAfterLogin(String expectedLoginErrorMessage) {
		
		String loginErrorMessageText = pom.getLoginPage().getLoginErrorMessageText();
		Assert.assertTrue("verify the login message after invalid credentials", loginErrorMessageText.contains(expectedLoginErrorMessage));
	}

}
