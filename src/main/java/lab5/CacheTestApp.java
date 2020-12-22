package lab5;

import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.model.Query;
import akka.japi.Pair;
import akka.stream.javadsl.Keep;
import akka.stream.javadsl.Sink;
import akka.stream.javadsl.Source;
import akka.pattern.Patterns;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;
import lab5.msgs.GetMsg;
import lab5.msgs.StoreMsg;
import lab5.actors.RouteActor;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import static org.asynchttpclient.Dsl.asyncHttpClient;

public class CacheTestApp{
    private static final String URL_ARG = "testURL";
    private static final String COUNT = "count";
    private static final String HOST = "localhost";
    private static final int PORT = 8080;
    private static final int CONST = 1;
    private static final Duration TIMEOUT = Duration.ofSeconds(5);

    public static void main(String[] args) throws Exception {
        ActorSystem system = ActorSystem.create("CacheTestApp");
        ActorRef actorRef = system.actorOf(Props.create(RouteActor.class));
        Http http = Http.get(system);
        ActorMaterializer materializer = ActorMaterializer.create(system);
        Flow<HttpRequest, HttpResponse, NotUsed> routeFlow = Flow.of(HttpRequest.class)
                .map(req -> {
                    Query q = req.getUri().query();
                    String url = q.get(URL_ARG).get();
                    int count = Integer.parseInt(q.get(COUNT).get());
                    return (new Pair<>(url, count));
                })
                .mapAsync(CONST, r -> {
                    CompletionStage<Object> stage = Patterns.ask(actorRef, new GetMsg(r.first()), TIMEOUT);
                    return (stage.thenCompose(res -> {
                        if ((int)res != -1) {
                            return (CompletableFuture.completedFuture(new Pair<>(r.first(), ((int)res))));
                        }

                        Flow<Pair<String, Integer>, Integer, NotUsed> reqFlow = Flow.<Pair<String, Integer>>create()
                                .mapConcat(p -> new ArrayList<>(Collections.nCopies(p.second(), p.first())))
                                .mapAsync(r.second(), url -> {
                                    long start = System.currentTimeMillis();
                                    asyncHttpClient().prepareGet(url).execute();
                                    long finish = System.currentTimeMillis();
                                    int time = (int)(finish - start);
                                    return CompletableFuture.completedFuture(time);
                                });
                        return (Source.single(r).via(reqFlow).toMat(Sink.fold(0, Integer::sum), Keep.right()))
                                .run(materializer).thenApply(sum -> new Pair<>(r.first(), (sum / r.second())));
                    }));
                })
                .map(r -> {
                    actorRef.tell(new StoreMsg(r.first(), r.second()), ActorRef.noSender());
                    return (HttpResponse.create().withEntity(r.second().toString() + '\n'));
                });

        CompletionStage<ServerBinding> binding = http.bindAndHandle(routeFlow,
                ConnectHttp.toHost(HOST, PORT), materializer);

        System.out.printf("Server listening...");
        System.in.read();
        binding.thenCompose(ServerBinding::unbind).thenAccept(unbound -> system.terminate());
    }
}
