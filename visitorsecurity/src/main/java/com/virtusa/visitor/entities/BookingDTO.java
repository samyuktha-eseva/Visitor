package com.virtusa.visitor.entities;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

public class BookingDTO { 
	
	private int id;
	private User user;
	private int nop;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@DateTimeFormat(pattern = "HH:mm")
	@Temporal(TemporalType.TIME)
	private Date time;
	
	public BookingDTO(int id, User user, int nop, Date date, Date time) {
		super();
		this.id = id;
		this.user = user;
		this.nop = nop;
		this.date = date;
		this.time = time;
	}
	
	public BookingDTO() {
		
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getNop() {
		return nop;
	}

	public void setNop(int nop) {
		this.nop = nop;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Booking convertToEntity(BookingDTO bookingDTO) {
		return new Booking(bookingDTO.getId(), bookingDTO.getUser(), bookingDTO.getNop(), bookingDTO.getDate(), bookingDTO.getTime());
	}
	
}
