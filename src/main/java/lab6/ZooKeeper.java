package lab6;

import akka.actor.ActorRef;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;

public class ZooKeeper implements Watcher {
    private ActorRef storage;
    private ZooKeeper zoo;

    public ZooKeeper(ActorRef storage) throws KeeperException, InterruptedException {
        this.storage = storage;
        zoo = new ZooKeeper()
    }
}
