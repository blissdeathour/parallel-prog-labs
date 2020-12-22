package lab4;

import akka.actor.Actor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.http.javadsl.Http;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.model.StatusCodes;
import akka.http.javadsl.server.Route;
import akka.pattern.Patterns;
import akka.stream.ActorMaterializer;
import akka.util.Timeout;
import lab4.msgs.ExecMsg;
import lab4.msgs.ExecRequest;
import lab4.msgs.GetMsg;
import lab4.msgs.StoreMsg;
import scala.concurrent.Await;
import scala.concurrent.Future;


import java.time.Duration;
import java.util.concurrent.TimeoutException;

import static akka.http.javadsl.server.Directives.*;


public class TestApp {
    ActorSystem actorSystem;
    ActorRef routeActor;
    private static final Timeout timeout = Timeout.create(Duration.ofSeconds(5));

    public static void main(String[] args) throws Exception {
        ActorSystem system = ActorSystem.create("TestApp");

        Http http = Http.get(system);
        ActorMaterializer materializer = ActorMaterializer.create(system);

    }

    public Route getRoute() {
        path("execute", () -> route(post(() ->
                entity(Jackson.unmarshaller(ExecRequest.class), b -> {
                    routeActor.tell(new ExecMsg(b.getPackageID(), b.getJsScript(), b.getFuncName(), b.getTests()),
                            ActorRef.noSender());
                    return (complete(StatusCodes.OK, String.format("Testing for %s started\n", b.getPackageID())));
                })))),
        path("retreive", () -> route(get(() ->
                parameter("packageID", pID -> {
                    Future<Object> future = Patterns.ask(routeActor, new GetMsg(pID), timeout);
                    StoreMsg res;
                    try {
                        res = (StoreMsg) Await.result(future, timeout.duration());
                    } catch (TimeoutException | InterruptedException e) {
                        e.printStackTrace();
                    }
                    
                }))))
    }
}
