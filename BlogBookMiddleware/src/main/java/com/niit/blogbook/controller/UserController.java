package com.niit.blogbook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.blogbook.dao.UserDAO;
import com.niit.blogbook.model.UserDetail;

@RestController
public class UserController {
	@Autowired
	UserDAO userDAO;

	@PostMapping(value = "/registerUser")
	public ResponseEntity<String> registerUser(@RequestBody UserDetail user) {
		if (userDAO.addUser(user))
			return new ResponseEntity<String>("Successfull", HttpStatus.OK);
		else
			return new ResponseEntity<String>("Unsuccessfull", HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@GetMapping(value = "/deleteUser/{username}")
	public ResponseEntity<String> deleteUser(@PathVariable("username") String username) {
		UserDetail user = userDAO.getUser(username);
		if (userDAO.deleteUser(user)) {
			return new ResponseEntity<String>("Successfull", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Unsuccessfull", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/approveUser/{username}")
	public ResponseEntity<String> approveUser(@PathVariable("username") String username) {
		UserDetail user = userDAO.getUser(username);
		if (userDAO.approveUser(user))
			return new ResponseEntity<String>("Successfull", HttpStatus.OK);
		else
			return new ResponseEntity<String>("Unsuccessfull", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping(value = "/rejectUser/{username}")
	public ResponseEntity<String> rejectUser(@PathVariable("username") String username) {
		UserDetail user = userDAO.getUser(username);
		if (userDAO.rejectUser(user))
			return new ResponseEntity<String>("Successfull", HttpStatus.OK);
		else
			return new ResponseEntity<String>("Unsuccessfull", HttpStatus.INTERNAL_SERVER_ERROR);
	}
}