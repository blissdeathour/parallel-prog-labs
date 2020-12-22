package lab4;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;
import akka.actor.ActorRef;
import akka.routing.BalancingPool;
import org.junit.Before;

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
                .
    }
}

