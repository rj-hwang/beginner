package com.beginner.jpa.hibernate;

import com.beginner.jpa.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class TestUserDao {
	EntityManagerFactory entityManagerFactory;

	@Before
	public void setUp() {
		entityManagerFactory = Persistence.createEntityManagerFactory("my-hibernate-jpa");
	}

	@Test
	public void test() throws Exception {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		// 增
		entityManager.persist(new User("admin"));
		entityManager.persist(new User("test"));

		// 查
		List<User> users = entityManager.createQuery("from User", User.class).getResultList();

		entityManager.getTransaction().commit();
		entityManager.close();

		// test
		Assert.assertEquals(2, users.size());
	}
}