package com.beginner.jsr339;

import com.beginner.microserver.JettyServer;
import org.eclipse.jetty.server.Server;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestItByJetty {
	private Server server;
	private WebTarget target;

	@Before
	public void setUp() throws Exception {
		server = JettyServer.startServer();

		Client c = ClientBuilder.newClient();
		target = c.target(JettyServer.BASE_URI);
	}

	@After
	public void tearDown() throws Exception {
		server.stop();
	}

	@Test
	public void _404() {
		Response r = target.path("404").request().get();
		assertNotNull(r);
		assertEquals(Response.Status.NOT_FOUND.getStatusCode(), r.getStatus());
	}

	@Test
	public void hello() {
		String responseMsg = target.path("hello").request().get(String.class);
		assertEquals("Hello!", responseMsg);
	}

	@Test
	public void helloDragon() {
		String responseMsg = target.path("hello/dragon").request().get(String.class);
		assertEquals("Hello dragon!", responseMsg);
	}
}