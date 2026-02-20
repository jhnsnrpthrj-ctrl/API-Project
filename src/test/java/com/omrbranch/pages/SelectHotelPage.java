package com.omrbranch.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.omrbranch.utility.BaseClass;

public class SelectHotelPage extends BaseClass {
	
	public SelectHotelPage() {
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy (xpath = "//h5[contains(text(),'Select')]")
	private WebElement txtSelectHotelMeg;
	
	
	public String getSelectHotelMessage() {
		
		String elementText = getElementText(txtSelectHotelMeg);
		
		return elementText;
	}
	

}
