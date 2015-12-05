package com.beginner.jsr330;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

import static org.junit.Assert.*;

/**
 * https://github.com/google/guice/wiki/GettingStarted
 */
@Singleton
public class TestByGuice extends AbstractModule {
	@Inject
	private SingletonBean singletonBean1;
	@Inject
	private SingletonBean singletonBean2;
	@Inject
	private Provider<SingletonBean> singletonBeanProvider;
	@Inject
	private PrototypeBean prototypeBean1;
	@Inject
	private PrototypeBean prototypeBean2;
	@Inject
	private Provider<PrototypeBean> prototypeBeanProvider;
	private Injector injector;

	@Override
	protected void configure() {
	}

	@Before
	public void setUp() throws Exception {
		injector = Guice.createInjector(new TestByGuice());
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void di() throws Exception {
		TestByGuice jsr330 = injector.getInstance(TestByGuice.class);

		// 单例 bean 任何时候使用 @Inject 注入的都是同一个实例
		assertEquals(jsr330.singletonBean1, jsr330.singletonBean2);

		// 单例 bean 的 provider 任何时候 get 回来的都是同一个实例
		assertEquals(jsr330.singletonBeanProvider.get(), jsr330.singletonBeanProvider.get());

		// 原型 bean 每次使用 @Inject 注入的都是不同的实例
		assertNotEquals(jsr330.prototypeBean1, jsr330.prototypeBean2);

		// 原型 bean 的 provider 任何时候 get 回来的都是不同的实例
		assertNotEquals(jsr330.prototypeBeanProvider.get(), jsr330.prototypeBeanProvider.get());
	}

	/**
	 * 注入完成后调用生命周期方法，guice 本身不支持
	 */
	@PostConstruct
	public void afterInjected() {
		System.out.println("TestByGuice: afterInjected");
		assertNotNull(this.singletonBean1);
	}

	/**
	 * 对象销毁前调用的生命周期方法，guice 本身不支持
	 */
	@PreDestroy
	public void beforeDestroy() {
		System.out.println("TestByGuice: beforeDestroy");
		assertNotNull(this.singletonBean1);
	}
}