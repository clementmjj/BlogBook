package com.niit.blogbook.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.blogbook.model.Blog;
import com.niit.blogbook.model.BlogComment;
import com.niit.blogbook.model.BlogDislike;
import com.niit.blogbook.model.BlogLike;
import com.niit.blogbook.model.Forum;
import com.niit.blogbook.model.ForumComment;
import com.niit.blogbook.model.Job;
import com.niit.blogbook.model.UserDetail;

@Configuration
@ComponentScan("com.niit")
@EnableTransactionManagement
public class DbConfig {

	@Bean(name="dataSource")
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		dataSource.setUsername("system");
		dataSource.setPassword("password123");
		System.out.println("---Data Source created---");
		return dataSource;
	}

	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory() {
		Properties hibernateProp = new Properties();
		hibernateProp.put("hibernate.hbm2ddl.auto", "update");
		hibernateProp.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");

		LocalSessionFactoryBuilder factoryBuilder = new LocalSessionFactoryBuilder(getDataSource());
		factoryBuilder.addProperties(hibernateProp);

		factoryBuilder.addAnnotatedClass(Blog.class);
		factoryBuilder.addAnnotatedClass(UserDetail.class);
		factoryBuilder.addAnnotatedClass(BlogComment.class);
		factoryBuilder.addAnnotatedClass(Job.class);
		factoryBuilder.addAnnotatedClass(Forum.class);
		factoryBuilder.addAnnotatedClass(ForumComment.class);
		factoryBuilder.addAnnotatedClass(BlogLike.class);
		factoryBuilder.addAnnotatedClass(BlogDislike.class);

		SessionFactory sessionFactory = factoryBuilder.buildSessionFactory();
		System.out.println("---SessionFactory created---");
		return sessionFactory;
	}

	@Bean
	public HibernateTransactionManager getHibernateTransactionManager(SessionFactory sessionFactory) {
		System.out.println("---TransactionManager created---");
		return new HibernateTransactionManager(sessionFactory);
	}
}