package com.virtusa.visitor.repositories;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.virtusa.visitor.entities.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
	@Query("SELECT b FROM Booking b where user.id=?1")
	List<Booking> findByUserId(int uid);
	
	@Query("SELECT b FROM Booking b where date=?1 and time=?2")
	Booking findByDateAndTime(Date date, Date time);
	
	@Transactional
	@Modifying
	@Query("UPDATE Booking b SET nop=?1, date=?2, time=?3 where id=?4")
	void updateBooking(int nop, Date date, Date time, int bid);
	
	@Query("SELECT b FROM Booking b where auth_user_id = ?1")
	List<Booking> findByAuthUserId(int id);
}
