package com.niit.blogbook.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class ForumComment {
	@Id
	@GeneratedValue
	private int commentId;
	private int forumId;
	private String commentMessage;
	private Date commentDate;
	private int userId;
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public int getForumId() {
		return forumId;
	}
	public void setForumId(int forumId) {
		this.forumId = forumId;
	}
	public String getCommentMessage() {
		return commentMessage;
	}
	public void setCommentMessage(String commentMessage) {
		this.commentMessage = commentMessage;
	}
	public Date getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
}
