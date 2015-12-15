package com.beginner.microserver;

import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

/**
 * Main class.
 */
public class JettyServer {
	// Base URI the server will listen on
	// 实测不支持设置 contextPath
	public static final String BASE_URI = "http://localhost:9000/";

	/**
	 * Starts server exposing JAX-RS resources defined in this application.
	 *
	 * @return server.
	 */
	public static Server startServer() {
		// create a resource config that scans for JAX-RS resources and providers
		// in com.example package
		final ResourceConfig rc = new ResourceConfig().packages("com.beginner");

		// create and start a new instance of jetty server
		// exposing the Jersey application at BASE_URI
		return JettyHttpContainerFactory.createServer(URI.create(BASE_URI), rc);
	}

	/**
	 * Main method.
	 *
	 * @throws IOException
	 */
	public static void main(String[] args) throws Exception {
		final Server server = startServer();
		System.out.println(String.format("Jersey app started, available at "
				+ "%s. Hit enter to stop it...", BASE_URI));
		System.in.read();
		server.stop();
	}
}

