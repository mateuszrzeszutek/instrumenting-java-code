package io.rzeszut.instrumentingjavacode.bytebuddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.implementation.MethodDelegation;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Ex2_DelegatingCalls {

  @Test
  void delegates_calls() throws Exception {
    try (var unloadedClass = new ByteBuddy()
        .subclass(Object.class)
        .defineMethod("hello", String.class, List.of(Visibility.PUBLIC))
        .withParameters(String.class)
        .intercept(MethodDelegation.to(new Actual("Hello")))
        .make()) {

      var generatedClass = unloadedClass.load(getClass().getClassLoader()).getLoaded();
      var helloMethod = generatedClass.getDeclaredMethod("hello", String.class);

      var instance = generatedClass.getConstructor().newInstance();
      assertEquals("Hello World", helloMethod.invoke(instance, "World"));
    }
  }

  public record Actual(String greeting) {

    public String hello(String who) {
      return greeting + " " + who;
    }
  }
}
