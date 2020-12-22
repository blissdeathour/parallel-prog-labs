package lab4;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import akka.actor.ActorRef;
import org.junit.Before;

public class RouterActor extends AbstractActor {
    private ActorRef execActor;
    private ActorRef storeActor;

    @Before
    public void init() {

    }

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create().
                match(ExecMsg.class, msg -> execActor)
    }
}

