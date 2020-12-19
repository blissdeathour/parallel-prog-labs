package lab4;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import junit.framework.TestResult;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class ImplementActor extends AbstractActor {
    private final static String ENGINE_NAME = "nashorn";

    private void show_msg(String msg, String packageID, String testName) {
        System.out.printf(msg + packageID + '-' + testName);
    }

    private TestsResult execTests(ExecMsg msg) throws Exception {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName(ENGINE_NAME);
        engine.eval(msg.getCode());

        Invocable invocable = (Invocable) engine;
        TestsResult testsResult = new TestsResult();

        for (ExecRequest.TestEntry test : msg.getTests()) {
            String result = invocable.invokeFunction(msg.getFunction(), test.getParams()).toString();
            if (result.equals(test.getExpectedResult())) {
                show_msg("Test passed: ", msg.getPackageID(), test.getTestName());
                testsResult.getResults().put(test.getTestName(), true);
            }
            else {
                show_msg("Test failed: ", msg.getPackageID(), test.getTestName());
                testsResult.getResults().put(test.getTestName(), false);
            }
        }
        return testsResult;
    }

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(ExecMsg.class, msg -> {
                    System.out.printf("Execute: %s\n", msg.toString());
                    TestsResult result = execTests(msg);
                    sender().tell(new Message(msg.getPackageID(), result, true), self());
                })
                .build();
    }
}
