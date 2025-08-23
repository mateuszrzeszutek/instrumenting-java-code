package io.rzeszut.instrumentingjavacode.bytebuddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.Test;

class Ex4_AdviceDecoration {

  public static class SomeClass {
    public void saySomething() {
      System.out.println("Something");
    }
  }

  @Test
  void decorates_with_advice() throws Exception {
    try (var unloadedClass = new ByteBuddy()
        .subclass(SomeClass.class)
        .method(ElementMatchers.named("saySomething"))
        .intercept(Advice.to(TimedAdvice.class))
        .make()) {

      Class<? extends SomeClass> klass = unloadedClass.load(getClass().getClassLoader()).getLoaded();

      SomeClass instance = klass.getDeclaredConstructor().newInstance();

      instance.saySomething();
    }
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
