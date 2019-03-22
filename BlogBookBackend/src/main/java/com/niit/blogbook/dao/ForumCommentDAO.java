package com.niit.blogbook.dao;

import java.util.List;

import com.niit.blogbook.model.ForumComment;

public interface ForumCommentDAO {
	public boolean addForumComment(ForumComment forumComment);

	public boolean deleteForumComment(ForumComment forumComment);

	public boolean editForumComment(ForumComment forumComment);

	public List<ForumComment> getForumCommentList();
	
	public ForumComment getForumComment(int commentId);
}
