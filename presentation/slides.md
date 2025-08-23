---
title: Instrumenting Java Code
author: Mateusz Rzeszutek
date: 18th September 2025
theme: Copenhagen
colortheme: seahorse
fonttheme: professionalfonts
fontsize: 12pt
---

# Who am I?

::: columns

:::: column
![OTel](img/opentelemetry-horizontal-color.png)\
::::

:::: column
I’m an emeritus maintainer of the [OpenTelemetry Instrumentation for Java](https://github.com/open-telemetry/opentelemetry-java-instrumentation)
::::

:::

# What is instrumentation?

According to [Wikipedia](https://en.wikipedia.org/wiki/Instrumentation_(computer_programming)):

> In computer programming, instrumentation is the act of modifying software so that analysis can be performed on it.

# What is instrumentation?

Java instrumentation techniques:

- Manual instrumentation
- Auto instrumentation
    - Code generation
    - Javaagents
    - Compile-time instrumentation
- ...

# Manual instrumentation

How to instrument the following?

```java
public interface Supplier<T> {
  T get();
}
```

# Manual instrumentation

Some libraries expose hooks for instrumentation:

- OkHttp has Interceptors and EventListeners
- HikariCP has MetricsTrackerFactory
- ...

Others expose interfaces that can be proxied.

# JDBC?

```java
interface PreparedStatement {
  // ...
  boolean execute();
  boolean execute(String);
  boolean execute(String, int);
  boolean execute(String, int[]);
  boolean execute(String, String[]);
  int[] executeBatch();
  long[] executeLargeBatch();
  long executeLargeUpdate();
  long executeLargeUpdate(String);
  long executeLargeUpdate(String, int);
  long executeLargeUpdate(String, int[]);
  long executeLargeUpdate(String, String[]);
  // ...
}
```

# Code generation: `java.util.Proxy`

```java
PreparedStatement ps = // ...;

var timedStatement = Proxy.newProxyInstance(
    ps.getClass().getClassLoader(),
    new Class[]{ PreparedStatement.class },
    new TimedStatementHandler(ps));
);
```

# Code generation: AOP, Spring, AspectJ

`java.util.Proxy` can generate implementations for interfaces, but not classes or abstract classes.

# Code generation: AOP, Spring, AspectJ

```java
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Timed {
}
```

# Code generation: other utilities

- [java.util.Proxy](https://docs.oracle.com/javase/8/docs/technotes/guides/reflection/proxy.html)
- [AspectJ](https://eclipse.dev/aspectj/)
- [CGLib](https://github.com/cglib/cglib)
- [Javassist](https://www.javassist.org/)
- [ASM](https://asm.ow2.io/)
- [Class-File API](https://openjdk.org/jeps/457)
- [Byte Buddy](https://bytebuddy.net/)
- ...

# Code generation: ASM

```java
var cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
cw.visit(Opcodes.V24, Opcodes.ACC_PUBLIC,
    "org.example.Generated", null, null, null);
var mw = cw.visitMethod(Opcodes.ACC_PUBLIC,
    "toString", "()Ljava/lang/String;", null, null);
mw.visitCode();
mw.visitLdcInsn("Hello World");
mw.visitInsn(Opcodes.ARETURN);
mw.visitEnd();
cw.visitEnd();
var bytes = cw.toByteArray();
```

# Code generation: Class-File

```java
var bytes = ClassFile.of()
    .build(
        ClassDesc.of("org.example.Generated"),
        cb -> cb.withMethodBody(
            "toString",
            MethodTypeDesc.of(
                ClassDesc.of("java.lang.String")),
            ClassFile.ACC_PUBLIC,
            code -> code.ldc("Hello World").areturn()
        ));
```

# Byte Buddy

```java
try (var unloadedClass = new ByteBuddy()
    .subclass(Object.class)
    .name("org.example.Generated")
    .method(ElementMatchers.named("toString"))
    .intercept(FixedValue.value("Hello World"))
    .make()) {

  var bytes = unloadedClass.getBytes();
}
```

# Javaagent

