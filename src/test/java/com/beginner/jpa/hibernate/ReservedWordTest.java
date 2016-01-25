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

public class ReservedWordTest {
	EntityManagerFactory entityManagerFactory;
	EntityManager entityManager;

	@Before
	public void setUp() {
		entityManagerFactory = Persistence.createEntityManagerFactory("my-hibernate-jpa");
		entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		cleanData();
		entityManager.getTransaction().commit();
		entityManager.getTransaction().begin();
	}

	private void cleanData() {
		entityManager.createQuery("delete from Actor").executeUpdate();
	}

	@After
	public void destroy() {
		entityManager.getTransaction().commit();
		entityManager.close();

		// 如果 hibernate.hbm2ddl.auto=create-drop, 调用 close 方法才会触发 drop.
		entityManagerFactory.close();
	}

	@Test
	public void testJPAReservedWord() throws Exception {
		// 增
		Actor actor = new Actor(ActorType.Group, "test");
		entityManager.persist(actor);
		Assert.assertNotNull(actor.getId());

		//==== empty 为 jpa 保留字，需要添加别名方可，否则报错:
		// java.lang.ClassCastException: org.hibernate.hql.internal.ast.tree.SqlNode cannot be cast to org.hibernate.hql.internal.ast.tree.FromReferenceNode
		//==== 如果属性名也刚好是数据库保留字，则需要添加 @Column(name="\"empty\"")

		// 查
		Actor user = entityManager.createQuery("select a from Actor a where a.empty = :empty", Actor.class)
				.setParameter("empty", false)
				.getSingleResult();
		Assert.assertNotNull(user);
		Assert.assertEquals(actor, user);

		// 更新
		int c = entityManager.createQuery("update Actor a set a.empty = :empty where id = :id")
				.setParameter("empty", false)
				.setParameter("id", user.getId())
				.executeUpdate();
		Assert.assertEquals(1, c);
	}

	@Test
	public void testDBReservedWord() throws Exception {
		String from = "fff";
		// 增
		Actor actor = new Actor(ActorType.Group, "test");
		actor.setFrom(from);
		entityManager.persist(actor);
		Assert.assertNotNull(actor.getId());

		//==== from 为数据库保留字，需要添加 @Column(name="\"from\"")

		// 查
		Actor user = entityManager.createQuery("select a from Actor a where a.from = :from", Actor.class)
				.setParameter("from", from)
				.getSingleResult();
		Assert.assertNotNull(user);
		Assert.assertEquals(actor, user);

		// 更新
		int c = entityManager.createQuery("update Actor a set a.from = :from where id = :id")
				.setParameter("from", from)
				.setParameter("id", user.getId())
				.executeUpdate();
		Assert.assertEquals(1, c);
	}
}