package com.beginner.jsr330;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Jsr330ScopeMetadataResolver;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

import static org.junit.Assert.*;

/**
 * ComponentScan 必须配置 scopeResolver 为 Jsr330ScopeMetadataResolver 才能符合 jsr330 标准
 * http://rongjih.blog.163.com/blog/static/33574461201561431445978
 */
@Configuration
@ComponentScan(basePackages = "com.beginner.jsr330", scopeResolver = Jsr330ScopeMetadataResolver.class)
public class TestBySpring {
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
	@Inject
	private SpringBean springBean1;
	@Inject
	private SpringBean springBean2;
	private AnnotationConfigApplicationContext ctx;

	@Before
	public void setUp() throws Exception {
		ctx = new AnnotationConfigApplicationContext(TestBySpring.class);
	}

	@After
	public void tearDown() throws Exception {
		// 注册一个关闭挂钩 registerShutdownHook(), 确保 spring 正常关闭，并调用相关的 destroy 方法
		// 否则 @PreDestroy 注解的方法不会正常调用
		ctx.registerShutdownHook();
	}

	@Test
	public void di() throws Exception {
		TestBySpring jsr330 = ctx.getBean(TestBySpring.class);

		// 单例 bean 任何时候使用 @Inject 注入的都是同一个实例
		assertEquals(jsr330.singletonBean1, jsr330.singletonBean2);

		// 单例 bean 的 provider 任何时候 get 回来的都是同一个实例
		assertEquals(jsr330.singletonBeanProvider.get(), jsr330.singletonBeanProvider.get());

		// 原型 bean 每次使用 @Inject 注入的都是不同的实例
		assertNotEquals(jsr330.prototypeBean1, jsr330.prototypeBean2);

		// 原型 bean 的 provider 任何时候 get 回来的都是不同的实例
		assertNotEquals(jsr330.prototypeBeanProvider.get(), jsr330.prototypeBeanProvider.get());

		// 使用 Jsr330ScopeMetadataResolver 后, spring 自身的默认行为也改为与 jsr330 保持一致
		assertNotEquals(jsr330.springBean1, jsr330.springBean2);
	}

	/**
	 * 注入完成后调用生命周期方法
	 * 相当于实现 Spring 的 InitializingBean 接口方法 和DisposableBean 接口
	 */
	@PostConstruct
	public void afterInjected() {
		System.out.println("TestBySpring: afterInjected");
		assertNotNull(this.singletonBean1);
	}

	/**
	 * 对象销毁前调用的生命周期方法
	 * 相当于实现 Spring 的 DisposableBean 接口方法
	 */
	@PreDestroy
	public void beforeDestroy() {
		System.out.println("TestBySpring: beforeDestroy");
		assertNotNull(this.singletonBean1);
	}
}