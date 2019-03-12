package com.niit.blogbook.dao;

import java.util.List;

import com.niit.blogbook.model.Blog;
import com.niit.blogbook.model.UserDetail;

public interface BlogDAO {

	public boolean addBlog(Blog blog);
	public boolean deleteBlog(Blog blog);
	public boolean updateBlog(Blog blog);
	public boolean approveBlog(Blog blog);
	public boolean rejectBlog(Blog blog);
	public boolean incrementLike(Blog blog);
	public boolean incrementDislike(Blog blog);
	public boolean decrementLike(Blog blog);
	public boolean decrementDislike(Blog blog);
	public List<Blog> getBlogList();
	public Blog getBlog(int blogId);
	
	//increment likes ... could be in update blog method
	//increment dislikes... could be in update blog method
}
