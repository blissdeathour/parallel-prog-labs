package lab4;

import akka.actor.AbstractActor;
import lab4.TestsResult;
import lab4.execMsg;
import lab4.execRequest;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class ImplementActor extends AbstractActor {
    private final static String ENGINE_NAME = "nashorn";

    private void show_msg(String msg, String packageID, String testName) {
        System.out.printf(msg + packageID + '-' + testName);
    }

    private TestsResult execTests(execMsg msg) throws Exception {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName(ENGINE_NAME);
        engine.eval(msg.getCode());

        Invocable invocable = (Invocable) engine;
        TestsResult testsResult = new TestsResult();

        for (execRequest.TestEntry test : msg.getTests()) {
            String result = invocable.invokeFunction(msg.getFunction(), test.getParams()).toString();
            if (result.equals(test.getExpectedResult())) {
                show_msg("Test passed: ", msg.getPackageID(), test.getTestName());
                testsResult.getResults().put(test.getTestName(), "")
            }
        }
    }
}
