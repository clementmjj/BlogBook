package com.niit.blogbook.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.blogbook.dao.BlogDAO;
import com.niit.blogbook.model.Blog;
import com.niit.blogbook.model.UserDetail;

@Repository("blogDAO")
@Transactional
public class BlogDAOImpl implements BlogDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean addBlog(Blog blog) {
		try {
			sessionFactory.getCurrentSession().save(blog);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean deleteBlog(Blog blog) {
		try {
			sessionFactory.getCurrentSession().remove(blog);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateBlog(Blog blog) {
		try {
			sessionFactory.getCurrentSession().update(blog);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean approveBlog(Blog blog) {
		blog.setStatus("A");
		try {
			sessionFactory.getCurrentSession().update(blog);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean rejectBlog(Blog blog) {
		blog.setStatus("R");
		try {
			sessionFactory.getCurrentSession().update(blog);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Blog> getBlogList() {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Blog");
		List<Blog> blogList = query.list();
		session.close();
		return blogList;
	}

	@Override
	public Blog getBlog(int blogId) {
		Session session = sessionFactory.openSession();
		Blog blog = session.get(Blog.class, blogId);
		session.close();
		return blog;
	}

	@Override
	public boolean incrementLike(Blog blog) {
		try {
			blog.setLikes(blog.getLikes() + 1);
			sessionFactory.getCurrentSession().update(blog);
			System.out.println("impl: like incremented. likes count = " + blog.getLikes());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean incrementDislike(Blog blog) {
		try {
			blog.setDislikes(blog.getDislikes() + 1);
			sessionFactory.getCurrentSession().update(blog);
			System.out.println("impl: dislike incremented. dislikes count = " + blog.getDislikes());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean decrementLike(Blog blog) {
		try {
			blog.setLikes(blog.getLikes() - 1);
			sessionFactory.getCurrentSession().update(blog);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean decrementDislike(Blog blog) {
		try {
			blog.setDislikes(blog.getDislikes() - 1);
			sessionFactory.getCurrentSession().update(blog);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
