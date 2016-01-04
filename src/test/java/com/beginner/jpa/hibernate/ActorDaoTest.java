package com.beginner.jpa.hibernate;

import com.beginner.jpa.Actor;
import com.beginner.jpa.ActorType;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ActorDaoTest {
	EntityManagerFactory entityManagerFactory;
	EntityManager entityManager;

	@Before
	public void setUp() {
		entityManagerFactory = Persistence.createEntityManagerFactory("my-hibernate-jpa");
		entityManager = entityManagerFactory.createEntityManager();
	}

	@After
	public void destroy() {
		entityManager.close();

		// 如果 hibernate.hbm2ddl.auto=create-drop, 调用 close 方法才会触发 drop.
		entityManagerFactory.close();
	}

	@Test
	public void test() throws Exception {
		entityManager.getTransaction().begin();

		// 增
		entityManager.persist(new Actor(ActorType.Group, "test"));
		entityManager.persist(new Actor(ActorType.User, "admin"));
		entityManager.persist(new Actor(null, "unknown"));

		// 查
		List<Actor> users = entityManager.createQuery("from Actor", Actor.class).getResultList();

		entityManager.getTransaction().commit();

		// test
		// 注: 初始化数据文件中插入了 1 条
		Assert.assertEquals(4, users.size());
	}
}