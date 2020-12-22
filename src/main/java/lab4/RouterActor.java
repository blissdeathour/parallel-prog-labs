package lab4;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import akka.actor.ActorRef;

public class RouterActor extends AbstractActor {
    private ActorRef execActor;
    private ActorRef storeActor;

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create().
                match(ExecMsg.class, msg -> execActor)
    }
}

