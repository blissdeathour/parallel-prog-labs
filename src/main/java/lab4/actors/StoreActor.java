package lab4.actors;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import lab4.msgs.GetMsg;
import lab4.msgs.StoreMsg;
import lab4.msgs.TestsResult;

import java.util.HashMap;
import java.util.Map;

public class StoreActor extends AbstractActor {
    private static final Map<String, TestsResult> testsResults = new HashMap<>();

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create().match(StoreMsg.class, msg -> {
                    System.out.printf("StoreActor toStore: %s\n", msg.printMsg());
                    testsResults.put(msg.getPackageId(), msg.getRes());
                })
                .match(GetMsg.class, req -> {
                    System.out.printf("StoreActor toGet: %s\n", req.getPackageId());
                    sender().tell(new StoreMsg(req.getPackageId(), testsResults.get(req.getPackageId()), false), self());
                })
                .build();
    }
}
