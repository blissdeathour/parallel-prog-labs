package lab4;

public class execMsg extends execRequest {
    private String packageID;
    private String code;
    private String function;
    private TestEntry[] tests;

    public execMsg(String packageID, String code, String function, TestEntry[] tests) {
        this.packageID = packageID;
        this.code = code;
        this.function = function;
        this.tests = tests;
    }

    public execMsg() { }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return (this.code);
    }

    public String getPackageID() {
        return (this.packageID);
    }

    public TestEntry[] getTests() {
        return (this.tests);
    }
}
