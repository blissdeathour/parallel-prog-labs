package lab4.msgs;

public class Message {
    private String packageID;
    private TestsResult result;
    private Boolean isReq;

    public Message(String packageID, TestsResult result, boolean isReq) {
        this.packageID = packageID;
        this.result = result;
        this.isReq = isReq;
    }

    public void setPackageID(String packageID) {
        this.packageID = packageID;
    }

    public String getPackageID() {
        return (this.packageID);
    }

    public void setResult(TestsResult result) {
        this.result = result;
    }

    public TestsResult getResult() {
        return (this.result);
    }

    public void setIsReq(Boolean isReq) {
        this.isReq = isReq;
    }

    public Boolean getIsReq() {
        return (this.isReq);
    }
}