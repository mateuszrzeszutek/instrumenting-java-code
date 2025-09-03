package io.rzeszut.instrumentingjavacode.compiletime;

import net.bytebuddy.asm.Advice;

@SuppressWarnings("unused")
public class TimedAdvice {

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
