package lab4;

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

         public Object[] getParams() {
            return (this.params);
         }

         public void setParams(Object[] params) {
            this.params = params;
         }

         @Override
        public String toString() {
            return testName;
         }
    }
    private String packageID;
    private String jsScript;
    private String funcName;

    public execRequest(String packageID, String jsScript, String funcName) {
        this.packageID = packageID;
        this.jsScript = jsScript;
        this.funcName = funcName;
    }

    public String getPackageID() {
        return (this.packageID);
    }

    public void setPackageID(String packageID) {
        this.packageID = packageID;
    }

    
}
