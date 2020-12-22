package lab4;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import java.util.HashMap;
import java.util.Map;

public class StoreActor extends AbstractActor {
    private static final Map<String, TestsResult> testsResults = new HashMap<>();

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create(). match(())
    }
}
