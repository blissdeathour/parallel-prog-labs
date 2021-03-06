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
                .match(Message.class, msg -> servers = msg.getServers()))
                .match(ServerClass.class, msg -> {
                    Random random = new Random();
                    getSender().tell(servers.get(random.nextInt()), ActorRef.noSender());
                })
                .build();
    }
}
