package com.omrbranch.manager;

import com.omrbranch.pages.BookHotelPage;
import com.omrbranch.pages.BookingConfirmPage;
import com.omrbranch.pages.ExploreHotelPage;
import com.omrbranch.pages.LoginPage;
import com.omrbranch.pages.MyBookingPage;
import com.omrbranch.pages.SelectHotelPage;

public class PageObjectManager {
  private LoginPage loginPage;
  private ExploreHotelPage exploreHotelPage;
  private SelectHotelPage selectHotelPage;
  private BookHotelPage bookHotelPage;
  private BookingConfirmPage bookingConfirmPage;
  private MyBookingPage myBookingPage;

  public LoginPage getLoginPage() {
    return (loginPage == null) ? loginPage = new LoginPage() : loginPage;
    
  }

  public ExploreHotelPage getExploreHotelPage() {
    return (exploreHotelPage == null) ? exploreHotelPage = new ExploreHotelPage() : exploreHotelPage;
  }

  public SelectHotelPage getSelectHotelPage() {
    return (selectHotelPage == null) ? selectHotelPage = new SelectHotelPage() : selectHotelPage;
  }

  public BookHotelPage getBookHotelPage() {
    return (bookHotelPage == null) ? bookHotelPage = new BookHotelPage() : bookHotelPage;
  }

  public BookingConfirmPage getBookingConfirmPage() {
    return (bookingConfirmPage == null) ? bookingConfirmPage = new BookingConfirmPage() : bookingConfirmPage;
  }

  public MyBookingPage getMyBookingPage() {
    return (myBookingPage == null) ? myBookingPage = new MyBookingPage() : myBookingPage;
  }

}
