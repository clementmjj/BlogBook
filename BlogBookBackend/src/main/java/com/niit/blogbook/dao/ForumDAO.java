package com.niit.blogbook.dao;

import java.util.List;

import com.niit.blogbook.model.Forum;

public interface ForumDAO {
	public boolean addForum(Forum forum);

	public boolean deleteForum(Forum forum);

	public boolean updateForumDetails(Forum forum);

	public boolean approveForum(Forum forum);

	public boolean rejectForum(Forum forum);

	public Forum getForum(int forumId);

	public List<Forum> getForumList();

	public List<Forum> getLimitedForumList(String username, int startRowNum, int endRowNum);
}
