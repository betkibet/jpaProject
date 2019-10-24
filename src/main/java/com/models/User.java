package com.models;

import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Table(name="user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Column(name="email")
	@Email(message = "Please provide a valid email")
	@NotEmpty(message = "Please provide your email")
	private String email;
	
	@Column(name="password")
	@Length(min = 5, message= "Your password must contain at least 5 characters")
	@NotEmpty(message = "please supply your password")
	private String password;
	
	@Column(name = "name")
	@NotEmpty(message = "Supply your name")
	private String name;
	
	@Column(name = "last_name")
	@NotEmpty(message = "Please supply your last name")
	private String lastName;
	
	@Column(name = "active")
	private int active;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;
	
	
	
}
