package com.omrbranch.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.omrbranch.utility.BaseClass;

public class ExploreHotelPage extends BaseClass {
	public ExploreHotelPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@data-testid='username']")
	private WebElement textLoginMessage;

	public WebElement getTextLoginMessage() {
		return textLoginMessage;
	}

	@FindBy(xpath = "//h3[(text()='Hotel Booking')]")
	private WebElement btnHotelBooking;

	@FindBy(id = "state")
	private WebElement selectState;

	@FindBy(id = "city")
	private WebElement selectCity;

	@FindBy(id = "room_type")
	private WebElement selectRoomType;

	@FindBy(name = "check_in")
	private WebElement selectCheckIn;
	
	@FindBy(xpath = "//a[text()='30']")
	private WebElement selectCheckInDate;

	@FindBy(name = "check_out")
	private WebElement selectCheckOut;
	
	@FindBy(xpath = "//a[text()='31']")
	private WebElement selectCheckOutDate;

	@FindBy(xpath = "//select[@id='no_rooms']")
	private WebElement selectNoOfRoom;

	@FindBy(xpath = "//select[@id='no_adults']")
	private WebElement selectNoOfAdult;

	@FindBy(xpath = "//input[@id='no_child']")
	private WebElement selectNoOfChild;

	@FindBy(id = "searchBtn")
	private WebElement btnSearch;

	public String getLoginMessageText() {
		String elementText = getElementText(textLoginMessage);
		return elementText;

	}

	public void bookingDetails(String state, String city, String roomType, String checkInDate, String checkOutDate,
			String noOfRooms, String noOfAdults, String noOfChilren) {
		

		selectByValue(selectState, state);
		selectByValue(selectCity, city);
		selectByValue(selectRoomType, roomType);
		elementClick(selectCheckIn);
		elementClick(selectCheckInDate);
		elementClick(selectCheckOut);
		elementClick(selectCheckOutDate);
		
		selectByValue(selectNoOfRoom, noOfRooms);
		selectByValue(selectNoOfAdult, noOfAdults);
		elementSendKeys(selectNoOfChild, noOfChilren);
		
		clickUsingJs(btnSearch);
	}

}
