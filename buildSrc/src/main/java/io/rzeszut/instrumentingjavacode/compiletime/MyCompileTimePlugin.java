package io.rzeszut.instrumentingjavacode.compiletime;

import net.bytebuddy.asm.Advice;
import net.bytebuddy.build.Plugin;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.matcher.ElementMatcher;

import java.io.IOException;

import static net.bytebuddy.matcher.ElementMatchers.named;

public final class MyCompileTimePlugin implements Plugin {

  private final ElementMatcher<TypeDescription> matcher = named("io.rzeszut.example.App");

  @Override
  public boolean matches(TypeDescription typeDescription) {
    return matcher.matches(typeDescription);
  }

  @Override
  public DynamicType.Builder<?> apply(DynamicType.Builder<?> builder,
                                      TypeDescription typeDescription,
                                      ClassFileLocator classFileLocator) {
    return builder.visit(
        Advice.to(TimedAdvice.class).on(named("getGreeting")));
  }

  @Override
  public void close() {
  }
}