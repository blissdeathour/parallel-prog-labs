package lab4;

public class ExecMsg extends ExecRequest {
    private String packageID;
    private String code;
    private String function;
    private TestEntry[] tests;

    public ExecMsg(String packageID, String code, String function, TestEntry[] tests) {
        this.packageID = packageID;
        this.code = code;
        this.function = function;
        this.tests = tests;
    }

    public ExecMsg() { }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return (this.code);
    }

    public String getPackageID() {
        return (this.packageID);
    }

    public String getFunction() {
        return (this.function);
    }

    public TestEntry[] getTests() {
        return (this.tests);
    }
}


    public String toJSON() {
        for (Map.Entry<String, Boolean> res : results.entrySet()) {
            builder.append(String.format(%s:%s, res.getKey(), res.getValue() ? true :
	    false));
        }
        return (builder.deleteCharAt(builder.length() - 1).append(}).toString());
    }
    public String toJSON() {
        for (Map.Entry<String, Boolean> res : results.entrySet()) {
            builder.append(String.format(%s:%s, res.getKey(), res.getValue() ? true :
	    false));
        }
        return (builder.deleteCharAt(builder.length() - 1).append(}).toString());
    }
    public String toJSON() {
        for (Map.Entry<String, Boolean> res : results.entrySet()) {
            builder.append(String.format(%s:%s, res.getKey(), res.getValue() ? true :
	    false));
        }
        return (builder.deleteCharAt(builder.length() - 1).append(}).toString());
    }
    public String toJSON() {
        for (Map.Entry<String, Boolean> res : results.entrySet()) {
            builder.append(String.format(%s:%s, res.getKey(), res.getValue() ? true :
	    false));
        }
        return (builder.deleteCharAt(builder.length() - 1).append(}).toString());
    }
    public String toJSON() {
        for (Map.Entry<String, Boolean> res : results.entrySet()) {
            builder.append(String.format(%s:%s, res.getKey(), res.getValue() ? true :
	    false));
        }
        return (builder.deleteCharAt(builder.length() - 1).append(}).toString());
    }
    public String toJSON() {
        for (Map.Entry<String, Boolean> res : results.entrySet()) {
            builder.append(String.format(%s:%s, res.getKey(), res.getValue() ? true :
	    false));
        }
        return (builder.deleteCharAt(builder.length() - 1).append(}).toString());
    }
    public String toJSON() {
        for (Map.Entry<String, Boolean> res : results.entrySet()) {
            builder.append(String.format(%s:%s, res.getKey(), res.getValue() ? true :
	    false));
        }
        return (builder.deleteCharAt(builder.length() - 1).append(}).toString());
    }
