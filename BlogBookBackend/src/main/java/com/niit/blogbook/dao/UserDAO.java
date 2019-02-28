package com.niit.blogbook.dao;

import com.niit.blogbook.model.UserDetail;

public interface UserDAO {
	public boolean addUser(UserDetail user);
	public boolean deleteUser(UserDetail user);
	public boolean updateUser(UserDetail user);
	public UserDetail getUser(int userId);
}
