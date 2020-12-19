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

    private static TestsResult execTests(execMsg msg) throws Exception {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName(ENGINE_NAME);
        engine.eval(msg.getCode());

        Invocable invocable = (Invocable) engine;
        TestsResult testsResult = new TestsResult();

        for (execRequest.TestEntry : msg.tes)
    }
}
