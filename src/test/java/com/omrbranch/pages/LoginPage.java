package com.omrbranch.pages;

import java.awt.AWTException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.omrbranch.utility.BaseClass;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginPage extends BaseClass {

	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "email")
	private WebElement txtUserName;

	@FindBy(id = "pass")
	private WebElement txtPassword;

	@FindBy(xpath = "//button[@value='login']")
	private WebElement btnLogin;

	@FindBy(id = "errorMessage")
	private WebElement textLoginErrorMessage;

	public WebElement getTxtUserName() {
		return txtUserName;
	}

	public WebElement getTxtPassword() {
		return txtPassword;
	}

	public WebElement getBtnLogin() {
		return btnLogin;
	}

	public WebElement getTextLoginErrorMessage() {
		return textLoginErrorMessage;
	}

	public void login(String username, String password) {
		elementSendKeys(txtUserName, username);
		elementSendKeys(txtPassword, password);
		elementClick(btnLogin);
	}

	public void loginWithEnterKey(String username, String password) throws AWTException {
		elementSendKeys(txtUserName, username);
		elementSendKeys(txtPassword, password);
		enterKey();
	}

	public String getLoginErrorMessageText() {
		String elementText = getElementText(textLoginErrorMessage);
		
		return elementText;
		
	}

}
