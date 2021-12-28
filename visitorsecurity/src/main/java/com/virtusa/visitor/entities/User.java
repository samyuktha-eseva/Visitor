package com.virtusa.visitor.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "auth_user")
public class User implements Serializable{

	private static final long serialVersionUID = -2048969305116877823L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;

	@Column
	@Email
	@Size(min=9, max=30)
	private String email;
	
	@Column(unique=true)
	@NotEmpty
	private String username;
	
	@Column(unique=true)
	@NotEmpty
	private String password;
	
	@Column
	@Size(min = 10, max = 10, message = "Phone Number must be 10 digits long")
	private String phno;
	
	@OneToMany(mappedBy="user")
	private List<Booking> bookings;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "auth_user_role", joinColumns = @JoinColumn(name = "auth_user_id"), inverseJoinColumns = @JoinColumn(name = "auth_role_id"))
	private Collection<Role> roles;
	
	public User(@Email @Size(min = 9, max = 30) String email, @NotEmpty String username, @NotEmpty String password,
			@Size(min = 10, max = 10, message = "Phone Number must be 10 digits long") String phno,
			Collection<Role> roles) {
		super();
		this.email = email;
		this.username = username;
		this.password = password;
		this.phno = phno;
		this.roles = roles;
	}
	
	public User() {
	}
	
	public String getPhno() {
		return phno;
	}

	public void setPhno(String phno) {
		this.phno = phno;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}
	
	public UserDTO convertToDTO(User user) {
		return new UserDTO(user.getEmail(), user.getUsername(), user.getPassword(), user.getPhno(), user.getRoles());
	}
	
}
