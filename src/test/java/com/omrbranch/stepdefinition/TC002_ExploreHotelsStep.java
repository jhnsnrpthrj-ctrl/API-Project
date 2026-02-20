package com.omrbranch.stepdefinition;





import org.junit.Assert;

import com.omrbranch.manager.PageObjectManager;
import com.omrbranch.utility.BaseClass;

import io.cucumber.java.en.*;

public class TC002_ExploreHotelsStep extends BaseClass{
	
	PageObjectManager pom = new PageObjectManager();
	
	@When("User searches hotel {string}, {string}, {string}, {string}, {string}, {string}, {string} and {string}")
	public void userSearchesHotelAnd(String state, String city, String roomType, String checkInDate, String checkOutDate, String noOfRooms, String noOfAdults, String noOfChilren) {
		
		pom.getExploreHotelPage().bookingDetails(state, city, roomType, checkInDate, checkOutDate, noOfRooms, noOfAdults, noOfChilren);
	}
	
	@Then("User should verify after search hotel success message {string}")
	public void userShouldVerifyAfterSearchHotelSuccessMessage(String expSelectHotelMessage) {
		
		String actSelectHotelMessage = pom.getSelectHotelPage().getSelectHotelMessage();
		Assert.assertEquals("Verify the Select Hotel Message", expSelectHotelMessage,actSelectHotelMessage);
	}


	@When("User searches hotel {string}, {string}, {string}, {string}, {string} and {string}")
	public void userSearchesHotelAnd(String state, String city, String checkInDate, String checkOutDate, String noOfRooms, String noOfAdults) {
		
	}


	@Then("User clicks Search button")
	public void userClicksSearchButton() {
		
	}
	
	@Then("User should verify after search hotel error message {string}, {string}, {string}, {string}, {string} and {string}")
	public void userShouldVerifyAfterSearchHotelErrorMessageAnd(String string, String string2, String string3, String string4, String string5, String string6) {
		
	}
	

	@When("User clicks sort from low to high")
	public void userClicksSortFromLowToHigh() {
		
	}
	
	@Then("User should verify after sorting that prices are listed from low to high")
	public void userShouldVerifyAfterSortingThatPricesAreListedFromLowToHigh() {
		
	}
	

	@When("User clicks sort from descending order")
	public void userClicksSortFromDescendingOrder() {
		
	}
	
	@Then("User should verify after sorting that names are in descending order")
	public void userShouldVerifyAfterSortingThatNamesAreInDescendingOrder() {
		
	}
	

	@When("User applies filter for room types ending with {string}")
	public void userAppliesFilterForRoomTypesEndingWith(String string) {
		
	}
	
	@Then("User should verify that all listed room types end with {string}")
	public void userShouldVerifyThatAllListedRoomTypesEndWith(String string) {
		
	}

	
	@Then("User should verify the header contains {string}")
	public void userShouldVerifyTheHeaderContains(String string) {
		
	}
	
}
