package lab5.msgs;

public class StoreMsg {
    private String url;
    private int time;

    public StoreMsg(String url, int time) {
        this.url = url;
        this.time = time;
    }

    public String getUrl() {
        return (this.url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getTime() {
        return (this.time);
    }

    public void setTime(int time) {
        this.time = time;
    }
}
