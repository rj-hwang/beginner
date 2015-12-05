package com.beginner.jsr330;

import javax.inject.Named;

/**
 * 注意如下区别:
 * 1) jsr330 标准为 prototype,
 * 2) 使用 spring 实现时, 如果没有配置 scopeResolver 为 Jsr330ScopeMetadataResolver, 默认为 singleton
 * http://rongjih.blog.163.com/blog/static/33574461201561431445978
 * 3）使用 guice 实现时, 完全符合 jsr330 标准
 */
@Named
public class PrototypeBean {
	public String hello() {
		return "prototype";
	}
}