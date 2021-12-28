package com.virtusa.visitor.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "bookings")
public class Booking implements Serializable {

	private static final long serialVersionUID = 5293347830201643936L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="auth_user_id", nullable=false)
	private User user;

	@Column
	private int nop;
	
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private java.util.Date date;
	
	@Column
	@DateTimeFormat(pattern = "HH:mm")
	@Temporal(TemporalType.TIME)
	private java.util.Date time;
	
	public Booking(int id, User user, int nop, Date date, Date time) {
		super();
		this.id = id;
		this.user = user;
		this.nop = nop;
		this.date = date;
		this.time = time;
	}
	
	public Booking() {
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public java.util.Date getTime() {
		return time;
	}

	public void setTime(java.util.Date time) {
		this.time = time;
	}

	public java.util.Date getDate() {
		return date;
	}

	public void setDate(java.util.Date date) {
		this.date = date;
	}
	
	public BookingDTO convertToDTO(Booking booking) {
		return new BookingDTO(booking.getId(), booking.getUser(), booking.getNop(), booking.getDate(), booking.getTime());
	}
	
}
