package com.beginner.microserver;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.simple.SimpleContainerFactory;
import org.glassfish.jersey.simple.SimpleServer;

import java.io.IOException;
import java.net.URI;

/**
 * Main class.
 */
public class JerseySimpleServer {
    // Base URI the server will listen on
    // 实测不支持设置 contextPath
    public static final String BASE_URI = "http://localhost:9000/";

    /**
     * Starts server exposing JAX-RS resources defined in this application.
     *
     * @return server.
     */
    public static SimpleServer startServer() {
        // create a resource config that scans for JAX-RS resources and providers
        // in com.example package
        final ResourceConfig rc = new ResourceConfig().packages("com.beginner");

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return SimpleContainerFactory.create(URI.create(BASE_URI), rc);
    }

    /**
     * Main method.
     *
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        final SimpleServer server = startServer();
        System.out.println(String.format("Jersey app started, available at "
                + "%s. Hit enter to stop it...", BASE_URI));
        System.in.read();
        server.close();
    }
}