package com.beginner.jsr330;

import javax.inject.Named;
import javax.inject.Singleton;

@Named
@Singleton
public class SingletonBean {
	public String hello() {
		return "singleton";
	}
}