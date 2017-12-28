package com.rongjih.beginner.java.webflux;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.ipc.netty.http.server.HttpServer;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

public class App {
  private static final Logger logger = LoggerFactory.getLogger(App.class);

  public static void main(String[] args) throws InterruptedException {
    logger.info("Starting...");
    HandlerFunction<ServerResponse> hello = request -> ServerResponse.ok().body(Mono.just("hello"), String.class);
    RouterFunction router = route(GET("/"), hello);
    HttpHandler handler = RouterFunctions.toHttpHandler(router);

    String host = "localhost";
    int port = 8081;
    HttpServer.create(host, port).newHandler(new ReactorHttpHandlerAdapter(handler)).block();

    logger.warn("Started {}:{}", host, port);
    Thread.currentThread().join();
  }
}