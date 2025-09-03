package io.rzeszut.instrumentingjavacode.javaagent;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;

import java.io.IOException;
import java.lang.instrument.Instrumentation;

import static net.bytebuddy.matcher.ElementMatchers.named;

public final class MyJavaagent {

  public static void premain(String agentArgs, Instrumentation inst) throws IOException {
    System.out.println("before main()");

    new AgentBuilder.Default()
        .type(named("io.rzeszut.example.App"))
        .transform(new AgentBuilder.Transformer.ForAdvice()
            .advice(named("getGreeting"),
                MyJavaagent.class.getName() + "$TimedAdvice"))
        .installOn(inst);
  }

  @SuppressWarnings("unused")
  public static class TimedAdvice {
    @Advice.OnMethodEnter
    public static long onEnter() {
      return System.nanoTime();
    }

    @Advice.OnMethodExit(suppress = Throwable.class)
    public static void onExit(@Advice.Enter long startNanos) {
      var endNanos = System.nanoTime();
      System.out.println("Time: " + (endNanos - startNanos) + "ns");
    }
  }
}
