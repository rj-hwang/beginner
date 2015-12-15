package com.beginner.microserver;

import com.sun.net.httpserver.HttpServer;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

/**
 * Main class.
 */
public class JdkServer {
	// Base URI the jdk server will listen on
	public static final String BASE_URI = "http://localhost:9000/rest/";

	/**
	 * Starts server exposing JAX-RS resources defined in this application.
	 *
	 * @return server.
	 */
	public static HttpServer startServer() {
		// create a resource config that scans for JAX-RS resources and providers
		// in com.example package
		final ResourceConfig rc = new ResourceConfig().packages("com.beginner");

		// create and start a new instance of jetty server
		// exposing the Jersey application at BASE_URI
		return JdkHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
	}

	/**
	 * Main method.
	 *
	 * @throws IOException
	 */
	public static void main(String[] args) throws Exception {
		final HttpServer server = startServer();
		System.out.println(String.format("Jersey app started, available at "
				+ "%s. Hit enter to stop it...", BASE_URI));
		System.in.read();
		server.stop(0);
	}
}

