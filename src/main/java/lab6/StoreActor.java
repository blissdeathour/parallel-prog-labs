package lab6;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.japi.pf.ReceiveBuilder;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class StoreActor {
    private List<String> servers = Collections.emptyList();
    private Random random = new Random();

    @Override
    public AbstractActor.Receive createReceive() {
        return (ReceiveBuilder.create()
                .match(ServersList.class, msg -> servers = msg.getServers()))
                .match(ServerClass.class, msg -> sender().tell(servers.get(random.nextInt())))
                .build();
    }

    private ActorRef sender() {
    }
}
