package com.niit.blogbook.dao;

import java.util.List;

import com.niit.blogbook.model.BlogComment;

public interface BlogCommentDAO {
	public BlogComment getBlogComment(int commentId);

	public boolean addBlogComment(BlogComment blogComment);

	public boolean editBlogComment(BlogComment blogComment);

	public boolean deleteBlogComment(BlogComment blogComment);

	public List<BlogComment> getBlogCommentList(int blogId);
}
