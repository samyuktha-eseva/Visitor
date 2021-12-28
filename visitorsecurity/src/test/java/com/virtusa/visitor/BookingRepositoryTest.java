package com.virtusa.visitor;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.virtusa.visitor.entities.Booking;
import com.virtusa.visitor.entities.Role;
import com.virtusa.visitor.entities.User;
import com.virtusa.visitor.repositories.BookingRepository;
import com.virtusa.visitor.repositories.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(true)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
class BookingRepositoryTest {
	
	@Autowired
	private BookingRepository bookrepo;
	
    @Autowired
    private UserRepository userrepo;
    
    Logger log = Logger.getLogger(BookingRepositoryTest.class);
    
    public User createNewTestUser(String username) {
    	User user = new User();
    	user.setUsername(username);
    	user.setPassword(username);
    	user.setEmail("test@gmail.com");
    	user.setPhno("5555555555");
    	user.setRoles(Arrays.asList(new Role("ROLE_USER")));
    	userrepo.save(user);
    	return user;
    }
    
    public Booking createNewTestBooking(String username, int days) {
    	User user = createNewTestUser(username);
    	Booking book = new Booking();
    	book.setUser(user);
    	book.setNop(1);
    	Date date = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
    	LocalDateTime.from(date.toInstant().atZone(ZoneId.systemDefault())).plusDays(days);
    	book.setDate(date);
    	book.setTime(date);
    	bookrepo.save(book);
    	return book;
    }
    
    public Booking createNewTestBookingManualUser(User user, int days) {
    	Booking book = new Booking();
    	book.setUser(user);
    	book.setNop(1);
    	Date date = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
    	LocalDateTime.from(date.toInstant().atZone(ZoneId.systemDefault())).plusDays(days);
    	book.setDate(date);
    	book.setTime(date);
    	bookrepo.save(book);
    	return book;
    }
    
    @Test
    void testAddBooking() {
    	BasicConfigurator.configure();
    	log.info("\\n\\n\\nI'm starting add test\n\n\n");
    	Booking inputbook = createNewTestBooking("test1",2);
    	Booking check = bookrepo.findByDateAndTime(inputbook.getDate(), inputbook.getTime());
    	assertThat(check).isNotNull();
    }
    
    @Test
    void testGetAllBookings() {
    	BasicConfigurator.configure();
    	log.info("\\n\\n\\nI'm starting getall test\n\n\n\n");
    	createNewTestBooking("test1",1);
    	createNewTestBooking("test2",2);
    	createNewTestBooking("test3",3);
    	
    	List<Booking> bookings = (List<Booking>)bookrepo.findAll();
    	log.info("\n\n\n"+bookings.get(2).getUser().getUsername());
    	log.info(bookings.get(1).getUser().getUsername()+"\n\n\n");
        assertThat(bookings).size().isGreaterThan(2);
    	   	
    }
    
    @Test
    void testGetBookingsByUserId() {
    	BasicConfigurator.configure();
    	log.info("\\n\\n\\nI'm starting getbyuserid test\n\n\n\n");
    	
    	User user = createNewTestUser("test1");
    	User retrievedUser = userrepo.findByUsername("test1");
    	createNewTestBookingManualUser(user, 15);
    	createNewTestBookingManualUser(user, 16);
    	createNewTestBookingManualUser(user, 17);
    	List<Booking> bookings = bookrepo.findByUserId(retrievedUser.getId());
        assertThat(bookings).size().isEqualTo(3);
    }
      
   @Test
   @Transactional
    void testDeleteBooking() {
       BasicConfigurator.configure();
       log.info("\n\n\nI'm starting delete test\n\n\n\n");
	   Booking inputbook = createNewTestBooking("test1", 5);
       bookrepo.delete(inputbook);
       User user = userrepo.findByUsername("test1");
  	   List<Booking> deletedProduct = bookrepo.findByAuthUserId(user.getId());
  	   assertThat(deletedProduct).isEmpty();
  }
   
    @Test
	@Transactional
    void testUpdateBooking() {
    	BasicConfigurator.configure();
    	log.info("\n\n\nI'm starting update test\n\n\n\n");
    	createNewTestBooking("test1", 11);
    	User user = userrepo.findByUsername("test1");
    	List<Booking> retrievedbook=bookrepo.findByAuthUserId(user.getId());
    	retrievedbook.get(0).setNop(5);
    	bookrepo.save(retrievedbook.get(0));
    	List<Booking> updatedbooking = bookrepo.findByAuthUserId(user.getId());
    	assertThat(updatedbooking.get(0).getNop()).isEqualTo(5);

    	  	
    }
    
    
}
