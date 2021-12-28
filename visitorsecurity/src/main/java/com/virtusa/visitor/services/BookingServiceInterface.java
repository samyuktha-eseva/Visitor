package com.virtusa.visitor.services;

import java.util.Date;
import java.util.List;

import com.virtusa.visitor.entities.Booking;
import com.virtusa.visitor.entities.BookingDTO;

public interface BookingServiceInterface {
	public Booking saveBooking(BookingDTO bookingDTO);
	public List<Booking> getBookings();
	public List<Booking> getUserBookings(int uid);
	public Booking deleteBooking(int bid);
	public Booking getBookingByBid(int bid);
	public Booking getBookingByDateAndTime(Date date, Date time);
	public void updateBookingById(int nop, Date date, Date time, int bid);
}
