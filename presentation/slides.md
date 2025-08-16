---
title: Instrumenting Java Code
author: Mateusz Rzeszutek
date: 18th September 2025
theme: Copenhagen
colortheme: seahorse
fonttheme: professionalfonts
fontsize: 14pt
---

# Who am I?

::: columns

:::: column
![OTel](presentation/img/opentelemetry-horizontal-color.png)\
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

# Manual instrumentation

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

