package com.niit.blogbook.dao;

import java.util.List;

import com.niit.blogbook.model.ForumComment;

public interface ForumCommentDAO {
	public boolean addComment(ForumComment comment);

	public boolean deleteComment(ForumComment comment);

	public boolean editComment(ForumComment comment);

	public List<ForumComment> getForumCommentList();
	
	public ForumComment getComment(int commentId);
}
