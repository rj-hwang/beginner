package com.beginner;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AppTest {
	@Test
	public void testPlus() {
		assertEquals(2, App.plus(1, 1));
	}
}