package org.jacoco.agent.rt.internal_8cf7cdb;

import java.lang.instrument.Instrumentation;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import org.jacoco.agent.rt.internal_8cf7cdb.core.runtime.AgentOptions;
import org.jacoco.agent.rt.internal_8cf7cdb.core.runtime.IRuntime;
import org.jacoco.agent.rt.internal_8cf7cdb.core.runtime.InjectedClassRuntime;
import org.jacoco.agent.rt.internal_8cf7cdb.core.runtime.ModifiedSystemClassRuntime;

public final class PreMain {
    public static void premain(String options, Instrumentation inst) throws Exception {
        AgentOptions agentOptions = new AgentOptions(options);
        Agent agent = Agent.getInstance(agentOptions);
        IRuntime runtime = createRuntime(inst);
        runtime.startup(agent.getData());
        FileHttpServer.start();
        inst.addTransformer(new CoverageTransformer(runtime, agentOptions, IExceptionLogger.SYSTEM_ERR));
    }

    private static IRuntime createRuntime(Instrumentation inst) throws Exception {
        if (redefineJavaBaseModule(inst))
            return (IRuntime)new InjectedClassRuntime(Object.class, "$JaCoCo");
        return ModifiedSystemClassRuntime.createFor(inst, "java/lang/UnknownError");
    }

    private static boolean redefineJavaBaseModule(Instrumentation instrumentation) throws Exception {
        try {
            Class.forName("java.lang.Module");
        } catch (ClassNotFoundException e) {
            return false;
        }
        Instrumentation.class.getMethod("redefineModule", new Class[] { Class.forName("java.lang.Module"), Set.class, Map.class, Map.class, Set.class, Map.class }).invoke(instrumentation, new Object[] { getModule(Object.class),
                Collections.emptySet(),
                Collections.emptyMap(),
                Collections.singletonMap("java.lang",
                        Collections.singleton(
                                getModule(InjectedClassRuntime.class))),
                Collections.emptySet(),
                Collections.emptyMap() });
        return true;
    }

    private static Object getModule(Class<?> cls) throws Exception {
        return Class.class.getMethod("getModule", new Class[0]).invoke(cls, new Object[0]);
    }
}
