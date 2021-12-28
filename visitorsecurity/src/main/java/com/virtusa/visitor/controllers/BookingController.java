package com.virtusa.visitor.controllers;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.virtusa.visitor.entities.Booking;
import com.virtusa.visitor.entities.BookingDTO;
import com.virtusa.visitor.entities.User;
import com.virtusa.visitor.services.BookingService;

@Controller
public class BookingController {
	
	@Autowired
	BookingService service;
	
	Logger log = Logger.getLogger(BookingController.class);
	
	private static final String ROLE_ADMIN = "ROLE_ADMIN";
	private static final String BIDVALUE = "bidvalue";
	private static final String BOOKERROR = "bookerror";
	private static final String BOOKINGS = "bookings";
	private static final String LOGGEDUSER = "loggedUser";
	private static final String ADMIN_HOME = "redirect:/admin/home";
	private static final String USER_HOME = "redirect:/user/home";
	private static final String NEWBOOKING = "redirect:/user/newbooking";
	
	@GetMapping("/allbookings")
	public String getAllBookings(Model model) {
		List<Booking> books = service.getBookings();
		model.addAttribute(BOOKINGS, books);
		return "allbookings";
	}
	
	@GetMapping("/user/home")
	public String getUserBookings(HttpServletRequest request, Model model) {
		request.getSession().removeAttribute(BOOKERROR);
		User user = (User)request.getSession().getAttribute(LOGGEDUSER);
		if(user==null) {
			request.getSession().setAttribute("sessionout", "Session has timed out");
			return "redirect:/";
		}
		
		if(request.isUserInRole(ROLE_ADMIN))
			return ADMIN_HOME;
		
		List<Booking> books = service.getUserBookings(user.getId());
		model.addAttribute(BOOKINGS, books);
		return "home";
	}
	
	@GetMapping("/user/newbooking")
	public String getNewBookingPage(Model model) {
		model.addAttribute("booking", new BookingDTO());
		return "newbook";
	}
	
	@PostMapping("/user/newbooking")
	public String addBooking(@ModelAttribute("booking") BookingDTO book, Model model, HttpServletRequest request) {
		int nop = book.getNop();
		if(nop<=0) {
			request.getSession().setAttribute(BOOKERROR, "You have to book for at least 1 person");
			return NEWBOOKING;
		}
		Date date = book.getDate();
		Date time = book.getTime();
		if(service.getBookingByDateAndTime(date, time)!=null) {
			request.getSession().setAttribute(BOOKERROR,"This time slot is already taken by someone" );
			return NEWBOOKING;
		}
		book.setUser((User)request.getSession().getAttribute(LOGGEDUSER));
		
		try {
			service.saveBooking(book);
		} catch (IllegalArgumentException e) {
			request.getSession().setAttribute(BOOKERROR, "You cannot book for a time in the past");
			return NEWBOOKING;
		}
		
		return USER_HOME;
	}
	
	@GetMapping("/user/editbooking")
	public String viewEditBooking(@RequestParam(name="bid", required=false) Integer bid, Model model, HttpServletRequest request) {
		BasicConfigurator.configure();
		if(bid==null) {
			bid = (Integer)request.getSession().getAttribute(BIDVALUE);
			request.getSession().removeAttribute(BIDVALUE);
		}
		model.addAttribute("booking", service.getBookingByBid(bid));
		return "editbook";
	}
	
	@PostMapping("/user/editbooking")
	public String doEditBooking(@ModelAttribute("booking") BookingDTO bookDTO, HttpServletRequest request) {
		BasicConfigurator.configure();
		Booking book = bookDTO.convertToEntity(bookDTO);
		int nop = book.getNop();
		if(nop<=0) {
			request.getSession().setAttribute(BOOKERROR, "You have to book for at least 1 person");
			request.getSession().setAttribute(BIDVALUE, book.getId());
			return "redirect:/user/editbooking";
		}
		log.info(book.getId());	
		try {
			service.updateBookingById(book.getNop(), book.getDate(), book.getTime(), book.getId());
		} catch (IllegalArgumentException e) {
			request.getSession().setAttribute(BOOKERROR, "You cannot book for a time in the past");
			request.getSession().setAttribute(BIDVALUE, book.getId());
			return "redirect:/user/editbooking";
		}
		
		request.getSession().removeAttribute(BOOKERROR);
		if(request.isUserInRole(ROLE_ADMIN))
			return ADMIN_HOME;
		return USER_HOME;
	}
	
	@PostMapping("/user/deletebooking")
	public String deleteInitial(@RequestParam("bid") int bid, HttpServletRequest request) {
		service.deleteBooking(bid);
		if(request.isUserInRole(ROLE_ADMIN))
			return ADMIN_HOME;
		return USER_HOME;
	}
	
	@GetMapping("/admin/home")
	public String showAdminHome(HttpSession session, Model model, RedirectAttributes ratt) {
		session.removeAttribute(BOOKERROR);
		User user = (User)session.getAttribute(LOGGEDUSER);
		if(user==null) {
			ratt.addFlashAttribute("sessionout", "Session has timed out");
			return "redirect:/";
		}
		List<Booking> books = service.getBookings();
		model.addAttribute(BOOKINGS, books);
		return "adminhome";
	}
	
}
