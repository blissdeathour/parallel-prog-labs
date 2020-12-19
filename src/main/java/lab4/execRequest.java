package lab4;

import org.apache.hadoop.yarn.proto.YarnProtos;

public class execRequest {
    public static class TestEntry {
        private String testName;
        private String expectedResult;
        private Object[] params;

        public TestEntry(String testName, String expectedResult, Object[] params) {
            this.testName = testName;
            this.expectedResult = expectedResult;
            this.params = params;
        }

        public String getTestName() {
            return (this.testName);
        }

        public void setTestName(String testName) {
            this.testName = testName;
        }

        public void setExpectedResult(String expectedResult) {
            this.expectedResult = expectedResult;
        }

        public String getExpectedResult() {
            return (this.expectedResult);
        }

       
    }
    private String packageID;
    private String jsScript;
    private String funcName;
    private TestEntry[] tests;

    public execRequest(String packageID, String jsScript, String funcName, TestEntry[] tests) {
        this.packageID = packageID;
        this.jsScript = jsScript;
        this.funcName = funcName;
        this.tests = tests;
    }

    public String getPackageID() {
        return (this.packageID);
    }

    public void setPackageID(String packageID) {
        this.packageID = packageID;
    }

    public String getJsScript() {
        return (this.jsScript);
    }

    public void setFuncName(String funcName) {
        this.funcName = funcName;
    }

    public String getFuncName() {
        return (this.funcName);
    }

    public TestEntry[] getTests() {
        return (this.tests);
    }

    public void setTests(TestEntry[] tests) {
        this.tests = tests;
    }

    public void setJsScript(String jsScript) {
        this.jsScript = jsScript;
    }

    @Override
    public String toString() {
        return (packageID);
    }
}
