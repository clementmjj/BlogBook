package com.niit.blogbook.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.niit.blogbook.dao.ProfilePicDAO;
import com.niit.blogbook.model.ProfilePic;
import com.niit.blogbook.model.UserDetail;

@RestController
public class ProfilePicController {
	@Autowired
	ProfilePicDAO profilePicDao;

	@RequestMapping(value = "/addProfilePic", method = RequestMethod.POST)
	public ResponseEntity<?> addProfilePic(@RequestParam(value = "profilePic") CommonsMultipartFile file,
			HttpSession session) {
		UserDetail userDetail = (UserDetail) session.getAttribute("userDetail");
		if (userDetail == null)
			return new ResponseEntity<String>("Unauthorized user", HttpStatus.NOT_FOUND);
		else {
			if (getProfilePic(userDetail.getUsername()) == null) {
				if (file != null) {
					ProfilePic profilePic = new ProfilePic();
					profilePic.setUsername(userDetail.getUsername());
					profilePic.setImage(file.getBytes());
					profilePicDao.addProfilePic(profilePic);
					return new ResponseEntity<>("Profile pic added", HttpStatus.OK);
				} else
					return new ResponseEntity<String>("no file selected", HttpStatus.NOT_FOUND);
			} else
				return new ResponseEntity<String>("profile pic already exists", HttpStatus.CONFLICT);
		}
	}

	@RequestMapping(value = "/getProfilePic/{username}", method = RequestMethod.GET)
	public @ResponseBody byte[] getProfilePic(@PathVariable("username") String username) {
		ProfilePic profilePic = profilePicDao.getProfilePic(username);
		if (profilePic != null)
			return profilePic.getImage();
		else
			return null;
	}

	@RequestMapping(value = "/deleteProfilePic/{username}", method = RequestMethod.GET)
	public ResponseEntity<?> deleteProfilePic(@PathVariable("username") String username) {
		ProfilePic profilePic = profilePicDao.getProfilePic(username);
		if (profilePicDao.deleteProfilePic(username))
			return new ResponseEntity<>(HttpStatus.OK);
		else
			return new ResponseEntity<>("Error deleting profile pic", HttpStatus.OK);
	}
}
