package org.jacoco.agent.rt.internal_8cf7cdb;

import java.lang.instrument.Instrumentation;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import org.jacoco.agent.rt.internal_8cf7cdb.core.runtime.AgentOptions;
import org.jacoco.agent.rt.internal_8cf7cdb.core.runtime.IRuntime;
import org.jacoco.agent.rt.internal_8cf7cdb.core.runtime.InjectedClassRuntime;
import org.jacoco.agent.rt.internal_8cf7cdb.core.runtime.ModifiedSystemClassRuntime;
import org.jacoco.agent.rt.internal_8cf7cdb.local.LocalApiServer;

public final class PreMain {

    public static void premain(String options, Instrumentation inst) throws Exception {
        if (options.contains("-Deureka.instance.metadata-map.coverageGitUrl")) {
            String value = options.split("-Deureka\\.instance\\.metadata-map\\.coverageGitUrl")[1].split(",")[0].trim().replace("=", "");
            System.setProperty("eureka.instance.metadata-map.coverageGitUrl", value);
        }
        if (options.contains("-Deureka.instance.metadata-map.coverageGitBranch")) {
            String value = options.split("-Deureka\\.instance\\.metadata-map\\.coverageGitBranch")[1].split(",")[0].trim().replace("=", "");
            System.setProperty("eureka.instance.metadata-map.coverageGitBranch", value);
        }
        if (options.contains("-Deureka.instance.metadata-map.coverageGitCommitId")) {
            String value = options.split("-Deureka\\.instance\\.metadata-map\\.coverageGitCommitId")[1].trim().replace("=", "");
            System.setProperty("eureka.instance.metadata-map.coverageGitCommitId", value);
        }
        if (options.contains(",-Deureka")) {
            options = options.split(",-Deureka")[0];
        }
        AgentOptions agentOptions = new AgentOptions(options);
        agentOptions.setPort(FileHttpServer.tryPort(agentOptions.getPort()));
        Agent agent = Agent.getInstance(agentOptions);
        IRuntime runtime = createRuntime(inst);
        runtime.startup(agent.getData());
        startServer(agentOptions);
        inst.addTransformer(new CoverageTransformer(runtime, agentOptions, IExceptionLogger.SYSTEM_ERR));
    }

    private static void startServer(AgentOptions agentOptions) {
        String localEnabled = System.getProperty("coverage.local.enabled");
        boolean isLocal = "true".equalsIgnoreCase(localEnabled);
        FileHttpServer fileHttpServer = FileHttpServer.start(isLocal);
        System.setProperty("eureka.instance.metadata-map.coveragePort", agentOptions.getPort() + "");
        System.setProperty("eureka.instance.metadata-map.coverageFileHttpServerPort", fileHttpServer.getPort() + "");
        if (isLocal) {
            LocalApiServer localApiServer = LocalApiServer.start(fileHttpServer.getPort(), agentOptions.getPort());
            if (localApiServer != null) {
                System.setProperty("eureka.instance.metadata-map.coverageLocalApiServerPort", localApiServer.getPort() + "");
            }
        }
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
