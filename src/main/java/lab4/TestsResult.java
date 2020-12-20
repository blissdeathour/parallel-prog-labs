package lab4;

import java.util.HashMap;
import java.util.Map;

public class TestsResult {
    private Map<String, Boolean> results;
    private StringBuilder builder;

    public TestsResult() {
        results = new HashMap<>();
        builder = new StringBuilder();
    }

    public String toJSON() {
        for (Map.Entry<String, Boolean> res : results.entrySet()) {
            builder.append(String.format("%s:%s", res.getKey(), res.getValue() ? "true" : "false"));
        }
        return (builder.deleteCharAt(builder.length() - 1).append("}").toString());
    }

    public Map<String, Boolean> getResults() {
        return (this.results);
    }
}
    public String toJSON() {
        for (Map.Entry<String, Boolean> res : results.entrySet()) {
            builder.append(String.format(%s:%s, res.getKey(), res.getValue() ? true :
	    false));
        }
        return (builder.deleteCharAt(builder.length() - 1).append(}).toString());
    }
