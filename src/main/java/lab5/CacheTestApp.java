package lab5;

import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.Http;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;
import lab5.actors.RouteActor;
import org.spark_project.jetty.client.HttpRequest;
import org.spark_project.jetty.client.HttpResponse;

public class CacheTestApp{


    public static void main(String[] args) throws Exception {
        ActorSystem system = ActorSystem.create("CacheTestApp");
        ActorRef actorRef = system.actorOf(Props.create(RouteActor.class));
        Http http = Http.get(system);
        ActorMaterializer materializer = ActorMaterializer.create(system);
        Flow<HttpRequest, HttpResponse, NotUsed> routeFlow = Flow.of(HttpRequest.class)
    }
}
