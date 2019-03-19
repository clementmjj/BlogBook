package com.niit.blogbook.dao;

import java.util.List;

import com.niit.blogbook.model.BlogComment;

public interface BlogCommentDAO {
	public BlogComment getBlogComment(int commentId);
	public boolean addComment(BlogComment blogComment);
	public boolean editComment(BlogComment blogComment);
	public boolean deleteComment(BlogComment blogComment);
	public List<BlogComment> getBlogCommentList(int blogId);
}
