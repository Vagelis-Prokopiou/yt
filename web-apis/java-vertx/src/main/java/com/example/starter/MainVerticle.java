// mvn package && mvn exec:java
// mvn clean package && java -jar target/starter-1.0.0-SNAPSHOT-fat.jar

package com.example.starter;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.Json;

import java.util.ArrayList;

public class MainVerticle extends AbstractVerticle {
    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        vertx.createHttpServer().requestHandler(req -> {
            var users = new ArrayList(1000);
            for (int i = 1; i < 1001; i++) {
                var stringIndex = Integer.toString(i);
                users.add(new User(
                        i,
                        "FirstName" + stringIndex,
                        "LastName" + stringIndex,
                        25,
                        "Java Vert.x")
                );
            }

            req.response()
                    .putHeader("content-type", "application/json; charset=utf-8")
                    .end(
                            // Json.encode(users)
                            Json.encodeToBuffer(users)
                    );
        }).listen(8888, http -> {
            if (http.succeeded()) {
                startPromise.complete();
                System.out.println("HTTP server started on port 8888");
            } else {
                startPromise.fail(http.cause());
            }
        });
    }
    
    public static void main(String[] args){
        Vertx vertx = Vertx.vertx();
        DeploymentOptions options = new DeploymentOptions().setInstances(Runtime.getRuntime().availableProcessors());
        vertx.deployVerticle(MainVerticle.class.getName(), options);
    }
}
