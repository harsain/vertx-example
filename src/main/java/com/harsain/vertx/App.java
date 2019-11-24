package com.harsain.vertx;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class App {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        HttpServer httpServer = vertx.createHttpServer();


        Router router = Router.router(vertx);
        router
            .get("/helloall")
            .handler(App::handle);

        router.get("/hello/:name")
                .handler(App::helloNameHandler);

        httpServer.requestHandler(router).listen(8081);
    }

    private static void handle(RoutingContext routingContext) {
        JsonObject jsonObject = new JsonObject().put("message", "learning Vertx");
        HttpServerResponse response = routingContext.response();
        response.putHeader("Content-Type", "application/json; charset=UTF8");
        response.end(jsonObject.encodePrettily());
    }

    private static void helloNameHandler(RoutingContext routingContext) {
        JsonObject responseJson = new JsonObject().put("message", "Hello! " + routingContext.request().getParam("name"));
        routingContext.response().putHeader("Content-Type", "application/json; charset=UTF8").end(responseJson.encodePrettily());
    }
}
