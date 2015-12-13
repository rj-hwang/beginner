package com.beginner.jsr339;

import com.beginner.microserver.JdkServer;
import com.sun.net.httpserver.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestUserResource {
    private HttpServer server;
    private WebTarget target;

    @Before
    public void setUp() throws Exception {
        server = JdkServer.startServer();

        Client c = ClientBuilder.newClient();
        target = c.target(JdkServer.BASE_URI);
    }

    @After
    public void tearDown() throws Exception {
        server.stop(0);
    }

    @Test
    public void _404() {
        Response r = target.path("404").request().get();
        assertNotNull(r);
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), r.getStatus());
    }

    @Test
    public void getById() {
        Response r = target.path("users/1").request(MediaType.APPLICATION_JSON).get();
        assertNotNull(r);
        assertEquals(Response.Status.OK.getStatusCode(), r.getStatus());

        String user = r.readEntity(String.class);
        assertNotNull(user);
        System.out.println(user);
    }
}