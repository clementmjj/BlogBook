package com.niit.blogbook.dao;

import java.util.List;

import com.niit.blogbook.model.BlogDislike;
import com.niit.blogbook.model.BlogLike;

public interface BlogLikeDislikeDAO {

	public boolean addBlogLike(BlogLike like);

	public boolean removeBlogLike(BlogLike like);

	public boolean addBlogDislike(BlogDislike dislike);

	public boolean removeBlogDislike(BlogDislike dislike);

	public BlogLike getBlogLikeById(int likeId);

	public BlogDislike getBlogDislikeById(int dislikeId);

	public BlogLike getBlogLikeByUser(String username, int blogId);

	public BlogDislike getBlogDislikeByUser(String username, int blogId);

	public List<BlogLike> getBlogLikesList(String username);

	public List<BlogDislike> getBlogDislikesList(String username);
}