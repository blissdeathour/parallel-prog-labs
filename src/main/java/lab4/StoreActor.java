package lab4;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import java.util.HashMap;
import java.util.Map;

public class StoreActor extends AbstractActor {
    private static final Map<String, TestsResult> testsResults = new HashMap<>();

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create(). match(StoreMsg.class, msg -> {
            System.out.printf("StoreActor: %s\n", msg.toString());
            testsResults.put(msg.getPackageId(), msg.getRes());
        })
                .match()
    }
}
