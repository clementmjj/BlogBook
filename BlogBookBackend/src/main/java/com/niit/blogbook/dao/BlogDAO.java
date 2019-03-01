package com.niit.blogbook.dao;

import java.util.List;

import com.niit.blogbook.model.Blog;

public interface BlogDAO {

	public boolean addBlog(Blog blog);
	public boolean deleteBlog(Blog blog);
	public boolean updateBlog(Blog blog);
	public boolean approveBlog(Blog blog);
	public boolean rejectBlog(Blog blog);
	public List<Blog> getBlogList();
	public Blog getBlog(int blogId);
}