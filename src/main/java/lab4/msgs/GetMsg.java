package lab4.msgs;

public class GetMsg {
    private String packageId;

    public GetMsg(String packageId) {
        this.packageId = packageId;
    }

    public String getPackageId() {
        return (this.packageId);
    }
}
