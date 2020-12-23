package lab6;

import java.util.List;

public class Message {
    private List<String> servers;

    public Message(List<String> servers) {
        this.servers = servers;
    }

    public List<String> getServers() {
        return (this.servers);
    }
}
