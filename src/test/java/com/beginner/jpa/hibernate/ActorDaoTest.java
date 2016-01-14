package com.beginner.jpa.hibernate;

import com.beginner.jpa.Actor;
import com.beginner.jpa.ActorType;
import com.owlike.genson.Genson;
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
	public void log() throws Exception {
		System.out.println("---- begin ----");
		entityManager.getTransaction().begin();

		System.out.println("---- start insert ----");
		// 增
		Actor actor = new Actor(ActorType.User, "test");
		entityManager.persist(actor);
		System.out.println("actor=" + new Genson().serialize(actor));
		System.out.println("---- end insert ------");

		// 查
		System.out.println("---- start find ----");
		List<Actor> users = entityManager.createQuery("select a from Actor a where type=?", Actor.class)
				.setParameter(1, ActorType.User)
				.getResultList();
		Assert.assertFalse(users.isEmpty());
		System.out.println("actors=" + new Genson().serialize(users));
		System.out.println("---- end find ------");

		entityManager.getTransaction().commit();
		System.out.println("---- end ------");
	}

	//@Test
	public void test() throws Exception {
		entityManager.getTransaction().begin();

		// 增
		Actor actor = new Actor(ActorType.Group, "test");
		entityManager.persist(actor);
		System.out.println("1:actor.id=" + actor.getId());
		//Assert.assertNotNull(actor.getId());
		entityManager.persist(new Actor(ActorType.User, "admin"));
		entityManager.persist(new Actor(null, "unknown"));

		// 查
		List<Actor> users = entityManager.createQuery("select a from Actor a", Actor.class).getResultList();

		entityManager.getTransaction().commit();

		System.out.println("2:actor.id=" + actor.getId());
		Assert.assertNotNull(actor.getId());

		// test
		// 注: 初始化数据文件中插入了 1 条
		Assert.assertEquals(4, users.size());
	}
}