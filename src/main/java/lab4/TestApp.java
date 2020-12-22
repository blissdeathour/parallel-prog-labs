package lab4;

import akka.actor.Actor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.http.javadsl.Http;
import akka.http.javadsl.server.Route;
import akka.stream.ActorMaterializer;

import static akka.http.javadsl.server.Directives.*;


public class TestApp {
    ActorSystem actorSystem;
    ActorRef routeActor;

    public static void main(String[] args) throws Exception {
        ActorSystem system = ActorSystem.create("TestApp");

        Http http = Http.get(system);
        ActorMaterializer materializer = ActorMaterializer.create(system);

    }

    public Route getRoute() {
        path("execute", () -> route(post(() ->
                en)))
    }
}
