package com.beginner.servlet2;

import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.io.PrintWriter;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class TestHelloServletByMockito {
	HttpServletRequest request;
	HttpServletResponse response;

	@Before
	public void setUp() {
		request = mock(HttpServletRequest.class);
		response = mock(HttpServletResponse.class);
	}

	@Test
	public void doGet() throws Exception {
		when(request.getParameter("name")).thenReturn("hello doGet");
		OutputStream os = new ByteArrayOutputStream();
		PrintWriter writer = new PrintWriter(os);
		when(response.getWriter()).thenReturn(writer);

		new HelloServlet().doGet(request, response);

		verify(request, atLeast(1)).getParameter("name"); // only if you want to verify username was called...
		writer.flush(); // it may not have been flushed yet...

		assertTrue(os.toString().contains("name=hello doGet"));
	}

	@Test
	public void doPost() throws Exception {
		when(request.getParameter("name")).thenReturn("hello doPost");
		OutputStream os = new ByteArrayOutputStream();
		PrintWriter writer = new PrintWriter(os);
		when(response.getWriter()).thenReturn(writer);

		new HelloServlet().doPost(request, response);

		verify(request, atLeast(1)).getParameter("name");
		writer.flush();

		assertTrue(os.toString().contains("name=hello doPost"));
	}
}