package lab4;

import akka.actor.AbstractActor;
import lab4.TestsResult;
import lab4.execMsg;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class ImplementActor extends AbstractActor {
    private final static String ENGINE_NAME = "nashorn";

    private static TestsResult execTests(execMsg msg) throws Exception {

    }
}
