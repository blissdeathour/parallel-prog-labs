package lab5.actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.japi.pf.ReceiveBuilder;
import lab5.msgs.GetMsg;

import java.util.HashMap;
import java.util.Map;

public class RouteActor extends AbstractActor {
    private Map<String, Integer> cache = new HashMap<>();

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(GetMsg.class, msg -> {
                    getSender().tell(cache.getOrDefault(msg.getUrl(), -1)), ActorRef.noSender();
                })
    }
}
