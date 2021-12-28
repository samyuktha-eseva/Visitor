package com.virtusa.visitor.services;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.visitor.entities.Booking;
import com.virtusa.visitor.entities.BookingDTO;
import com.virtusa.visitor.repositories.BookingRepository;

@Service
public class BookingService implements BookingServiceInterface {

	@Autowired
	BookingRepository repo;
	
	Logger log = Logger.getLogger(BookingService.class);

	@SuppressWarnings("deprecation")
	@Override
	public Booking saveBooking(BookingDTO bookingDTO) {
		Booking booking = bookingDTO.convertToEntity(bookingDTO);
		Date date = booking.getDate();
		Date time = booking.getTime();
		Date datetime = new Date(date.getYear(),date.getMonth(), date.getDate(),time.getHours(),time.getMinutes(),time.getSeconds());
		LocalDateTime local = LocalDateTime.now();
		Date current = Date.from(local.atZone(ZoneId.systemDefault()).toInstant());
		
		if(datetime.after(current)) {
			return repo.save(booking);
		}
		
		throw new IllegalArgumentException();
	}

	@Override
	public List<Booking> getBookings() {
		return repo.findAll();
	}

	@Override
	public List<Booking> getUserBookings(int uid) {
		return repo.findByUserId(uid);
	}

	@Override
	public Booking deleteBooking(int bid) {
			Booking book = repo.findById(bid).orElse(null);
			repo.deleteById(bid);
			return book;
	}

	@Override
	public Booking getBookingByBid(int bid) {
		return repo.findById(bid).orElse(null);
	}

	@Override
	public Booking getBookingByDateAndTime(Date date, Date time) {
		return repo.findByDateAndTime(date, time);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void updateBookingById(int nop, Date date, Date time, int bid) {
		BasicConfigurator.configure();
		Date datetime = new Date(date.getYear(),date.getMonth(), date.getDate(),time.getHours(),time.getMinutes(),time.getSeconds());
		LocalDateTime local = LocalDateTime.now();
		Date current = Date.from(local.atZone(ZoneId.systemDefault()).toInstant());
		log.info("\n\nbefore entering\n\n");
		if(datetime.after(current)) {
			log.info("\n\nentered\n\n");
			repo.updateBooking(nop, date, time, bid);
			return;
		}
		
		throw new IllegalArgumentException();
	}

}
