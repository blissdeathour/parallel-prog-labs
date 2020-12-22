package lab4.actors;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;
import akka.actor.ActorRef;
import akka.pattern.Patterns;
import akka.routing.BalancingPool;
import akka.util.Timeout;
import lab4.msgs.ExecMsg;
import lab4.msgs.GetMsg;
import org.junit.Before;
import scala.concurrent.Await;
import java.time.Duration;
import scala.concurrent.Future;

public class RouterActor extends AbstractActor {
    private ActorRef execActor;
    private ActorRef storeActor;

    @Before
    public void init() {
        storeActor = getContext().actorOf(Props.create(StoreActor.class));
        execActor = getContext().actorOf(
                new BalancingPool(3).props(Props.create(ImplementActor.class))
        );
    }

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(ExecMsg.class, msg -> execActor.tell(msg, self()))
                .match(StoreActor.class, msg -> storeActor.tell(msg, self()))
                .match(GetMsg.class, msg -> {
                    Future<Object> future = Patterns.ask(storeActor, msg, Timeout.create(Duration.ofSeconds(6)));
                    sender().tell(Await.result(future, Timeout.create(Duration.ofSeconds(6)).duration()), self());
                })
                .build();
    }
}

