package lab4;

import akka.NotUsed;
import akka.actor.Actor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.model.StatusCodes;
import akka.http.javadsl.server.Route;
import akka.pattern.Patterns;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;
import akka.util.Timeout;
import lab4.actors.RouterActor;
import lab4.msgs.ExecMsg;
import lab4.msgs.ExecRequest;
import lab4.msgs.GetMsg;
import lab4.msgs.StoreMsg;
import scala.concurrent.Await;
import scala.concurrent.Future;
import java.util.concurrent.CompletionStage;

import java.time.Duration;
import java.util.concurrent.TimeoutException;

import static akka.http.javadsl.server.Directives.*;


public class TestApp {
    static ActorSystem actorSystem;
    static ActorRef routeActor;
    private static final Timeout timeout = Timeout.create(Duration.ofSeconds(5));
    private final static String IP = "localhost";
    private final static int PORT = 8080;

    public static void main(String[] args) throws Exception {
        ActorSystem system = ActorSystem.create("TestApp");

        Http http = Http.get(system);
        ActorMaterializer materializer = ActorMaterializer.create(system);
        actorSystem = system;
        routeActor = actorSystem.actorOf(Props.create(RouterActor.class));
        Flow<HttpRequest, HttpResponse, NotUsed> routeFlow = route(
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
                                return (complete(StatusCodes.INTERNAL_SERVER_ERROR, "Error\n"));
                            }
                            if (res != null && res.getRes() != null) {
                                return (complete(StatusCodes.OK, res.getRes().toJSON() + "\n"));
                            }
                            else {
                                return (complete(StatusCodes.OK, res.getRes().toJSON() + "\n"));
                            }
                        })))), complete(StatusCodes.NOT_FOUND, "Bad request\n")).flow(system, materializer);
        CompletionStage<ServerBinding> binding = http.bindAndHandle(
                routeFlow, ConnectHttp.toHost(IP, PORT), materializer
        );

        System.out.printf("Server listening...");
        System.in.read();
        binding.thenCompose(ServerBinding::unbind).thenAccept(unbound -> system.terminate());
    }
}
