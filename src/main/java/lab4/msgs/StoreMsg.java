package lab4.msgs;

public class StoreMsg {
    private String packageId;
    private TestsResult res;
    private Boolean isReq;

    public StoreMsg(String packageId, TestsResult res, boolean isReq) {
        this.packageId = packageId;
        this.res = res;
        this.isReq = isReq;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public String getPackageId() {
        return (this.packageId);
    }

    public void setRes(TestsResult res) {
        this.res = res;
    }

    public TestsResult getRes() {
        return (this.res);
    }

    public void setIsReq(boolean isReq) {
        this.isReq = isReq;
    }

    public boolean getIsReq() {
        return (this.isReq);
    }

    public String printMsg() {
        return (String.format("%s - %s", packageId, res.toString()));
    }
}
