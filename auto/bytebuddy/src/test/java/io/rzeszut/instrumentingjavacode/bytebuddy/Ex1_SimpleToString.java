package io.rzeszut.instrumentingjavacode.bytebuddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Ex1_SimpleToString {

  @Test
  void hello_world_class() throws Exception {
    try (var unloadedClass = new ByteBuddy()
        .subclass(Object.class)
        .name("org.example.Generated")
        .method(ElementMatchers.named("toString"))
        .intercept(FixedValue.value("Hello World"))
        .make()) {

      var generatedClass = unloadedClass.load(getClass().getClassLoader()).getLoaded();

      assertEquals("Hello World", generatedClass.getConstructor().newInstance().toString());
    }
  }
}
